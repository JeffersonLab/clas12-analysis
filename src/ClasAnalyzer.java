import org.jlab.jnp.hipo.data.HipoEvent;
import org.jlab.jnp.hipo.io.HipoReader;
import org.jlab.jnp.hipo.io.HipoWriter;

public class ClasAnalyzer {
	private boolean writeHipoSkim = false;
	private String inputFile;
	private String outputFile;
	
	boolean processEvent(ClasEvent event) {
		System.out.println("You should override this method in your analyzer");
		return false;
	}
	
	public ClasAnalyzer openFile(String inputFile) {
		this.inputFile = inputFile;
		return this;	
	}
	
	public ClasAnalyzer setHipoOutputFile(String outputFile) {
		writeHipoSkim = true;
		this.outputFile = outputFile;
		return this;
	}

	public ClasAnalyzer processEvents() {
		HipoReader reader = new HipoReader();
		reader.open(inputFile);
		
		HipoWriter writer = reader.createWriter();
		writer.setCompressionType(1);
		if(writeHipoSkim) {
			writer.open(outputFile);
		}
		long eventNumber = 0;
		int nEventsTotal = reader.getEventCount();
		while(reader.hasNext()==true){
			HipoEvent hipoEvent = reader.readNextEvent();
			  if(hipoEvent.hasGroup("REC::Particle")){
				  ClasEvent event = new ClasEvent();
				  ClasEventBuilder.buildEvent(hipoEvent, event);
				  if(processEvent(event)&&writeHipoSkim) writer.writeEvent(hipoEvent);
				  eventNumber++;
				  //if(eventNumber%100000==0) System.out.printf("Percent Complete:[%4.2f%%]\n",((double)eventNumber/(double)nEventsTotal)*100.0);
			  }
		}
		
		return this;
	}

}
