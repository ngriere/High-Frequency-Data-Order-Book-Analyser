/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Order;
import Models.AddOrder;
import Models.ExecutedOrder;
import Models.CancelledOrder;
import Models.Ask;
import Models.AssetInfo;
import Models.Bid;
import Models.DecryptingValues;
import static Models.DecryptingValues.DateBegin;
import static Models.DecryptingValues.DateEnd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas_2
 */
public class DataController extends Thread implements DecryptingValues {

    private static ArrayList<AssetInfo> existingAddOrders = new ArrayList<AssetInfo>();
    private static double valuePrice;
    private ArrayList<Listener> listeners = new ArrayList<Listener>();
    public ClockController horloge;
    private File file;

    public DataController(File file, ClockController horloge) {
        this.file = file;
        this.horloge = horloge;
    }

    public void add(Listener listener) {
        this.listeners.add(listener);
    }

    public void fireListener(Order order) {
        for (Listener l : this.listeners) {
            l.handleNewOrder(order);
        }
    }

    public double getOpenPrice() {
        Scanner sc = null;
        double openPrice = 0;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(file)));
            while (openPrice == 0) {
                String line = sc.nextLine();
                Order currentOrder = ReadLine(line);
                if (currentOrder instanceof ExecutedOrder) {
                    try {
                        detectAddOrderReference(currentOrder);
                    } catch (IOException ex) {
                        Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    openPrice = ((ExecutedOrder) currentOrder).getPrice();

                } else if (currentOrder instanceof AddOrder) {
                    AddOrder addObject = (AddOrder) currentOrder;
                    AssetInfo addInfo = new AssetInfo(addObject.getOrderId(), addObject.getSymbol(), addObject.getShares(), addObject.getSideIndicator(), addObject.getPrice());
                    existingAddOrders.add(addInfo);
                }
            }
        } catch (FileNotFoundException ex) {

            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, null, ex);
        }

        existingAddOrders = new ArrayList<AssetInfo>();
        return openPrice;

    }

    /*
     Examine information on 1 line
     Return corresponding Order Object
     */
    private static Order ReadLine(String line) {

        Order currentOrder = null;
        String type = getMessageType(line);
        String orderId = getOrderId(line);
        int date = getDate(line);

        switch (type) {
            case AddType:

                String sideIndicator = getSideIndicator(line);
                int shares = getShares(line, AddSharesBegin, AddSharesEnd);
                String symbol = getSymbol(line);
                double price = getPrice(line);
                setValuePrice(price);
                switch (sideIndicator) {
                    case SideIndicatorBuy:
                        // Create a new order object
                        currentOrder = new Bid(date, type, orderId,
                                sideIndicator, shares, symbol, price);
                        break;
                    case SideIndicatorSell:
                        currentOrder = new Ask(date, type, orderId,
                                sideIndicator, shares, symbol, price);
                        break;
                    default:
                        break;
                }

                break;

            // TODO: Update corresponding order
            case ExecuteType:
                int executedShares = getShares(line, ExeSharesBegin, ExeSharesEnd);
                currentOrder = new ExecutedOrder(date, type, orderId, executedShares);
                break;

            // TODO: Delete corresponding order
            case CancelType:
                int cancelledShares = getShares(line, CancelSharesBegin, CancelSharesEnd);
                currentOrder = new CancelledOrder(date, type, orderId, cancelledShares);
                break;
        }

        return currentOrder;
    }

    /*
     *  Fonction qui charge et lit un fichier 
     * Le fichier est envoyé en paramètre d'entrée.
     * En sortie on renvoie des données lues
     */
    @Override
    public void run() {

        // Instanciation 
        Scanner sc = null; // Scanner

        try {
            // Instanciation d'un scanner couplé à un BufferedReader
            // Le scanner récupère les informations ligne par ligne
            sc = new Scanner(new BufferedReader(new FileReader(file)));

            while (sc.hasNext()) {
                String line = sc.nextLine();

                // Read current Line
                Order currentOrder = ReadLine(line);

                boolean test = false;
                while (!test) {
                    if (currentOrder != null) {

                        //System.out.println("Horloge: "+horloge.getTime()+"   Ordre:   "+currentOrder.getDate());
                        //System.out.println(horloge.test(currentOrder));
                        if (horloge.test(currentOrder)) {
                            // System.out.println("Dans la boucle");
                            //System.out.println("Horloge: " + transfoInverse(horloge.getTime()) + "   Ordre:   " + transfoInverse(currentOrder.getDate()));    
                            // Examine object type
                            if (currentOrder instanceof AddOrder) {
                                AddOrder addObject = (AddOrder) currentOrder;

                                // Examine Add Type
                                if (addObject instanceof Ask) {
                                    Ask askObject = (Ask) addObject;
                                    this.fireListener(askObject);
                                    //System.out.println(" " + ClockController.transfoInverse(currentOrder.getDate()) + " Side: " + askObject.getSideIndicator() + " Price: " + askObject.getPrice());
                                    test = true;
                                } else if (addObject instanceof Bid) {
                                    Bid bidObject = (Bid) addObject;
                                    this.fireListener(bidObject);
                                    //System.out.println(" " + ClockController.transfoInverse(currentOrder.getDate()) + " Side: " + bidObject.getSideIndicator() + " Price: " + bidObject.getPrice());
                                    //System.out.println(bidObject.getSideIndicator() + " : " + bidObject.getPrice());
                                    test = true;
                                }

                                // Save Add to Arraylist for future reference
                                AssetInfo addInfo = new AssetInfo(addObject.getOrderId(), addObject.getSymbol(), addObject.getShares(), addObject.getSideIndicator(), addObject.getPrice());
                                existingAddOrders.add(addInfo);
                            } else {
                                // Detect add order reference
                                detectAddOrderReference(currentOrder);
                                test = true;
                            }

                        }
                    }

                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Fichier introuvable");
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //On ferme le scanner
            sc.close();
        }
    }

    private void detectAddOrderReference(Order currentOrder) throws IOException {

        AssetInfo currentAdd = new AssetInfo();

        outerloop:
        for (int i = 0; i < existingAddOrders.size(); i++) {
            currentAdd = existingAddOrders.get(i);

            // If there is a match between current orderID examined and an existing add order
            if (currentAdd.getReference().equals(currentOrder.getOrderId())) {

                // keep track of shares left on add order
                int nbSharesLeft = currentAdd.getNbShares() - currentOrder.getShares();

                // Refresh number of shares
                currentAdd.setNbShares(nbSharesLeft);

                if (nbSharesLeft == 0) {
                    // Remove order from Arraylist
                    existingAddOrders.remove(i);
                }

                /* 
                 *Examine order type
                 */
                // if Cancel order
                if (currentOrder instanceof CancelledOrder) {
                    CancelledOrder cancelledOrder = (CancelledOrder) currentOrder;

                    // set Price of order
                    cancelledOrder.setPrice(currentAdd.getPrice());

                    //System.out.println("Cancel " + ClockController.transfoInverse(cancelledOrder.getDate()) + " Side: " + currentAdd.getSideIndicator() + " Price: " + currentAdd.getPrice());

                    // Fire listener
                    this.fireListener(cancelledOrder);
                } else if (currentOrder instanceof ExecutedOrder) {
                    ExecutedOrder executedOrder = (ExecutedOrder) currentOrder;

                    // set Price of order
                    executedOrder.setPrice(currentAdd.getPrice());

                    //System.out.println("Executed " + ClockController.transfoInverse(executedOrder.getDate()) + " Side: " + currentAdd.getSideIndicator() + " Price: " + currentAdd.getPrice());

                    // Fire listener
                    this.fireListener(executedOrder);
                }

                // Exit loop for search optimization purpose
                break outerloop;
            }
        }
    }

    private static String getMessageType(String line) {
        String type = line.substring(MessageTypeBegin, MessageTypeEnd);
        return type;
    }

    private static String getOrderId(String line) {
        String orderId = line.substring(OrderBegin, OrderEnd);
        return orderId;
    }

    private static int getDate(String line) {
        int date = parseInt(line.substring(DateBegin, DateEnd));
        return date;
    }

    private static String getSideIndicator(String line) {
        String sideIndicator
                = line.substring(AddSideIndicatorBegin, AddSideIndicatorEnd);
        return sideIndicator;
    }

    private static int getShares(String line, int begin, int end) {
        int shares = parseInt(line.substring(begin, end));
        return shares;
    }

    private static String getSymbol(String line) {
        String symbol = line.substring(AddSymbolBegin, AddSymbolEnd);
        symbol = symbol.replaceAll("\\s+", "");
        return symbol;
    }

    private static double getPrice(String line) {
        String priceString = line.substring(AddPriceBegin, AddPriceSeparation)
                + "." + line.substring(AddPriceSeparation, AddPriceEnd);
        double price = parseDouble(priceString);
        return price;
    }

    private static void setValuePrice(double price) {
        DataController.valuePrice = price;
    }

}
