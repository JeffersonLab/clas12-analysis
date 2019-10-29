import java.util.ArrayList;

public class ClasEvent {
	private int runNumber;
	private int eventNumber;
	private float eventTime;
	private int type;
	private short eventCategory;
	private short NPGP;
	private long TRG;
	private boolean[] triggerBits = new boolean[32];
	private float bcg;
	private double clock;
	private float startTime;
	private float RFTime;
	private int helicity;
	private float processingTime;
	private boolean useft;
	private float startTimeFT;
	private short eventCategoryFT;

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
		if(this.useft) {
			return this.eventCategoryFT;
		}else {
			return this.eventCategory;
		}
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
		for (int i = 31; i >= 0; i--) {
			triggerBits[i] = (tRG & (1 << i)) != 0;
		}
	}
	
	public boolean isTrigBit(int bit) {
		return triggerBits[bit];
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
		if(this.useft) {
			return this.startTimeFT;
		}else {
			return this.startTime;
		}
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

	public void setStartTimeFT(float startTimeFT) {
		this.startTimeFT = startTimeFT;
	}

	public void setEventCategoryFT(short eventCategoryFT) {
		this.eventCategoryFT = eventCategoryFT;
	}

	public int N(int pid) {
		int nParticles = 0;
		for (ClasParticle particle : particles) {
			if (particle.getPid() == pid)
				nParticles++;
		}
		return nParticles;
	}

	public int NCharged(int charge) {
		int nParticles = 0;
		for (ClasParticle particle : particles) {
			if (particle.getCharge() == charge)
				nParticles++;
		}
		return nParticles;
	}

	public ClasParticle getParticle(int pid, int index) {
		int n = -1;
		for (ClasParticle particle : particles) {
			if (particle.getPid() == pid) {
				n++;
				if (n == index) {
					return particle;
				}
			}
		}
		return null;
	}

	public ClasParticle getParticleByCharge(int charge, int index) {
		int n = -1;
		for (ClasParticle particle : particles) {
			if (particle.getCharge() == charge) {
				n++;
				if (n == index) {
					return particle;
				}
			}
		}
		return null;
	}

	public boolean isUseft() {
		return useft;
	}

	public void setUseft(boolean useft) {
		this.useft = useft;
		for(ClasParticle particle : this.particles) {
			particle.setUseFT(useft);
		}
	}

}
