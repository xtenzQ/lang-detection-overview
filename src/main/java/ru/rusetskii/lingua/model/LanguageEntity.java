package ru.rusetskii.lingua.model;

import org.jetbrains.annotations.NotNull;

public class LanguageEntity implements Comparable<LanguageEntity> {
    private final String language;
    private final double prob;

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

    @Override
    public int compareTo(@NotNull LanguageEntity o) {
        return Double.compare(this.prob, o.prob);
    }
}
