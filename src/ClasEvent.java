import java.util.ArrayList;

import org.jlab.clas.physics.Vector3;
import org.jlab.jnp.hipo.data.HipoEvent;
import org.jlab.jnp.hipo.data.HipoGroup;

public class ClasEvent {
	int eventNumber;
	int runNumber;
	ArrayList<ClasParticle> particles = new ArrayList<ClasParticle>();
	double beamEnergy;
	int targetType;
	float sttime;
	float rftime;
	float bcg;
	long trig;

	public long getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(int eventNumber) {
		this.eventNumber = eventNumber;
	}

	public int getRunNumber() {
		return runNumber;
	}

	public void setRunNumber(int runNumber) {
		this.runNumber = runNumber;
	}

	public ArrayList<ClasParticle> getParticles() {
		return particles;
	}

	public void setParticles(ArrayList<ClasParticle> particles) {
		this.particles = particles;
	}

	public double getBeamEnergy() {
		return beamEnergy;
	}

	public void setBeamEnergy(double beamEnergy) {
		this.beamEnergy = beamEnergy;
	}

	public int getTargetType() {
		return targetType;
	}

	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}

	public int N(int pid) {
		int nParticles = 0;	
		for(ClasParticle particle:particles) {
			if(particle.getPid()==pid) nParticles++;
		}
		return nParticles;
	}

}
