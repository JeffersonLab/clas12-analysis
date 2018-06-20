/*
 * Bank: REC::Scintillator 
 * info: Scintillator responses for particle bank 
 * Noraim Nunez (CSUDH), June 20, 2018 
 */

public class Scintillator {
    
    int index;  //#16 Index of the hit in the specific detector bank
    int pindex; //#16 Row number in the particle bank hit is associated with
    int detector;//#8 Detector ID,defined in COATJAVA DetectorType 
    int sector; //#8 Sector of the Detector hit
    int layer;  //#8 Layer of the Detector hit 
    int component; //Component of the Detector hit 
    float energy; //Energy associated with the hit (GeV)
    float time; //Time associated with the hit (ns)
    float path; //Path from vertex to the hit position (cm)
    float chi2; //Quality of hit-track matching
    float x; //X coordinate of the hit (cm)
    float y; //Y coordinate of the hit (cm)
    float z; //Z coordinate of the hit (cm)
    float hx; //X coordinate of the matched hit (cm)
    float hy; //Y coordinate of the matched hit (cm)
    float hz; //Z coordinate of the matched hit (cm)
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
    public float gettime() {
        return time; 
    }
    public void settime(float time) {
        this.time = time;
    } 
    public float getpath() {
        return chi2; 
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
