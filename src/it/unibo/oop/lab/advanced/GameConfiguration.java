package it.unibo.oop.lab.advanced;

import java.io.IOException;

public interface GameConfiguration {

    void setMinNum(int min);

    int getMinNum();

    void setMaxNum(int max);

    int getMaxNum();

    void setMaxAttempts(int attemps);

    void readConfigurationFile(String path) throws IOException;

    DrawNumber initializeGameInstance();
}
