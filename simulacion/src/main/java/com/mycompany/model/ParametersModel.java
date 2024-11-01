/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author aris-
 */
public class ParametersModel {
    
    //Number of days to attend
    private int nOdta;
    
    //Time it takes for a patient with an appointment to arrive - minutes
    private int tTpaa;
    
    //Maximum number of patients with appointments to attend
    private int mNpaa;
    
    //Exponential time it takes for a patient to arrive without an appointment
    private int eTtpawa;
    
    //Maximum number of patients without appointments to attend
    private int mNpwaa;
    
    //Normal patient care time 
    private int pCt;
    
    //standard deviation 
    private int sD;
        
    //Number of staff during office hours 
    private int nSdoh;
    private int nSdoh2;
    private int nSdoh3;
    private int nSdoh4;

    public ParametersModel(int nOdta, int tTpaa, int mNpaa, int eTtpawa, int mNpwaa, int pCt, int sD, int nSdoh, int nSdoh2, int nSdoh3, int nSdoh4) {
        this.nOdta = nOdta;
        this.tTpaa = tTpaa;
        this.mNpaa = mNpaa;
        this.eTtpawa = eTtpawa;
        this.mNpwaa = mNpwaa;
        this.pCt = pCt;
        this.sD = sD;
        this.nSdoh = nSdoh;
        this.nSdoh2 = nSdoh2;
        this.nSdoh3 = nSdoh3;
        this.nSdoh4 = nSdoh4;
    }

    public int getnOdta() {
        return nOdta;
    }

    public int gettTpaa() {
        return tTpaa;
    }

    public int getmNpaa() {
        return mNpaa;
    }

    public int geteTtpawa() {
        return eTtpawa;
    }

    public int getmNpwaa() {
        return mNpwaa;
    }

    public int getpCt() {
        return pCt;
    }

    public int getsD() {
        return sD;
    }

    public int getnSdoh() {
        return nSdoh;
    }

    public int getnSdoh2() {
        return nSdoh2;
    }

    public int getnSdoh3() {
        return nSdoh3;
    }

    public int getnSdoh4() {
        return nSdoh4;
    }
}
