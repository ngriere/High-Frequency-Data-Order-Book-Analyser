/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.text.*;
import java.util.Date;

/**
 *
 * @author vinujamaniccavasagam
 */
public class AddOrder extends Order {

    private final String symbol;
    private final NumberFormat priceFormat = new DecimalFormat("000000.0000");
    private double price;
    private final String sideIndicator;

    public AddOrder(int date, String type, String orderId,
            int sharesQuantity, String symbol, double price, String sideIndicator) {
        super(date, type, orderId, sharesQuantity);
        this.symbol = symbol;
        this.price = price;
        this.sideIndicator = sideIndicator;
    }

    public double getPrice() {
        return price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSideIndicator() {
        return sideIndicator;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getShares() {
        return this.shares;
    }
}
