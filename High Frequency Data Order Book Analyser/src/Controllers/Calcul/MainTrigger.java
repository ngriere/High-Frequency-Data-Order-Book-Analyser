package Controllers.Calcul;

import Controllers.Listener;
import Models.*;
import Views.GUI_PPE_HFT;
import java.util.StringTokenizer;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GE
 */
public class MainTrigger implements Listener {

    private Calculus _calculus = null;
    private Spread _spread = null;
    //private Function _function = null;
    //private Models _models = null;

    private int shares = 0;
    private double min = 0;
    private double max = 0;
    private double mean = 0;
    private double variance = 0;
    private double median = 0;
    private double varday = 0;
    private double volat = 0;
    private int N = 0;
    private double avg_spread;
    private double best_spread;

    private GUI_PPE_HFT window;

    public MainTrigger(double openPrice, GUI_PPE_HFT window) {
        _calculus = new Calculus(openPrice);
        _spread = new Spread();
        this.window = window;
        this.window.getjOpen().setText(""+openPrice);
    }

    public double getShares() {
        return shares;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getMean() {
        return mean;
    }

    public double getVariance() {
        return variance;
    }

    public double getMedian() {
        return median;
    }

    public int getN() {
        return N;
    }

    @Override
    public void handleNewOrder(Order order) {
        if (order instanceof ExecutedOrder) {
            _calculus.IncrOrder();
            if (_calculus.getN() == 0) {
                ExecutedOrder excord = (ExecutedOrder) order;
                _calculus.setLastprice(excord.getPrice());
                shares = _calculus.getShares(order);
                min = _calculus.getMin(order);
                max = _calculus.getMax(order);
                mean = _calculus.getMean(order);
                variance = _calculus.getVariance(order);
                //median = 0;
                volat = _calculus.getVolatility(order);

            } else {
                shares = _calculus.getShares(order);
                min = _calculus.getMin(order);
                max = _calculus.getMax(order);
                mean = _calculus.getMean(order);
                variance = _calculus.getVariance(order);
                //median = 0;
                varday = _calculus.getVar(order);
                volat = _calculus.getVolatility(order);
            }

            window.getjVolume().setText("" + shares);
            window.getjLow().setText("" + min);
            window.getjHigh().setText("" + max);
            String varianceString = "" + variance;
            window.getjVolatility().setText(tronque(varianceString, 3)+"%");
            String vardayString = "" + varday;
            window.getjVariation().setText(tronque(vardayString, 3)+"%");

        } else if (order instanceof CancelledOrder) {

        } else if (order instanceof AddOrder) {
            if (order instanceof Ask) {
                _spread.nask++;
                _spread.Best_Spread(order, 'A');
            } else if (order instanceof Bid) {
                _spread.nbid++;
                _spread.Best_Spread(order, 'B');
            }
            if (_spread.getNask() > 0 && _spread.getNbid() > 0) {
                this.best_spread = _spread.getBest_spread();
                this.avg_spread = _spread.getAvg_spread();
            } else {
                
            }
        }
        window.getjPanelFunctions().repaint();
    }

    public static String tronque(String chaine, int nbDecimales) {
        String avant;
        String apres;

        StringTokenizer st = new StringTokenizer(chaine, ".");
        avant = st.nextToken();
        if (st.hasMoreTokens()) {
            apres = st.nextToken();
        } else {
            return avant;
        }

        if (apres.length() <= nbDecimales) {
            return chaine;
        }
        return chaine.substring(0, chaine.length()
                - apres.length() + nbDecimales);
    }

}
