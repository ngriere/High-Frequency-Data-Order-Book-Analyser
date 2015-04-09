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
public class AssetInfo {

    String reference;
    String nameFile;
    String sideIndicator;
    double price;
    int nbShares;

    public AssetInfo() {

    }

    public AssetInfo(String orderId, String symbol, int nbShares, String sideIndicator, double price) {
        this.reference = orderId;
        this.nameFile = symbol;
        this.nbShares = nbShares;
        this.sideIndicator = sideIndicator;
        this.price = price;
    }

    public String getReference() {
        return this.reference;
    }

    public String getSideIndicator() {
        return this.sideIndicator;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getSymbol() {
        return this.nameFile;
    }

    public int getNbShares() {
        return this.nbShares;
    }

    public void setNbShares(int nbSharesLeft) {
        this.nbShares = nbSharesLeft;
    }
}