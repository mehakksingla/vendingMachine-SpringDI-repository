/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingMachine.dao;

import com.sg.vendingMachine.dto.Change;
import com.sg.vendingMachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 * @author Mehak Singla
 */
@Component
public class vendingMachineDaoFileImpl implements vendingMachineDao {
    private final String ITEM_FILE;
    private static final String DELIMITER ="::";
    private BigDecimal insertedMoney;
    private HashMap<String, Item> inventory;

    public vendingMachineDaoFileImpl(){
        ITEM_FILE = "inventory.txt";
        inventory = new HashMap<>();
    }
    
    public vendingMachineDaoFileImpl(String ItemTextFile) { //For testing purposes
        ITEM_FILE = ItemTextFile;
        inventory = new HashMap<>();
    }
    
    @Override
    public void setInsertedMoney(BigDecimal money) {
        this.insertedMoney = money;
    }

    @Override
    public BigDecimal returnInsertedMoney() {
        BigDecimal bd = this.insertedMoney;
        this.insertedMoney.equals(0);
        return bd;
    }

    @Override
    public Item getItem(String menuKey) {
        return inventory.get(menuKey);
    }

    @Override
    public void vendItem(String choice) {
        this.getItem(choice).vend();
    }

    @Override
    public Change calculateChange(String choice) {
        BigDecimal bd = this.insertedMoney;
        this.insertedMoney.equals(0);
        return new Change(bd.subtract(getItem(choice).getPrice()));
    }
    
    public Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        Item itemFromFile = new Item(itemTokens[0], itemTokens[1], itemTokens[2], itemTokens[3]);
        return itemFromFile;
    }
   
    public String marshallItem(Item item){
        String itemText = item.getMenuKey()+ DELIMITER 
                        + item.getName() + DELIMITER 
                        + item.getPrice() + DELIMITER 
                        + item.getStock();
        return itemText;
    }

    @Override
    public void loadInventory() throws ItemPersistenceException {
        Scanner scanner;
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(ITEM_FILE)));
        }
        catch(FileNotFoundException e){
            throw new ItemPersistenceException("Couldn't load items into inventory");
        }
        
        String currentLine;
        Item currentItem;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);

            inventory.put(currentItem.getMenuKey(), currentItem);
        }
        
        scanner.close();
    }

    @Override
    public void writeInventory() throws ItemPersistenceException {
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        }
        catch(IOException e){
            throw new ItemPersistenceException("Could not save inventory data.");
        }
        
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(inventory.values());
    }

    @Override
    public BigDecimal getInsertedmoney() {
        return insertedMoney;
    }
    
}