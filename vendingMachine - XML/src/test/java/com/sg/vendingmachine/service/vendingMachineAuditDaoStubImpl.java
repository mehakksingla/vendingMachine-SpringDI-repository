/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingmachine.service;

import com.sg.vendingMachine.dao.ItemPersistenceException;
import com.sg.vendingMachine.dao.vendingMachineAuditDao;

/**
 * @author Mehak Singla
 */
public class vendingMachineAuditDaoStubImpl implements vendingMachineAuditDao{

    @Override
    public void writeAuditEntry(String entry) throws ItemPersistenceException {

    }

}
