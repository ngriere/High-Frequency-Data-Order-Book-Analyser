package Controllers.Calcul;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author GE
 */
import Models.*;

public class Spread {
    
    private double avg_spread = 0;
    private double best_spread = 0;
    
    private double best_ask = 0;
    private double best_bid = 0;
    private double avg_ask = 0;
    private double avg_bid = 0;
    private double bid = 0;
    public int nbid = 0;
    private double ask = 0;
    public int nask = 0;
    
    public Spread() {
        
    }

    public double getAvg_spread() {
        return avg_spread;
    }

    public double getBest_spread() {
        return best_spread;
    }

    public void setBest_ask(double best_ask) {
        this.best_ask = best_ask;
    }

    public void setBest_bid(double best_bid) {
        this.best_bid = best_bid;
    }

    public void setAvg_ask(double avg_ask) {
        this.avg_ask = avg_ask;
    }

    public void setAvg_bid(double avg_bid) {
        this.avg_bid = avg_bid;
    }
    
    

    public void setBid(double bid) {
        this.bid = bid;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public int getNbid() {
        return nbid;
    }

    public int getNask() {
        return nask;
    }
    
    public void Best_Spread(Order order, char side) {
        AddOrder addord = (AddOrder)order;
        switch(side) {
            case 'A':
                if(this.best_ask == 0) { this.best_ask = addord.getPrice(); }
                if(addord.getPrice() < this.best_ask) {
                    this.best_ask = addord.getPrice();
                }
                break;
            case'B':
                if(this.best_bid == 0) { this.best_bid = addord.getPrice(); }
                if(addord.getPrice() > this.best_bid) {
                    this.best_bid = addord.getPrice();
                }
                break;
        }
        if(this.best_bid != 0 && this.best_ask !=0) {
        this.best_spread = Math.abs(this.best_bid - this.best_ask);}
        else {
            this.best_spread = 0;
        }
    }


    public void Avg_Spread(Order order, char side) {
        AddOrder addord = (AddOrder)order;
        switch(side) {
            case 'A':
                if(this.best_ask == 0) { this.best_ask = addord.getPrice(); }
                avg_ask = ((avg_ask * (nask-1)) + addord.getPrice()) / nask;
                break;
            case'B':
                if(this.best_bid == 0) { this.best_bid = addord.getPrice(); }
                avg_ask = ((avg_ask * (nask-1)) + addord.getPrice()) / nask;
                break;                    
        }
        if(this.avg_bid != 0 && this.avg_ask !=0) {
        this.avg_spread = Math.abs(this.avg_bid - this.avg_ask);}
        else {
            this.avg_spread = 0;
        }
    }
}
