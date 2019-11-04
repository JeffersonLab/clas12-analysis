package org.jlab.clas12.analysis;
/*
 * Bank: REC::Event 
 * info: Event header bank 
 * Noraim Nunez (CSUDH), June 22, 2018 
 */


public class EventHeader {
    
   private int NRUN;
   private int NEVENT;
   private float EVNTime;
   private int TYPE; //Data or MC
   private int EvCAT;//Category, if >0:  e-, e-p, e-pi+
   private int NPGP;//# of Final (Timed-based) Reconstructed Particles*100 + Number of Geometrically Reconstructed Particles
   private int TRG;//Trigger Type (CLAS12_e-, FT_CLAS12_h, CLAS12_H,...) + Prescale Factor
   private float BCG;
   private double Lt; 
   private float STTime; 
   private float RFTime;
   private int Helic; 
   private float PTIME; 

    public int getNRUN() {
        return NRUN;
    }

    public void setNRUN(int NRUN) {
        this.NRUN = NRUN;
    }

    public int getNEVENT() {
        return NEVENT;
    }

    public void setNEVENT(int NEVENT) {
        this.NEVENT = NEVENT;
    }

    public float getEVNTime() {
        return EVNTime;
    }

    public void setEVNTime(float EVNTime) {
        this.EVNTime = EVNTime;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    public int getEvCAT() {
        return EvCAT;
    }

    public void setEvCAT(int EvCAT) {
        this.EvCAT = EvCAT;
    }

    public int getNPGP() {
        return NPGP;
    }

    public void setNPGP(int NPGP) {
        this.NPGP = NPGP;
    }

    public int getTRG() {
        return TRG;
    }

    public void setTRG(int TRG) {
        this.TRG = TRG;
    }

    public float getBCG() {
        return BCG;
    }

    public void setBCG(float BCG) {
        this.BCG = BCG;
    }

    public double getLt() {
        return Lt;
    }

    public void setLt(double Lt) {
        this.Lt = Lt;
    }

    public float getSTTime() {
        return STTime;
    }

    public void setSTTime(float STTime) {
        this.STTime = STTime;
    }

    public float getRFTime() {
        return RFTime;
    }

    public void setRFTime(float RFTime) {
        this.RFTime = RFTime;
    }

    public int getHelic() {
        return Helic;
    }

    public void setHelic(int Helic) {
        this.Helic = Helic;
    }

    public float getPTIME() {
        return PTIME;
    }

    public void setPTIME(float PTIME) {
        this.PTIME = PTIME;
    }
    

   
    
    
}
