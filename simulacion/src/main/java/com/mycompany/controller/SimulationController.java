/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Simulation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.view.SimulationView;

/**
 *
 * @author aris-
 */
public class SimulationController implements ActionListener{
    
    private static SimulationView simulationView = new SimulationView();;
    private Simulation simulation;
    
    public SimulationController(Simulation simulation)
    {
        this.simulation = simulation;
        simulationView.velocidadBt.addActionListener(this);
    }
    
    public void show()
    {
        simulationView.setVisible(true);
        new Thread(simulation).start();
    }
    
    public void closeView()
    {
        simulationView.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(simulationView.velocidadBt))
        {
            Simulation.setMilliseconds(Integer.parseInt(simulationView.velocidadInt.getText()));
        }
    }
    
    public static SimulationView getSimulationView()
    {
        return SimulationController.simulationView;
    }
}
