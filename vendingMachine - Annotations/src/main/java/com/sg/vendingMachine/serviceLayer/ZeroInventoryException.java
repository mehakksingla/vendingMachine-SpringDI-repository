/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingMachine.serviceLayer;

/**
 * @author Mehak Singla
 */
public class ZeroInventoryException extends Exception{
    
    public ZeroInventoryException(String message){
        super(message);
    }
    
    public ZeroInventoryException(String message, Throwable cause){
        super(message, cause);
    }

}
