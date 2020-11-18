package ru.skillfactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Egor Voronianskii
 * @version 1.0
 */
public class SpaceCounter extends Thread {

    private List<String> text;

    private AtomicInteger spaces = new AtomicInteger(0);

    public SpaceCounter(List<String> text) {
        this.text = text;
    }

    @Override
    public void run() {
        for (String string : text) {
            if (string != null) {
                for(char ch : string.toCharArray()) {
                    if (Character.isSpaceChar(ch)) {
                        spaces.incrementAndGet();
                    }
                }
            }
        }
    }

    public int getSpaces() {
        return spaces.get();
    }
}
