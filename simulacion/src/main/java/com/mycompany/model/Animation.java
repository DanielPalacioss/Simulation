/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import com.mycompany.controller.SimulationController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aris-
 */
public class Animation implements Runnable{

    @Override
    public void run() {
        try {
            SimulationController.getSimulationView().punto.setSelected(false);
            SimulationController.getSimulationView().punto1.setSelected(false);
            SimulationController.getSimulationView().punto2.setSelected(false);
            SimulationController.getSimulationView().punto3.setSelected(false);
            SimulationController.getSimulationView().punto4.setSelected(false);
            SimulationController.getSimulationView().punto5.setSelected(false);
            SimulationController.getSimulationView().punto6.setSelected(false);
            SimulationController.getSimulationView().punto7.setSelected(false);
            
            SimulationController.getSimulationView().punto.setSelected(true);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto.setSelected(false);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto1.setSelected(true);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto1.setSelected(false);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto2.setSelected(true);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto2.setSelected(false);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto3.setSelected(true);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto3.setSelected(false);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto4.setSelected(true);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto4.setSelected(false);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto5.setSelected(true);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto5.setSelected(false);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto6.setSelected(true);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto6.setSelected(false);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto7.setSelected(true);
            Thread.sleep(250);
            SimulationController.getSimulationView().punto7.setSelected(false);
            Thread.sleep(250);
        } catch (InterruptedException ex) {
            Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
