/*
 * Bank: REC::Cherenkov
 * info: Cherenkov responses for particle bank
 * Noraim Nunez (CSUDH), June 20, 2018 
 */

public class CherenkovHit {

    int index;   //#16 index of the hit in the specific detector bank
    int pindex;  //#16 row number in the particle bank hit is associated with
    int detector;//#8 detector ID,defined in COATJAVA DetectorType 
    int sector;  //#8 Sector of the Detector hit
    float nphe;  //# of photoelectrons from Cherenkov radiation
    float time;  //Time associated with the hit (ns)
    float path;  //Path from vertex to the hit position (cm)
    float chi2;  //quality of hit-track matching
    float x;     //X coordinate of the hit (cm)
    float y;    //Y coordinate of the hit (cm)
    float z;    //Z coordinate of the hit (cm)
    float theta;//Theta of the matched hit (deg)
    float phi;  //Phi of the mathced hit (deg)
    float dtheta;//Expected Theta Resolution (deg)
    float dphi; //Expected Phi Resolution (deg)
    int status; //Hit status 
    
    public int getindex() {
        return index;
    }

    public void setindex(int index) {
        this.index = index;
    }
    
    public int pindex() {
        return pindex;
    }
    
    public void setpindex(int pindex) {
        this.pindex = pindex;
    }
    
    public int detector() {
        return detector;
    }
    
    public void detector(int detector) {
        this.detector = detector;
    }
        
    public int sector() {
        return sector;
    }
    
    public void sector(int sector) {
        this.sector = sector;
    }
    
    public float nphe() {
        return nphe;
    }
    
    public void nphe(float nphe) {
        this.nphe = nphe;
    }
    
    public float time() {
        return time;
    }
    
    public void time(float time) {
        this.time = time;
    } 
    
    public float path() {
        return path; 
    }
    public void path(float path) {
        this.path = path;
    } 
    public float chi2() {
        return chi2; 
    }
    public void chi2(float chi2) {
        this.chi2 = chi2;
    } 
    
    public float x() {
        return x; 
    }
    public void x(float x) {
        this.x = x;
    } 
    public float y() {
        return y; 
    }
   
    public void y(float y) {
        this.y = y;
    }

    public float z() {
        return z; 
    }
    public void z(float z) {
        this.z = z;
    }
        
    public float theta() {
        return theta; 
    }
    public void theta(float theta) {
        this.theta = theta;
    }

    public float dtheta() {
        return dtheta; 
    }
    public void dtheta(float dtheta) {
        this.dtheta = dtheta;
    }
        
        public float dphi() {
        return dphi; 
    }
    public void dphi(float dphi) {
        this.dphi = dphi;
    }
    public int status() {
        return status;
    }
    
    public void status(int status) {
        this.status = status;
    }
       
    }