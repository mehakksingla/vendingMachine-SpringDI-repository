/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingMachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 * @author Mehak Singla
 */
@Component
public class UserIOConsoleImpl implements UserIO {
    
    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.print(msg);
    }
    
    @Override
    public void println(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        Double d = sc.nextDouble();
        return d;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        Double d;
        do{
            System.out.println(prompt);
            d= Double.parseDouble(sc.nextLine());

            if (d > max || d< min){
                System.out.println("Your entry was not within the specified bounds."); 
            }
        }while(d > max || d< min);
         
        return d;
       
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float f = sc.nextFloat();
        return f;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
       Float f;
       do{
           System.out.println(prompt);
           f = Float.parseFloat(sc.nextLine());
           if(f>max || f<min){
               System.out.println("Your values are not within range.");
           }
       }while(f>max || f<min);
       return f;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int i = sc.nextInt();
        return i;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
       Integer i;
       do{
           System.out.println(prompt);
           i = Integer.parseInt(sc.nextLine());
           if(i>max || i<min){
               System.out.println("Your values are not within range.");
           }
       }while(i>max || i<min);
       return i;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long lg = sc.nextLong();
        return lg;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        Long lg;
        do{
            System.out.println(prompt);
            lg = Long.parseLong(sc.nextLine());
            
            if(lg>max || lg<min){
                System.out.println("Your values are not within range.");
            }
        }while(lg>max || lg<min);
        return lg;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String str = sc.nextLine();
        return str;
    }

    @Override
    public BigDecimal readCurrency(String prompt) {
        System.out.println(prompt);
        BigDecimal bd = sc.nextBigDecimal();
        return bd;      
    }

}
