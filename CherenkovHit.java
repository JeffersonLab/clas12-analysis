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
    
    public int getpindex() {
        return pindex;
    }
    
    public void setpindex(int pindex) {
        this.pindex = pindex;
    }
    
    public int getdetector() {
        return detector;
    }
    
    public void setdetector(int detector) {
        this.detector = detector;
    }
        
    public int getsector() {
        return sector;
    }
    
    public void setsector(int sector) {
        this.sector = sector;
    }
    
    public float getnphe() {
        return nphe;
    }
    
    public void setnphe(float nphe) {
        this.nphe = nphe;
    }
    
    public float gettime() {
        return time;
    }
    
    public void settime(float time) {
        this.time = time;
    } 
    
    public float getpath() {
        return path; 
    }
    public void setpath(float path) {
        this.path = path;
    } 
    public float getchi2() {
        return chi2; 
    }
    public void setchi2(float chi2) {
        this.chi2 = chi2;
    } 
    
    public float getx() {
        return x; 
    }
    public void setx(float x) {
        this.x = x;
    } 
    public float gety() {
        return y; 
    }
   
    public void sety(float y) {
        this.y = y;
    }

    public float getz() {
        return z; 
    }
    public void setz(float z) {
        this.z = z;
    }
        
    public float gettheta() {
        return theta; 
    }
    public void settheta(float theta) {
        this.theta = theta;
    }

    public float getdtheta() {
        return dtheta; 
    }
    public void setdtheta(float dtheta) {
        this.dtheta = dtheta;
    }
        
        public float getdphi() {
        return dphi; 
    }
    public void setdphi(float dphi) {
        this.dphi = dphi;
    }
    public int getstatus() {
        return status;
    }
    
    public void setstatus(int status) {
        this.status = status;
    }
       
    }