package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class Controller {
    private static final String STDPATH = System.getProperty("user.home") + System.getProperty("file.separator") + "output.txt";
    private File currentFile;

    public Controller() {
       this.createFile(STDPATH);
    }

    public final void setAsCurrentFile(final String pathName) {
        try {
            if (this.currentFile.delete()) {
                this.createFile(pathName);
            }
        } catch (SecurityException e) {
            System.out.println("Error deleting file from the system");
        }
    }


    private void createFile(final String pathName) {
        this.currentFile = new File(pathName);
        this.currentFile.setWritable(true);
        this.currentFile.deleteOnExit();

        try {
             this.currentFile.createNewFile();
         } catch (IOException e) {
             System.out.println("Error while creating the output file");
             e.printStackTrace();
         }
    }


    public final File getFile() {
        return this.currentFile;
    }


    public final void writeOnFile(final String inputString) {
        try (PrintStream ps = new PrintStream(this.currentFile.getPath())) {
            ps.print(inputString);
        } catch (FileNotFoundException e1) {
           System.out.println("Error : " + e1.getMessage());
            e1.printStackTrace();
        }
    }
}
