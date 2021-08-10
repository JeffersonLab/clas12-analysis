package org.jlab.clas12.analysis;
import java.util.ArrayList;

public class ClasEvent {

    private int runNumber;
    private int eventNumber;
    private int unixTime;
    private int type;
    private int mode;
    private long eventCategory;
    private short NPGP;
    private long topology;
    private long TRG;
    private boolean[] triggerBits = new boolean[32];
    private float bcg;
    private float torus;
    private float solenoid;
    private double clock;
    private float startTime;
    private float RFTime;
    private int helicity;
    private float processingTime;
    private boolean useft;
    private float startTimeFT;
    private long eventCategoryFT;

    ArrayList<ClasParticle> particles = new ArrayList<ClasParticle>();

    ArrayList<VertDoca> docas = new ArrayList<>();

    public long getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(int eventNumber) {
        this.eventNumber = eventNumber;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
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

    public ArrayList<VertDoca> getDOCAS() {
        return docas;
    }

    public void setDOCAS(ArrayList<VertDoca> docas) {
        this.docas = docas;
    }


    public float getTorus() {
        return torus;
    }

    public void setTorus(float torus) {
        this.torus = torus;
    }

    public float getSolenoid() {
        return solenoid;
    }

    public void setSolenoid(float solenoid) {
        this.solenoid = solenoid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getEventCategory() {
        if(this.useft) {
            return this.eventCategoryFT;
        }else {
            return this.eventCategory;
        }
    }

    public int getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(int unixTime) {
        this.unixTime = unixTime;
    }

    public long getTopology() {
        return topology;
    }

    public void setTopology(long topology) {
        this.topology = topology;
    }

    public void setEventCategory(long eventCategory) {
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

    public void setEventCategoryFT(long eventCategoryFT) {
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

    @Override
    public String toString() {
        String str = "";
        for (ClasParticle particle: this.particles){
            str += particle.toString()+"\n";
        }
        return str;
    }

    // Given two particles return an instance of VertDOCA
    public VertDoca getDOCA(ClasParticle p1, ClasParticle p2) {
        // p1Index = 1
        // p2Index = 2
        // In VertDoca object
        // d.getIndex1() returns 2
        // d.getIndex2() returns 1
        int p1Index = particles.indexOf(p1);
        int p2Index = particles.indexOf(p2);

        for (VertDoca d : docas) {
            if (d.getIndex1() == p1Index && d.getIndex2() == p2Index) {
                return d;
            } else if (d.getIndex1() == p2Index && d.getIndex2() == p1Index) {
                return d;
            } else {
                return null;
            }
        }
        return null;
    }


}