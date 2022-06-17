package ru.rusetskii.lingua.detector;

import com.optimaize.langdetect.DetectedLanguage;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import ru.rusetskii.lingua.model.DetectionResult;
import ru.rusetskii.lingua.model.LanguageEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.rusetskii.lingua.Main.MAX_N;

public class OptimaizeDetector extends AbstractDetector {

    private final LanguageDetector languageDetector;

    public OptimaizeDetector() {
        List<LanguageProfile> languageProfiles;
        try {
            languageProfiles = new LanguageProfileReader().readAllBuiltIn();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                .withProfiles(languageProfiles)
                .build();
    }

    @Override
    public DetectionResult detect(String input) {
        long start = System.currentTimeMillis();
        List<DetectedLanguage> languages = languageDetector.getProbabilities(CommonTextObjectFactories.forDetectingOnLargeText().forText(input));
        long end = System.currentTimeMillis();
        List<LanguageEntity> languageList = convert(languages);
        return new DetectionResult(languageList, end - start);
    }

    private List<LanguageEntity> convert(List<DetectedLanguage> languages) {
        List<LanguageEntity> languageList = new ArrayList<>();

        for (DetectedLanguage language : languages) {
            languageList.add(new LanguageEntity(language.getLocale().toString(), language.getProbability()));
        }

        int max = Math.min(languageList.size(), MAX_N);

        return languageList.subList(0, max);
    }
}
