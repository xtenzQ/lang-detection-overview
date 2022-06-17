package ru.rusetskii.lingua.detector;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;
import ru.rusetskii.lingua.model.DetectionResult;
import ru.rusetskii.lingua.model.LanguageEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CybozuDetector extends AbstractDetector {

    private Detector detector;

    public CybozuDetector() {
        try {
            DetectorFactory.loadProfile(new File(Detector.class.getResource("/profiles").toURI()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public DetectionResult detect(String input) {
        List<Language> languages;
        try {
            detector = DetectorFactory.create();
            detector.append(input);

            long start = System.currentTimeMillis();
            languages = detector.getProbabilities();
            long end = System.currentTimeMillis();
            List<LanguageEntity> languageList = convert(languages);
            return new DetectionResult(languageList, end - start);
        } catch (LangDetectException e) {

        }
        return new DetectionResult();
    }

    private List<LanguageEntity> convert(List<Language> languages) {
        List<LanguageEntity> languageList = new ArrayList<>();

        for (Language language : languages) {
            languageList.add(new LanguageEntity(language.lang, language.prob));
        }

        return languageList;
    }
}
