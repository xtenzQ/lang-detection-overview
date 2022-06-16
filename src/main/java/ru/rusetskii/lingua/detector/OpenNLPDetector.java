package ru.rusetskii.lingua.detector;

import opennlp.tools.langdetect.*;
import ru.rusetskii.lingua.model.DetectionResult;

import java.io.IOException;
import java.io.InputStream;

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
        String res = formatOutput(languages);
        return new DetectionResult(res, end - start);
    }

    private String formatOutput(Language[] input) {
        String res = "";
        for (int i = 0; i < 5; i++) {
            res += input[i].getLang() + ":" + input[i].getConfidence() + ", ";
        }
        return res;
    }
}
