/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import com.mycompany.controller.SimulationController;

/**
 *
 * @author aris-
 */
public class PatientsQueue {

    private Queue head;
    private Queue queue;
    //Llevar el conteo de pacientes en cola y el tipo de paciente
    private int ba =0;
    private int wa =0;
    Simulation simulation;

    public PatientsQueue(Simulation simulation) {
        this.simulation = simulation;
    }

    public class Queue {

        private Queue next;
        private final PatientCare patient;

        public Queue(PatientCare patient) {
            this.patient = patient;
        }

        public Queue getNext() {
            return next;
        }

        public void setNext(Queue next) {
            this.next = next;
        }

        public PatientCare getPatient() {
            return patient;
        }
        
    }

    public void add(PatientCare patientCare) {
        if (patientCare.getPatientType().equals("wa")) {
            this.wa++;
            System.out.println("agregando wa "+wa);
        } else {
            System.out.println("agregando ba "+ba);
            this.ba++;
        }

        Queue patient = new Queue(patientCare);
        if (head == null) {
            head = patient;
            queue = patient;
        } else {
            queue.next = patient;
            queue = patient;
        }
        updateWaiting();
    }

    public void dequeue(String patientType) {
        Queue temp = head;
        if (temp != null && temp.patient.getPatientType().equals(patientType)) {
            head = head.next;
            if (patientType.equals("wa")) {
                this.wa--;
                System.out.println("agregando wa "+wa);
                simulation.addNWiPwT();
                simulation.addWIpWt(sumTime(temp.patient));
            } else {
                this.ba--;
                System.out.println("agregando ba "+ba);
                simulation.addNPwTwA();
                simulation.addPWtWa(sumTime(temp.patient));
            }
            updateWaiting();
            Simulation.getExecutor().execute(temp.patient);
        } else if (temp != null) {
            while (temp.next != null) {
                if (temp.next.patient.getPatientType().equals(patientType)) {
                    if (patientType.equals("wa")) {
                        this.wa--;
                        simulation.addNWiPwT();
                        simulation.addWIpWt(sumTime(temp.patient));
                    } else {
                        this.ba--;
                        simulation.addNPwTwA();
                        simulation.addPWtWa(sumTime(temp.patient));
                    }
                    Simulation.getExecutor().execute(temp.next.patient);
                    temp.next = temp.next.next;
                    updateWaiting();
                    break;
                } else {
                    temp = temp.next;
                }
            }
        }
    }

    public void updateWaiting() {
        SimulationController.getSimulationView().waiting.setText(this.ba + "                 " + (this.ba + this.wa) + "                 " + this.wa);
    }

    public float sumTime(PatientCare patientCare) {
        String[] reloj = SimulationController.getSimulationView().reloj.getText().split(":");
        int hour = Integer.parseInt(reloj[0]) - patientCare.getHour()[0];
        int minutes = Integer.parseInt(reloj[1]) - patientCare.getHour()[1];
        float seconds = Integer.parseInt(reloj[2]) / 3600;
        if (minutes < 0) {
            hour--;
            minutes = 60 - minutes;
        }
        hour = hour * 60;
        return hour + minutes + seconds;
    }

    public Queue getHead() {
        return head;
    }

    public void setHead(Queue head) {
        this.head = head;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public int getBa() {
        return ba;
    }

    public void setBa(int ba) {
        this.ba = ba;
    }

    public int getWa() {
        return wa;
    }

    public void setWa(int wa) {
        this.wa = wa;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public void resetQueue()
    {
        this.head = null;
        this.queue = null;
        this.ba = 0;
        this.wa = 0;
    }
    
}
