package org.jlab.clas12.examples;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;

import org.jlab.clas.physics.LorentzVector;
import org.jlab.clas12.analysis.ClasAnalyzer;
import org.jlab.clas12.analysis.ClasEvent;
import org.jlab.clas12.analysis.ClasParticle;
import org.jlab.groot.data.H1F;
import org.jlab.groot.graphics.EmbeddedCanvas;

public class OmegaAnalyzer extends ClasAnalyzer {
	static EmbeddedCanvas canvas = new EmbeddedCanvas();
	static H1F omegaMass = new H1F("RhoMass",100,0,1.5);	
	static H1F omegaProtonSelected = new H1F("RhoMassProton",100,0,1.5);
	static H1F pi0Mass = new H1F("RhoMass",100,0,1.5);	
	static H1F missingMass = new H1F("missingMass",100,-1.0,1.0);
	static H1F missingMassSelection = new H1F("missingMassSelection",100,-1.0,1.0);
	

	public static void main(String args[]) {	
		omegaMass.setTitleX("M(#pi^+#pi^-) [GeV/c^2]");
		omegaMass.setTitleY("Counts");
		omegaMass.setTitle("M(3#pi)");
		missingMass.setTitleX("MM^2(e^-#pi^+#pi^-#gamma#gamma) [GeV/c^2]");
		missingMass.setTitleY("Counts");
		omegaProtonSelected.setTitleX("M(#pi^+#pi^-#pi^0) [GeV/c^2]");
		omegaProtonSelected.setTitleY("Counts");

		missingMassSelection.setFillColor(42);
		omegaProtonSelected.setFillColor(44);
		
		
		JFrame frame = new JFrame("Number of Photoelectrons");
		canvas.divide(2, 2);
		canvas.setPreferredSize(new Dimension(1680, 1050));
		canvas.cd(0);
		canvas.draw(omegaMass);
		canvas.draw(omegaProtonSelected,"same");
		canvas.cd(1);
		canvas.draw(missingMass);
		canvas.draw(missingMassSelection,"same");
		canvas.cd(2);
		canvas.draw(omegaProtonSelected);
		canvas.cd(3);
		canvas.draw(pi0Mass);
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
		
		OmegaAnalyzer analyzer =  new OmegaAnalyzer();
		//analyzer.openFile("/Users/wphelps/Desktop/rga/phys/phys_3105.hipo");
		//analyzer.openFile("/Users/wphelps/Desktop/rga/phys/phys_3105.hipo");
		String dir = "/Users/wphelps/Desktop/rga/v16/skim8_ep/";
		File directory = new File(dir);
		String[] filesList = directory.list();
		for (int i = 0; i < filesList.length; i++) {
			try {
				analyzer.openFile(dir + filesList[i]);
				analyzer.processEvents();
			} catch (Exception e) {
			}
		}
	}
	
	@Override
	public boolean processEvent(ClasEvent event){
		if(event.N(211)==1&&event.N(-211)==1&&event.N(11)==1&&event.N(2212)==1&&event.N(22)>=2) {
			ClasParticle pip = event.getParticle(211,0);
			ClasParticle pim = event.getParticle(-211,0);
			ClasParticle gamma1 = event.getParticle(22, 0);
			ClasParticle gamma2 = event.getParticle(22,1);
			ClasParticle electron = event.getParticle(11,0);
			ClasParticle proton = event.getParticle(2212,0);

			LorentzVector p_pip = pip.getP4();
			LorentzVector p_pim = pim.getP4();
			LorentzVector p_electron = electron.getP4();
			LorentzVector p_gamma1 = gamma1.getP4();
			LorentzVector p_gamma2 = gamma2.getP4();
			LorentzVector p_proton = proton.getP4();
			
			LorentzVector beam = new LorentzVector();
			//beam.setPxPyPzE(0.0, 0.0, 6.413, 6.413);
			beam.setPxPyPzE(0.0, 0.0, 10.594, 10.594);

			LorentzVector target = new LorentzVector();
			target.setPxPyPzE(0.0, 0.0, 0.0, 0.938);
			
			LorentzVector omega = new LorentzVector();
			omega.add(beam);
			omega.add(target);
			omega.sub(p_proton);
			omega.sub(p_electron);
			omegaMass.fill(omega.mass());

			LorentzVector pi0 = new LorentzVector();
			pi0.add(p_gamma1);
			pi0.add(p_gamma2);
			
		
			
			
			LorentzVector missingParticle = new LorentzVector();
			missingParticle.add(beam);
			missingParticle.add(target);
			missingParticle.sub(p_pip);
			missingParticle.sub(p_pim);
			missingParticle.sub(p_proton);
			missingParticle.sub(p_electron);
			missingParticle.sub(p_gamma1);
			missingParticle.sub(p_gamma2);

		
			pi0Mass.fill(pi0.mass());
			missingMass.fill(missingParticle.mass2());
			//if(Math.abs(missingParticle.mass()-1.05)<.125&&Math.abs(pi0.mass()-.135)<.05) {
				if(Math.abs(pi0.mass()-.135)<.035 && Math.abs(missingParticle.mass2())<.035 ) {
				
				missingMassSelection.fill(missingParticle.mass2());
				omegaProtonSelected.fill(omega.mass());
			}
			
			
		}
		return false;
	}
	
}
