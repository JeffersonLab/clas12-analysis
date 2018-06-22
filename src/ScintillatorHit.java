/*
 * Bank: REC::Scintillator 
 * info: Scintillator responses for particle bank 
 * Noraim Nunez (CSUDH), June 20, 2018 
 */

public class ScintillatorHit extends DetectorHit {
    
   private int layer;  //#8 Layer of the Detector hit 
   private int component; //Component of the Detector hit 
   private float energy; //Energy associated with the hit (GeV)
   private float hx; //X coordinate of the matched hit (cm)
   private float hy; //Y coordinate of the matched hit (cm)
   private float hz; //Z coordinate of the matched hit (cm)



    public float getlayer() {
        return layer;
    } 
    public void setlayer(int layer) {
        this.layer = layer;
    }
        public float getcomponent() {
        return component;
    } 
    public void setcomponent(int component) {
        this.component = component;
    }
    public float getenergy() {
        return energy;
    }
    public void setenergy(float energy) {
        this.energy = energy;
    } 
    
    public float gethx() {
        return hx; 
    }
    public void sethx(float hx) {
        this.hx = hx;
    } 
    public float gethy() {
        return hy; 
    }
    public void sethy(float hy) {
        this.hy = hy;
    }
    public float gethz() {
        return hz; 
    }
    public void sethz(float hz) {
        this.hz = hz;
    }
}