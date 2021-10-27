/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingMachine.serviceLayer;

import com.sg.vendingMachine.dao.ItemPersistenceException;
import com.sg.vendingMachine.dto.Change;
import com.sg.vendingMachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Mehak Singla
 */

public interface vendingMachineServiceLayer {
   
    public boolean setInsertedMoney(BigDecimal money) throws PettyCashException, InvalidInputException, ItemPersistenceException;
    
    public Change vendItem(String choice) throws InsufficientFundsException, ZeroInventoryException, ItemPersistenceException;
    
    public Change returnMoney() throws ItemPersistenceException;
   
    public List<Item> getAllItems() throws ItemPersistenceException;
    
    public void loadInventory() throws ItemPersistenceException;
    
    public void writeInventory() throws ItemPersistenceException;
   
}
    
