/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Models.DataLastExecuted;
/**
 *
 * @author bachir
 */
public interface LastListener {
    public void handleBestLast(int time, double  Price, int volume);
}
