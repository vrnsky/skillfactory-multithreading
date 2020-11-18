package ru.skillfactory;

import java.util.List;

public class App {

    private SpaceCounter spaceCounter;

    private WordsCounter wordsCounter;

    private long startTime = 1L;

    private void startThreads() {
        this.spaceCounter.start();
        this.wordsCounter.start();
    }

    public void start(List<String> text, long ms) {
        this.initThreads(text);
        this.startThreads();

        try {
            this.waitForThreads(ms);
            if (System.currentTimeMillis() - startTime > ms) {
                stopThread();
            }
        } catch (InterruptedException e) {
            System.out.println("Calculation was stopped");
        }
    }

    public void initThreads(List<String> text) {
        this.wordsCounter = new WordsCounter(text);
        this.spaceCounter = new SpaceCounter(text);
    }


    public void waitForThreads(long ms) throws InterruptedException {
        this.spaceCounter.join(ms);
        this.wordsCounter.join(ms);
    }

    public void stopThread() throws InterruptedException {
        if (this.spaceCounter.isAlive()) {
            this.spaceCounter.interrupt();
        }

        if (this.wordsCounter.isAlive()) {
            this.wordsCounter.interrupt();
        }

        this.wordsCounter.join();
        this.spaceCounter.join();
    }


    public int getWords() {
        return this.wordsCounter.getWords();
    }

    public int getSpaces() {
        return this.spaceCounter.getSpaces();
    }

}
