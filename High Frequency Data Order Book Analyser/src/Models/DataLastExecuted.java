/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author bachir
 */
public class DataLastExecuted {

    private String timestamp;
    private double price;
    private int shares;

    public DataLastExecuted(String timestamp, double price, int shares) {

        this.timestamp = timestamp;
        this.price = price;
        this.shares = shares;

    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }


    


}
