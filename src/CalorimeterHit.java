/*
 * Bank: REC::Calorimeter
 * info: Calorimeter responses for particle bank 
 * Noraim Nunez (CSUDH), June 20, 2018 
 */

public class CalorimeterHit extends DetectorHit {
	private int layer; // #8 Layer of the Detector hit
	private float energy; // Energy associated with the hit (GeV)
	private float hx; // X coordinate of the matched hit (cm)
	private float hy; // Y coordinate of the matched hit (cm)
	private float hz; // Z coordinate of the matched hit (cm)
	private float lu; // Distance on U-side
	private float lv; // Distance on V-side
	private float lw; // Distance on W-side
	private float du; // Shower width on U-side
	private float dv; // Shower width on V-side
	private float dw; // Shower width on W-side
	private float m2u; // 2nd moment of the shower on U-side
	private float m2v; // 2nd moment of the shower on V-side
	private float m2w; // 3rd moment of the shower on W-side
	private float m3u; // 3rd moment of the shower on U-side
	private float m3v; // 3rd moment of the shower on V-side
	private float m3w; // 3rd moment of the shower on W-side

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
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

	public float getLu() {
		return lu;
	}

	public void setLu(float lu) {
		this.lu = lu;
	}

	public float getLv() {
		return lv;
	}

	public void setLv(float lv) {
		this.lv = lv;
	}

	public float getLw() {
		return lw;
	}

	public void setLw(float lw) {
		this.lw = lw;
	}

	public float getDu() {
		return du;
	}

	public void setDu(float du) {
		this.du = du;
	}

	public float getDv() {
		return dv;
	}

	public void setDv(float dv) {
		this.dv = dv;
	}

	public float getDw() {
		return dw;
	}

	public void setDw(float dw) {
		this.dw = dw;
	}

	public float getM2u() {
		return m2u;
	}

	public void setM2u(float m2u) {
		this.m2u = m2u;
	}

	public float getM2v() {
		return m2v;
	}

	public void setM2v(float m2v) {
		this.m2v = m2v;
	}

	public float getM2w() {
		return m2w;
	}

	public void setM2w(float m2w) {
		this.m2w = m2w;
	}

	public float getM3u() {
		return m3u;
	}

	public void setM3u(float m3u) {
		this.m3u = m3u;
	}

	public float getM3v() {
		return m3v;
	}

	public void setM3v(float m3v) {
		this.m3v = m3v;
	}

	public float getM3w() {
		return m3w;
	}

	public void setM3w(float m3w) {
		this.m3w = m3w;
	}

}