package org.jlab.clas12.analysis;

import java.io.File;

import org.jlab.clas12.analysis.ClasEvent;
import org.jlab.jnp.hipo4.io.HipoReader;

public class ClasAnalyzer {
	private boolean writeHipoSkim = false;
	private String inputFile = "";
	private String outputFile = "";
	private String inputDirectory = "";

	public boolean processEvent(ClasEvent event) {
		System.err.println("You should override the processEvent() method in your analyzer class");
		return false;
	}

	public ClasAnalyzer openFile(String inputFile) {
		this.inputFile = inputFile;
		return this;
	}

	public ClasAnalyzer openDirectory(String inputDirectory) {
		this.inputDirectory = inputDirectory;
		return this;
	}

	public ClasAnalyzer setHipoOutputFile(String outputFile) {
		writeHipoSkim = true;
		this.outputFile = outputFile;
		return this;
	}

	public ClasAnalyzer processEvents() {
		return processEvents(-1);
	}

	public ClasAnalyzer processEvents(int limit) {
		// HipoReader reader = new HipoReader();

		if (this.inputDirectory != "") {
			File directory = new File(this.inputDirectory);
			String[] filesList = directory.list();
			for (int i = 0; i < filesList.length; i++) {
				try {
					HipoReader reader = new HipoReader();
					reader.open(this.inputDirectory + filesList[i]);
					long eventNumber = 0;
					int nEventsTotal = reader.getEventCount();
					while (reader.hasNext() == true && (eventNumber < limit || limit == -1)) {

						ClasEvent event = new ClasEvent();
						ClasEventBuilder.buildEvent(reader, event);
						if (processEvent(event) && writeHipoSkim) // writer.writeEvent(hipoEvent);
							eventNumber++;
						// if(eventNumber%100000==0) System.out.printf("Percent
						// Complete:[%4.2f%%]\n",((double)eventNumber/(double)nEventsTotal)*100.0);

					}
					reader.close();
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		} else {
			try {
				HipoReader reader = new HipoReader();
				reader.open(inputFile);
				reader.setTags(0);
				long eventNumber = 0;
				int nEventsTotal = reader.getEventCount();
				while (reader.hasNext() == true && (eventNumber < limit || limit == -1)) {

					ClasEvent event = new ClasEvent();
					ClasEventBuilder.buildEvent(reader, event);
					if (processEvent(event) && writeHipoSkim) // writer.writeEvent(hipoEvent);
						eventNumber++;
					// if(eventNumber%100000==0) System.out.printf("Percent
					// Complete:[%4.2f%%]\n",((double)eventNumber/(double)nEventsTotal)*100.0);

				}
				reader.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}

		// HipoWriter writer = reader.createWriter();
		// writer.setCompressionType(1);
		// if(writeHipoSkim) {
		// writer.open(outputFile);
		// }

		return this;
	}

}
