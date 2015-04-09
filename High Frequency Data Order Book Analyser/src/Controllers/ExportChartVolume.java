/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
public class ExportChartVolume implements LastListener {

    private static WritableWorkbook outWorkBook = null;
    private static WritableSheet out = null;
    private int nrows;
    private ArrayList<Number> list_data;
    private final int size = 5000;
    private int i;
    private boolean bool;
    GUI_PPE_HFT window;

    public ExportChartVolume(GUI_PPE_HFT window) throws WriteException {
        this.window = window;
        list_data = new ArrayList<>();
        this.nrows = 1;
        this.i = 0;
        this.bool = false;
    }

    public void addData(int date, double price, double shares) {

        list_data.add(new Number(0, nrows, date));
        list_data.add(new Number(1, nrows, price));
        list_data.add(new Number(2, nrows, shares));

        nrows++;
    }

    public void endExport() throws IOException, WriteException {

        Date mydate = new Date();
        //String file = "C:/Users/Nicolas_2/Desktop/bachir V4/Nico/Export/TemplateChart-Spread-" + mydate.getHours() + "h" + mydate.getMinutes() + ".xls";
        String file = "Export\\Volume\\TemplateChart-Volume" + mydate.getHours() + "h" + mydate.getMinutes() + ".xls";
        File f = new File(file);
        f.getParentFile().mkdir();
        f.createNewFile();

        try {
            outWorkBook = Workbook.createWorkbook(f);
            out = outWorkBook.createSheet("Data", 0);
            // Récupération de l'onglet courant (le premier onglet)
            out = outWorkBook.getSheet(0);
            Label labelD = new Label(0, 0, "Date");
            Label labelP = new Label(1, 0, "Price");
            Label labelS = new Label(2, 0, "Shares");

            //Ajout des cellules 
            out.addCell(labelD);
            out.addCell(labelP);
            out.addCell(labelS);

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

    public void setBoolean(boolean bool) {
        this.bool = bool;
    }

    public void reInitialize() {
        this.i = 0;
        this.list_data = new ArrayList<>();
    }

    @Override
    public void handleBestLast(int time, double price, int volume) {

        if (bool) {
            if (this.i <= size) {
                if (this.i == size) {
                    try {

                        this.endExport();
                        window.getSE_EXE().setEnabled(true);
                        window.getEE_EXE().setEnabled(false);

                    } catch (IOException | WriteException ex) {
                        Logger.getLogger(ExportChartSpread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    this.addData(time, price, volume);
                }
                this.i++;

            }
        }
    }
}
