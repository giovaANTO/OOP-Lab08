package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class FileLoggerView extends AbstractLoggerView {

    private static final String LOGPATH = System.getProperty("user.home") + System.getProperty("file.separator") + "gamelogger.txt";
    private File logfile;
    private PrintStream ps;

    /**
     * Create the logfile.
     */
    public void initializeLogger() {
        this.logfile = new File(LOGPATH);
        this.logfile.setWritable(true);

        try {
             this.logfile.createNewFile();
             this.ps = new PrintStream(this.logfile.getPath());
         } catch (IOException e) {
             System.out.println("Error while creating the logfile file");
             e.printStackTrace();
         }
    }

    /**
     * Terminating the log.
     */
    public void terminateLogger() {
        this.log(MessageEnum.INFO_MESSAGE, "Stop logging on file");
        this.ps.close();
    }

    /**
     * @param messageType
     * @param text
     */
    public void log(final MessageEnum messageType, final String text) {
        ps.print(this.prepareMessage(messageType, text) + "\n");
    }
}
