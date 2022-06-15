import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;

import java.util.Map;

public class LinguaDetector extends Detector {

    private LanguageDetector detector;

    public LinguaDetector() {
        detector = LanguageDetectorBuilder.fromAllLanguages().build();
    }

    @Override
    public void detect(String input) {
        System.out.println("LinguaDetector:");
        long start = System.currentTimeMillis();
        Map<Language, Double> languageMap = detector.computeLanguageConfidenceValues(input);
        long end = System.currentTimeMillis();
        System.out.println("DEBUG: Logic A took " + (end - start) + " MilliSeconds");
        System.out.println(languageMap + "\n");
    }
}
