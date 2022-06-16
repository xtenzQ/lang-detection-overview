import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;

import java.io.File;
import java.util.List;

public class CybozuDetector extends AbstractDetector {

    private Detector abstractDetector;

    public CybozuDetector() {
        try {
            DetectorFactory.loadProfile(new File(Detector.class.getResource("/profiles").toURI()));
            abstractDetector = DetectorFactory.create();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String[] detect(String input) {
        List<Language> languages;
        try {
            long start = System.currentTimeMillis();
            abstractDetector.append(input);
            languages = abstractDetector.getProbabilities();
            long end = System.currentTimeMillis();
            return new String[] { languages.toString(), String.valueOf(end - start) };
        } catch (LangDetectException e) {

        }
        return new String[] {};
    }
}
