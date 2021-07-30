package org.jlab.clas12.analysis;
import org.jlab.clas.pdg.PDGDatabase;
import org.jlab.clas.pdg.PDGParticle;
import org.jlab.detector.base.DetectorType;
import org.jlab.jnp.hipo4.data.Bank;
import org.jlab.jnp.hipo4.data.Event;
import org.jlab.jnp.hipo4.data.SchemaFactory;
import org.jlab.jnp.hipo4.io.HipoReader;

public class ClasEventBuilder {


	public static void buildEvent(HipoReader reader, ClasEvent event) {
		SchemaFactory factory = reader.getSchemaFactory();
		Event hipoEvent = new Event();
		reader.nextEvent(hipoEvent);
		if (hipoEvent.hasBank(factory.getSchema("REC::Event"))) {
			boolean hasRECFTEvent = hipoEvent.hasBank(factory.getSchema("RECFT::Event"));
			Bank recfteventbank = new Bank(factory.getSchema("RECFT::Event"));
			Bank bank = new Bank(factory.getSchema("REC::Event"));
			hipoEvent.read(bank);
//			System.out.println(factory.getSchema("REC::Event"));
//		event.setRunNumber(bank.getInt("NRUN",0));
//		event.setEventNumber(bank.getInt("NEVENT",0));
//		event.setEventTime(bank.getFloat("EVNTime",0));
//		event.setType((int) bank.getByte("TYPE",0));
//		event.setEventCategory(bank.getShort("EvCAT",0));
//		event.setNPGP(bank.getShort("NPGP",0));
//		event.setTRG(bank.getLong("TRG",0));
//		event.setBcg(bank.getFloat("BCG",0));
//		event.setClock(bank.getDouble("LT",0));
			event.setStartTime(bank.getFloat("startTime", 0));
//		event.setRFTime(bank.getFloat("RFTime",0));
//		event.setHelicity((int) bank.getByte("Helic",0));
//		event.setProcessingTime(bank.getFloat("PTIME",0));
			if (hasRECFTEvent) {
				hipoEvent.read(recfteventbank);
				event.setStartTimeFT(recfteventbank.getFloat("startTime", 0));
			}
			boolean hasFTBank = hipoEvent.hasBank(factory.getSchema("RECFT::Particle"));

			if (hipoEvent.hasBank(factory.getSchema("REC::Particle"))) {
				Bank partBank = new Bank(factory.getSchema("REC::Particle"));
				Bank recFTBank = new Bank(factory.getSchema("RECFT::Particle"));
				hipoEvent.read(partBank);
				if (hasFTBank) {
					hipoEvent.read(recFTBank);
				}
				for (int i = 0; i < partBank.getRows(); i++) {
					ClasParticle particle = new ClasParticle();
					//int charge = ((int) partBank.getInt("charge",i));
					//System.out.println("PID:"+partBank.getInt("pid",i)+"Charge="+charge+" i="+i+" length:"+partBank.getRows());
					particle.setPid(partBank.getInt("pid", i));
					particle.setCharge((int) partBank.getByte("charge", i));
//					System.out.println("Pid:"+particle.getPid()+" Charge:"+particle.getCharge()+"Charge bytes:"+partBank.getByte("charge",i));
					particle.setBeta(partBank.getFloat("beta", i));
					particle.setChi2pid(partBank.getFloat("chi2pid", i));
					particle.setStatus(partBank.getShort("status", i));

					double vx = partBank.getFloat("vx", i);
					double vy = partBank.getFloat("vy", i);
					double vz = partBank.getFloat("vz", i);

					double px = partBank.getFloat("px", i);
					double py = partBank.getFloat("py", i);
					double pz = partBank.getFloat("pz", i);

					if (hasFTBank) {
						particle.setPidFT(recFTBank.getInt("pid", i));
//					System.out.println("RecFT PID:"+recFTBank.getInt("pid",i));
						particle.setChi2pidFT(recFTBank.getFloat("chi2pid", i));
						particle.setBetaFT(recFTBank.getFloat("beta", i));
						particle.setStatusFT(recFTBank.getShort("status", i));
//					System.out.println("PID:"+recFTBank.getInt("pid",i)+"Beta="+recFTBank.getFloat("beta",i)+" chi2="+recFTBank.getFloat("chi2pid",i)+" status:"+recFTBank.getShort("status",i));

					}


					particle.getP4().setPxPyPzM(px, py, pz, 0);
					particle.setMass();

//				if (particle.getPid() != 0) {
//					try {
//						PDGParticle pgd = PDGDatabase.getParticleById(particle.getPid());
//						particle.getP4().setPxPyPzM(px, py, pz, pgd.mass());
//					}catch(Exception e) {
//						System.out.println("Some messed up PID:"+particle.getPid()+" i"+i + " "+px+" "+py+" "+pz  );
//						particle.getP4().setPxPyPzM(px, py, pz,
//								Math.sqrt(px * px + py * py + pz * pz) / particle.getBeta());
//					}
//				} else {
//					particle.getP4().setPxPyPzM(px, py, pz,
//							Math.sqrt(px * px + py * py + pz * pz) / particle.getBeta());
//				}
					particle.getVertex().setXYZ(vx, vy, vz);

					event.getParticles().add(particle);
				}
			}

			if (hipoEvent.hasBank(factory.getSchema("REC::Cherenkov"))) {
				Bank cherenkovBank = new Bank(factory.getSchema("REC::Cherenkov"));
				hipoEvent.read(cherenkovBank);
				for (int i = 0; i < cherenkovBank.getRows(); i++) {
					CherenkovHit hit = new CherenkovHit();
					hit.setIndex(cherenkovBank.getShort("index", i));
					hit.setPindex(cherenkovBank.getShort("pindex", i));
					hit.setDetector(cherenkovBank.getByte("detector", i));
					hit.setSector(cherenkovBank.getByte("sector", i));
					hit.setTime(cherenkovBank.getFloat("time", i));
					hit.setPath(cherenkovBank.getFloat("path", i));
					hit.setNphe(cherenkovBank.getFloat("nphe", i));
					hit.setX(cherenkovBank.getFloat("x", i));
					hit.setY(cherenkovBank.getFloat("y", i));
					hit.setZ(cherenkovBank.getFloat("z", i));
					hit.setChi2(cherenkovBank.getFloat("chi2", i));
					hit.setStatus(cherenkovBank.getShort("status", i));
					event.getParticles().get(hit.getPindex()).getHits().put(DetectorType.getType(hit.getDetector()), hit);
				}
			}

			if (hipoEvent.hasBank(factory.getSchema("REC::Calorimeter"))) {
				Bank calorimeterBank = new Bank(factory.getSchema("REC::Calorimeter"));
				hipoEvent.read(calorimeterBank);
				for (int i = 0; i < calorimeterBank.getRows(); i++) {
					CalorimeterHit hit = new CalorimeterHit();
					hit.setIndex(calorimeterBank.getShort("index", i));
					hit.setPindex(calorimeterBank.getShort("pindex", i));
					hit.setDetector(calorimeterBank.getByte("detector", i));
					hit.setSector(calorimeterBank.getByte("sector", i));
					hit.setLayer(calorimeterBank.getByte("layer", i));
					hit.setEnergy(calorimeterBank.getFloat("energy", i));
					hit.setTime(calorimeterBank.getFloat("time", i));
					hit.setPath(calorimeterBank.getFloat("path", i));
					hit.setChi2(calorimeterBank.getFloat("chi2", i));
					hit.setX(calorimeterBank.getFloat("x", i));
					hit.setY(calorimeterBank.getFloat("y", i));
					hit.setZ(calorimeterBank.getFloat("z", i));
					hit.setHx(calorimeterBank.getFloat("hx", i));
					hit.setHy(calorimeterBank.getFloat("hy", i));
					hit.setHz(calorimeterBank.getFloat("hz", i));
					hit.setLu(calorimeterBank.getFloat("lu", i));
					hit.setLv(calorimeterBank.getFloat("lv", i));
					hit.setLw(calorimeterBank.getFloat("lw", i));
					hit.setDu(calorimeterBank.getFloat("du", i));
					hit.setDv(calorimeterBank.getFloat("dv", i));
					hit.setDw(calorimeterBank.getFloat("dw", i));
					// hit.setM2u(calorimeterBank.getNode("m2u").getFloat(i));
					// hit.setM2v(calorimeterBank.getNode("m2v").getFloat(i));
					// hit.setM2w(calorimeterBank.getNode("m2w").getFloat(i));
					// hit.setM3u(calorimeterBank.getNode("m3u").getFloat(i));
					// hit.setM3v(calorimeterBank.getNode("m3v").getFloat(i));
					// hit.setM3w(calorimeterBank.getNode("m3w").getFloat(i));
					hit.setStatus(calorimeterBank.getShort("status", i));
					//System.out.println(DetectorType.getType(hit.getDetector()));
					event.getParticles().get(hit.getPindex()).getHits().put(DetectorType.getType(hit.getDetector()), hit);
//					System.out.println(hit.getEnergy());
				}
			}

			if (hipoEvent.hasBank(factory.getSchema("REC::Scintillator"))) {
				Bank scintillatorBank = new Bank(factory.getSchema("REC::Scintillator"));
				hipoEvent.read(scintillatorBank);
				for (int i = 0; i < scintillatorBank.getRows(); i++) {
					ScintillatorHit hit = new ScintillatorHit();
					hit.setIndex(scintillatorBank.getShort("index", i));
					hit.setPindex(scintillatorBank.getShort("pindex", i));
					hit.setDetector(scintillatorBank.getByte("detector", i));
					hit.setSector(scintillatorBank.getByte("sector", i));
					hit.setLayer(scintillatorBank.getByte("layer", i));
					hit.setEnergy(scintillatorBank.getFloat("energy", i));
					hit.setTime(scintillatorBank.getFloat("time", i));
					hit.setPath(scintillatorBank.getFloat("path", i));
					hit.setChi2(scintillatorBank.getFloat("chi2", i));
					hit.setX(scintillatorBank.getFloat("x", i));
					hit.setY(scintillatorBank.getFloat("y", i));
					hit.setZ(scintillatorBank.getFloat("z", i));
					hit.setHx(scintillatorBank.getFloat("hx", i));
					hit.setHy(scintillatorBank.getFloat("hy", i));
					hit.setHz(scintillatorBank.getFloat("hz", i));
					hit.setStatus(scintillatorBank.getShort("status", i));
					event.getParticles().get(hit.getPindex()).getHits().put(DetectorType.getType(hit.getDetector()), hit);
				}
			}

//		if (hipoEvent.hasGroup("REC::CovMat")) {
//			HipoGroup trajectoryBank = hipoEvent.getGroup("REC::CovMat");
//			for (int i = 0; i < trajectoryBank.getNode("pindex").getDataSize(); i++) {
//				float[][] covarianceMatrix = event.getParticles().get(trajectoryBank.getNode("pindex").getShort(i))
//						.getCovarianceMatrix();
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C11").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C13").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C14").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C15").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C22").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C23").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C24").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C25").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C33").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C34").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C35").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C44").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C45").getFloat(i);
//				covarianceMatrix[0][0] = trajectoryBank.getNode("C55").getFloat(i);
//			}
//
//		}
//
//		/*
//		 * for (String string : hipoEvent.getGroupList()) {
//		 * System.out.println(string);
//		}*/
//
			if (hipoEvent.hasBank(factory.getSchema("REC::Traj"))) {
				Bank trajectoryBank = new Bank(factory.getSchema("REC::Traj"));
				hipoEvent.read(trajectoryBank);
				for (int i = 0; i < trajectoryBank.getRows(); i++) {
					ParticleTrajectory trajectory = new ParticleTrajectory();
					trajectory.setPindex(trajectoryBank.getShort("pindex", i));
					trajectory.setIndex(trajectoryBank.getShort("index", i));
					trajectory.setDetId(trajectoryBank.getByte("detector", i));
					trajectory.setX(trajectoryBank.getFloat("x", i));
					trajectory.setY(trajectoryBank.getFloat("y", i));
					trajectory.setZ(trajectoryBank.getFloat("z", i));
					trajectory.setCx(trajectoryBank.getFloat("cx", i));
					trajectory.setCy(trajectoryBank.getFloat("cy", i));
					trajectory.setCz(trajectoryBank.getFloat("cz", i));
					trajectory.setPathlength(trajectoryBank.getFloat("path", i));
					event.getParticles().get(trajectory.getPindex()).getTrajectoryInfo().add(trajectory);
				}
			}
/*
		if (hipoEvent.hasGroup("REC::Track")) {
			HipoGroup ParticleTrackBank = hipoEvent.getGroup("REC::Track");
			for (int i = 0; i < ParticleTrackBank.getNode("pindex").getDataSize(); i++) {
				ParticleTrack hit = new ParticleTrack();
				hit.setIndex(ParticleTrackBank.getNode("index").getShort(i));
				hit.setPindex(ParticleTrackBank.getNode("pindex").getShort(i));
				hit.setDetector(ParticleTrackBank.getNode("detector").getByte(i));
				hit.setSector(ParticleTrackBank.getNode("sector").getByte(i));
				hit.setStatus(ParticleTrackBank.getNode("status").getShort(i));
				hit.setQ(ParticleTrackBank.getNode("q").getByte(i));
				hit.setChi2(ParticleTrackBank.getNode("chi2").getByte(i));
				hit.setNDF((int) ParticleTrackBank.getNode("ndf").getByte(i));
				hit.setPx_nomm(ParticleTrackBank.getNode("px_nomm").getFloat(i));
				hit.setPy_nomm(ParticleTrackBank.getNode("py_nomm").getFloat(i));
				hit.setPz_nomm(ParticleTrackBank.getNode("pz_nomm").getFloat(i));
				hit.setVx_nomm(ParticleTrackBank.getNode("vx_nomm").getFloat(i));
				hit.setVy_nomm(ParticleTrackBank.getNode("vy_nomm").getFloat(i));
				hit.setVz_nomm(ParticleTrackBank.getNode("vz_nomm").getFloat(i));

			}
		}*/


			//************* Not sure what to do with the VertaDoca ******************* if
			if (hipoEvent.hasBank(factory.getSchema("REC::VertDoca"))) {
				Bank docaBank = new Bank(factory.getSchema("REC::VertDoca"));
				hipoEvent.read(docaBank);
				for (int i = 0; i < docaBank.getRows(); ++i) {
					VertDoca doca = new VertDoca();
					doca.setIndex1(docaBank.getShort("index1", i));
					doca.setIndex2(docaBank.getShort("index2", i));

					doca.getCommon().setXYZ(docaBank.getFloat("x", i), docaBank.getFloat("y", i), docaBank.getFloat("z", i));

					doca.getPosTrack1().setXYZ(docaBank.getFloat("x1",i), docaBank.getFloat("y1", i), docaBank.getFloat("z1", i));

					doca.getDirectTrack1().setXYZ(docaBank.getFloat("cx1", i), docaBank.getFloat("cy1", i), docaBank.getFloat("cz1", i));
					doca.getPosTrack2().setXYZ(docaBank.getFloat("x2", i), docaBank.getFloat("y2", i), docaBank.getFloat("z2", i));

					doca.getDirectTrack2().setXYZ(docaBank.getFloat("cx2", i), docaBank.getFloat("cy2", i), docaBank.getFloat("cz2", i));
					doca.setR(docaBank.getFloat("r", i));
					event.getDOCAS().add(doca);
//					System.out.println("Vert Doca Exists");

				}
//		 * VertaDBank.getNode("index1").getDataSize(); i++) { VertaDoca hit = new
//		 * VertaDoca();
//		 *
//		 * trajectory.setPindex(trajectoryBank.getNode("pindex").getShort(i));
//		 *
//		 * setIndex1(VertaDBank.getNode("index1").getShort(i));
//		 * hit.setPindex(scintillatorBank.getNode("pindex").getShort(i));
//		 * hit.setDetector(scintillatorBank.getNode("detector").getfloat(i));
//		 * hit.setSector(scintillatorBank.getNode("sector").getfloat(i));
//		 * hit.setLayer(scintillatorBank.getNode("layer").getfloat(i));
//		 * hit.setEnergy(scintillatorBank.getNode("energy").getFloat(i));
//		 * hit.setTime(scintillatorBank.getNode("time").getFloat(i));
//		 * hit.setPath(scintillatorBank.getNode("path").getFloat(i));
//		 * hit.setChi2(scintillatorBank.getNode("chi2").getFloat(i));
//		 * hit.setX(scintillatorBank.getNode("x").getFloat(i));
//		 * hit.setY(scintillatorBank.getNode("y").getFloat(i));
//		 * hit.setZ(scintillatorBank.getNode("z").getFloat(i));
//		 * hit.setHx(scintillatorBank.getNode("hx").getFloat(i));
//		 * hit.setHy(scintillatorBank.getNode("hy").getFloat(i));
//		 * hit.setHz(scintillatorBank.getNode("hz").getFloat(i));
//		 * hit.setStatus(scintillatorBank.getNode("status").getfloat(i));
//		 * event.getParticles().get(hit.getPindex()).getHits().put(DetectorType.getType(
//		 * hit.getDetector()), hit); } }
//		 */
				/*
				 * ************************************************
				 *
				 *
				 * /* if (hipoEvent.hasGroup("REC::ForwardTagger")) { HipoGroup
				 * forwardTaggerBank = hipoEvent.getGroup("REC::Scintillator"); for (int i = 0;
				 * i < forwardTaggerBank.getNode("pindex").getDataSize(); i++) {
				 * ForwardTaggerHit hit = new ForwardTaggerHit();
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
	}
}