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

public class Function {

    /*public double Volatility(Object[] o)
     {  
     return Calculus.getStdDev(Calculus.LogOutPut(o));
     }*/
    public double Liquidity(Object time /*Tableau des temps d'exec*/, Object[] volume, Object[] exec) {
        double liquidity = 0;
        int i;
        double error = -1.00;

        try {
            if (volume.length != exec.length) {
                throw new LengthExc();
            }
            for (i = 0; i < volume.length; i++) {
                liquidity = liquidity + ((double) exec[i] * (double) volume[i]);
            }
            liquidity = liquidity / (double) time;
            return liquidity;
        } catch (LengthExc ex) {
            return error;
        }
    }

    /*public double Moving_Average(Object[] time, Object[] data, Object Start_Time, Object End_Time)
     {
     double error = -1.00;
     try {
     if(time.length != data.length) {
     throw new LengthExc();
     }
     for(int i=0;i<time.length;i++) {
     time[i] = (String)time[i];
     }
     int Start_Index = indexOf(time, (String)Start_Time);
     int End_Index = indexOf(time, (String)End_Time);
        
     Object[] Mov_Average = Arrays.copyOfRange(data, Start_Index, End_Index);
        
     //return Calculus.getMean(Mov_Average);
     }
     catch(LengthExc ex) {
     return error;
     }
     }*/
    /*public List<Order> Disparate_Var(OrderBook ord_b) {
        
     List<Order> result = null;
        
     for(int i=0; i < ord_b.getBook().size(); i++) {
     if(ord_b.getBook().get(i).getQuantity()> (ord_b.getMean() + (2 * ord_b.getvar()))) {
     result.add(ord_b.GetOrder(i));
     }
     }
     return result;
     }
    
     public List<Order> Disparate_Quantile(OrderBook ord_b, double quantile) {
        
     List<Order> result = null;
        
     for(int i=0; i < ord_b.getBook().size(); i++) {
     if(ord_b.getBook().get(i).getQuantity()> (ord_b.getQuantile(quantile))) {
     result.add(ord_b.GetOrder(i));
     }
     }
     return result;
     }*/
}
