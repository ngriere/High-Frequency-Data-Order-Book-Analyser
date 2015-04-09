/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.ModelOrderBookAsk;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author hugo
 */
public class TabAskView extends JPanel {

    private final ModelOrderBookAsk modele = new ModelOrderBookAsk();
    private final JTable tableau;

    public TabAskView() {
        super();
        tableau = new JTable(modele);

        JScrollPane scrollPane = new JScrollPane(tableau);
        scrollPane.setViewportView(tableau);

        BorderLayout blayout1 = new BorderLayout();
        setLayout(blayout1);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public ModelOrderBookAsk getModele() {
        return modele;
    }

    public JTable getTableau() {
        return tableau;
    }
}
