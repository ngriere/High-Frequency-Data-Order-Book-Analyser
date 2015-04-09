/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.BidListener;
import Controllers.Listener;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author hugo
 */
public class ModelOrderBookBid extends AbstractTableModel implements Listener {


    ArrayList<DataOrderBook> liste;
    private final String[] entetes = {"Shares", "Bid Price"};
    private ArrayList<BidListener> listeners;

    public ModelOrderBookBid() {
        super();   
        this.liste = new ArrayList<>();
         listeners = new ArrayList<BidListener>();

    }
    
       public void add(BidListener listener) {
        this.listeners.add(listener);
    }
       

    public void fireListener(DataOrderBook data) {
        for (BidListener l : this.listeners) {
            l.handleBestBid(data);
        }
        
    }

    public ArrayList<DataOrderBook> getListe() {
        return liste;
    }

    public void setListe(ArrayList<DataOrderBook> liste) {
        this.liste = liste;
    }

    @Override
    public int getRowCount() {
        return this.liste.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    @Override
    public Double getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return (double) this.liste.get(rowIndex).getVolume();

            case 1:
                return this.liste.get(rowIndex).getPrice();
            default:
                return 0.0;
        }

    }

    public double getFirstPrice() {
        return (double) this.liste.get(0).getPrice();

    }

    public void add(DataOrderBook data) {
        this.liste.add(data);

    }

    public void addRow(DataOrderBook data, int row) {
        this.liste.add(row, data);
    }

    public void delete(int i) {
        this.liste.remove(i);

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void setValueAt(int volume, int rowIndex) {

        DataOrderBook mdata = this.liste.get(rowIndex);

        int newVolume = mdata.getVolume() + volume;

        mdata.setVolume(newVolume);

    }

    public boolean actionAdd(Double price) {
        for (int i = 0; i < this.getRowCount(); i++) {
            if (price == this.liste.get(i).getPrice()) {
                return true;
            }
        }

        return false;
    }

    public int rechercheRowUpdate(double price) {
        for (int i = 0; i < this.getRowCount(); i++) {
            if (price == this.liste.get(i).getPrice()) {
                return i;
            }
        }

        return 10000;
    }

    public int rechercheRowAdd(double price) {
        
        int i = 0;

        while (i < this.getRowCount()) {
            if (price > this.liste.get(i).getPrice()) {
                return i;
            }
            i++;
        }

        return 1000;

      
    }

    @Override
    public void handleNewOrder(Order order) {

        if (order instanceof Bid) {
            Bid add = (Bid) order;
            if (actionAdd(add.getPrice())) {
                this.setValueAt(add.getShares(), rechercheRowUpdate(add.getPrice()));
            } else {
                int row = rechercheRowAdd(add.getPrice());
                DataOrderBook mData = new DataOrderBook(add.getPrice(), add.getShares(), add.getDate());
                if (row == 1000) {
                    this.add(mData);
                } else {
                    this.addRow(mData, row);
                    if(row==0){
                        this.fireListener(mData);
                    }
                }
            }
        } else {

            if (order instanceof CancelledOrder) {
                CancelledOrder cancel = (CancelledOrder) order;
                int row = rechercheRowUpdate(cancel.getPrice());
                if (!(row == 10000)) {
                    this.setValueAt(-cancel.getShares(), row);
                    if (!(this.getValueAt(row, 0) > 0)) {
                        this.delete(row);
                        if (row == 0) {
                        this.fireListener(this.liste.get(0));
                    }
                    }
                }
            }
            if (order instanceof ExecutedOrder) {
                ExecutedOrder exe = (ExecutedOrder) order;
                int row = rechercheRowUpdate(exe.getPrice());
                if (!(row == 10000)) {
                    this.setValueAt(-exe.getShares(), row);
                    if (!(this.getValueAt(row, 0) > 0)) {
                        this.delete(row);
                        if (row == 0) {
                        this.fireListener(this.liste.get(0));
                    }
                    }
                }
            }

        }

        this.fireTableDataChanged();
    }

}

