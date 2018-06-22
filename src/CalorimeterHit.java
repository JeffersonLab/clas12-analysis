/*
 * Bank: REC::Calorimeter
 * info: Calorimeter responses for particle bank 
 * Noraim Nunez (CSUDH), June 20, 2018 
 */

public class CalorimeterHit extends DetectorHit {
	private float energy; // Energy associated with the hit (GeV)
	float hx; // X coordinate of the matched hit (cm)
	float hy; // Y coordinate of the matched hit (cm)
	float hz; // Z coordinate of the matched hit (cm)
	float lu; // Distance on U-side
	float lv; // Distance on V-side
	float lw; // Distance on W-side
	float du; // Shower width on U-side
	float dv; // Shower width on V-side
	float dw; // Shower width on W-side
	float m2u; // 2nd moment of the shower on U-side
	float m2v; // 2nd moment of the shower on V-side
	float m2w; // 3rd moment of the shower on W-side
	float m3u; // 3rd moment of the shower on U-side
	float m3v; // 3rd moment of the shower on V-side
	float m3w; // 3rd moment of the shower on W-side

	public float getEnergy() {
		return energy;
	}

	public void setEnergy(float energy) {
		this.energy = energy;
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

	public float getlu() {
		return lu;
	}

	public void setlu(float lu) {
		this.lu = lu;
	}

	public float getlv() {
		return lv;
	}

	public void setlv(float lv) {
		this.lv = lv;
	}

	public float getlw() {
		return lw;
	}

	public void setlw(float lw) {
		this.lw = lw;
	}

	public float getdu() {
		return lu;
	}

	public void setdu(float du) {
		this.du = du;
	}

	public float getdv() {
		return dv;
	}

	public void setdv(float dv) {
		this.dv = dv;
	}

	public float getdw() {
		return dw;
	}

	public void setdw(float dw) {
		this.dw = dw;
	}

	public void getm2u(float m2u) {
		this.m2u = m2u;
	}

	public float setm2u() {
		return m2u;
	}

	public void getm2v(float m2v) {
		this.m2v = m2v;
	}

	public float setm2v() {
		return m2v;
	}

	public void getm2w(float m2w) {
		this.m2w = m2w;
	}

	public void getm3u(float m3u) {
		this.m3u = m3u;
	}

	public float setm3u() {
		return m3u;
	}

	public void getm3v(float m3v) {
		this.m3v = m3v;
	}

	public float setm3v() {
		return m3v;
	}

	public void getm3w(float m3w) {
		this.m3w = m3w;
	}

	public float setm3w() {
		return m3w;
	}
}
