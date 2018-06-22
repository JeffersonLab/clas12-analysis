/*
 * Bank: REC::Track 
 * info: Tracker for particle bank 
 * Noraim Nunez (CSUDH), June 22, 2018 
 */
public class ParticleTrack {
//    
    private int index; // #16 Index of the hit in the specific detector bank
    private int pindex; // #16 Row number in the particle bank hit is associated with
    private int detector;// #8 Detector ID,defined in COATJAVA DetectorType
    private int sector; // #8 Sector of the Detector hit
    private float chi2; // Quality of hit-track matching
    private int status; //Hit status 
//
    
    private int q; //charge of the track
    private int NDF; //# of degrees of freedom in track fitting
    private float px_nomm; //component of the momentum with no MM (cm)
    private float py_nomm; 
    private float pz_nomm; 
    private float vx_nomm;//component of the vertex (cm)
    private float vy_nomm;
    private float vz_nomm;    
    private float chi2_nomm; 
    private int NDF_nomm; //# of degrees of freedom in track fitting with no MM

    public int getq() {
        return q;
    } 
    public void setq(int q) {
        this.q = q; 
    }   
     public int getNDF() {
        return NDF;
    } 
    public void setNDF(int NDF) {
        this.NDF = NDF;  
    }
    public float getpx_nomm() {
        return px_nomm;
    } 
    public void setpx_nomm(float px_nomm) {
        this.px_nomm = px_nomm;  
    }
    public float getpy_nomm() {
        return py_nomm;
    } 
    public void setpy_nomm(float py_nomm) {
        this.py_nomm = py_nomm;  
    }

    public float getpz_nomm() {
        return px_nomm;
    } 
    public void setpz_nomm (float pz_nomm) {
        this.pz_nomm = pz_nomm;     
    }

    public float getvx_nomm() {
        return px_nomm;
    } 
    public void setvx_nomm(float vx_nomm) {
        this.vx_nomm = vx_nomm;      
    }
    public float getvy_nomm() {
        return vy_nomm;
    } 
    public void setvy_nomm(float vy_nomm) {
        this.vy_nomm = vy_nomm;      
    }
    public float getvz_nomm() {
        return vz_nomm;
    } 
    public void setvz_nomm(float vz_nomm) {
        this.vz_nomm = vz_nomm;  
    }
    public float getchi2_nomm() {
        return chi2_nomm;
    } 
    public void setchi2_nomm(float chi2_nomm) {
        this.chi2_nomm = chi2_nomm; 
    }
    public int getNDF_nomm() {
        return NDF_nomm;
    } 
    public void setNDF_nomm(int NDF_nomm) {
        this.NDF_nomm = NDF_nomm; 
    }   
    }
    
