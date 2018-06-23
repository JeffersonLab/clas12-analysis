/*
 * Bank: REC::Cherenkov
 * info: Cherenkov responses for particle banks
 * Noraim Nunez (CSUDH), June 20, 2018 
 */

public class CherenkovHit extends DetectorHit {

    private float nphe; // # of photoelectrons from Cherenkov radiation
    private float theta;// Theta of the matched hit (deg)
    private float phi; // Phi of the mathced hit (deg)
    private float dtheta;// Expected Theta Resolution (deg)
    private float dphi; // Expected Phi Resolution (deg)
	
    public float getNphe() {
		return nphe;
	}
	public void setNphe(float nphe) {
		this.nphe = nphe;
	}
	public float getTheta() {
		return theta;
	}
	public void setTheta(float theta) {
		this.theta = theta;
	}
	public float getPhi() {
		return phi;
	}
	public void setPhi(float phi) {
		this.phi = phi;
	}
	public float getDtheta() {
		return dtheta;
	}
	public void setDtheta(float dtheta) {
		this.dtheta = dtheta;
	}
	public float getDphi() {
		return dphi;
	}
	public void setDphi(float dphi) {
		this.dphi = dphi;
	}


}