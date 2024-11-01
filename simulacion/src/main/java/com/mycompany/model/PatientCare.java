/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import com.mycompany.controller.SimulationController;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aris-
 */
public class PatientCare implements Runnable {

    private int attentionTime;
    private static AtomicInteger pBa = new AtomicInteger(0);
    private static AtomicInteger wIP = new AtomicInteger(0);
    private static AtomicInteger finished = new AtomicInteger(0);
    private String patientType;
    private static AtomicInteger attending = new AtomicInteger(0);
    private int hour[];

    public PatientCare(int attentionTime, String patientType, int[] hour) {
        this.attentionTime = attentionTime;
        this.patientType = patientType;
        this.hour = hour;
    }

    @Override
    public void run() {
        ReentrantLock reentrantLock = new ReentrantLock();

        reentrantLock.lock();
        try {
            if (patientType.equals("wa")) {
                SimulationController.getSimulationView().walkIn_Patients.setText("" + PatientCare.wIP.incrementAndGet());
            } else {
                SimulationController.getSimulationView().patientsByAppointment.setText("" + PatientCare.pBa.incrementAndGet());
            }
            SimulationController.getSimulationView().attending.setText("" + PatientCare.attending.incrementAndGet());
        } finally {
            reentrantLock.unlock();
        }

        try {
            for (int m = 0; m < this.attentionTime; m++) {
                for (int s = 0; s < 60; s++) {
                    if (Thread.currentThread().isInterrupted()) { // Verificación de interrupción
                        System.out.println("Tarea interrumpida");
                        return; // Finaliza el método para salir del hilo
                    }
                    Thread.sleep(Simulation.getMilliseconds());
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restablece el estado de interrupción
            System.out.println("Tarea terminada por interrupción");
            return; // Finaliza el método
        }

        reentrantLock.lock();
        try {
            if (patientType.equals("wa")) {
                SimulationController.getSimulationView().walkIn_Patients.setText("" + PatientCare.wIP.decrementAndGet());
            } else {
                SimulationController.getSimulationView().patientsByAppointment.setText("" + PatientCare.pBa.decrementAndGet());
            }
            SimulationController.getSimulationView().finished.setText("" + PatientCare.finished.incrementAndGet());
            SimulationController.getSimulationView().attending.setText("" + PatientCare.attending.decrementAndGet());
        } finally {
            reentrantLock.unlock();
        }
    }

    public String getPatientType() {
        return patientType;
    }

    public static int getPBa() {
        return PatientCare.pBa.intValue();
    }

    public static int getWiP() {
        return PatientCare.wIP.intValue();
    }

    //total de atencion
    public static int getAt() {
        return PatientCare.pBa.intValue() + PatientCare.wIP.intValue();
    }

    public int[] getHour() {
        return hour;
    }

    public static void resetFinished() {
        PatientCare.finished = new AtomicInteger(0);
    }

    public static void resetPBa() {
        PatientCare.pBa = new AtomicInteger(0);
    }

    public static void resetWIP() {
        PatientCare.wIP = new AtomicInteger(0);
    }

    public static void resetAttending() {
        PatientCare.attending = new AtomicInteger(0);
    }

}
