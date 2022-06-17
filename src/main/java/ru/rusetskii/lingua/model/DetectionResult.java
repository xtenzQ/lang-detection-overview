package ru.rusetskii.lingua.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DetectionResult implements Comparable<DetectionResult> {
    private List<LanguageEntity> result;
    private long time;

    public List<LanguageEntity> getResult() {
        return result;
    }

    public long getTime() {
        return time;
    }

    public DetectionResult() { }

    public DetectionResult(List<LanguageEntity> result, long time) {
        this.result = result;
        this.time = time;
    }

    @Override
    public int compareTo(@NotNull DetectionResult o) {
        return 0;
    }
}
