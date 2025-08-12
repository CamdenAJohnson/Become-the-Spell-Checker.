package org.spellingb.model;

public class Word {
    private String text;
    private int length;
    private String difficulty; // "Easy", "Medium", or "Hard"

    public Word(String text, int length, String difficulty) {
        this.text = text;
        this.length = length;
        this.difficulty = difficulty;
    }

    public String getText() {
        return text;
    }

    public int getLength() {
        return length;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
