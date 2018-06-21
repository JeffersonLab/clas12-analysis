
public class Pi0Analyzer extends ClasAnalyzer {

	public static void main(String[] args) {
		Pi0Analyzer analyzer = new Pi0Analyzer();
		analyzer.openFile("/Users/wphelps/Desktop/rga/phys/phys_3105.hipo");
		analyzer.setHipoOutputFile("/Users/wphelps/Desktop/rga/phys/phys_3105_twogamma.hipo");
		analyzer.processEvents();
	}

	@Override
	boolean processEvent(ClasEvent event) {
		boolean writeEvent = false;
		if (event.N(22) == 2) {
			writeEvent = true;
		}
		return writeEvent;
	}

}
