package ru.rusetskii.lingua;

import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;

import java.util.Map;
import java.util.stream.Collectors;

public class LinguaDetector extends AbstractDetector {

    private final LanguageDetector detector;

    public LinguaDetector() {
        detector = LanguageDetectorBuilder.fromAllLanguages().build();
    }

    @Override
    public DetectionResult detect(String input) {
        long start = System.currentTimeMillis();
        Map<Language, Double> languageMap = detector.computeLanguageConfidenceValues(input).entrySet().stream().filter(x -> x.getValue() > 0.9).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        long end = System.currentTimeMillis();
        return new DetectionResult(languageMap.toString(), end - start);
    }
}
