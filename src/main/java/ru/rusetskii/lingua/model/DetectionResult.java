package ru.rusetskii.lingua.model;

import java.util.List;

public class DetectionResult {
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
}
