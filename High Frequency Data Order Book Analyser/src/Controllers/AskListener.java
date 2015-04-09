/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Models.DataOrderBook;

/**
 *
 * @author bachir
 */
public interface AskListener {
    public void handleBestAsk(DataOrderBook data);
    
}
