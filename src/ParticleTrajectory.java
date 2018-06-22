/*
 * Bank: REC::Track 
 * info: Trajectory for particle bank 
 * Noraim Nunez (CSUDH), June 22, 2018 
 */
public class ParticleTrajectory { //extends ParticleTrack
    
    // relates to detector 
    private int index; // #16 Index of the hit in the specific detector bank
    private int pindex; // #16 Row number in the particle bank hit is associated with
    private float x; // X coordinate of the hit (cm)
    private float y; // Y coordinate of the hit (cm)
    private float z; // Z coordinate of the hit (cm)
//
    //relates to particletrack 
    private int q; //charge of the track

    
    private int detId; 
    private float cx;//direction cosline of the track at the detector surface
    private float cy;
    private float cz;
    private float pathlength;//pathlength of the track to the detector surface from the DOCA point (cm)

    public int getdetId() {
        return detId;
    } 
    public void setdetId(int detId) {
	this.detId = detId;
    }
    public float getcx() {
	return cx;
    }
    public void setcx(float cx) {
	this.cx = cx;
    }
    public float getcy() {
	return cy;
    }
    public void setcy(float cy) {
		this.cy = cy;
    }
    public float getcz() {
	return cz;
    }
    public void setcz(float cz) {
	this.cz = cz;
    }
    public void setpathlength(float pathlength) {
		this.pathlength = pathlength;
    }
    public float getpathlength() {
	return pathlength;
	}

}


 
    
    
    
    
    
}
