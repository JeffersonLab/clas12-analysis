/*
 * Bank: REC::Scintillator 
 * info: Scintillator responses for particle bank 
 * Noraim Nunez (CSUDH), June 20, 2018 
 */

public class ScintillatorHit {
    
    
    int layer;  //#8 Layer of the Detector hit 
    int component; //Component of the Detector hit 
    float energy; //Energy associated with the hit (GeV)
    float hx; //X coordinate of the matched hit (cm)
    float hy; //Y coordinate of the matched hit (cm)
    float hz; //Z coordinate of the matched hit (cm)
    int status; //Hit status


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
    public int getstatus() {
        return status;
    }
    public void setstatus(int status) {
        this.status = status;
    }
}
