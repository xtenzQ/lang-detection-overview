package ru.rusetskii.lingua.detector;

import ru.rusetskii.lingua.model.DetectionResult;
import ru.rusetskii.lingua.model.LanguageEntity;

import java.util.List;

abstract class AbstractDetector {
    public abstract DetectionResult detect(String input);
}
