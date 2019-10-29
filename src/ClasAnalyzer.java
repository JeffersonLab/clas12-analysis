import org.jlab.jnp.hipo4.io.HipoReader;
import org.jlab.jnp.hipo4.io.HipoWriter;

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
		return processEvents(-1);
	}

	public ClasAnalyzer processEvents(int limit) {
		//HipoReader reader = new HipoReader();
		HipoReader reader = new HipoReader();
		reader.open(inputFile);
		reader.setTags(0);
		
		//HipoWriter writer = reader.createWriter();
//		writer.setCompressionType(1);
//		if(writeHipoSkim) {
//			writer.open(outputFile);
//		}
		long eventNumber = 0;
		int nEventsTotal = reader.getEventCount();
		while(reader.hasNext()==true&&(eventNumber<limit||limit==-1)){
			  
				  ClasEvent event = new ClasEvent();
				  ClasEventBuilder.buildEvent(reader, event);
				  if(processEvent(event)&&writeHipoSkim) //writer.writeEvent(hipoEvent);
				  eventNumber++;
				  //if(eventNumber%100000==0) System.out.printf("Percent Complete:[%4.2f%%]\n",((double)eventNumber/(double)nEventsTotal)*100.0);
			  
		}
		reader.close();	
		return this;
	}

}
