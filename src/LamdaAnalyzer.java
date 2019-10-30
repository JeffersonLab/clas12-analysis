import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;

import org.jlab.clas.pdg.PDGDatabase;
import org.jlab.clas.physics.LorentzVector;
import org.jlab.detector.base.DetectorType;
import org.jlab.groot.data.H1F;
import org.jlab.groot.graphics.EmbeddedCanvas;

public class LamdaAnalyzer extends ClasAnalyzer {
	static EmbeddedCanvas canvas = new EmbeddedCanvas();
	static H1F rhoMass = new H1F("RhoMass", 100, 0.9, 1.5);
	static H1F rhoMassProtonSelected = new H1F("RhoMassProton", 100, 0.9, 1.5);
	static H1F missingMass = new H1F("missingMass", 100, -1.5, 1.5);
	static H1F missingMassSelection = new H1F("missingMassSelection", 100, -1.5, 1.5);
	static H1F kaonpion = new H1F("kaonpionMassProton", 100, 0.5, 1.5);

	//static H1F pionMass = new H1F("massOfPion", 1000, -1.5, 1.5);
	//static H1F ppipMass = new H1F("ppipMass", 100, .8, 2.0);


	public static void main(String args[]) {
		rhoMass.setTitleX("M(pk^+) [GeV/c^2]");
		rhoMass.setTitleY("Counts");
		rhoMass.setTitle("");
		missingMass.setTitleX("MM(e^-k^+p#pi^-) [GeV/c^2]");
		missingMass.setTitleY("Counts");
		rhoMassProtonSelected.setTitleX("M(p#pi^-) [GeV/c^2]");
		rhoMassProtonSelected.setTitleY("Counts");
		kaonpion.setTitleX("M(k^+#pi^-) [GeV/c^2]");
		kaonpion.setTitleY("Counts");

		missingMassSelection.setFillColor(42);
		rhoMassProtonSelected.setFillColor(44);
		kaonpion.setFillColor(44);
		
		JFrame frame = new JFrame("Number of Photoelectrons");
		canvas.divide(2, 2);
		canvas.setPreferredSize(new Dimension(1680, 1050));
		canvas.cd(0);
		canvas.draw(rhoMass);
		canvas.draw(rhoMassProtonSelected, "same");
		canvas.cd(1);
		canvas.draw(missingMass);
		canvas.draw(missingMassSelection, "same");
		canvas.cd(2);
		canvas.draw(rhoMassProtonSelected);
		canvas.initTimer(500);
		canvas.setFont("Avenir");
		canvas.setTitleSize(32);
		canvas.setAxisTitleSize(24);
		canvas.setAxisLabelSize(18);
		canvas.setStatBoxFontSize(18);
		canvas.setStatBoxFontSize(18);
		canvas.cd(3);
		canvas.draw(kaonpion);
		//canvas.draw(ppipMass);
		// canvas.cd(3);
		// canvas.draw(pionMass);
		frame.add(canvas);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);

		LamdaAnalyzer analyzer = new LamdaAnalyzer();
		// analyzer.openFile("/Users/wphelps/Desktop/rga/phys/phys_3105.hipo");
		// analyzer.openFile("/Users/wphelps/Desktop/rga/phys2_4013.hipo");
		// analyzer.openFile("/Users/wphelps/Desktop/rga/5bp3p1/phys2_4013.hipo");
		// analyzer.openFile("/Users/wphelps/Desktop/pass0/phys2_4013.hipo");
		// analyzer.processEvents();
		String dir = "/Users/wphelps/Desktop/rga/skim11_elect_ft_kaon/";
		File directory = new File(dir);
		analyzer.openFile(dir+"skim11_5039.hipo");
		analyzer.processEvents();
		analyzer.openFile(dir+"skim11_5040.hipo");
		analyzer.processEvents();

//		String dir = args[0];
//		File directory = new File(dir);
//		String[] filesList = directory.list();
//		for (int i = 0; i < filesList.length; i++) {
//			try {
//				analyzer.openFile(dir + filesList[i]);
//				analyzer.processEvents();
//			} catch (Exception e) {
//			}
//		}
		//analyzer.openFile(dir+"skim4_5036.hipo");
		//analyzer.processEvents();
	}

	@Override
	boolean processEvent(ClasEvent event) {
		event.setUseft(true);
		if (event.N(2212) == 1 && event.N(11) == 1&&event.N(321)==1&&event.N(-211)==1) {
			ClasParticle proton = event.getParticle(2212, 0);
			ClasParticle pim = event.getParticle(-211, 0);
			ClasParticle kp = event.getParticle(321, 0);
			//System.out.println("pip status:"+pip.getStatus());
			ClasParticle electron = event.getParticle(11, 0);

			if (kp.getP4().p()<2.0) {//(electron.getStatus() < 2000 ) {
				// if (pip.getStatus() > 2000 && pip.getStatus() < 3000 && pim.getStatus() >
				// 2000 && pim.getStatus() < 3000) {
				LorentzVector p_proton = proton.getP4();
				LorentzVector p_pim = pim.getP4();
				LorentzVector p_kp = kp.getP4();
				LorentzVector p_electron = electron.getP4();

				LorentzVector beam = new LorentzVector();
				// beam.setPxPyPzE(0.0, 0.0, 6.413,
				// Math.sqrt(Math.pow(6.413, 2) + Math.pow(PDGDatabase.getParticleMass(11),
				// 2)));
				beam.setPxPyPzE(0.0, 0.0, 10.594, Math.sqrt(Math.pow(10.594, 2) + Math.pow(PDGDatabase.getParticleMass(11), 2)));

				LorentzVector target = new LorentzVector();
				target.setPxPyPzE(0.0, 0.0, 0.0, 0.938);

				LorentzVector rho = new LorentzVector();
				rho.add(p_proton);
				rho.add(p_pim);
				rhoMass.fill(rho.mass());
				
				LorentzVector ppipVec = new LorentzVector();
				// pionMass.fill(p_pip.mass());
				ppipVec.add(beam);
				ppipVec.add(target);
				ppipVec.sub(p_proton);
				ppipVec.sub(p_electron);
				//ppipMass.fill(ppipVec.mass());

				LorentzVector missingParticle = new LorentzVector();
				missingParticle.add(beam);
				missingParticle.add(target);
				missingParticle.sub(p_kp);
				missingParticle.sub(p_pim);
				missingParticle.sub(p_proton);
				missingParticle.sub(p_electron);
				missingMass.fill(missingParticle.mass2());
				
				LorentzVector kpimass = new LorentzVector();
				kpimass.add(p_kp);
				kpimass.add(p_pim);

				if (Math.abs(missingParticle.mass2()) < .25) {
					missingMassSelection.fill(missingParticle.mass2());
					rhoMassProtonSelected.fill(rho.mass());
					kaonpion.fill(kpimass.mass());
				}
				
			}

			// }
		}
		return false;
	}

}
