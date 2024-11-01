/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import com.mycompany.controller.SimulationController;
import com.mycompany.controller.StatisticsController;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aris-
 */
public class Simulation implements Runnable {

    private static int milliseconds = 1000;
    private final ParametersModel parametersModel;
    //contador del tiempo que le toma llegar a un paciente con cita
    private int tTpaaJS;
    //contador del tiempo que le toma llegar a un paciente sin cita
    private int eTtpawa;
    //tiempo que le toma en llegar a un paciente sin cita
    private int mETtpawa;
    private PatientsQueue patientsQueue;
    private int pCt;
    //maxima atencion por hora de medicos 
    private int maph;
    private Object[][] stats;
    private float wIpWt; //Walk-in patient waiting time
    private int nWiPwT;
    private float pWtWa; //Patient waiting time with appointment
    private int nPwTwA;
    private StatisticsController statisticsController;
    private static ExecutorService executor = Executors.newCachedThreadPool();
    
    public Simulation(ParametersModel parametersModel) {
        this.patientsQueue = new PatientsQueue(this);
        this.parametersModel = parametersModel;
        this.mETtpawa = parametersModel.geteTtpawa();
    }

    @Override
    public void run() {
        stats = new Object[parametersModel.getnOdta()+1][7];
        float[][] total = new float[1][6];
        Random rand = new Random();
        for (int d = 0; d < parametersModel.getnOdta(); d++) {
            SimulationController.getSimulationView().dia.setText("" + d);
            int ntApWaA = 0; //numero total de personas con cita en ser atendida
            float tApWaA = 0; //suma total del tiempo de personas con cita en ser atendida
            int ntApWOaA = 0; //numero total de personas sin cita en ser atendida
            float tApWOaA = 0; //suma total del tiempo de personas sin cita en ser atendida
            int aNpAa = 0; //número medio de personas que llegan con cita previa
            int naNpAa = 0;
            int aNpAwA = 0; //número promedio de personas que llegan sin cita previa
            int naNpAwA = 0;
            for (int h = 8; h <= 17; h++) {
                if (h < 11) {
                    this.maph = parametersModel.getnSdoh();
                } else if (h >= 11 && h < 13) {
                    this.maph = parametersModel.getnSdoh2();
                } else if (h >= 13 && h < 15) {
                    this.maph = parametersModel.getnSdoh3();
                } else {
                    this.maph = parametersModel.getnSdoh4();
                }
                for (int m = 0; m < 60; m++) {
                    if (h < 17) {
                        
                        //crear variable para agregar el tiempo que le toma en atender a un paciente
                        int numA = rand.nextInt(0, 6);
                        if (numA < 4) {
                            this.pCt = parametersModel.getpCt();
                        } else if (numA == 4) {
                            this.pCt = parametersModel.getpCt() + parametersModel.getsD();
                        } else {
                            this.pCt = parametersModel.getpCt() - parametersModel.getsD();
                        }

                        if (this.tTpaaJS < parametersModel.gettTpaa()) {
                            this.tTpaaJS++;
                        } else {
                            aNpAa += this.tTpaaJS;
                            naNpAa++;
                            this.tTpaaJS = 0;
                            int hour[] = {h,m};
                            PatientCare patientCareBa = new PatientCare(this.pCt, "ba", hour);
                            ntApWaA++;
                            tApWaA += this.pCt;
                            if (PatientCare.getPBa() < parametersModel.getmNpaa() && PatientCare.getAt() < this.maph) {
                                executor.execute(patientCareBa);
                            } else {
                                patientsQueue.add(patientCareBa);
                            }
                        }
                        if (this.eTtpawa < this.mETtpawa) {
                            this.eTtpawa++;
                        } else {
                            aNpAwA += eTtpawa;
                            naNpAwA++;
                            this.eTtpawa = 0;
                            int numA2 = rand.nextInt(0, 7);
                            if (numA2 < 5) {
                                this.mETtpawa = parametersModel.geteTtpawa();
                            } else if(numA2 == 5) {
                                this.mETtpawa = parametersModel.geteTtpawa() + numA2;
                            }
                            else 
                            {
                                this.mETtpawa = parametersModel.geteTtpawa() - numA2;
                            }
                            int hour[] = {h,m};
                            PatientCare patientCareWa = new PatientCare(this.pCt, "wa", hour);
                            ntApWOaA++;
                            tApWOaA += this.pCt;
                            if (PatientCare.getWiP() < parametersModel.getmNpwaa() && PatientCare.getAt() < this.maph) {
                                executor.execute(patientCareWa);
                            } else {
                                patientsQueue.add(patientCareWa);
                            }
                        }
                    }
                    else if(m == 20)
                    {
                        break;
                    }

                    for (int s = 0; s < 60; s++) {
                        SimulationController.getSimulationView().reloj.setText(h + ":" + m + ":" + s);
                        if (PatientCare.getPBa() < parametersModel.getmNpaa() && PatientCare.getAt() < this.maph) {
                            //executor.execute(new Dequeue("ba", patientsQueue));
                            patientsQueue.dequeue("ba");
                        }
                        if (PatientCare.getWiP() < parametersModel.getmNpwaa() && PatientCare.getAt() < this.maph) {
                            //executor.execute(new Dequeue("wa", patientsQueue));
                            patientsQueue.dequeue("wa");
                        }
                        try {
                            Thread.sleep(Simulation.milliseconds);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        executor.execute(new Animation());
                }
            }
        
            stats[d][0] = d+1;
            stats[d][1] = this.pWtWa/this.nPwTwA;
            total[0][0] += (float) stats[d][1];
            stats[d][2] = this.wIpWt/this.nWiPwT;
            total[0][1] += (float) stats[d][2];
            stats[d][3] = tApWaA/ntApWaA;
            total[0][2] += (float) stats[d][3];
            stats[d][4] = tApWOaA/ntApWOaA;
            total[0][3] += (float) stats[d][4];
            stats[d][5] = aNpAa/naNpAa;
            total[0][4] += ((Integer) stats[d][5]).floatValue();
            stats[d][6] = aNpAwA/naNpAwA;
            total[0][5] += ((Integer) stats[d][6]).floatValue();
            
            executor.shutdownNow();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println("El executor no terminó a tiempo; forzando el cierre.");
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.err.println("La espera de terminación fue interrumpida.");
            }
            executor = Executors.newCachedThreadPool();
           
            PatientCare.resetFinished();
            SimulationController.getSimulationView().waiting.setText(0 + "                 " + 0 + "                 " + 0);
            this.patientsQueue.resetQueue();
            PatientCare.resetPBa();
            PatientCare.resetWIP();
            PatientCare.resetAttending();
        }
        stats[parametersModel.getnOdta()][0] = "Total";
        stats[parametersModel.getnOdta()][1] = total[0][0]/parametersModel.getnOdta();
        stats[parametersModel.getnOdta()][2] = total[0][1]/parametersModel.getnOdta();
        stats[parametersModel.getnOdta()][3] = total[0][2]/parametersModel.getnOdta();
        stats[parametersModel.getnOdta()][4] = total[0][3]/parametersModel.getnOdta();
        stats[parametersModel.getnOdta()][5] = total[0][4]/parametersModel.getnOdta();
        stats[parametersModel.getnOdta()][6] = total[0][5]/parametersModel.getnOdta();
        StatisticsModel statisticsModel = new StatisticsModel(stats);
        statisticsController.setStatisticsModel(statisticsModel);
        statisticsController.show();
    }

    public static int getMilliseconds() {
        return Simulation.milliseconds;
    }

    public static void setMilliseconds(int milliseconds) {
        Simulation.milliseconds = milliseconds;
    }

    public void addWIpWt(float wIpWt) {
        this.wIpWt += wIpWt;
    }


    public void addNWiPwT() {
        this.nWiPwT++;
    }


    public void addPWtWa(float pWtWa) {
        this.pWtWa += pWtWa;
    }

    public void addNPwTwA() {
        this.nPwTwA++;
    }

    public void setStatisticsController(StatisticsController statisticsController) {
        this.statisticsController = statisticsController;
    }

    public static ExecutorService getExecutor() {
        return executor;
    }
    
}
