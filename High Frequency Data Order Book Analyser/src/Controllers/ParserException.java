/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

/**
 *
 * @author hugo
 */
public class ParserException extends Exception {

    public ParserException(){
        super();
    }

    public ParserException(String message){
        super(message);
    }

    public ParserException(Throwable cause){
        super(cause);
    }

    public ParserException(String message, Throwable cause){
        super(message, cause);
    }
}
