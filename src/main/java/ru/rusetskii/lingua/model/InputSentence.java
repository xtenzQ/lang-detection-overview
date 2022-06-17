package ru.rusetskii.lingua.model;

import java.util.Arrays;
import java.util.List;

public class InputSentence {
    private final String sentence;
    private final List<String> languages;

    public String getSentence() {
        return sentence;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public InputSentence(String sentence, String...languages) {
        this.sentence = sentence;
        this.languages = Arrays.asList(languages);
    }
}
