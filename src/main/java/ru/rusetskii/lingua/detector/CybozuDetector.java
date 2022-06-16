package ru.rusetskii.lingua.detector;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;
import ru.rusetskii.lingua.model.DetectionResult;

import java.io.File;
import java.util.List;

public class CybozuDetector extends AbstractDetector {

    private Detector detector;

    public CybozuDetector() {
        try {
            DetectorFactory.loadProfile(new File(Detector.class.getResource("/profiles").toURI()));
            detector = DetectorFactory.create();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public DetectionResult detect(String input) {
        List<Language> languages;
        try {
            long start = System.currentTimeMillis();
            detector.append(input);
            languages = detector.getProbabilities();
            long end = System.currentTimeMillis();
            return new DetectionResult(languages.toString(), end - start);
        } catch (LangDetectException e) {

        }
        return new DetectionResult();
    }
}
