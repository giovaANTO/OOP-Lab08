package it.unibo.oop.lab.mvc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class AppController implements Controller {

    private final Deque<String> printedStrings;
    private String nextString;

    public AppController() {
        this.printedStrings = new ArrayDeque<>();
    }

    public final void setNextString(final String string) throws IllegalArgumentException {
        if (string == null) {
            throw new IllegalArgumentException();
        }
       this.nextString = string;
    }

    public final void printCurrentString() throws IllegalStateException {
        if (this.nextString == null) {
            throw new IllegalStateException();
        }
        System.out.println(this.nextString);
        this.printedStrings.addFirst(this.nextString);
    }

    public final String getNextString() {
        return this.nextString;
    }

    public final List<String> history() {
        return List.copyOf(this.printedStrings);
    }

}
