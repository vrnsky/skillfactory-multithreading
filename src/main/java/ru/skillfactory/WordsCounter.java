package ru.skillfactory;

import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Egor Voronianskii
 * @version 1.0
 */
public class WordsCounter extends Thread {

    private AtomicInteger words = new AtomicInteger(0);

    private List<String> text;

    private AtomicBoolean shouldStop = new AtomicBoolean(false);

    public WordsCounter(List<String> text) {
        this.text = text;
    }

    @Override
    public void run() {
            for (String string : text) {
                StringTokenizer stringTokenizer = new StringTokenizer(string);
                while (stringTokenizer.hasMoreElements() && !Thread.currentThread().isInterrupted()) {
                    stringTokenizer.nextToken();
                    words.incrementAndGet();
                }
            }
    }

    public int getWords() {
        return words.get();
    }
}
