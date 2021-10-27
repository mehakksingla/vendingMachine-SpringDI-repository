/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingMachine.serviceLayer;

import com.sg.vendingMachine.dao.vendingMachineAuditDao;
import com.sg.vendingMachine.dao.ItemPersistenceException;
import com.sg.vendingMachine.dao.vendingMachineDao;
import com.sg.vendingMachine.dto.Change;
import com.sg.vendingMachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mehak Singla
 */
@Component
public class vendingMachineServiceLayerImpl implements vendingMachineServiceLayer{
    
    private final vendingMachineDao dao;
    private final vendingMachineAuditDao auditDao;

    @Autowired
    public vendingMachineServiceLayerImpl(vendingMachineDao dao, vendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
   
    @Override
    public boolean setInsertedMoney(BigDecimal money) throws PettyCashException, InvalidInputException, ItemPersistenceException {
    
    if(money.compareTo(new BigDecimal("5")) > 0){
        auditDao.writeAuditEntry("USER INSERTED TOO MUCH MONEY, PETTY CASH EXCEPTION THROWN, AMOUNT RETURNED TO USER");
        throw new PettyCashException("Machine doesn't accept more than $5\n");
    }
    else if(money.compareTo(new BigDecimal("0")) < 0){
        auditDao.writeAuditEntry("USER INSERTED NEGATIVE MONEY, INVALID INPUT EXCEPTION THROWN, VEND DENIED");
        throw new InvalidInputException("Invalid amount of money inserted.\n");
    }
    //call dao setInsertMoney
    dao.setInsertedMoney(money);
    auditDao.writeAuditEntry("USER INSERTED " + dao.getInsertedmoney());
    return true;
    }

    @Override
    public Change vendItem(String choice) throws InsufficientFundsException, ZeroInventoryException, ItemPersistenceException {
        //Using choice, check price of choice <= dao.insertedMoney
        if(dao.returnInsertedMoney().compareTo(dao.getItem(choice).getPrice()) < 0){
            auditDao.writeAuditEntry("USER DIDN'T INSERT ENOUGH FUNDS, INSUFFICIENT FUNDS EXCEPTION THROWN");
            throw new InsufficientFundsException("Not enough money inserted to purchase an item.\n");
        }
        else if(dao.getItem(choice).getStock() == 0){
            auditDao.writeAuditEntry("NO STOCK FOR REQUESTED ITEM, NO ITEM INVENTORY EXCEPTION THROWN");
            throw new ZeroInventoryException("Item is currently out of Stock.\n");
        }
        else{
            dao.vendItem(choice);
            auditDao.writeAuditEntry("ITEM " + dao.getItem(choice).getName() + " VENDED, CALCULATING AND RETURNING CHANGE");
            return dao.calculateChange(choice);
        }
    }

    @Override
    public Change returnMoney() throws ItemPersistenceException {
        auditDao.writeAuditEntry("RETURNING MONEY TO USER");
        return new Change(dao.returnInsertedMoney());
    }

    @Override
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public void loadInventory() throws ItemPersistenceException {
        try{
            dao.loadInventory();
            auditDao.writeAuditEntry("INVENTORY LOADED FROM THE TEXT FILE");
        }
        catch(ItemPersistenceException e){
            throw e;
        }
    }

    @Override
    public void writeInventory() throws ItemPersistenceException {
        try{
            dao.writeInventory();
            auditDao.writeAuditEntry("INVENTORY WRITTEN TO THE TEXT FILE");
        }
        catch(ItemPersistenceException e){
            throw e;
        }
    }

}
