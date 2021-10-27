/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingMachine.dao;

/**
 * @author Mehak Singla
 */
public class ItemPersistenceException extends Exception{
    
    public ItemPersistenceException(String message){
        super(message);
    }
    
    public ItemPersistenceException(String message, Throwable cause){
        super(message, cause);
    }

}
