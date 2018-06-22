/*
 * Bank: REC::Clas Particle 
 * info: Clas Particle responses for particle bank
 * Will Phelps (JLAB), June 20, 2018 
 */

import org.jlab.clas.physics.Vector3;

public class ClasParticle {
	private Vector3 momentum;
	private Vector3 vertex;
	private int pid;
	private int charge;
	private float chi2pid;
	private float beta; 
        private int status;
	
	
	public Vector3 getMomentum() {
		return momentum;
	}
	public void setMomentum(Vector3 momentum) {
		this.momentum = momentum;
	}
	public Vector3 getVertex() {
		return vertex;
	}
	public void setVertex(Vector3 vertex) {
		this.vertex = vertex;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public float getChi2pid() {
		return chi2pid;
	}
	public void setChi2pid(float chi2pid) {
		this.chi2pid = chi2pid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getBeta() {
		return beta;
	}
	public void setBeta(float beta) {
		this.beta = beta;
	}
	
	
}
