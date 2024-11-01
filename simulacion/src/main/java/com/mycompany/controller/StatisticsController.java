/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.StatisticsModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.view.StatisticsView;

/**
 *
 * @author aris-
 */
public class StatisticsController implements ActionListener{
    private StatisticsModel statisticsModel;
    private StatisticsView statisticsView = new StatisticsView();
    private StartupController startupController;
    private SimulationController simulationController;

    public StatisticsController(StartupController startupController, SimulationController simulationController) {
        this.startupController = startupController;
        this.statisticsView.homeBt.addActionListener(this);
        this.simulationController = simulationController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(statisticsView.homeBt))
        {
            closeView();
            startupController.show();
        }
    }

    public void show()
    {
        simulationController.closeView();
        statisticsModel.setStatisticsView(statisticsView);
        statisticsModel.addRows();
        statisticsView.setVisible(true);
        statisticsModel.createLineChart();
        
    }
    
    public void closeView()
    {
        statisticsView.dispose();
    }
    
    public void setStatisticsModel(StatisticsModel statisticsModel)
    {
        this.statisticsModel = statisticsModel;
    }
}
