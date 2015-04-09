/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.ModelLastExecuted;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Admin
 */
public class TabLastExecutedView extends JPanel {

    private ModelLastExecuted modeleExe = new ModelLastExecuted();
    private final JTable tableau;

    public TabLastExecutedView() {
        super();

        tableau = new JTable(modeleExe);

        JScrollPane scrollPane = new JScrollPane(tableau);
        scrollPane.setViewportView(tableau);

        BorderLayout blayout1 = new BorderLayout();
        setLayout(blayout1);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public ModelLastExecuted getModele() {
        return modeleExe;
    }

    public JTable getTableau() {
        return tableau;
    }
}
