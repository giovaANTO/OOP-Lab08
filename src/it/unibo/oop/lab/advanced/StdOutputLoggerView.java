package it.unibo.oop.lab.advanced;

public class StdOutputLoggerView extends AbstractLoggerView {


    /**
     * Procedures to initialize the logger.
     */
    public void initializeLogger() {
        this.log(MessageEnum.INFO_MESSAGE, "Starting up the logger");
    }

    /**
     * Terminate the logger.
     */
    public void terminateLogger() {
        this.log(MessageEnum.INFO_MESSAGE, "See you space logger");
    }

   /**
    * Print a message in the standard output.
    * @param messageType
    * @param text
    */
    public void log(final MessageEnum messageType, final String text) {
        System.out.println(this.prepareMessage(messageType, text));
    }

}
