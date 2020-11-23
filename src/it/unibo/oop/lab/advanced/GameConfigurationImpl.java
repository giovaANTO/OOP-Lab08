package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class GameConfigurationImpl implements GameConfiguration {

    private int minNum;
    private int maxNum;
    private int maxAttempts;

    public final void setMinNum(final int min) {
        this.minNum = min;
    }

    public final int getMinNum() {
        return this.minNum;
    }

    public final void setMaxNum(final int max) {
        this.maxNum = max;
    }

    public final int getMaxNum() {
        return this.maxNum;
    }

    public final void setMaxAttempts(final int attempts) {
        this.maxAttempts = attempts;
    }

    public final void readConfigurationFile(final String path) throws IOException {
        final InputStream configInputStream = ClassLoader.getSystemResourceAsStream(path);
        try (var fileInput = new BufferedReader(new InputStreamReader(configInputStream))) {
            String str;
            while ((str = fileInput.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(str);
                try {
                    this.setConfigurationByToken(token);
                } catch (IllegalConfigurationException e) {
                    System.out.println("Error while reading the configuration file");
                    e.printStackTrace();
                }
            }
        }
    }

    private void setConfigurationByToken(final StringTokenizer token) throws IllegalConfigurationException {
        Objects.requireNonNull(token, "This method require a valid token");
        switch (token.nextToken(":")) {
        case "minimum":
            this.minNum = Integer.valueOf(token.nextToken().strip());
            break;
        case "maximum":
            this.maxNum = Integer.valueOf(token.nextToken().strip());
            break;
        case "attempts":
            this.maxAttempts = Integer.valueOf(token.nextToken().strip());
            break; 
        default:
            throw new IllegalConfigurationException();
        }
    }

    private class IllegalConfigurationException extends Exception {
        private static final long serialVersionUID = 8886777512828593430L;
    }

    public final DrawNumber initializeGameInstance() {
        return new DrawNumberImpl(this.minNum, this.maxNum, this.maxAttempts);
    }
}
