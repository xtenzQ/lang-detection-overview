package ru.rusetskii.lingua.detector;

import opennlp.tools.langdetect.*;
import ru.rusetskii.lingua.model.DetectionResult;
import ru.rusetskii.lingua.model.LanguageEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.rusetskii.lingua.Main.MAX_N;

public class OpenNLPDetector extends AbstractDetector {

    private final LanguageDetectorME ld;

    public OpenNLPDetector() {
        InputStream inputStream = getClass()
                .getResourceAsStream("/langdetect-183.bin");
        LanguageDetectorModel model;
        try {
            model = new LanguageDetectorModel(Objects.requireNonNull(inputStream));
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
        List<LanguageEntity> languageList = convert(languages);
        return new DetectionResult(languageList, end - start);
    }

    private List<LanguageEntity> convert(Language[] languages) {
        List<LanguageEntity> languageList = new ArrayList<>();

        for (Language language : languages) {
            languageList.add(new LanguageEntity(language.getLang(), language.getConfidence()));
        }

        int max = Math.min(languageList.size(), MAX_N);

        return languageList.subList(0, max);
    }
}
