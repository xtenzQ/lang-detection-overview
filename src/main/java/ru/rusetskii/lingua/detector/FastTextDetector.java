package ru.rusetskii.lingua.detector;

import com.github.jfasttext.JFastText;
import ru.rusetskii.lingua.model.DetectionResult;
import ru.rusetskii.lingua.model.LanguageEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static ru.rusetskii.lingua.Main.MAX_N;

public class FastTextDetector extends AbstractDetector {

    public static final String EMPTY = "";
    public static final String LABEL = "__label__";

    private final JFastText jft;

    public FastTextDetector() {
        jft = new JFastText();
        jft.loadModel("src/main/resources/lid.176.ftz");
    }

    @Override
    public DetectionResult detect(String input) {
        long start = System.currentTimeMillis();
        List<JFastText.ProbLabel> probLabel = jft.predictProba(input, MAX_N);
        long end = System.currentTimeMillis();
        List<LanguageEntity> languageList = convert(probLabel);
        return new DetectionResult(languageList, end - start);
    }

    private List<LanguageEntity> convert(List<JFastText.ProbLabel> probLabel) {
        List<LanguageEntity> languageList = new ArrayList<>();

        for (JFastText.ProbLabel language : probLabel) {
            languageList.add(new LanguageEntity(prettifyLabel(language.label), prettifyDouble(Math.exp(language.logProb))));
        }

        int max = Math.min(languageList.size(), MAX_N);

        return languageList.subList(0, max);
    }

    private String prettifyLabel(String label) {
        return label.replaceAll(LABEL, EMPTY);
    }

    private double prettifyDouble(double prob) {
        return BigDecimal.valueOf(prob).setScale(9, RoundingMode.HALF_UP).doubleValue();
    }
}
