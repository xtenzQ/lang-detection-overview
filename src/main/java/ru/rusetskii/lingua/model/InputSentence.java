package ru.rusetskii.lingua.model;

import java.util.Arrays;

public class InputSentence {
    private final String sentence;
    private final String[] languages;

    public String getSentence() {
        return sentence;
    }

    public String[] getLanguages() {
        return languages;
    }

    public String printLanguages() {
        return Arrays.toString(this.languages);
    }

    public InputSentence(String sentence, String...languages) {
        this.sentence = sentence;
        this.languages = languages;
    }

    @Override
    public String toString() {
        return this.sentence + " " + Arrays.toString(this.languages);
    }
}
