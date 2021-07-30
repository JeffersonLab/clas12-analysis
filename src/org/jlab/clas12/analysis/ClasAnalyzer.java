package org.jlab.clas12.analysis;

import java.io.File;
import java.io.FilenameFilter;

import org.jlab.jnp.hipo4.data.Event;
import org.jlab.jnp.hipo4.io.HipoReader;
import org.jlab.jnp.hipo4.io.HipoWriter;
import org.jlab.jnp.hipo4.data.SchemaFactory;

public class ClasAnalyzer {
    private boolean writeHipoSkim = false;
    private String inputFile = "";
    private String outputFile = "";
    private String inputDirectory = "";
    private int verbosity = 1;

    public int getVerbosity() {
        return verbosity;
    }

    public void setVerbosity(int verbosity) {
        this.verbosity = verbosity;
    }

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
        if (this.inputDirectory != "") {
            File directory = new File(this.inputDirectory);
            String[] filesList = directory.list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".hipo");
                }
            });
            HipoWriter writer = null;
            for (int i = 0; i < filesList.length; i++) {
                try {
                    HipoReader reader = new HipoReader();
                    reader.open(this.inputDirectory + filesList[i]);
                    SchemaFactory factory = reader.getSchemaFactory();
                    if (i == 0 && writeHipoSkim) {
                        writer = new HipoWriter(factory);
                        writer.open(outputFile);
                    }
                    long eventNumber = 0;
                    int nEventsTotal = reader.getEventCount();
                    while (reader.hasNext() && (eventNumber < limit || limit == -1)) {
                        ClasEvent event = new ClasEvent();
                        Event hipoEvent = new Event();
                        reader.nextEvent(hipoEvent);
                        ClasEventBuilder.buildEvent(hipoEvent, factory, event);

                        if (processEvent(event)) {
                            if (writeHipoSkim) {
                                writer.addEvent(hipoEvent);
                            }
                            eventNumber++;
                        }
                        if (verbosity >= 2 && eventNumber % 100000 == 0) {
                            System.out.printf("Percent Complete:[%4.2f%%]\n", ((double) eventNumber / (double) nEventsTotal) * 100.0);
                        }
                    }

                    reader.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            try {
                if (writeHipoSkim) {
                    writer.close();
                }
            }catch (Exception e) {
                System.err.println(e);
            }
        } else {
            try {
                HipoReader reader = new HipoReader();
                reader.open(inputFile);
                SchemaFactory factory = reader.getSchemaFactory();
                HipoWriter writer = new HipoWriter(factory);
                if (writeHipoSkim) {
                    writer.open(outputFile);
                }
                reader.setTags(0);

                long eventNumber = 0;
                int nEventsTotal = reader.getEventCount();

                while (reader.hasNext() && (eventNumber < limit || limit == -1)) {
                    ClasEvent event = new ClasEvent();
                    Event hipoEvent = new Event();
                    reader.nextEvent(hipoEvent);
                    ClasEventBuilder.buildEvent(hipoEvent, factory, event);

                    if (processEvent(event)) {
                        if (writeHipoSkim) {
                            writer.addEvent(hipoEvent);
                        }
                        eventNumber++;
                    }

                    if (verbosity >= 2 && eventNumber % 100000 == 0) {
                        System.out.printf("Percent Complete:[%4.2f%%]\n", ((double) eventNumber / (double) nEventsTotal) * 100.0);
                    }
                }

                if (writeHipoSkim) {
                    writer.close();
                }

                reader.close();

            } catch (Exception e) {
                System.err.println(e);
            }
        }

        return this;
    }

}