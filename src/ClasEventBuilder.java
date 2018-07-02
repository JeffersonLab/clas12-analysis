import org.jlab.clas.pdg.PDGDatabase;
import org.jlab.clas.pdg.PDGParticle;
import org.jlab.detector.base.DetectorType;
import org.jlab.jnp.hipo.data.HipoEvent;
import org.jlab.jnp.hipo.data.HipoGroup;

public class ClasEventBuilder {

	public static void buildEvent(HipoEvent hipoEvent, ClasEvent event) {
		HipoGroup bank = hipoEvent.getGroup("REC::Event");
		event.setRunNumber(bank.getNode("NRUN").getInt(0));
		event.setEventNumber(bank.getNode("NEVENT").getInt(0));
		event.setEventTime(bank.getNode("EVNTime").getFloat(0));
		event.setType((int) bank.getNode("TYPE").getByte(0));
		event.setEventCategory(bank.getNode("EvCAT").getShort(0));
		event.setNPGP(bank.getNode("NPGP").getShort(0));
		event.setTRG(bank.getNode("TRG").getLong(0));
		event.setBcg(bank.getNode("BCG").getFloat(0));
		event.setClock(bank.getNode("LT").getDouble(0));
		event.setStartTime(bank.getNode("STTime").getFloat(0));
		event.setRFTime(bank.getNode("RFTime").getFloat(0));
		event.setHelicity((int) bank.getNode("Helic").getByte(0));
		event.setProcessingTime(bank.getNode("PTIME").getFloat(0));

		if (hipoEvent.hasGroup("REC::Particle")) {
			HipoGroup partBank = hipoEvent.getGroup("REC::Particle");
			for (int i = 0; i < partBank.getNode("pid").getDataSize(); i++) {
				ClasParticle particle = new ClasParticle();
				particle.setPid(partBank.getNode("pid").getInt(i));
				particle.setCharge((int) partBank.getNode("charge").getByte(i));
				// System.out.println("Pid:"+particle.getPid()+" Charge:"+particle.getCharge()+"
				// Charge bytes:"+partBank.getNode("charge").getByte(i));
				particle.setBeta(partBank.getNode("beta").getFloat(i));
				particle.setChi2pid(partBank.getNode("chi2pid").getFloat(i));
				particle.setStatus(partBank.getNode("status").getShort(i));

				double vx = partBank.getNode("vx").getFloat(i);
				double vy = partBank.getNode("vy").getFloat(i);
				double vz = partBank.getNode("vz").getFloat(i);

				double px = partBank.getNode("px").getFloat(i);
				double py = partBank.getNode("py").getFloat(i);
				double pz = partBank.getNode("pz").getFloat(i);

				if (particle.getPid() != 0) {
					PDGParticle pgd = PDGDatabase.getParticleById(particle.getPid());
					particle.getP4().setPxPyPzM(px, py, pz, pgd.mass());
				} else {
					particle.getP4().setPxPyPzM(px, py, pz,
							Math.sqrt(px * px + py * py + pz * pz) / particle.getBeta());
				}
				particle.getVertex().setXYZ(vx, vy, vz);

				event.getParticles().add(particle);
			}
		}

		if (hipoEvent.hasGroup("REC::Cherenkov")) {
			HipoGroup cherenkovBank = hipoEvent.getGroup("REC::Cherenkov");
			for (int i = 0; i < cherenkovBank.getNode("pindex").getDataSize(); i++) {
				CherenkovHit hit = new CherenkovHit();
				hit.setIndex(cherenkovBank.getNode("index").getShort(i));
				hit.setPindex(cherenkovBank.getNode("pindex").getShort(i));
				hit.setDetector(cherenkovBank.getNode("detector").getByte(i));
				hit.setSector(cherenkovBank.getNode("sector").getByte(i));
				hit.setTime(cherenkovBank.getNode("time").getFloat(i));
				hit.setPath(cherenkovBank.getNode("path").getFloat(i));
				// hit.setNphe((float) cherenkovBank.getNode("nphe").getShort(i));
				hit.setNphe(cherenkovBank.getNode("nphe").getFloat(i));
				hit.setX(cherenkovBank.getNode("x").getFloat(i));
				hit.setY(cherenkovBank.getNode("y").getFloat(i));
				hit.setZ(cherenkovBank.getNode("z").getFloat(i));
				hit.setChi2(cherenkovBank.getNode("chi2").getFloat(i));
				hit.setTheta(cherenkovBank.getNode("theta").getFloat(i));
				hit.setPhi(cherenkovBank.getNode("phi").getFloat(i));
				hit.setDtheta(cherenkovBank.getNode("dtheta").getFloat(i));
				hit.setDphi(cherenkovBank.getNode("dphi").getFloat(i));
				hit.setStatus(cherenkovBank.getNode("status").getShort(i));
				event.getParticles().get(hit.getPindex()).getHits().put(DetectorType.getType(hit.getDetector()), hit);
			}
		}

		if (hipoEvent.hasGroup("REC::Calorimeter")) {
			HipoGroup calorimeterBank = hipoEvent.getGroup("REC::Calorimeter");
			for (int i = 0; i < calorimeterBank.getNode("pindex").getDataSize(); i++) {
				CalorimeterHit hit = new CalorimeterHit();
				hit.setIndex(calorimeterBank.getNode("index").getShort(i));
				hit.setPindex(calorimeterBank.getNode("pindex").getShort(i));
				hit.setDetector(calorimeterBank.getNode("detector").getByte(i));
				hit.setSector(calorimeterBank.getNode("sector").getByte(i));
				hit.setLayer(calorimeterBank.getNode("layer").getByte(i));
				hit.setEnergy(calorimeterBank.getNode("energy").getFloat(i));
				hit.setTime(calorimeterBank.getNode("time").getFloat(i));
				hit.setPath(calorimeterBank.getNode("path").getFloat(i));
				hit.setChi2(calorimeterBank.getNode("chi2").getFloat(i));
				hit.setX(calorimeterBank.getNode("x").getFloat(i));
				hit.setY(calorimeterBank.getNode("y").getFloat(i));
				hit.setZ(calorimeterBank.getNode("z").getFloat(i));
				hit.setHx(calorimeterBank.getNode("hx").getFloat(i));
				hit.setHy(calorimeterBank.getNode("hy").getFloat(i));
				hit.setHz(calorimeterBank.getNode("hz").getFloat(i));
				hit.setLu(calorimeterBank.getNode("lu").getFloat(i));
				hit.setLv(calorimeterBank.getNode("lv").getFloat(i));
				hit.setLw(calorimeterBank.getNode("lw").getFloat(i));
				hit.setDu(calorimeterBank.getNode("du").getFloat(i));
				hit.setDv(calorimeterBank.getNode("dv").getFloat(i));
				hit.setDw(calorimeterBank.getNode("dw").getFloat(i));
				// hit.setM2u(calorimeterBank.getNode("m2u").getFloat(i));
				// hit.setM2v(calorimeterBank.getNode("m2v").getFloat(i));
				// hit.setM2w(calorimeterBank.getNode("m2w").getFloat(i));
				// hit.setM3u(calorimeterBank.getNode("m3u").getFloat(i));
				// hit.setM3v(calorimeterBank.getNode("m3v").getFloat(i));
				// hit.setM3w(calorimeterBank.getNode("m3w").getFloat(i));
				hit.setStatus(calorimeterBank.getNode("status").getShort(i));
				event.getParticles().get(hit.getPindex()).getHits().put(DetectorType.getType(hit.getDetector()), hit);

			}
		}

		if (hipoEvent.hasGroup("REC::Scintillator")) {
			HipoGroup scintillatorBank = hipoEvent.getGroup("REC::Scintillator");
			for (int i = 0; i < scintillatorBank.getNode("pindex").getDataSize(); i++) {
				ScintillatorHit hit = new ScintillatorHit();
				hit.setIndex(scintillatorBank.getNode("index").getShort(i));
				hit.setPindex(scintillatorBank.getNode("pindex").getShort(i));
				hit.setDetector(scintillatorBank.getNode("detector").getByte(i));
				hit.setSector(scintillatorBank.getNode("sector").getByte(i));
				hit.setLayer(scintillatorBank.getNode("layer").getByte(i));
				hit.setEnergy(scintillatorBank.getNode("energy").getFloat(i));
				hit.setTime(scintillatorBank.getNode("time").getFloat(i));
				hit.setPath(scintillatorBank.getNode("path").getFloat(i));
				hit.setChi2(scintillatorBank.getNode("chi2").getFloat(i));
				hit.setX(scintillatorBank.getNode("x").getFloat(i));
				hit.setY(scintillatorBank.getNode("y").getFloat(i));
				hit.setZ(scintillatorBank.getNode("z").getFloat(i));
				hit.setHx(scintillatorBank.getNode("hx").getFloat(i));
				hit.setHy(scintillatorBank.getNode("hy").getFloat(i));
				hit.setHz(scintillatorBank.getNode("hz").getFloat(i));
				hit.setStatus(scintillatorBank.getNode("status").getShort(i));
				event.getParticles().get(hit.getPindex()).getHits().put(DetectorType.getType(hit.getDetector()), hit);
			}
		}

		if (hipoEvent.hasGroup("REC::CovMat")) {
			HipoGroup trajectoryBank = hipoEvent.getGroup("REC::CovMat");
			for (int i = 0; i < trajectoryBank.getNode("pindex").getDataSize(); i++) {
				float[][] covarianceMatrix = event.getParticles().get(trajectoryBank.getNode("pindex").getShort(i))
						.getCovarianceMatrix();
				covarianceMatrix[0][0] = trajectoryBank.getNode("C11").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C13").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C14").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C15").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C22").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C23").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C24").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C25").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C33").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C34").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C35").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C44").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C45").getFloat(i);
				covarianceMatrix[0][0] = trajectoryBank.getNode("C55").getFloat(i);
			}

		}
		if (hipoEvent.hasGroup("REC::Trajectory")) {
			HipoGroup trajectoryBank = hipoEvent.getGroup("REC::Trajectory");
			for (int i = 0; i < trajectoryBank.getNode("pindex").getDataSize(); i++) {
				ParticleTrajectory trajectory = new ParticleTrajectory();
				trajectory.setPindex(trajectoryBank.getNode("pindex").getShort(i));
				trajectory.setIndex(trajectoryBank.getNode("index").getShort(i));
				trajectory.setDetId(trajectoryBank.getNode("detId").getShort(i));

				trajectory.setQ((int) trajectoryBank.getNode("q").getByte(i));
				trajectory.setX(trajectoryBank.getNode("x").getFloat(i));
				trajectory.setY(trajectoryBank.getNode("y").getFloat(i));
				trajectory.setZ(trajectoryBank.getNode("z").getFloat(i));
				trajectory.setCx(trajectoryBank.getNode("cx").getFloat(i));
				trajectory.setCy(trajectoryBank.getNode("cy").getFloat(i));
				trajectory.setCz(trajectoryBank.getNode("cz").getFloat(i));
				trajectory.setPathlength(trajectoryBank.getNode("pathlength").getFloat(i));
				event.getParticles().get(trajectory.getPindex()).getTrajectoryInfo().add(trajectory);
			}
		}

    /*             if (hipoEvent.hasGroup("REC::ParticleTrack")) {
			HipoGroup ParticleTrackBank = hipoEvent.getGroup("REC::ParticleTrack");
			for (int i = 0; i < ParticleTrackBank.getNode("pindex").getDataSize(); i++) {
				ParticleTrack hit = new ParticleTrack();
				hit.setIndex(ParticleTrackBank.getNode("index").getShort(i));
				hit.setPindex(ParticleTrackBank.getNode("pindex").getShort(i));
				hit.setDetector(ParticleTrackBank.getNode("detector").getByte(i));
				hit.setSector(ParticleTrackBank.getNode("sector").getByte(i));
				hit.setStatus(ParticleTrackBank.getNode("status").getShort(i));
				hit.setQ(ParticleTrackBank.getNode("q").getByte(i));
				hit.setChi2(ParticleTrackBank.getNode("chi2").getByte(i));
				hit.setNDF((int) ParticleTrackBank.getNode("ndf").getFloat(i));
				hit.setPx_nomm(ParticleTrackBank.getNode("px_nomm").getFloat(i));
				hit.setPy_nomm(ParticleTrackBank.getNode("py_nomm").getFloat(i));
				hit.setPz_nomm(ParticleTrackBank.getNode("pz_nomm").getFloat(i));
				hit.setVx_nomm(ParticleTrackBank.getNode("vx_nomm").getFloat(i));
				hit.setVy_nomm(ParticleTrackBank.getNode("vy_nomm").getFloat(i));
				hit.setVz_nomm(ParticleTrackBank.getNode("vz_nomm").getFloat(i));
  	\* 
                        }
		}
      
                
                	}
		}

      //************* Not sure what to do with the VertaDoca *******************
                    if (hipoEvent.hasGroup("REC::VertDoca")) {
			HipoGroup VertaDBank = hipoEvent.getGroup("REC::VertDoca");
			for (int i = 0; i < VertaDBank.getNode("index1").getDataSize(); i++) {
				VertaDoca hit = new VertaDoca();
                        
                            trajectory.setPindex(trajectoryBank.getNode("pindex").getShort(i));

                                setIndex1(VertaDBank.getNode("index1").getShort(i));
				hit.setPindex(scintillatorBank.getNode("pindex").getShort(i));
				hit.setDetector(scintillatorBank.getNode("detector").getfloat(i));
				hit.setSector(scintillatorBank.getNode("sector").getfloat(i));
				hit.setLayer(scintillatorBank.getNode("layer").getfloat(i));
				hit.setEnergy(scintillatorBank.getNode("energy").getFloat(i));
				hit.setTime(scintillatorBank.getNode("time").getFloat(i));
				hit.setPath(scintillatorBank.getNode("path").getFloat(i));
				hit.setChi2(scintillatorBank.getNode("chi2").getFloat(i));
				hit.setX(scintillatorBank.getNode("x").getFloat(i));
				hit.setY(scintillatorBank.getNode("y").getFloat(i));
				hit.setZ(scintillatorBank.getNode("z").getFloat(i));
				hit.setHx(scintillatorBank.getNode("hx").getFloat(i));
				hit.setHy(scintillatorBank.getNode("hy").getFloat(i));
				hit.setHz(scintillatorBank.getNode("hz").getFloat(i));
				hit.setStatus(scintillatorBank.getNode("status").getfloat(i));
				event.getParticles().get(hit.getPindex()).getHits().put(DetectorType.getType(hit.getDetector()), hit);
			}
                } 
/*         ************************************************       


		/*
		 * if (hipoEvent.hasGroup("REC::ForwardTagger")) { HipoGroup forwardTaggerBank =
		 * hipoEvent.getGroup("REC::Scintillator"); for (int i = 0; i <
		 * forwardTaggerBank.getNode("pindex").getDataSize(); i++) { ForwardTaggerHit
		 * hit = new ForwardTaggerHit();
		 * hit.setIndex(forwardTaggerBank.getNode("index").getShort(i));
		 * hit.setPindex(forwardTaggerBank.getNode("pindex").getShort(i));
		 * hit.setDetector(forwardTaggerBank.getNode("detector").getByte(i));
		 * hit.setSector(0);//oops. Need to remove from Detectorhit
		 * hit.setEnergy(forwardTaggerBank.getNode("energy").getFloat(i));
		 * hit.setTime(forwardTaggerBank.getNode("time").getFloat(i));
		 * hit.setPath(forwardTaggerBank.getNode("path").getFloat(i));
		 * hit.setChi2(forwardTaggerBank.getNode("chi2").getFloat(i));
		 * hit.setX(forwardTaggerBank.getNode("x").getFloat(i));
		 * hit.setY(forwardTaggerBank.getNode("y").getFloat(i));
		 * hit.setZ(forwardTaggerBank.getNode("z").getFloat(i));
		 * hit.setDx(forwardTaggerBank.getNode("dx").getFloat(i));
		 * hit.setDy(forwardTaggerBank.getNode("dy").getFloat(i));
		 * hit.setRadius(forwardTaggerBank.getNode("radius").getFloat(i));
		 * hit.setSize(forwardTaggerBank.getNode("size").getFloat(i));
		 * hit.setStatus(forwardTaggerBank.getNode("status").getShort(i));
		 * event.getParticles().get(hit.getPindex()).getHits().put(DetectorType.getType(
		 * hit.getDetector()), hit); } }
		 */
	}
}
