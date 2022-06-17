package ru.rusetskii.lingua.detector;

import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import ru.rusetskii.lingua.model.DetectionResult;
import ru.rusetskii.lingua.model.LanguageEntity;

import java.util.*;

import static ru.rusetskii.lingua.Main.MAX_N;

public class LinguaDetector extends AbstractDetector {

    private final LanguageDetector detector;

    public LinguaDetector() {
        detector = LanguageDetectorBuilder.fromAllLanguages().build();
    }

    @Override
    public DetectionResult detect(String input) {
        long start = System.currentTimeMillis();
        SortedMap<Language, Double> languages = detector.computeLanguageConfidenceValues(input);
        long end = System.currentTimeMillis();
        List<LanguageEntity> languageList = convert(languages);
        return new DetectionResult(languageList, end - start);
    }

    private List<LanguageEntity> convert(Map<Language, Double> languages) {
        List<LanguageEntity> languageList = new ArrayList<>();

        for (Map.Entry<Language, Double> language : languages.entrySet()) {
            languageList.add(new LanguageEntity(language.getKey().toString(), language.getValue()));
        }

        int max = Math.min(languageList.size(), MAX_N);

        return languageList.subList(0, max);
    }
}
