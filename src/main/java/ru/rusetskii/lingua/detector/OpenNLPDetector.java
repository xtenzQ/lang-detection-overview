package ru.rusetskii.lingua.detector;

import com.optimaize.langdetect.DetectedLanguage;
import opennlp.tools.langdetect.*;
import ru.rusetskii.lingua.model.DetectionResult;
import ru.rusetskii.lingua.model.LanguageEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class OpenNLPDetector extends AbstractDetector {

    private LanguageDetectorME ld;

    public OpenNLPDetector() {
        InputStream inputStream = getClass()
                .getResourceAsStream("/langdetect-183.bin");
        LanguageDetectorModel model = null;
        try {
            model = new LanguageDetectorModel(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ld = new LanguageDetectorME(model);
    }

    @Override
    public DetectionResult detect(String input) {
        long start = System.currentTimeMillis();
        Language[] languages = ld.predictLanguages(input);
        long end = System.currentTimeMillis();
        List<LanguageEntity> languageList = convert(languages).subList(0, 5);
        return new DetectionResult(languageList, end - start);
    }

    private List<LanguageEntity> convert(Language[] languages) {
        List<LanguageEntity> languageList = new ArrayList<>();

        for (Language language : languages) {
            languageList.add(new LanguageEntity(language.getLang(), language.getConfidence()));
        }

        return languageList;
    }
}
