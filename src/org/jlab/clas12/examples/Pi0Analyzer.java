package org.jlab.clas12.examples;
import java.awt.Dimension;

import javax.swing.JFrame;

import org.jlab.clas.physics.LorentzVector;
import org.jlab.clas12.analysis.ClasAnalyzer;
import org.jlab.clas12.analysis.ClasEvent;
import org.jlab.clas12.analysis.ClasParticle;
import org.jlab.groot.data.H1F;
import org.jlab.groot.graphics.EmbeddedCanvas;

public class Pi0Analyzer extends ClasAnalyzer {
	static EmbeddedCanvas canvas = new EmbeddedCanvas();
	static H1F pi0Mass = new H1F("pi0Mass", 100, 0, 1.2);
	
	public static void main(String[] args) {	
		
		JFrame frame = new JFrame("Basic Pi0 Analyzer");
		canvas.initTimer(500);
		canvas.setFont("Avenir");
		canvas.setTitleSize(32);
		canvas.setAxisTitleSize(24);
		canvas.setAxisLabelSize(18);
		canvas.setStatBoxFontSize(18);
		canvas.setStatBoxFontSize(18);
		pi0Mass.setTitleX("M(#gamma#gamma) [GeV/c^2]");
		pi0Mass.setTitleY("Counts");
		pi0Mass.setTitle("Two Photon Invariant Mass: Run 5036");
		pi0Mass.setFillColor(34);
		canvas.setPreferredSize(new Dimension(1680, 1050));
		canvas.draw(pi0Mass);

		frame.add(canvas);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);

		Pi0Analyzer analyzer = new Pi0Analyzer();
		String dir = "/Users/wphelps/Desktop/rga/skim12_elec_3pi/";
		//analyzer.openFile(dir+"skim4_5036.hipo"); //Can open single file
		analyzer.openDirectory(dir);  //Or all hipo files in a directory;
		analyzer.processEvents(); //Can also set an event limit with an integer argument

	}
	

	@Override
	public boolean processEvent(ClasEvent event) {		
				
		if (event.N(22) >= 2 && event.N(11) == 1) {
			ClasParticle gamma1 = event.getParticle(22, 0);
			ClasParticle gamma2 = event.getParticle(22, 1);
			ClasParticle electron = event.getParticle(11, 0);

			LorentzVector pi0 = new LorentzVector();
			pi0.add(gamma1.getP4());
			pi0.add(gamma2.getP4());

			pi0Mass.fill(pi0.mass());
			return true;
		}
		return false;
	}

}
