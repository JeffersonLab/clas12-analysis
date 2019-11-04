package org.jlab.clas12.analysis;

/*
 * Bank: REC::Clas Particle 
 * info: Clas Particle responses for particle bank
 * Will Phelps (JLAB), June 20, 2018 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jlab.clas.pdg.PDGDatabase;
import org.jlab.clas.pdg.PDGParticle;
import org.jlab.clas.physics.LorentzVector;
import org.jlab.clas.physics.Vector3;
import org.jlab.detector.base.DetectorType;

public class ClasParticle {
	private final LorentzVector momentum = new LorentzVector();
	private final Vector3 vertex = new Vector3();
	private boolean useft = false;
	private int pid;
	private int charge;
	private float chi2pid;
	private float beta;
	private int status;
	private int pidft;
	private int chargeft;
	private float chi2pidft;
	private float betaft;
	private int statusft;
	private float vt;
	private float vtft;
	private HashMap<DetectorType, DetectorHit> hits = new HashMap<DetectorType, DetectorHit>();
	private List<ParticleTrajectory> trajectoryInfo = new ArrayList<ParticleTrajectory>();
	private float[][] covarianceMatrix = new float[4][4];

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
		if (useft) {
			return pidft;
		} else {
			return pid;
		}
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
		if (useft) {
			return chi2pidft;
		} else {
			return chi2pid;
		}
	}

	public void setChi2pid(float chi2pid) {
		this.chi2pid = chi2pid;
	}

	public int getStatus() {
		if (useft) {
			return statusft;
		} else {
			return status;
		}
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSector() {
		int sector = -1000;
		for (DetectorType dtype : hits.keySet()) {
			int tempsector = hits.get(dtype).getSector();
			if (tempsector > 0) {
				sector = tempsector;
				System.out.println("Detector" + dtype.toString() + " Sector:" + sector);
			}
		}
		return sector;
	}

	public float getBeta() {
		if (useft) {
			return betaft;
		} else {
			return beta;
		}
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

	public List<ParticleTrajectory> getTrajectoryInfo() {
		return trajectoryInfo;
	}

	public void setTrajectoryInfo(List<ParticleTrajectory> trajectoryInfo) {
		this.trajectoryInfo = trajectoryInfo;
	}

	public float[][] getCovarianceMatrix() {
		return covarianceMatrix;
	}

	public void setCovarianceMatrix(float[][] covarianceMatrix) {
		this.covarianceMatrix = covarianceMatrix;
	}

	public boolean isUseFT() {
		return useft;
	}

	public void setUseFT(boolean useft) {
		this.useft = useft;
	}

	public void setPidFT(int pidft) {
		this.pidft = pidft;
	}

	public void setChargeFt(int chargeft) {
		this.chargeft = chargeft;
	}

	public void setChi2pidFT(float chi2pidft) {
		this.chi2pidft = chi2pidft;
	}

	public void setBetaFT(float betaft) {
		this.betaft = betaft;
	}

	public void setStatusFT(int statusft) {
		this.statusft = statusft;
		this.setMass();
	}

	public void setMass() {
		double px = this.getP4().px();
		double py = this.getP4().py();
		double pz = this.getP4().pz();

		if (this.getPid() != 0) {
			try {
				PDGParticle pgd = PDGDatabase.getParticleById(this.getPid());
				this.getP4().setPxPyPzM(px, py, pz, pgd.mass());
			} catch (Exception e) {
				System.out.println("Some messed up PID:" + this.getPid() + " " + px + " " + py + " " + pz);
				this.getP4().setPxPyPzM(px, py, pz, Math.sqrt(px * px + py * py + pz * pz) / this.getBeta());
			}
		} else {
			this.getP4().setPxPyPzM(px, py, pz, Math.sqrt(px * px + py * py + pz * pz) / this.getBeta());
		}
	}

	public float getVt() {
		if(this.useft) {
			return this.vtft;
		}else {
			return this.vt;
		}
	}

	public void setVt(float vt) {
		this.vt = vt;
	}
	
	public void setVtFT(float vt) {
		this.vtft = vt;
	}

}
