
/*
 * Bank: REC::Clas Particle 
 * info: Clas Particle responses for particle bank
 * Will Phelps (JLAB), June 20, 2018 
 */

import java.util.HashMap;

import org.jlab.clas.physics.LorentzVector;
import org.jlab.clas.physics.Vector3;
import org.jlab.detector.base.DetectorType;

public class ClasParticle {
	private final LorentzVector momentum = new LorentzVector();
	private final Vector3 vertex = new Vector3();
	private int pid;
	private int charge;
	private float chi2pid;
	private float beta;
	private int status;
	private HashMap<DetectorType, DetectorHit> hits = new HashMap<DetectorType, DetectorHit>();

	public LorentzVector getP4() {
		return momentum;
	}

	public boolean isDetectorHit(DetectorType detector) {
		return hits.containsKey(detector);
	}

	public DetectorHit getDetectorHit(DetectorType detector) {
		return hits.get(detector);
	}

	public Vector3 getVertex() {
		return vertex;
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

	public HashMap<DetectorType, DetectorHit> getHits() {
		return hits;
	}

	public void setHits(HashMap<DetectorType, DetectorHit> hits) {
		this.hits = hits;
	}

}
