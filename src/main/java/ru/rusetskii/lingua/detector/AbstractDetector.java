package ru.rusetskii.lingua.detector;

import ru.rusetskii.lingua.model.DetectionResult;

abstract class AbstractDetector {
    public abstract DetectionResult detect(String input);
}
