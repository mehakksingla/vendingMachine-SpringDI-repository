/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingmachine.dao;

import com.sg.vendingMachine.dao.vendingMachineDao;
import com.sg.vendingMachine.dao.vendingMachineDaoFileImpl;
import com.sg.vendingMachine.dto.Change;
import com.sg.vendingMachine.dto.Item;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mehak Singla
 */
public class VendingMachineDaoFileImplTest {
    public vendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest(){
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testInventory.txt";
        testDao =  new vendingMachineDaoFileImpl(testFile);
        testDao.loadInventory();
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetItem() throws Exception {
        assertNotNull(testDao.getItem("A"));
        assertEquals(testDao.getItem("A").getName(), "TestItem1");
        assertEquals(testDao.getItem("A").getMenuKey(), "A");
        assertEquals(testDao.getItem("A").getPrice(), new BigDecimal("3.50"));
        assertEquals(testDao.getItem("A").getStock(), 5);
    }
    
    @Test
    public void testGetAllItems() throws Exception{
        List<Item> itemList = testDao.getAllItems();
        
        assertNotNull(itemList);
        assertEquals(itemList.size(), 3); //Theres 3 test items in inventoryTest.txt
    }
    
    @Test
    public void testVendItem() throws Exception{
        testDao.vendItem("A");
        assertEquals(testDao.getItem("A").getStock(), 4);
    }
    
    @Test
    public void testCalculateChange() throws Exception{
        testDao.setInsertedMoney(new BigDecimal ("4"));
        Change change = testDao.calculateChange("A");
        
        assertNotNull(change);
        assertEquals(change.getAmount(), new BigDecimal(".50"));
        assertEquals(change.getNumOfQuarters(), 2);
        assertEquals(change.getNumOfDimes(), 0);
        assertEquals(change.getNumOfNickels(), 0);
        assertEquals(change.getNumOfPennies(), 0);
    }

}