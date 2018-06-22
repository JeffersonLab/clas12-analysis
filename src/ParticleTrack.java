/*
 * Bank: REC::Track 
 * info: Tracker for particle bank 
 * Noraim Nunez (CSUDH), June 22, 2018 
 */
public class ParticleTrack {
    
    int q; //charge of the track
    int NDF; //# of degrees of freedom in track fitting
    float px_nomm; //component of the momentum with no MM (cm)
    float py_nomm; 
    float pz_nomm; 
    float vx_nomm;//component of the vertex (cm)
    float vy_nomm;
    float vz_nomm;    
    float chi2_nomm; 
    int NDF_nomm; //# of degrees of freedom in track fitting with no MM

    public float getq() {
        return q;
    } 
    public void setq(int q) {
        this.q = q; 
    }   
     public float getNDF() {
        return NDF;
    } 
    public void setNDF(int NDF) {
        this.NDF = NDF;  
    }
    public float getpx_nomm() {
        return px_nomm;
    } 
    public void setpx_nomm(int px_nomm) {
        this.px_nomm = px_nomm;  
    }
    public float getpy_nomm() {
        return py_nomm;
    } 
    public void setpy_nomm(int py_nomm) {
        this.py_nomm = py_nomm;  
    }

    public float getpz_nomm() {
        return px_nomm;
    } 
    public void setpz_nomm (int pz_nomm) {
        this.pz_nomm = pz_nomm;     
    }

    public float getvx_nomm() {
        return px_nomm;
    } 
    public void setvx_nomm(int vx_nomm) {
        this.vx_nomm = vx_nomm;      
    }
    public float getvy_nomm() {
        return vy_nomm;
    } 
    public void setpvy_nomm(int vx_nomm) {
        this.vy_nomm = vy_nomm;      
    }
    public float getvz_nomm() {
        return vz_nomm;
    } 
    public void setvz_nomm(int vz_nomm) {
        this.vz_nomm = vz_nomm;  
    }
    public float getchi2_nomm() {
        return chi2_nomm;
    } 
    public void setchi2_nomm(int chi2_nomm) {
        this.chi2_nomm = chi2_nomm; 
    }
    public float getNDF_nomm() {
        return NDF_nomm;
    } 
    public void setNDF_nomm(int NDF_nomm) {
        this.NDF_nomm = NDF_nomm; 
    }   
        
       
    }
    
