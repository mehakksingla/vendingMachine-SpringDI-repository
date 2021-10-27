/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.vendingMachine;

import com.sg.vendingMachine.Controller.vendingMachineController;
import com.sg.vendingMachine.dao.vendingMachineAuditDao;
import com.sg.vendingMachine.dao.vendingMachineAuditDaoFileImpl;
import com.sg.vendingMachine.dao.vendingMachineDao;
import com.sg.vendingMachine.dao.vendingMachineDaoFileImpl;
import com.sg.vendingMachine.serviceLayer.vendingMachineServiceLayer;
import com.sg.vendingMachine.serviceLayer.vendingMachineServiceLayerImpl;
import com.sg.vendingMachine.ui.UserIO;
import com.sg.vendingMachine.ui.UserIOConsoleImpl;
import com.sg.vendingMachine.ui.vendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Mehak Singla
 */
public class App {
    

     public static void main(String[] args) {
//        UserIO myIo = new UserIOConsoleImpl();
//        vendingMachineView myView = new vendingMachineView(myIo);
//        vendingMachineDao myDao = new vendingMachineDaoFileImpl();
//        vendingMachineAuditDao myAuditDao = new vendingMachineAuditDaoFileImpl();
//        vendingMachineServiceLayer myService = new vendingMachineServiceLayerImpl(myDao, myAuditDao);
//        vendingMachineController controller = new vendingMachineController(myService, myView);
//        controller.run();

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.sg.vendingMachine");
        appContext.refresh();
        
        vendingMachineController controller = appContext.getBean("vendingMachineController", vendingMachineController.class);
        controller.run();
        
    }   

}
