/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author vinujamaniccavasagam
 */
public interface DecryptingValues {

    public final static String AddType = "A";
    public final static String ExecuteType = "E";
    public final static String CancelType = "X";
    
    public final static String SideIndicatorBuy = "B";
    public final static String SideIndicatorSell = "S";

    public final static int DateBegin = 1;
    public final static int DateEnd = 9;

    public final static int MessageTypeBegin = 9;
    public final static int MessageTypeEnd = 10;

    public final static int OrderBegin = 10;
    public final static int OrderEnd = 22;

    // ADD
    public final static int AddSideIndicatorBegin = 22;
    public final static int AddSideIndicatorEnd = 23;
    
    public final static int AddSharesBegin = 23;
    public final static int AddSharesEnd = 29;

    public final static int AddSymbolBegin = 29;
    public final static int AddSymbolEnd = 35;

    public final static int AddPriceBegin = 35;
    public final static int AddPriceSeparation = 41;
    public final static int AddPriceEnd = 45;

    // EXECUTE
    public final static int ExeSharesBegin = 22;
    public final static int ExeSharesEnd = 28;

    public final static int ExeIdBegin = 29;
    public final static int ExeIdEnd = 41;

    // CANCEL
    public final static int CancelSharesBegin = 22;
    public final static int CancelSharesEnd = 28;

}
