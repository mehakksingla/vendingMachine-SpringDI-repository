/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingMachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Mehak Singla 
 */
public class Item {
    
    private String name, menuKey;
    private int stock;
    private BigDecimal price;

    public Item(String menuKey, String name, String price, String stock) {
        this.menuKey = menuKey;
        this.name = name;
        this.price = new BigDecimal(price);
        this.stock = Integer.parseInt(stock);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.menuKey);
        hash = 71 * hash + this.stock;
        hash = 71 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.stock != other.stock) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.menuKey, other.menuKey)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }
    
    public String getName() {
        return name;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public int getStock() {
        return stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void vend(){
        stock--;
    }

}
    