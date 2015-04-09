/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author GE
 */
public class Order {

    protected final int date;
    protected final String type;
    protected final String orderId;
    protected final int shares;

    public Order(int date, String type, String orderId, int shares) {
        this.date = date;
        this.type = type;
        this.orderId = orderId;
        this.shares = shares;
    }

    public String getType() {
        return this.type;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public int getDate() {
        return this.date;
    }

    public int getShares() {
        return this.shares;
    }
}
