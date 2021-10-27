/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingMachine.serviceLayer;

/**
 * @author Mehak Singla
 */
public class PettyCashException extends Exception{
    
    public PettyCashException(String message){
        super(message);
    }
    
    public PettyCashException(String message, Throwable cause){
        super(message, cause);
    }

}
