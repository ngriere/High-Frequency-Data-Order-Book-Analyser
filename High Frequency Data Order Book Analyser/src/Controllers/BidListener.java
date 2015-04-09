/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Models.DataOrderBook;

/**
 *
 * @author Nicolas_2
 */
public interface BidListener {
    
    public void handleBestBid(DataOrderBook data);
    
}
