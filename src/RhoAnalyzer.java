import java.awt.Dimension;

import javax.swing.JFrame;

import org.jlab.clas.physics.LorentzVector;
import org.jlab.groot.data.H1F;
import org.jlab.groot.graphics.EmbeddedCanvas;

public class RhoAnalyzer extends ClasAnalyzer {
	static EmbeddedCanvas canvas = new EmbeddedCanvas();
	static H1F rhoMass = new H1F("RhoMass",100,0,1.5);	
	static H1F rhoMassProtonSelected = new H1F("RhoMassProton",100,0,1.5);
	static H1F missingMass = new H1F("missingMass",100,0,1.5);
	static H1F missingMassSelection = new H1F("missingMassSelection",100,0,1.5);

	public static void main(String args[]) {	
		rhoMass.setTitleX("M(#pi^+#pi^-) [GeV/c^2]");
		rhoMass.setTitleY("Counts");
		rhoMass.setTitle("#rho(770): Run 3105");
		missingMass.setTitleX("MM(e^-#pi^+#pi^-) [GeV/c^2]");
		missingMass.setTitleY("Counts");
		rhoMassProtonSelected.setTitleX("M(#pi^+#pi^-) [GeV/c^2]");
		rhoMassProtonSelected.setTitleY("Counts");

		missingMassSelection.setFillColor(42);
		rhoMassProtonSelected.setFillColor(44);
		
		
		JFrame frame = new JFrame("Number of Photoelectrons");
		canvas.divide(2, 2);
		canvas.setPreferredSize(new Dimension(1680, 1050));
		canvas.cd(0);
		canvas.draw(rhoMass);
		canvas.draw(rhoMassProtonSelected,"same");
		canvas.cd(1);
		canvas.draw(missingMass);
		canvas.draw(missingMassSelection,"same");
		canvas.cd(2);
		canvas.draw(rhoMassProtonSelected);
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
		
		RhoAnalyzer analyzer =  new RhoAnalyzer();
		analyzer.openFile("/Users/wphelps/Desktop/rga/phys/phys_3105.hipo");
		analyzer.processEvents();
	}
	
	@Override
	boolean processEvent(ClasEvent event){
		if(event.N(211)==1&&event.N(-211)==1&&event.N(11)==1) {
			ClasParticle pip = event.getParticle(211,0);
			ClasParticle pim = event.getParticle(-211,0);
			ClasParticle electron = event.getParticle(11,0);
			LorentzVector p_pip = pip.getP4();
			LorentzVector p_pim = pim.getP4();
			LorentzVector p_electron = electron.getP4();

			LorentzVector beam = new LorentzVector();
			beam.setPxPyPzE(0.0, 0.0, 6.413, 6.413);
			
			LorentzVector target = new LorentzVector();
			target.setPxPyPzE(0.0, 0.0, 0.0, 0.938);
			
			LorentzVector rho = new LorentzVector();
			rho.add(p_pip);
			rho.add(p_pim);
			rhoMass.fill(rho.mass());
			
			LorentzVector missingParticle = new LorentzVector();
			missingParticle.add(beam);
			missingParticle.add(target);
			missingParticle.sub(rho);
			missingParticle.sub(p_electron);
			missingMass.fill(missingParticle.mass());
			if(Math.abs(missingParticle.mass()-1.05)<.1) {
				missingMassSelection.fill(missingParticle.mass());
				rhoMassProtonSelected.fill(rho.mass());
			}
			
		}
		return false;
	}
	
}
