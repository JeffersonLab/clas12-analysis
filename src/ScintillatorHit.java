/*
 * Bank: REC::Scintillator 
 * info: Scintillator responses for particle bank 
 * Noraim Nunez (CSUDH), June 20, 2018 
 */

public class ScintillatorHit extends DetectorHit {

	private int layer; // #8 Layer of the Detector hit
	private int component; // Component of the Detector hit
	private float energy; // Energy associated with the hit (GeV)
	private float hx; // X coordinate of the matched hit (cm)
	private float hy; // Y coordinate of the matched hit (cm)
	private float hz; // Z coordinate of the matched hit (cm)

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public int getComponent() {
		return component;
	}

	public void setComponent(int component) {
		this.component = component;
	}

	public float getEnergy() {
		return energy;
	}

	public void setEnergy(float energy) {
		this.energy = energy;
	}

	public float getHx() {
		return hx;
	}

	public void setHx(float hx) {
		this.hx = hx;
	}

	public float getHy() {
		return hy;
	}

	public void setHy(float hy) {
		this.hy = hy;
	}

	public float getHz() {
		return hz;
	}

	public void setHz(float hz) {
		this.hz = hz;
	}

}