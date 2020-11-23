package it.unibo.oop.lab.advanced;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;
    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @throws IOException
     * @throws FileNotFoundException
     * 
     */
    public DrawNumberApp() throws FileNotFoundException, IOException {
        final GameConfiguration configurator = new GameConfigurationImpl();
        configurator.readConfigurationFile("config.yml");
        this.model = configurator.initializeGameInstance();
        this.views = new ArrayList<>();
        this.views.add(new DrawNumberViewImpl());
        this.views.add(new StdOutputLoggerView());
        this.views.add(new FileLoggerView());
        this.views.stream().forEach(v -> {
            v.setObserver(this);
            v.start();
        });
    }

    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.eventResult(result);
        } catch (IllegalArgumentException e) {
            this.eventNumberIncorrect();
        } catch (AttemptsLimitReachedException e) {
           this.limitsReached();
        }
    }

    private void eventResult(final DrawResult result) {
        this.views.forEach(v -> v.result(result));
    }

    private void eventNumberIncorrect() {
        this.views.forEach(v -> v.numberIncorrect());
    }

    private void limitsReached() {
        this.views.forEach(v -> v.limitsReached());
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *                 ignored
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void main(final String... args) throws FileNotFoundException, IOException {
        new DrawNumberApp();
    }

}
