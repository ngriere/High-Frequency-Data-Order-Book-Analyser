/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Nicolas_2
 */
public class ExecutedOrder extends Order {

    private double price;

    public ExecutedOrder(int date, String type, String orderId, int shares) {
        super(date, type, orderId, shares);

        // Initialise price
        price = 0;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
