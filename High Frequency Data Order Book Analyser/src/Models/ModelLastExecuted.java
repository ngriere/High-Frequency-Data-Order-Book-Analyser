/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import static Controllers.ClockController.transfoInverse;
import Controllers.LastListener;
import Controllers.Listener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maniccav
 */
public class ModelLastExecuted extends AbstractTableModel implements Listener {

    private final List<DataLastExecuted> listLastExecuted = new ArrayList<DataLastExecuted>();
        private ArrayList<LastListener> listeners;


    private final String[] entetes = {"Timestamp", "Price", "Num Shares"};

    public ModelLastExecuted() {

        super();
        listeners = new ArrayList<LastListener>();

    }
public void add(LastListener listener) {
        this.listeners.add(listener);
    }

    public void fireListener(int time,double price,int volume) {
        for (LastListener l : this.listeners) {
            l.handleBestLast(time,price,volume);
        }
    }
    @Override
    public int getRowCount() {
        return listLastExecuted.size();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listLastExecuted.get(rowIndex).getTimestamp();
            case 1:
                return listLastExecuted.get(rowIndex).getPrice();
            case 2:
                return listLastExecuted.get(rowIndex).getShares();

            default:
                return null; //Ne devrait jamais arriver
        }
    }

    public void add(DataLastExecuted LE) {
        listLastExecuted.add(LE);

        fireTableRowsInserted(listLastExecuted.size() - 1, listLastExecuted.size() - 1);
    }

    public void remove(int rowIndex) {
        listLastExecuted.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    @Override
    public void handleNewOrder(Order order) {

        if (order instanceof ExecutedOrder) {
            ExecutedOrder exe = (ExecutedOrder) order;
                        this.fireListener(exe.getDate(),exe.getPrice(),exe.getShares());

            String date = transfoInverse(exe.getDate());
            DataLastExecuted executedOrder = new DataLastExecuted(date, exe.getPrice(), exe.getShares());

            if (listLastExecuted.size() == 50) {
                this.listLastExecuted.remove(49);
            }
            this.listLastExecuted.add(0, executedOrder);
        }
        this.fireTableDataChanged();
    }
}
