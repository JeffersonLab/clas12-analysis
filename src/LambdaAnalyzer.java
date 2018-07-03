import java.awt.Dimension;
import javax.swing.JFrame;

import org.jlab.clas.physics.LorentzVector;
import org.jlab.groot.data.H1F;
import org.jlab.groot.data.IDataSet;
import org.jlab.groot.graphics.EmbeddedCanvas;

/**
 *
 * @author noraimnunez
 */

public class LambdaAnalyzer extends ClasAnalyzer { 
    static EmbeddedCanvas canvas = new EmbeddedCanvas(); 
    static H1F LambdaMass = new H1F("LambdaMass",100,0.5,1.7);
    static H1F LambdaSelected = new H1F("Lambda",100,0.5,1.7);
    static H1F missingMass = new H1F("missingMass",100,0.5,1.7);
    static H1F missingMassSelection = new H1F("missingMassSelection",100,0,1.5);
    private static IDataSet LambdaMasSelected;

            
            
public static void main(String args[]) {
LambdaMass.setTitleX("M(p#pi^-) [Gev/c^2]");
LambdaMass.setTitleY("Nuber of Counts");
LambdaMass.setTitle("Lambda Analyze");

missingMassSelection.setFillColor(42);
LambdaSelected.setFillColor(44);

JFrame frame = new JFrame("blah blah");
    canvas.divide(2, 2);
    canvas.setPreferredSize(new Dimension(1680, 1050));
    canvas.cd(0);
    canvas.draw(LambdaMass);
    canvas.draw(LambdaSelected,"same");
    canvas.cd(1);	
    canvas.draw(missingMass);
    canvas.draw(missingMassSelection,"same");
    canvas.cd(2);
    canvas.draw(LambdaMasSelected);
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
    analyzer.openFile("/Users/noraimnunez/Documents/GitHub/clas12-analysis/src/noraim/out_clas_004013.evio.31.hipo");
    analyzer.processEvents();
    analyzer.openFile("/Users/noraimnunez/Documents/GitHub/clas12-analysis/src/noraim/out_clas_004013.evio.32.hipo");
    analyzer.processEvents();
    analyzer.openFile("/Users/noraimnunez/Documents/GitHub/clas12-analysis/src/noraim/out_clas_004013.evio.33.hipo");
    analyzer.processEvents();
  }
	

    @Override 
    boolean processEvent(ClasEvent event){
        if (event.N(-211)==1&&event.N(2212)==1) { 
    ClasParticle pim = event.getParticle(-211,0);
    ClasParticle p = event.getParticle(2212, 0);
    LorentzVector p_pim = pim.getP4();
    LorentzVector p_p = p.getP4();
    
    LorentzVector target = new LorentzVector(); 
    target.setPxPyPzE (0.0, 0.0, 0.0, 0.938);
    
    LorentzVector beam = new LorentzVector(); 
    beam.setPxPyPzE (0.0, 0.0, 0.139, 0.139);   
    
    LorentzVector Lambda = new LorentzVector(); 
    Lambda.add(p_pim);
    Lambda.add(p_p); 
    LambdaMass.fill(Lambda.mass());
    
        LorentzVector missingParticle = new LorentzVector();
		missingParticle.add(beam);
		missingParticle.add(target);
		missingParticle.sub(Lambda); 
                
		missingMass.fill(missingParticle.mass());
	if(Math.abs(missingParticle.mass()-1.05)<.1) {    

        missingMassSelection.fill(missingParticle.mass());
	LambdaSelected.fill(Lambda.mass());
            }
        }
        	return false;         
    }
}    
    
    
    

