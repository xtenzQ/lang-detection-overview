package ru.rusetskii.lingua;

import static ru.rusetskii.lingua.Constants.*;

public class DetectionResult {
    private String result;
    private long time;

    public String getResult() {
        for (String row : CLEAN_UP_REGEX) {
            result = result.replaceAll(row, EMPTY);
        }
        return result;
    }

    public long getTime() {
        return time;
    }

    public DetectionResult() { }

    public DetectionResult(String result, long time) {
        this.result = result;
        this.time = time;
    }
}
