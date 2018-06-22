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

    public float nphe() {
	return nphe;
    }
    
    public void nphe(float nphe) {
	this.nphe = nphe;
    }

    public float theta() {
	return theta;
}

    public void theta(float theta) {
	this.theta = theta;
    }

    public float getPhi() {
	return phi;
    }

    public void setPhi(float phi) {
	this.phi = phi;
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

}