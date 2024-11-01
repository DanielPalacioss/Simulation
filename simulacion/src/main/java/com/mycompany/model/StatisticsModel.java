/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import com.mycompany.view.StatisticsView;
import java.awt.BasicStroke;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author aris-
 */
public class StatisticsModel {

    private StatisticsView statisticsView;
    private Object[][] stats;
            
    public StatisticsModel(Object[][] stats)
    {
        this.stats = stats;
    }

    public void addRows() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) statisticsView.Stats.getModel(); 
        for (int i = 0; i < stats.length; i++) {
            defaultTableModel.addRow(stats[i]);
        }
    }

    public void setStatisticsView(StatisticsView statisticsView) {
        this.statisticsView = statisticsView;
    }
    
    // Método para crear la gráfica de líneas
    public void createLineChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Llena el dataset con los datos de stats (excluyendo la última fila "Total")
        for (int i = 0; i < stats.length - 1; i++) {
            String category = "Día " + stats[i][0];
            dataset.addValue((Number) stats[i][1], "Average waiting time for people with appointments", category);
            dataset.addValue((Number) stats[i][2], "Average waiting time for walk-ins", category);
            dataset.addValue((Number) stats[i][3], "Average time for people to be seen by appointment", category);
            dataset.addValue((Number) stats[i][4], "Average time of people to be attended without an appointment", category);
            dataset.addValue((Number) stats[i][5], "Average time for people to arrive with an appointment", category);
            dataset.addValue((Number) stats[i][6], "Average time of people arriving without an appointment", category);
        }

        // Crear el gráfico
        JFreeChart lineChart = ChartFactory.createLineChart(
                "line graph", // Título del gráfico
                "Days",               // Etiqueta del eje X
                "Averages",              // Etiqueta del eje Y
                dataset,              // Dataset
                PlotOrientation.VERTICAL,
                true,                 // Mostrar leyenda
                true,                 // Mostrar tooltips
                false                 // URL
        );
        
        // Obtén el plot del gráfico y establece un renderizador para hacer las líneas más gruesas
        CategoryPlot plot = lineChart.getCategoryPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();

        // Ajusta el grosor de cada serie
        float lineWidth = 2.5f; // Grosor de las líneas
        for (int i = 0; i < dataset.getRowCount(); i++) {
            renderer.setSeriesStroke(i, new BasicStroke(lineWidth));
        }
        
        plot.setRenderer(renderer);

        // Crear un panel para el gráfico y añadirlo a una ventana
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        JFrame frame = new JFrame();
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
