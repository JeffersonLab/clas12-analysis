package org.jlab.clas12.examples;
import java.awt.Dimension;

import javax.swing.JFrame;

import org.jlab.clas.pdg.PDGDatabase;
import org.jlab.clas.physics.LorentzVector;
import org.jlab.clas12.analysis.ClasAnalyzer;
import org.jlab.clas12.analysis.ClasEvent;
import org.jlab.clas12.analysis.ClasParticle;
import org.jlab.groot.data.H1F;
import org.jlab.groot.graphics.EmbeddedCanvas;

public class LambdaAnalyzer extends ClasAnalyzer {
	static EmbeddedCanvas canvas = new EmbeddedCanvas();
	static H1F hppim = new H1F("RhoMass", 200, 0.9, 2.0);
	static H1F hlambdamass = new H1F("RhoMassProton", 200, 0.9, 2.0);
	static H1F hmissingMass = new H1F("missingMass", 100, -1.5, 1.5);
	static H1F missingMassSelection = new H1F("missingMassSelection", 100, -1.5, 1.5);
	static H1F hmissingLambda =  new H1F("MissingLambda",200,0.9,2.0);
	static int counter1 = 0;
	static int counterTotal = 0;

	public static void main(String args[]) {
		hppim.setTitleX("M(p#pi^-) [GeV/c^2]");
		hppim.setTitleY("Counts");
		hmissingMass.setTitleX("MM(e^-p#pi^-K^+) [GeV/c^2]");
		hmissingMass.setTitleY("Counts");
		hlambdamass.setTitleX("M(p#pi^-) [GeV/c^2]");
		hlambdamass.setTitleY("Counts");
		hmissingLambda.setTitleX("MM(e^-K^+) [GeV/c^2]");
		hmissingLambda.setTitleY("Counts");

		missingMassSelection.setFillColor(42);
		hlambdamass.setFillColor(44);
		hmissingLambda.setFillColor(44);
		
		JFrame frame = new JFrame("Preliminary Lambda Analysis");
		canvas.divide(2, 2);
		canvas.setPreferredSize(new Dimension(1680, 1050));
		canvas.cd(0);
		canvas.draw(hppim);
		canvas.draw(hlambdamass, "same");
		canvas.cd(1);
		canvas.draw(hmissingMass);
		canvas.draw(missingMassSelection, "same");
		canvas.cd(2);
		canvas.draw(hlambdamass);
		canvas.cd(3);
		canvas.draw(hmissingLambda);
		canvas.initTimer(500);
		canvas.setFont("Avenir");
		canvas.setTitleSize(32);
		canvas.setAxisTitleSize(24);
		canvas.setAxisLabelSize(18);
		canvas.setStatBoxFontSize(18);
		canvas.setStatBoxFontSize(18);
		frame.add(canvas);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);

		LambdaAnalyzer analyzer = new LambdaAnalyzer();

		String dir = "/Volumes/External_HDD/skim11/"; 	// Skim 11 is w/electron in FT + K+ anywhere (RECFT)
		dir = "/Users/wphelps/Desktop/data/test_clas12_analyzer/"; 			// Skim 14 is w/electron in FD + K+ anywhere (REC)

//		analyzer.openDirectory(dir);
		analyzer.openFile(dir+"output.hipo");
//		analyzer.setHipoOutputFile(dir+"output.hipo");
//		analyzer.processEvents(100000);
		analyzer.processEvents();
		System.out.println("Total # of events written:"+counter1);
		System.out.println("Total # of events read:"+counterTotal);
	}

	@Override
	public boolean processEvent(ClasEvent event) {
		counterTotal += 1;
		event.setUseft(false); // Selects whether to use the RECFT bank or REC bank

		
		if (event.N(2212) == 1 && event.N(11) >= 1&&event.N(321)==1&&event.N(-211)==1&&event.N(-321)==0&&event.N(211)==0&&event.N(-2212)==0) {
			ClasParticle proton = event.getParticle(2212, 0);
			ClasParticle pim = event.getParticle(-211, 0);
			ClasParticle kp = event.getParticle(321, 0);
			ClasParticle electron = event.getParticle(11, 0);
			
			if (kp.getP4().p()<2.0 && kp.isFD() && electron.isFD()) {
				LorentzVector p_proton = proton.getP4();
				LorentzVector p_pim = pim.getP4();
				LorentzVector p_kp = kp.getP4();
				LorentzVector p_electron = electron.getP4();
				LorentzVector beam = new LorentzVector();
				beam.setPxPyPzM(0.0, 0.0, 10.594, PDGDatabase.getParticleMass(11));
				LorentzVector target = new LorentzVector();
				target.setPxPyPzE(0.0, 0.0, 0.0, 0.938);

				
				/**************** FT Electron energy correction ****************/
//				LorentzVector p_electron_pre_correction = electron.getP4();
//				double p_mag = p_electron_pre_correction.p();
//				double p_mag_corrected = 1+(-0.0004*Math.pow(p_mag,4)+0.0071*Math.pow(p_mag,3)-0.0432*Math.pow(p_mag,2)+0.1356*p_mag-0.0257)/p_mag;
////				double p_mag_corrected = -.001*Math.pow(p_mag,3)+0.025*Math.pow(p_mag,2)-0.149*p_mag+1.377;
////				System.out.println(p_mag_corrected);
//				p_electron = new LorentzVector();
//				p_electron.setPxPyPzM(p_electron_pre_correction.px()*p_mag_corrected, p_electron_pre_correction.py()*p_mag_corrected, p_electron_pre_correction.pz()*p_mag_corrected, PDGDatabase.getParticleMass(11));

				
				

				LorentzVector lambda = new LorentzVector();
				lambda.add(p_proton);
				lambda.add(p_pim);
				hppim.fill(lambda.mass());
				
				LorentzVector missingParticle = new LorentzVector();
				missingParticle.add(beam);
				missingParticle.add(target);
				missingParticle.sub(p_kp);
				missingParticle.sub(p_pim);
				missingParticle.sub(p_proton);
				missingParticle.sub(p_electron);
				
				LorentzVector missingElectron = new LorentzVector();
				missingElectron.add(beam);
				missingElectron.add(target);
				missingElectron.sub(p_kp);
				missingElectron.sub(p_pim);
				missingElectron.sub(p_proton);
				
				
				LorentzVector missingLambda = new LorentzVector();
				missingLambda.add(beam);
				missingLambda.add(target);
				missingLambda.sub(p_kp);
				missingLambda.sub(p_electron);
				
				
				hmissingMass.fill(missingParticle.mass2());
				
				if (Math.abs(missingParticle.mass2()) < 0.05 && missingParticle.vect().mag()<.1) {
					missingMassSelection.fill(missingParticle.mass2());
					if(missingLambda.mass2()>0) {
						double missingLambda_mass = Math.sqrt(missingLambda.mass2());
						hmissingLambda.fill(missingLambda_mass);
						hlambdamass.fill(lambda.mass());
					}
				}
				counter1 += 1;
				return true;
			}
		}
		return false;
	}

}
