import com.optimaize.langdetect.DetectedLanguage;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;

import java.io.IOException;
import java.util.List;

public class OptimaizeDetector extends Detector {

    private LanguageDetector languageDetector;

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
    public void detect(String input) {
        System.out.println("OptimaizeDetector:");
        long start = System.currentTimeMillis();
        List<DetectedLanguage> languages = languageDetector.getProbabilities(CommonTextObjectFactories.forDetectingOnLargeText().forText(input));
        long end = System.currentTimeMillis();
        System.out.println("DEBUG: Logic A took " + (end - start) + " MilliSeconds");
        System.out.println(languages + "\n");
    }
}
