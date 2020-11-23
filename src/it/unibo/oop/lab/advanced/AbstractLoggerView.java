package it.unibo.oop.lab.advanced;

public abstract class AbstractLoggerView implements DrawNumberView, LoggerViewInterface {

    private DrawNumberViewObserver observer;

    public final void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
    }

    /**
     * Setup the logger.
     * This method will call initializeLogger for setting up the preliminary operations 
     * that must done in order to setup the log correctly.
     */
    public void start() {
        this.initializeLogger();
    }

    /**
     * Display the numberIncorrect warning message to the user.
     */
    public void numberIncorrect() {
        this.log(MessageEnum.WARNING_MESSAGE, "Incorrect number, please retry");
    }

    /**
     * Show the result to the user.
     * @param res
     */
    public void result(final DrawResult res) {
        this.log(MessageEnum.INFO_MESSAGE, res.getDescription());
    }

    /**
     * Display the limitsReached warning message to the user.
     */
    public void limitsReached() {
        this.log(MessageEnum.WARNING_MESSAGE, "Limit reached");
    }

    /**
     * Display an error message in the standard output.
     * @param message
     */
    public void displayError(final String message) {
        this.log(MessageEnum.ERROR_MESSAGE, message);
    }


    public abstract void initializeLogger();

    public abstract void terminateLogger();

    /**
     * Prepare the message to log.
     * @param messageType the type of the message to print
     * @param text the text to print
     * @return he formatted message
     */
    public String prepareMessage(final MessageEnum messageType, final String text) {
        return messageType.getTag() + ": " + text;
    }
}
