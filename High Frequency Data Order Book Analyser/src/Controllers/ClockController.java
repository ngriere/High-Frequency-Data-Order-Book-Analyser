/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Order;
import static java.lang.Integer.parseInt;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hugo
 */
public class ClockController extends Thread {

    public static DateFormat dateformat = new SimpleDateFormat("HH:mm:ss:SSS");
    private int time;
    private final String heureDepart;
    private int decalage;
    private long debutPause;
    private ArrayList<ClockListener> listeners;
    private boolean pause;

    public ClockController(String heureDepart) {
        this.heureDepart = heureDepart;
        decalage = transfo(dateformat.format(new Date())) - transfo(this.heureDepart);
        this.listeners = new ArrayList<ClockListener>();
        this.pause=false;
    }

    public int getTime() {
        return this.time;
    }
    
public void setPause(boolean pause){
    this.pause=pause;
}
    public void add(ClockListener clock) {
        this.listeners.add(clock);
    }

    public void fireListener(int time) {
        for (ClockListener l : this.listeners) {
            l.handleClock(time);
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            String date = dateformat.format(new Date());
            int l = transfo(date);
            time = l - decalage;
            if(!this.pause){
            this.fireListener(this.getTime());
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClockController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
        

    }

    public void setDecalage(int newDecalage) {
        this.decalage = this.decalage + newDecalage;
    }

    public void pause() {

        this.debutPause = System.currentTimeMillis();
        this.setPause(true);
    }

    public void reprendre() {
        this.setDecalage((int) (System.currentTimeMillis() - this.debutPause));
        this.setPause(false);
    }

    public static int transfo(String date) {
        int h = parseInt(date.substring(0, 2));
        int m = parseInt(date.substring(3, 5));
        int s = parseInt(date.substring(6, 8));
        int sss = parseInt(date.substring(9, 12));
        int coucou = h * 3600 * 1000 + m * 60 * 1000 + s * 1000 + sss;
        return coucou;
    }

    public static String transfoInverse(int nb) {
        int h = nb / (3600 * 1000);
        int m = (nb % (3600 * 1000)) / (60 * 1000);
        int s = ((nb % (3600 * 1000)) % (60 * 1000)) / 1000;
        int ms = ((nb % (3600 * 1000)) % (1000));

        String hh = "" + h;
        String mm = "" + m;
        String ss = "" + s;
        String SSS = "" + ms;

        if (h < 10) {
            hh = "0" + h;
        }
        if (m < 10) {
            mm = "0" + m;
        }
        if (s < 10) {
            ss = "0" + s;
        }
        if (ms < 100) {
            if (ms < 10) {
                SSS = "00" + ms;
            } else {
                SSS = "0" + ms;
            }
        }

        return hh + ":" + mm + ":" + ss + ":" + SSS;
    }

    public boolean test(Order order) {

        if (this.getTime() >= order.getDate()) {
            return true;
        } else {
            return false;
        }

    }
}
