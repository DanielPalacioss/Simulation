/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.ParametersModel;
import com.mycompany.model.Simulation;
import com.mycompany.view.HomeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author aris-
 */
public class StartupController implements ActionListener{
    
    private HomeView homeView = new HomeView();
    private SimulationController simulationController;
    private StatisticsController statisticsController;
    private Simulation simulation;
    public StartupController()
    {
        homeView.simularBt.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(homeView.simularBt))
        {
            closeView();
            addSimulationModelToController();
            this.statisticsController = new StatisticsController(this, this.simulationController);
            this.simulation.setStatisticsController(statisticsController);
            this.simulationController.show();
        }
    }
    
    public void show()
    {
        homeView.setVisible(true);
    }
    
    public void closeView()
    {
        homeView.dispose();
    }
    
    public void addSimulationModelToController()
    {
        //metodo de validaciones
        ParametersModel parametersModel = new ParametersModel((int)homeView.nOdtaJS.getValue(), (int)homeView.tTpaaJS.getValue(), (int)homeView.mNpaaJS.getValue(), (int)homeView.eTtpawaJS.getValue(), (int)homeView.mNpwaaJS.getValue(), (int)homeView.pCtJS.getValue(), (int)homeView.sDjS.getValue(), (int)homeView.nSdohJS.getValue(), (int)homeView.nSdoh2JS.getValue(), (int)homeView.nSdoh3JS.getValue(), (int)homeView.nSdoh4JS.getValue());
        simulation = new Simulation(parametersModel);
        this.simulationController = new SimulationController(simulation);
    }
}
