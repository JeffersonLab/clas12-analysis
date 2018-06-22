/*
 * Bank: REC::Track 
 * info: Trajectory for particle bank 
 * Noraim Nunez (CSUDH), June 22, 2018 
 */
public class ParticleTrajectory { //extends ParticleTrack
    
    // same name as detector components 
    private int index; // position of the track at the detector surface (cm)
    private int pindex; // 
    private float x; // position of the track at the detector surface (cm)
    private float y; // 
    private float z; // 
//
    
    //relates to particletrack 
    private int q; //charge of the track

    
    private int detId; 
    private float cx;//direction cosline of the track at the detector surface
    private float cy;
    private float cz;
    private float pathlength;//pathlength of the track to the detector surface from the DOCA point (cm)

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPindex() {
        return pindex;
    }

    public void setPindex(int pindex) {
        this.pindex = pindex;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getDetId() {
        return detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public float getCx() {
        return cx;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public float getCy() {
        return cy;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public float getCz() {
        return cz;
    }

    public void setCz(float cz) {
        this.cz = cz;
    }

    public float getPathlength() {
        return pathlength;
    }

    public void setPathlength(float pathlength) {
        this.pathlength = pathlength;
    }
} 
   
