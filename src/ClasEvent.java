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

	public ClasEvent(HipoEvent hipoEvent) {
		HipoGroup bank = hipoEvent.getGroup("REC::Event"); 
		runNumber = bank.getNode("NRUN").getInt()[0];
		eventNumber = bank.getNode("NEVENT").getInt()[0];
	    HipoGroup partBank = hipoEvent.getGroup("REC::Particle");
	    for(int i = 0; i<partBank.getNode("pid").getDataSize(); i++) {
	    		ClasParticle particle = new ClasParticle();
	    		particle.setPid(partBank.getNode("pid").getInt(i));
	    		particle.setCharge((int)partBank.getNode("charge").getByte(i));
	    		particle.setBeta(partBank.getNode("beta").getFloat(i));
	    		particle.setChi2pid(partBank.getNode("chi2pid").getFloat(i));
	    		particle.setStatus(partBank.getNode("status").getShort(i));
	    		
	    		double vx = partBank.getNode("vx").getFloat(i);
	    		double vy = partBank.getNode("vy").getFloat(i);
	    		double vz = partBank.getNode("vz").getFloat(i);
	    		
	    		double px = partBank.getNode("px").getFloat(i);
	    		double py = partBank.getNode("py").getFloat(i);
	    		double pz = partBank.getNode("pz").getFloat(i);

	    		particle.setMomentum(new Vector3(px,py,pz));
	    		particle.setVertex(new Vector3(vx,vy,vz));

	    		particles.add(particle);

	    }

	}

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
