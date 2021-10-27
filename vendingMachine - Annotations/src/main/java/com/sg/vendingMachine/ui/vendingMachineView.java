/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingMachine.ui;

import com.sg.vendingMachine.dto.Change;
import com.sg.vendingMachine.dto.Item;
import com.sg.vendingMachine.serviceLayer.InvalidInputException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mehak Singla
 */
@Component
public class vendingMachineView {
    
    
    private UserIO io;
    private static final int MONEY_SCALE = 2;

    @Autowired
    public vendingMachineView(UserIO io) {
        this.io = io;
    }

    public int getMainMenuChoice() {
        io.println("\n=====PLEASE CHOOSE ONE OPTION=====");
        io.println("1. Make a purchase");
        io.println("2. List All Items");
        io.println("3. Exit");
        return io.readInt("Select one option: ", 1,3);
    }
    
    public BigDecimal inputMoney() throws InvalidInputException {
        try{
            return new BigDecimal(io.readString("Insert Money: ")).setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        }
        catch(NumberFormatException e){
            throw new InvalidInputException("That is not a valid numerical value");
        }
    }
    
    public void displayErrorMessage(String message) {
        io.println(message);
    }
    
    public void displayItemMenuChoices(List<Item> allItems) {
        String itemString, itemHeader;
        itemHeader = String.format("%-15s%-20s%-10s%-10s\n", "Option", "Item Name", "Price", "Stock");
        //Format a string with the specified number of characters and also left justify.
        io.print("=====VENDING OPTIONS======\n");
        io.println(itemHeader);
        for(Item item : allItems) {
            itemString = String.format("%-15s%-20s$%-10s%-10s\n", item.getMenuKey(), item.getName(), item.getPrice(), item.getStock());
            io.print(itemString);
        }
//        io.readString("Please hit enter to continue");
    }
    
    public String getItemMenuChoice() {
        String choice = io.readString("Select an item from the vending options:");
        return choice.toUpperCase();
    }
    
    public void displayChange(Change change) {
        io.println(change.toString());
        io.readString("Please hit enter to continue");
    }
    
    public void displayVended() {
        io.println("\nPLEASE COLLECT YOUR ITEM FROM THE MACHINE. "
                + "\nTHANK YOU FOR YOUR PURCHASE!");
    }
    
    public void displayRefund(Change change) {
        io.println("$ " + change.getAmount() + " has been refunded to you.");
        io.readString("Please hit enter to continue");
    }
    
    public void displayItemList(List<Item> allItems){
        String itemString;
        for(Item item : allItems) {
            itemString = String.format("%-15s%-20s$%-10s%-10s\n", item.getMenuKey(), item.getName(), item.getPrice(), item.getStock());
            io.print(itemString);
        }
        io.readString("Please hit enter to continue");
    }
    
    public void displayExitBanner(){
        io.print("Good Bye!!!");
    }
}
