import java.awt.Dimension;

import javax.swing.JFrame;

import org.jlab.detector.base.DetectorType;
import org.jlab.groot.data.H1F;
import org.jlab.groot.data.H2F;
import org.jlab.groot.graphics.EmbeddedCanvas;

public class Pi0Analyzer extends ClasAnalyzer {

	static EmbeddedCanvas can = new EmbeddedCanvas();
	static H1F nphe = new H1F("NPE", 100, 0, 50);
	static H2F betavsp = new H2F("NPE", 100, 0, 5, 100, 0, 1);

	public static void main(String[] args) {
		JFrame frame = new JFrame("Number of Photoelectrons");
		can.setPreferredSize(new Dimension(1680, 1050));
		can.divide(2, 1);
		can.drawNext(nphe);
		can.drawNext(betavsp);
		can.initTimer(100);
		frame.add(can);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);

		Pi0Analyzer analyzer = new Pi0Analyzer();
		analyzer.openFile("/Users/wphelps/Desktop/rga/phys/phys_3105.hipo");
		// analyzer.setHipoOutputFile("/Users/wphelps/Desktop/rga/phys/phys_3105_twogamma.hipo");
		analyzer.processEvents();

	}

	@Override
	boolean processEvent(ClasEvent event) {
		boolean writeEvent = false;
		for (ClasParticle particle : event.getParticles()) {
			betavsp.fill(particle.getP4().p(), particle.getBeta());
		}
		if (event.N(11) == 1) {
			writeEvent = true;
			ClasParticle electron = event.getParticles().get(0);
			if (electron.isDetectorHit(DetectorType.HTCC)) {
				CherenkovHit hit = (CherenkovHit) electron.getDetectorHit(DetectorType.HTCC);
				// System.out.println("Sector electron #1:"+ hit.getSector()+"
				// Phi:"+hit.getPhi()+" Theta:"+hit.getTheta() + " nphe:"+hit.getNphe());
				nphe.fill(hit.getNphe());
			}
		}
		return writeEvent;
	}

}
