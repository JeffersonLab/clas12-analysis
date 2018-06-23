/*
 * Bank: REC::Particle  
 * info: Reconstructed particle  
 * Noraim Nunez (CSUDH), June 22, 2018 
 */
public class Particle {
    
    private int pid;
    private float px;
    private float py;
    private float pz;
    private float vx;
    private float vy;
    private float vz;
    private int charge;
    private float beta;
    private float chi2pid;
    private int status; 

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public float getPx() {
        return px;
    }

    public void setPx(float px) {
        this.px = px;
    }

    public float getPy() {
        return py;
    }

    public void setPy(float py) {
        this.py = py;
    }

    public float getPz() {
        return pz;
    }

    public void setPz(float pz) {
        this.pz = pz;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public float getVz() {
        return vz;
    }

    public void setVz(float vz) {
        this.vz = vz;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public float getBeta() {
        return beta;
    }

    public void setBeta(float beta) {
        this.beta = beta;
    }

    public float getChi2pid() {
        return chi2pid;
    }

    public void setChi2pid(float chi2pid) {
        this.chi2pid = chi2pid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

    