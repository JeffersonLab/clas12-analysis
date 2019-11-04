package org.jlab.clas12.analysis;
/*
 * Bank: REC::Cherenkov
 * info: Cherenkov responses for particle banks
 * Noraim Nunez (CSUDH), June 20, 2018 
 */

public class CherenkovHit extends DetectorHit {

    private float nphe; // # of photoelectrons from Cherenkov radiation
	
    public float getNphe() {
		return nphe;
	}
	public void setNphe(float nphe) {
		this.nphe = nphe;
	}
}