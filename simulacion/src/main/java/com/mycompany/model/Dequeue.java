/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author aris-
 */
public class Dequeue implements Runnable{

    String patientType;
    PatientsQueue queue;

    public Dequeue(String patientType, PatientsQueue queue) {
        this.patientType = patientType;
        this.queue = queue;
    }

    @Override
    public void run() {
        PatientsQueue.Queue temp = queue.getHead();
        ReentrantLock reentrantLock = new ReentrantLock();
        if (temp != null && temp.getPatient().getPatientType().equals(patientType)) {
            queue.setHead(temp.getNext());
            if (patientType.equals("wa")) {
                reentrantLock.lock();
                queue.setWa(queue.getWa()-1);
                reentrantLock.unlock();
                System.out.println(queue.getWa());
                queue.getSimulation().addNWiPwT();
                queue.getSimulation().addWIpWt(queue.sumTime(temp.getPatient()));
                reentrantLock.unlock();
            } else {
                reentrantLock.lock();
                queue.setBa(queue.getBa()-1);
                System.out.println(queue.getBa());
                queue.getSimulation().addNPwTwA();
                queue.getSimulation().addPWtWa(queue.sumTime(temp.getPatient()));
                reentrantLock.unlock();
            }
            queue.updateWaiting();
            Simulation.getExecutor().execute(temp.getPatient());
        } else if (temp != null) {
            while (temp.getNext() != null) {
                if (temp.getNext().getPatient().getPatientType().equals(patientType)) {
                    if (patientType.equals("wa")) {
                        reentrantLock.lock();
                        queue.setWa(queue.getWa()-1);
                        queue.getSimulation().addNWiPwT();
                        queue.getSimulation().addWIpWt(queue.sumTime(temp.getPatient()));
                        reentrantLock.unlock();
                    } else {
                        reentrantLock.lock();
                        queue.setBa(queue.getBa()-1);
                        queue.getSimulation().addNPwTwA();
                        queue.getSimulation().addPWtWa(queue.sumTime(temp.getPatient()));
                        reentrantLock.unlock();
                    }
                    Simulation.getExecutor().execute(temp.getNext().getPatient());
                    temp.setNext(temp.getNext().getNext());
                    queue.updateWaiting();
                    break;
                } else {
                    temp = temp.getNext();
                }
            }
        }
    }
    
}
