package ru.rusetskii.lingua.model;

import java.util.Arrays;

public class LanguageEntity {
    private String language;
    private double prob;

    public LanguageEntity(String language, double prob) {
        this.language = language;
        this.prob = prob;
    }

    public String getLanguage() {
        return language;
    }

    public double getProb() {
        return prob;
    }

    @Override
    public String toString() {
        return this.language + ":" + this.prob;
    }
}
