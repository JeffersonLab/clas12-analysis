import java.util.ArrayList;

public class ClasEvent {
	private int runNumber;
	private int eventNumber;
	private float eventTime;
	private int type;
	private short eventCategory;
	private short NPGP;
	private long TRG;
	private float bcg;
	private double clock;
	private float startTime;
	private float RFTime;
	private int helicity;
	private float processingTime;
	
	ArrayList<ClasParticle> particles = new ArrayList<ClasParticle>();


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

	public float getEventTime() {
		return eventTime;
	}

	public void setEventTime(float eventTime) {
		this.eventTime = eventTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public short getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(short eventCategory) {
		this.eventCategory = eventCategory;
	}

	public short getNPGP() {
		return NPGP;
	}

	public void setNPGP(short nPGP) {
		NPGP = nPGP;
	}

	public long getTRG() {
		return TRG;
	}

	public void setTRG(long tRG) {
		TRG = tRG;
	}

	public float getBcg() {
		return bcg;
	}

	public void setBcg(float bcg) {
		this.bcg = bcg;
	}

	public double getClock() {
		return clock;
	}

	public void setClock(double clock) {
		this.clock = clock;
	}

	public float getStartTime() {
		return startTime;
	}

	public void setStartTime(float startTime) {
		this.startTime = startTime;
	}

	public float getRFTime() {
		return RFTime;
	}

	public void setRFTime(float rFTime) {
		RFTime = rFTime;
	}

	public int getHelicity() {
		return helicity;
	}

	public void setHelicity(int helicity) {
		this.helicity = helicity;
	}

	public float getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(float processingTime) {
		this.processingTime = processingTime;
	}

	public int N(int pid) {
		int nParticles = 0;	
		for(ClasParticle particle:particles) {
			if(particle.getPid()==pid) nParticles++;
		}
		return nParticles;
	}

	public ClasParticle getParticle(int pid, int index) {
		int n = -1;
		for(ClasParticle particle :  particles) {
			if(particle.getPid()==pid) {
				n++;
				if(n==index) {
					return particle;
				}
			}
		}
		return null;
	}

}
