/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Views.SimulationParameters;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import jxl.write.WriteException;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author hugo
 */
public class Main {

    private static File resultFile = null;
    private static String hDep = null;
    private static Boolean simulationParametersComplete = false;

    public static void setSelectedFile(File selectedFile) {
        resultFile = selectedFile;
    }

    public static void setSelectedStartTime(String selectedStartTime) {
        hDep = selectedStartTime;
    }

    public static void main(String[] args) throws IOException, InterruptedException, WriteException {

        
        //Paramètres
        SimulationParameters simulationParameters = new SimulationParameters();
        RefineryUtilities.centerFrameOnScreen(simulationParameters);
        simulationParameters.setVisible(true);

        while (simulationParametersComplete == false) {
            sleep(1);
            // Get user selected time and file to open
            if (hDep == null || resultFile == null) {
                simulationParametersComplete = false;
            } else {
                simulationParametersComplete = true;
            }
        }

        //App Controller
        AppController ac = new AppController(hDep, resultFile);
        //Lancement des threads
        ac.launch();

    }
}
