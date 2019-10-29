import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jlab.clas.physics.Vector3;
import org.jlab.detector.base.DetectorType;
import org.jlab.groot.base.GStyle;
import org.jlab.groot.data.H1F;
import org.jlab.groot.data.H2F;
import org.jlab.groot.data.TDirectory;
import org.jlab.groot.graphics.EmbeddedCanvas;

import jdk.internal.dynalink.support.DefaultInternalObjectFilter;

public class HTCCAnalyzer extends ClasAnalyzer {

	static EmbeddedCanvas can = new EmbeddedCanvas();
	static H1F hnphe = new H1F("NPE", 50, 0, 50);
	static H2F npevstheta_electrons = new H2F("NPE_vs_theta_electrons", 300, 0, 45, 150, 0, 50);
	static H2F npevsphi_electrons = new H2F("NPE_vs_phi_electrons", 300, -180, 180, 150, 0, 50);
	static H2F npe_thetavsphi_electrons = new H2F("NPE_vs_phi_electrons", 300, -180, 180, 150, 0, 50);
	static H2F events_thetavsphi_electrons = new H2F("NPE_vs_phi_electrons", 300, -180, 180, 150, 0, 50);
	static H2F avgnpe_thetavsphi_electrons = new H2F("NPE_vs_phi_electrons", 300, -180, 180, 150, 0, 50);
	static int htcc_radius = 116;
	static H2F npe_xy_electrons = new H2F("NPE_vs_phi_electrons", 300, -htcc_radius, htcc_radius, 300, -htcc_radius, htcc_radius);
	static H2F events_xy_electrons = new H2F("NPE_vs_phi_electrons", 300, -htcc_radius, htcc_radius, 300, -htcc_radius, htcc_radius);
	static H2F avgnpe_xy_electrons = new H2F("NPE_vs_phi_electrons", 300, -htcc_radius, htcc_radius, 300, -htcc_radius, htcc_radius);
	static H1F hradius = new H1F("NPE", 200, 0, 100);

	static long eventCounter = 0;
	static long start_time = 0;
	public static void main(String[] args) {
		
		initCanvas();

		start_time = System.currentTimeMillis();
		HTCCAnalyzer analyzer = new HTCCAnalyzer();
		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				analyzer.openFile(args[i]);
				analyzer.processEvents();
			}
		} else {
			String dir = "/Users/wphelps/Desktop/rga/skim4/";
			//String dir = "/Users/wphelps/Desktop/rga/output_4013_slurm_clara_dev/";
			//String dir = "/Users/wphelps/Desktop/rga/output_4013_dev_2npe/";
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
		/*
		TDirectory dirFile = new TDirectory();
		dirFile.mkdir("/folder/");
		dirFile.cd("/folder/");
		dirFile.add("hnpe", avgnpe_xy_electrons);
		dirFile.writeFile("/Users/wphelps/Desktop/02npe.hipo");
		dirFile.ls();
		*/
	
		GStyle.getAxisAttributesX().setTitleFontSize(18);
		GStyle.getAxisAttributesY().setTitleFontSize(18);
		GStyle.getAxisAttributesX().setLabelFontSize(16);
		GStyle.getAxisAttributesY().setLabelFontSize(16);

	}

	private static void initCanvas() {

		npevstheta_electrons.setTitleX("Electron #theta [deg]");
		npevstheta_electrons.setTitleY("NPE");

		npevsphi_electrons.setTitleX("Electron #phi [deg]");
		npevsphi_electrons.setTitleY("NPE");

		avgnpe_thetavsphi_electrons.setTitleX("Electron #phi [deg]");
		avgnpe_thetavsphi_electrons.setTitleY("Electron #theta [deg]");

		npe_thetavsphi_electrons.setTitleX("Electron #phi [deg]");
		npe_thetavsphi_electrons.setTitleY("Electron #theta [deg]");

		events_thetavsphi_electrons.setTitleX("Electron #phi [deg]");
		events_thetavsphi_electrons.setTitleY("Electron #theta [deg]");

		avgnpe_xy_electrons.setTitleX("Electron X @ HTCC [cm]");
		avgnpe_xy_electrons.setTitleY("Electron Y @ HTCC [cm]");

		JFrame frame = new JFrame("Number of Photoelectrons");
		can.setPreferredSize(new Dimension(1680, 1050));
		int pad = 0;
		can.divide(4, 2);

		can.cd(pad++);
		can.draw(npevstheta_electrons);
		can.cd(pad++);
		can.draw(npevsphi_electrons);
		can.cd(pad++);
		can.draw(npe_thetavsphi_electrons);
		can.cd(pad++);
		can.draw(events_thetavsphi_electrons);
		can.cd(pad++);
		can.draw(avgnpe_thetavsphi_electrons);
		can.getPad().getAxisZ().setRange(0, 30);
		can.cd(pad++);
		can.draw(avgnpe_xy_electrons);
		can.getPad().getAxisZ().setRange(0, 30);
		can.cd(pad++);
		can.draw(hradius);

		can.initTimer(1000);
		frame.add(can);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);

	}

	@Override
	boolean processEvent(ClasEvent event) {
		eventCounter++;
		
		boolean writeEvent = false;
		
		if(eventCounter%10000==0) {
			long deltat = System.currentTimeMillis() - start_time;
			System.out.println("Frequency:"+((double)eventCounter)/((double)deltat/1000.0) + "eventCounter:"+eventCounter+" deltaT:"+deltat/1000.0);
		}

		if (event.N(11) >= 1) {
			writeEvent = true;
			ClasParticle electron = event.getParticle(11, 0);
			
			if (electron.isDetectorHit(DetectorType.HTCC)) {
				CherenkovHit hit = (CherenkovHit) electron.getDetectorHit(DetectorType.HTCC);
				//System.out.println("Status:"+electron.getStatus()+" npe:"+hit.getNphe());
				if (Math.abs(electron.getStatus())/1000 != 2) {
					return false;
				}
				if(electron.getTrajectoryInfo().size()<10) {
					return false;
				}
				double x = electron.getTrajectoryInfo().get(0).getX();
				double y = electron.getTrajectoryInfo().get(0).getY();
				double z = electron.getTrajectoryInfo().get(0).getZ();

				Vector3 electronAtHTCC = new Vector3();
				electronAtHTCC.setXYZ(x, y, z);
				npevstheta_electrons.fill(Math.toDegrees(electronAtHTCC.theta()), hit.getNphe());
				npevsphi_electrons.fill(Math.toDegrees(electronAtHTCC.phi()), hit.getNphe());
				npe_thetavsphi_electrons.fill(Math.toDegrees(electronAtHTCC.phi()), Math.toDegrees(electronAtHTCC.theta()), hit.getNphe());
				events_thetavsphi_electrons.fill(Math.toDegrees(electronAtHTCC.phi()), Math.toDegrees(electronAtHTCC.theta()));
				npe_xy_electrons.fill(x, y, hit.getNphe());
				events_xy_electrons.fill(x, y);
				
				for (int i = 0; i < events_xy_electrons.getDataBufferSize(); i++) {
					float npe = npe_xy_electrons.getDataBufferBin(i);
					float nevents = events_xy_electrons.getDataBufferBin(i);
					double r = Math.sqrt(x*x+y*y);
					hradius.fill(r);
					if (nevents > 20) {
						avgnpe_xy_electrons.setDataBufferBin(i, npe / nevents);
					} else {
						avgnpe_xy_electrons.setDataBufferBin(i, 0);
					}
				}
				
				for (int i = 0; i < npevsphi_electrons.getDataBufferSize(); i++) {
					float npe = npe_thetavsphi_electrons.getDataBufferBin(i);
					float nevents = events_thetavsphi_electrons.getDataBufferBin(i);
					if (nevents > 2) {
						avgnpe_thetavsphi_electrons.setDataBufferBin(i, npe / nevents);
					} else {
						avgnpe_thetavsphi_electrons.setDataBufferBin(i, 0);
					}
				}
			}
		}
		return writeEvent;
	}

}
