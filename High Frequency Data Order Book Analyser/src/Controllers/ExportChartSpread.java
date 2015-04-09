/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DataOrderBook;
import Views.GUI_PPE_HFT;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author GE
 */
public class ExportChartSpread implements AskListener, BidListener {

    private static WritableWorkbook outWorkBook = null;
    private static WritableSheet out = null;
    private int nrows;
    private ArrayList<Number> list_data;
    private double last_bid;
    private double last_ask;
    private int i;
    private final int size = 5000;
    private boolean bool;
    GUI_PPE_HFT window;

    public ExportChartSpread(GUI_PPE_HFT window) throws WriteException {
        this.window=window;
        list_data = new ArrayList<>();
        this.i = 0;
        this.nrows = 1;
        this.bool = false;
    }

    public void setBoolean(boolean bool) {
        this.bool = bool;
    }

    public void reInitialize() {
        this.i = 0;
        this.list_data = new ArrayList<>();
    }

    public void addData(int date, double bid, double ask) {

        if (nrows == 1) {
            if (bid == 0) {
                list_data.add(new Number(0, 1, date));
                list_data.add(new Number(1, 1, ask));
                list_data.add(new Number(2, 1, ask));
                last_bid = ask;
                last_ask = ask;
            } else {
                list_data.add(new Number(0, 1, date));
                list_data.add(new Number(1, 1, bid));
                list_data.add(new Number(2, 1, bid));
                last_bid = bid;
                last_ask = bid;
            }
        } else {
            if (bid == 0) {
                list_data.add(new Number(0, nrows, date));
                list_data.add(new Number(1, nrows, last_bid));
                list_data.add(new Number(2, nrows, ask));
                last_ask = ask;
            } else {
                list_data.add(new Number(0, nrows, date));
                list_data.add(new Number(1, nrows, bid));
                list_data.add(new Number(2, nrows, last_ask));
                last_bid = bid;
            }
        }
        nrows++;
    }

    public void endExport() throws IOException, WriteException {

        Date mydate = new Date();
        String file = "Export\\Spread\\TemplateChart-Spread-" + mydate.getHours() + "h" + mydate.getMinutes() + ".xls";
        File f = new File(file);
        f.getParentFile().mkdir();
        f.createNewFile();

        try {
            outWorkBook = Workbook.createWorkbook(f);
            out = outWorkBook.createSheet("Data", 0);
            // Récupération de l'onglet courant (le premier onglet)
            out = outWorkBook.getSheet(0);
            Label labelD = new Label(0, 0, "Date");
            Label labelB = new Label(1, 0, "Bid");
            Label labelA = new Label(2, 0, "Ask");

            //Ajout des cellules 
            out.addCell(labelD);
            out.addCell(labelB);
            out.addCell(labelA);

        } catch (IOException ex) {
            Logger.getLogger(ExportChartSpread.class.getName()).log(Level.SEVERE, null, ex);

        }
        this.WriteData();
        outWorkBook.write();

        outWorkBook.close();

    }

    private void WriteData() throws WriteException {
        for (Number e : list_data) {
            out.addCell(e);
        }
    }

    @Override
    public void handleBestAsk(DataOrderBook data) {

        if (bool) {
            if (this.i <= size) {
                if (this.i == size) {
                    try {
                        this.endExport();
                         window.getSE_BA().setEnabled(true);
                         window.getEE_BA().setEnabled(false);
                    } catch (IOException | WriteException ex) {
                        Logger.getLogger(ExportChartSpread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    this.addData(data.getTime(), 0, data.getPrice());
                }
                this.i++;

            }
        }
    }

    @Override
    public void handleBestBid(DataOrderBook data) {

        if (bool) {
            if (this.i <= size) {
                if (this.i == size) {
                    try {
                        this.endExport();
                         window.getSE_BA().setEnabled(true);
                         window.getEE_BA().setEnabled(false);
                    } catch (IOException | WriteException ex) {
                        Logger.getLogger(ExportChartSpread.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    this.addData(data.getTime(), data.getPrice(), 0);
                }
                this.i++;
            }

        }
    }

}
