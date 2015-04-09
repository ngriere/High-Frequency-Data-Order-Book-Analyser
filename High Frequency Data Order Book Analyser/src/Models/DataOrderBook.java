/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author hugo
 */
public class DataOrderBook {
    
    private double price;
    private int volume;
    private int time;

    public DataOrderBook(double price, int volume, int time) {
        this.price = price;
        this.volume = volume;
        this.time=time;
    }

    public double getPrice() {
        return price;
    }
    public int getTime() {
        return time;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }    
}
