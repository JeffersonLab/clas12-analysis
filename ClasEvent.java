/*
 * Bank: REC::Cherenkov
 * info: Cherenkov responses for particle bank
 * Noraim Nunez (CSUDH), June 20, 2018 
 */

import java.util.ArrayList;

public class ClasEvent {
	long eventNumber;
	int runNumber;
	ArrayList<ClasParticle> particles;
	double beamEnergy;
	int targetType;
        float sttime; 
        float rftime;
        float bcg;
        long trig; 
        
        
	public long getEventNumber() {
		return eventNumber;
	}
	public void setEventNumber(long eventNumber) {
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
	
}
