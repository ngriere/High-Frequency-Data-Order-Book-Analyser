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

public class Calculus {
    
    private int shares = 0;
    private double min = -1;
    private double max = -1;
    private double mean = 0;
    private double variance = 0;
    private double median = 0;
    private double volatility = 0;
    private double openprice = 0;
    private double lastprice = 0;
    private double logrend = 0;
    private double var_logrend = 0;
    private int N = 0;


    public int getN() {
        return N;
    }

    public void setLastprice(double lastprice) {
        this.lastprice = lastprice;
    }
    
    
    public void IncrOrder() {
        N++;
    }
    
    public Calculus(double open_price) { 
        this.openprice = open_price;
        this.max = open_price;
        this.min = open_price;
    }
    
    public int getShares(Order order)
    {
        ExecutedOrder excord = (ExecutedOrder)order;
        this.shares = this.shares + excord.getShares();
        return this.shares;
    }
    
    public double getMean(Order order)
    {
        ExecutedOrder excord = (ExecutedOrder)order;
        
        this.mean = ((this.mean * (this.N - 1)) + excord.getPrice()) / this.N;
        
        return this.mean;
    }
    
    public Double getVar(Order order) 
    {
        ExecutedOrder excord = (ExecutedOrder)order;
        return (excord.getPrice() - this.openprice)/this.openprice * 100;
        
    }
    
    public double getMin(Order order)
    {
        ExecutedOrder excord = (ExecutedOrder)order;
        if(this.min == -1) { this.min = excord.getPrice(); }
        else {
            if(excord.getPrice() < this.min) {
                this.min = excord.getPrice();
            }
        }
        return this.min;
    }
    
    public double getMax(Order order)
    {
        ExecutedOrder excord = (ExecutedOrder)order;
        if(this.max == -1) { this.max = excord.getPrice(); }
        else {
            if(excord.getPrice() > this.max) {
                this.max = excord.getPrice();
            }
        }
        return this.max;
    }

    public double getVariance(Order order)
    {
        ExecutedOrder excord = (ExecutedOrder)order;
        this.variance = ((this.variance * (this.N - 1)) + Math.pow((excord.getPrice()-this.mean), 2)) / this.N;
        
        return this.variance*100;
    }
    
    public double getVolatility(Order order) 
    {
        ExecutedOrder excord = (ExecutedOrder)order;
        double logrend_tmp;
        double logrend;
        logrend_tmp = Math.log(excord.getPrice()/this.lastprice);
        this.logrend = ((this.logrend * (this.N - 1)) + logrend_tmp) / this.N;
        this.var_logrend = ((this.logrend * (this.N - 1)) + Math.pow((excord.getPrice()-this.logrend), 2)) / this.N;
        this.volatility = Math.sqrt(this.var_logrend);
        return this.volatility;
    }
    
    
    

    /*public double Median(Order order) 
    {
       double[] b = new double[data.length];
       //System.arraycopy(Object Src, int SrcPos, Object Dest, int DestPos, int length)
       System.arraycopy(data, 0, b, 0, b.length);
       Arrays.sort(b);

       if (data.length % 2 == 0) 
       {
          return (b[(b.length / 2) - 1] + b[b.length / 2]) / 2.0;
       } 
       else 
       {
          return b[b.length / 2];
       }
    }*/
    
    
}
