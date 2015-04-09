/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Order;

/**
 *
 * @author hugo
 */
public interface Listener {
    
    public void handleNewOrder(Order order);
    
}
