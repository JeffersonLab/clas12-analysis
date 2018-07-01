import java.awt.Dimension;

import javax.swing.JFrame;

import org.jlab.clas.pdg.PhysicsConstants;
import org.jlab.clas.physics.LorentzVector;
import org.jlab.detector.base.DetectorType;
import org.jlab.groot.base.GStyle;
import org.jlab.groot.data.H1F;
import org.jlab.groot.data.H2F;
import org.jlab.groot.graphics.EmbeddedCanvas;
import org.jlab.groot.math.F1D;

public class Pi0Analyzer extends ClasAnalyzer {

	static EmbeddedCanvas can = new EmbeddedCanvas();
	static H1F nphe = new H1F("NPE", 50, 0, 50);
	static H1F momentum_pos = new H1F("Momentum_pos", 100, 0, 10);
	static H1F momentum_neg = new H1F("Momentum_neg", 100, 0, 10);
	static H1F momentum_all = new H1F("Momentum_all", 100, 0, 10);
	static H1F particleTiming = new H1F("Timing Check", 1000, -10, 10);
	static H1F ftof_mass = new H1F("ftofMass", 200, 0.001, 1.5);
	static H1F threepi_mass = new H1F("ftofMass", 50, 0.000, 1.0);
	static H1F twogamma_mass = new H1F("ftofMass", 70, 0.000, 1.0);
	static H1F twogamma_mass_pi0 = new H1F("ftofMass_pi0", 70, 0.000, 1.0);
	static H1F twokaon = new H1F("twoKaon", 50, .9, 1.9);
	static H1F twopion = new H1F("twoPion", 100, 0.0, 1.5);
	static H2F betavsp_forwardtagger = new H2F("NPE", 200, 0, 5, 200, .1, 1.2);
	static H2F betavsp_central = new H2F("NPE", 200, 0, 5, 200, .1, 1.2);
	static H2F betavsp_forward = new H2F("NPE", 200, 0, 5, 200, .1, 1.2);
	static H2F nphevsp_pions = new H2F("NPE", 100, 0, 7, 50, 0, 50);
	static H2F nphevsp_electrons = new H2F("NPE_electrons", 100, 0, 7, 50, 0, 50);
	static H2F nphevsp_protons = new H2F("NPE_protons", 100, 0, 7, 50, 0, 50);
	static H2F thetavsp_electrons = new H2F("theta_vs_electrons", 100, 0, 45,100,0,7);
	static H2F npevstheta_electrons = new H2F("NPE_vs_theta_electrons", 100, 0, 45,50,0,50);
	static H2F npevsphi_electrons = new H2F("NPE_vs_phi_electrons", 100, -180, 180,50,0,50);

	
	public static void main(String[] args) {
		initCanvas();

		Pi0Analyzer analyzer = new Pi0Analyzer();
		for (int i = 0; i < args.length; i++) {
			// analyzer.openFile("/Users/wphelps/Desktop/rga/phys2_3938.hipo");
			analyzer.openFile(args[i]);

			// analyzer.setHipoOutputFile("/Users/wphelps/Desktop/rga/phys/phys_3105_twogamma.hipo");
			analyzer.processEvents();
		}

		if (args.length == 0) {
			//analyzer.openFile("/Users/wphelps/Desktop/rga/4013Timing.hipo");
			analyzer.openFile("/Users/wphelps/Desktop/rga/phys/phys_3105.hipo");

			analyzer.processEvents();
		}

		GStyle.getAxisAttributesX().setTitleFontSize(18);
		GStyle.getAxisAttributesY().setTitleFontSize(18);
		GStyle.getAxisAttributesX().setLabelFontSize(16);
		GStyle.getAxisAttributesY().setLabelFontSize(16);

	}

	private static void initCanvas() {
		nphe.setTitleX("Number of Photoelectrons");
		nphe.setTitleY("Number of Events");
		nphe.setTitle("HTCC Response for Electrons");

		momentum_all.setTitleX("Momentum of particles [GeV/c]");
		momentum_all.setTitleY("Number of Events");
		// momentum_all.setTitle("");

		betavsp_central.setTitleX("Momentum of particles [GeV/c]");
		betavsp_central.setTitleY("#beta");
		betavsp_central.setTitle("#beta vs p: #theta>35^o");

		betavsp_forward.setTitleX("Momentum of particles [GeV/c]");
		betavsp_forward.setTitleY("#beta");
		betavsp_forward.setTitle("#beta vs p: #theta<35^o");

		betavsp_forwardtagger.setTitleX("Momentum of particles [GeV/c]");
		betavsp_forwardtagger.setTitleY("#beta");
		betavsp_forwardtagger.setTitle("#beta vs p: #theta<5^o");

		nphevsp_pions.setTitleX("Momentum of particles [GeV/c]");
		nphevsp_pions.setTitleY("Number of Photoelectrons");
		nphevsp_pions.setTitle("HTCC Response for Pions");

		nphevsp_electrons.setTitleX("Momentum of particles [GeV/c]");
		nphevsp_electrons.setTitleY("Number of Photoelectrons");
		nphevsp_electrons.setTitle("HTCC Response for Electrons");

		nphevsp_protons.setTitleX("Momentum of particles [GeV/c]");
		nphevsp_protons.setTitleY("Number of Photoelectrons");
		nphevsp_protons.setTitle("HTCC Response for Protons");
		
		thetavsp_electrons.setTitleX("Electron #theta [deg]");
		thetavsp_electrons.setTitleY("Electron momentum [GeV/c]");

		npevstheta_electrons.setTitleX("Electron #theta [deg]");
		npevstheta_electrons.setTitleY("NPE");
		
		npevsphi_electrons.setTitleX("Electron #phi [deg]");
		npevsphi_electrons.setTitleY("NPE");


		ftof_mass.setTitleX("Mass [GeV/c^2]");
		ftof_mass.setTitleY("Counts");
		ftof_mass.setTitle("FTOF Mass");

		twokaon.setTitleX("M(k^+k^-) [GeV/c^2]");
		twokaon.setTitleY("Counts");

		twopion.setTitleX("M(#pi^+#pi^-) [GeV/c^2]");
		twopion.setTitleY("Counts");

		threepi_mass.setTitleX("M(#pi^+#pi^-#pi^0) [GeV/c^2]");
		threepi_mass.setTitleY("Counts");

		twogamma_mass.setTitleX("M(#gamma#gamma) [GeV/c^2]");
		twogamma_mass.setTitleY("Counts");

		F1D pion = new F1D("pion", "x/sqrt(.139*.139+x*x)", 0.3, 5.0);
		F1D kaon = new F1D("kaon", "x/sqrt(.494*.494+x*x)", 0.3, 5.0);
		F1D proton = new F1D("proton", "x/sqrt(.938*.938+x*x)", 0.3, 5.0);

		JFrame frame = new JFrame("Number of Photoelectrons");
		can.setPreferredSize(new Dimension(1680, 1050));
		int pad = 0;
		can.divide(5, 4);
		can.cd(pad++);
		can.draw(nphe);
		can.cd(pad++);
		can.draw(betavsp_central);
		can.draw(pion, "same");
		can.draw(kaon, "same");
		can.draw(proton, "same");
		can.cd(pad++);
		can.draw(betavsp_forward);
		can.draw(pion, "same");
		can.draw(kaon, "same");
		can.draw(proton, "same");
		can.cd(pad++);
		can.draw(betavsp_forwardtagger);
		can.draw(pion, "same");
		can.draw(kaon, "same");
		can.draw(proton, "same");
		can.cd(pad++);
		can.draw(momentum_all);
		can.draw(momentum_pos, "same");
		can.draw(momentum_neg, "same");
		can.cd(pad++);
		can.draw(ftof_mass);
		can.cd(pad++);
		can.draw(twokaon);
		can.cd(pad++);
		can.draw(twopion);
		momentum_pos.setLineColor(44);
		momentum_pos.setFillColor(44);
		momentum_neg.setLineColor(42);
		momentum_neg.setFillColor(42);
		twogamma_mass_pi0.setFillColor(42);

		can.cd(pad++);
		can.draw(nphevsp_pions);
		can.cd(pad++);
		can.draw(nphevsp_electrons);
		can.cd(pad++);
		can.draw(nphevsp_protons);
		can.cd(pad++);
		can.draw(particleTiming);
		can.cd(pad++);
		can.draw(twogamma_mass);
		can.draw(twogamma_mass_pi0, "same");
		can.cd(pad++);
		can.draw(threepi_mass);
		can.cd(pad++);
		can.draw(thetavsp_electrons);
		can.cd(pad++);
		can.draw(npevstheta_electrons);
		can.cd(pad++);
		can.draw(npevsphi_electrons);

		can.initTimer(500);
		frame.add(can);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);

	}

	@Override
	boolean processEvent(ClasEvent event) {
		boolean writeEvent = false;
		if (event.getStartTime() < 0) {
			return false;
		}

		if (event.N(211) == 1 && event.N(-211) == 1 && event.N(11) > 0) {
			ClasParticle pip = event.getParticle(211, 0);
			ClasParticle pim = event.getParticle(-211, 0);
			LorentzVector rho = new LorentzVector(pip.getP4());
			rho.add(pim.getP4());
			twopion.fill(rho.mass());
		}

		if (event.N(321) == 1 && event.N(-321) == 1 && event.N(11) > 0) {
			ClasParticle kp = event.getParticle(321, 0);
			ClasParticle km = event.getParticle(-321, 0);
			LorentzVector phi = new LorentzVector(kp.getP4());
			phi.add(km.getP4());
			twokaon.fill(phi.mass());
		}

		if (event.N(22) == 2 && event.N(11) == 1 && event.N(211) == 1 && event.N(-211) == 1) {
			ClasParticle gamma1 = event.getParticle(22, 0);
			ClasParticle gamma2 = event.getParticle(22, 1);
			ClasParticle electron1 = event.getParticle(11, 0);
			ClasParticle pip = event.getParticle(211, 0);
			ClasParticle pim = event.getParticle(-211, 0);

			if (gamma1 != null && gamma2 != null && pip != null && pim != null && electron1 != null) {
				LorentzVector pgamma1 = gamma1.getP4();
				LorentzVector pgamma2 = gamma2.getP4();
				LorentzVector ppip = pip.getP4();
				LorentzVector ppim = pim.getP4();
				LorentzVector pelectron = electron1.getP4();
				LorentzVector pi0 = new LorentzVector(pgamma1);
				pi0.add(pgamma2);
				LorentzVector omega = new LorentzVector(pi0);
				omega.add(ppip);
				omega.add(ppim);

				twogamma_mass.fill(pi0.mass());
				if (Math.abs(pi0.mass() - .135) < .075) {
					twogamma_mass_pi0.fill(pi0.mass());
					threepi_mass.fill(omega.mass());
				}
			}
		}

		for (int i = 0; i < event.getParticles().size(); i++) {
			ClasParticle particle = event.getParticles().get(i);
			if (i == 0 && particle.getPid() != 11)
				break;

			// if(Math.abs(event.getStartTime()-particle.get
			if (particle.getCharge() != 0 && particle.getPid() != 0) {
				momentum_all.fill(particle.getP4().p());
				if (particle.isDetectorHit(DetectorType.FTOF)) {
					ScintillatorHit hit = (ScintillatorHit) particle.getDetectorHit(DetectorType.FTOF);
					particleTiming.fill(hit.getTime() - event.getStartTime()
							- (hit.getPath() / (particle.getBeta() * PhysicsConstants.speedOfLight())));
				}
				if (particle.getCharge() > 0) {
					momentum_pos.fill(particle.getP4().p());
				} else if (particle.getCharge() < 0) {
					momentum_neg.fill(particle.getP4().p());
				}
				if (i != 0) {
					if (Math.toDegrees(particle.getP4().theta()) > 35.) {
						betavsp_central.fill(particle.getP4().p(), particle.getBeta());
					} else if (Math.toDegrees(particle.getP4().theta()) > 5.) {
						betavsp_forward.fill(particle.getP4().p(), particle.getBeta());
						ftof_mass.fill(Math.sqrt(Math.pow(particle.getP4().p() / particle.getBeta(), 2)
								- Math.pow(particle.getP4().p(), 2)));

					} else if (Math.toDegrees(particle.getP4().theta()) < 5.) {
						betavsp_forwardtagger.fill(particle.getP4().p(), particle.getBeta());
					}
				}
			}
		}
		if (event.N(11) == 1) {
			writeEvent = true;
			ClasParticle electron = event.getParticles().get(0);
			if (electron.isDetectorHit(DetectorType.HTCC)) {
				CherenkovHit hit = (CherenkovHit) electron.getDetectorHit(DetectorType.HTCC);
				// System.out.println("Sector electron #1:"+ hit.getSector()+"
				// Phi:"+hit.getPhi()+" Theta:"+hit.getTheta() + " nphe:"+hit.getNphe());
				nphe.fill(hit.getNphe());
				thetavsp_electrons.fill(Math.toDegrees(electron.getP4().theta()), electron.getP4().p());
				npevstheta_electrons.fill(Math.toDegrees(electron.getP4().theta()), hit.getNphe());
				npevsphi_electrons.fill(Math.toDegrees(electron.getP4().phi()), hit.getNphe());
			}
		}
		if (event.N(211) > 0 || event.N(-211) > 0) {
			for (ClasParticle particle : event.getParticles()) {
				if (particle.getPid() == 211 || particle.getPid() == -211) {
					if (particle.isDetectorHit(DetectorType.HTCC)) {
						CherenkovHit hit = (CherenkovHit) particle.getDetectorHit(DetectorType.HTCC);
						nphevsp_pions.fill(particle.getP4().p(), hit.getNphe());
					}
				}

			}
		}
		if (event.N(11) > 0) {
			for (ClasParticle particle : event.getParticles()) {
				if (particle.getPid() == 11) {
					if (particle.isDetectorHit(DetectorType.HTCC)) {
						CherenkovHit hit = (CherenkovHit) particle.getDetectorHit(DetectorType.HTCC);
						nphevsp_electrons.fill(particle.getP4().p(), hit.getNphe());
					
					}
				}

			}
		}
		if (event.N(2212) > 0 || event.N(-2212) > 0) {
			for (ClasParticle particle : event.getParticles()) {
				if (particle.getPid() == 2212 || particle.getPid() == -2212) {
					if (particle.isDetectorHit(DetectorType.HTCC)) {
						CherenkovHit hit = (CherenkovHit) particle.getDetectorHit(DetectorType.HTCC);
						nphevsp_protons.fill(particle.getP4().p(), hit.getNphe());
					}
				}

			}
		}
		return writeEvent;
	}

}
