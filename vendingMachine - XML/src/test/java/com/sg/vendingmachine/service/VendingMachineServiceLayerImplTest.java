/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingmachine.service;

import com.sg.vendingMachine.serviceLayer.InsufficientFundsException;
import com.sg.vendingMachine.dao.ItemPersistenceException;
import com.sg.vendingMachine.serviceLayer.ZeroInventoryException;
import com.sg.vendingMachine.dao.vendingMachineAuditDao;
import com.sg.vendingMachine.dao.vendingMachineDao;
import com.sg.vendingMachine.dto.Change;
import com.sg.vendingMachine.serviceLayer.InvalidInputException;
import com.sg.vendingMachine.serviceLayer.PettyCashException;
import com.sg.vendingMachine.serviceLayer.vendingMachineServiceLayer;
import com.sg.vendingMachine.serviceLayer.vendingMachineServiceLayerImpl;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Mehak Singla
 */
public class VendingMachineServiceLayerImplTest {
    vendingMachineServiceLayer service;

    public VendingMachineServiceLayerImplTest() {
//        vendingMachineDao dao = new vendingMachineDaoStubImpl();
//        vendingMachineAuditDao auditDao = new vendingMachineAuditDaoStubImpl();
//        service = new vendingMachineServiceLayerImpl (dao, auditDao);

        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = appContext.getBean("serviceLayer", vendingMachineServiceLayer.class);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSetInsertedMoneyOverPay() {
        try{
            service.setInsertedMoney(new BigDecimal("5.50"));
        }
        catch(PettyCashException | InvalidInputException | ItemPersistenceException e){
            String expected = "Machine doesn't accept more than $5\n";
            assertEquals(e.getMessage(), expected);
        }
    }
    
    @Test
    public void testSetInsertedMoneyInvalid() {
        try{
            service.setInsertedMoney(new BigDecimal("-1"));
        }
        catch(PettyCashException | InvalidInputException | ItemPersistenceException e){
            String expected = "Invalid amount of money inserted.\n";
            assertEquals(e.getMessage(), expected);
        }
    }
    
    
    @Test
    public void testSetInsertedMoneyValid() {
        boolean valid = false;
        try{
            valid = service.setInsertedMoney(new BigDecimal("4.50"));
        }
        catch(PettyCashException | InvalidInputException | ItemPersistenceException e){
            fail();
        }
        finally{
            assertTrue(valid);
        }
    }
    
    @Test
    public void testVendItemNotEnoughMoney() {
        try{
            service.setInsertedMoney(new BigDecimal(".50"));
            service.vendItem("A");
        }
        catch(InsufficientFundsException | ItemPersistenceException | ZeroInventoryException | InvalidInputException | PettyCashException e){
            String expected = "Not enough money inserted to purchase an item.\n";
            assertEquals(e.getMessage(), expected);
        }
    }
    
    @Test
    public void testVendItemOutOfStock() {
        try{
            service.setInsertedMoney(new BigDecimal("4.50"));
            service.vendItem("C");
        }
        catch(InsufficientFundsException | ItemPersistenceException | ZeroInventoryException | InvalidInputException | PettyCashException e){
            String expected = "Item is currently out of Stock.\n";
            assertEquals(e.getMessage(), expected);
        }
    }
    
    @Test
    public void testVendItemSuccess() {
        Change change = null;
        try{
            service.setInsertedMoney(new BigDecimal("4.50"));
            change = service.vendItem("A");
        }
        catch(InsufficientFundsException | ItemPersistenceException | ZeroInventoryException | InvalidInputException | PettyCashException e){
            fail();
        }
        finally{
            assertNotNull(change);
        }
    }    
    
    @Test
    public void testReturnMoney() {
        Change change = null;
        try{
            service.setInsertedMoney(new BigDecimal("4.50"));
            change = service.returnMoney();
        }
        catch(ItemPersistenceException | InvalidInputException | PettyCashException e){
            fail();
        }
        finally{
            assertNotNull(change);
            assertEquals(change.getAmount(), new BigDecimal("4.50"));
        }
    }    
    
}