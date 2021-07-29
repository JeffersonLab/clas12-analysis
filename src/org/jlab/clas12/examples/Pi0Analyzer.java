package org.jlab.clas12.examples;


import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import org.jlab.clas.physics.LorentzVector;
import org.jlab.clas12.analysis.ClasAnalyzer;
import org.jlab.clas12.analysis.ClasEvent;
import org.jlab.clas12.analysis.ClasParticle;
import org.jlab.clas12.analysis.VertDoca;
import org.jlab.detector.base.DetectorType;
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

		String dir = "/home/vishalfenn/Documents/cpsc/outside/jlab/skim/";
		String out = "/home/vishalfenn/Documents/cpsc/outside/jlab/";
//		analyzer.openFile(dir+"out_lam_00046.rec.hipo"); //Can open single file
//		analyzer.openDirectory(dir);  //Or all hipo files in a directory;

//		analyzer.setHipoOutputFile(out + "skimTest4.hipo");

		analyzer.openFile(out + "skimTest6.hipo");
//		analyzer.setHipoOutputFile(out + "skimTest6.hipo");

		analyzer.processEvents(); //Can also set an event limit with an integer argument

	}


	@Override
	public boolean processEvent(ClasEvent event) {
		event.setUseft(true); //Uses the RECFT::Event and RECFT::Particle banks if available.

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