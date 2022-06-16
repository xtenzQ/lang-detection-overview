package ru.rusetskii.lingua.detector;

import com.optimaize.langdetect.DetectedLanguage;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import ru.rusetskii.lingua.model.DetectionResult;

import java.io.IOException;
import java.util.List;

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
        return new DetectionResult(languages.toString(), end - start);
    }
}
