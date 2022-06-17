package ru.rusetskii.lingua.detector;

import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import com.optimaize.langdetect.DetectedLanguage;
import ru.rusetskii.lingua.model.DetectionResult;
import ru.rusetskii.lingua.model.LanguageEntity;

import java.util.ArrayList;
import java.util.List;
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
        Map<Language, Double> languages = detector.computeLanguageConfidenceValues(input).entrySet().stream()
                .filter(x -> x.getValue() > 0.9).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        long end = System.currentTimeMillis();
        List<LanguageEntity> languageList = convert(languages);
        return new DetectionResult(languageList, end - start);
    }

    private List<LanguageEntity> convert(Map<Language, Double> languages) {
        List<LanguageEntity> languageList = new ArrayList<>();

        for (Map.Entry<Language, Double> language : languages.entrySet()) {
            languageList.add(new LanguageEntity(language.getKey().toString(), language.getValue()));
        }

        return languageList;
    }
}
