/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author bachir
 */
public class Ask extends AddOrder {

    public Ask(int date, String type, String orderId,
            String sideIndicator, int sharesQuantity, String symbol,
            double price) {
        super(date, type, orderId, sharesQuantity, symbol, price, sideIndicator);

    }
}
