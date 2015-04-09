package Controllers;

import Controllers.Calcul.MainTrigger;
import Views.GUI_PPE_HFT;
import java.awt.event.ActionEvent;
import java.io.File;
import jxl.write.WriteException;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.ui.RefineryUtilities;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 * Main Controller of PPE_HFT application
 *
 * @author Nicolas_2
 */
public class AppController implements ActionListener {

    private ClockController horloge;
    private DataController dataControl;
    private GUI_PPE_HFT window;
    private MainTrigger mt;
    private ExportChartSpread es;
    private ExportChartVolume ev;
    private final JButton Pause;
    private final JButton Resume;
  
    public AppController(String hDep, File resultFile) {
        
           //Instantiation et lancement de l'horloge
        this.horloge = new ClockController(hDep);

        this.dataControl = new DataController(resultFile, horloge);

        // Launch Main Window
        RegularTimePeriod Tdebut = new Millisecond(0, 0, 30, 8, 1, 1, 2011);
        
        double openPrice = this.dataControl.getOpenPrice();
        this.window = new GUI_PPE_HFT(resultFile.getName().replace(".txt", ""), Tdebut, openPrice);
        
        RefineryUtilities.centerFrameOnScreen(window);

        this.mt = new MainTrigger(openPrice, window);

        try {

            this.es = new ExportChartSpread(window);
            window.getPanelAsk().getModele().add(es);
            window.getPanelBid().getModele().add(es);

            this.ev = new ExportChartVolume(window);
            window.getPanelLastExecuted().getModele().add(ev);

        } catch (WriteException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Add listeners on Window components
        window.getPanelAsk().getModele().add(window.getGraphView());
        window.getPanelBid().getModele().add(window.getGraphView());
        window.getPanelLastExecuted().getModele().add(window.getGraphView());
        
        dataControl.add(window.getPanelAsk().getModele());
        dataControl.add(window.getPanelBid().getModele());
        dataControl.add(window.getPanelLastExecuted().getModele());
        dataControl.add(mt);
        
        horloge.add(window);

        // Add listeners to buttons
        Pause = window.getButtonPause();
        Resume = window.getButtonResume();

        Pause.addActionListener(this);
        Resume.addActionListener(this);

        window.getVE().addActionListener(this);
        window.getVA().addActionListener(this);
        window.getVB().addActionListener(this);
        window.getSE_BA().addActionListener(this);
        window.getEE_BA().addActionListener(this);
        window.getSE_EXE().addActionListener(this);
        window.getEE_EXE().addActionListener(this);
        //window.pack();
        window.setVisible(true);
    }

    public void launch() {
        this.horloge.start();
        this.dataControl.start();
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(e.getActionCommand());

        String action = e.getActionCommand();

        if (action.equals("Pause")) {
            this.horloge.pause();
            //à changer, pas besoin de mettre le dataControl en pause
            //il faut mettre le graph en pause
            this.dataControl.suspend();
            
            //on désactive le bouton pause
            Pause.setEnabled(false);
            //on réactive le bouton reprendre
            Resume.setEnabled(true);
            window.getGraphView().pause();

        }
        if (action.equals("Resume")) {
            this.horloge.reprendre();
            //voir au dessus, jouer sur le graph pas sur le dataControl
            this.dataControl.resume();
            //on désactive le bouton reprendre
            Resume.setEnabled(false);

            //on réactive le bouton pause
            Pause.setEnabled(true);
            window.getGraphView().reprendre();

        }

        if (action.equals("Volume Bid")) {
            if (this.window.getVB().isSelected()) {
                window.getGraphView().setDisplayVolumeBid(true);
            } else {
                window.getGraphView().setDisplayVolumeBid(false);
            }
        }
        if (action.equals("Volume Executed")) {
            if (this.window.getVE().isSelected()) {
                window.getGraphView().setDisplayVolumeExecuted(true);
            } else {
                window.getGraphView().setDisplayVolumeExecuted(false);
            }
        }
        if (action.equals("Volume Ask")) {
            if (this.window.getVA().isSelected()) {
                window.getGraphView().setDisplayVolumeAsk(true);
            } else {
                window.getGraphView().setDisplayVolumeAsk(false);
            }
        }

        if (action.equals("Start Export Bid/Ask")) {
            System.out.println("DÃ©but export Bid/Ask");
            es.setBoolean(true);
            window.getSE_BA().setEnabled(false);
            window.getEE_BA().setEnabled(true);

        }
        if (action.equals("End Export Bid/Ask")) {
            System.out.println("Fin export Bid/Ask");
            es.setBoolean(false);

            try {
                es.endExport();
            } catch (IOException | WriteException ex) {
                Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
            }
            es.reInitialize();
            window.getSE_BA().setEnabled(true);
            window.getEE_BA().setEnabled(false);
        }
        if (action.equals("Start Export Executed")) {
            System.out.println("DÃ©but export Executed");
            ev.setBoolean(true);
            window.getSE_EXE().setEnabled(false);
            window.getEE_EXE().setEnabled(true);

        }
        if (action.equals("End Export Executed")) {
            System.out.println("Fin export Executed");
            ev.setBoolean(false);

            try {
                ev.endExport();
            } catch (IOException | WriteException ex) {
                Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
            }
            window.getSE_EXE().setEnabled(true);
            window.getEE_EXE().setEnabled(false);
            ev.reInitialize();
        }

        if (action.equals("Spread")) {
            System.out.println("Spread");

        }
    }

  
}
