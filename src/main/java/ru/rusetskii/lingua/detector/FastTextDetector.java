package ru.rusetskii.lingua.detector;

import com.github.jfasttext.JFastText;
import ru.rusetskii.lingua.model.DetectionResult;

import java.util.List;

public class FastTextDetector extends AbstractDetector {

    private final JFastText jft;

    public FastTextDetector() {
        jft = new JFastText();
        jft.loadModel("src/main/resources/lid.176.ftz");
    }

    @Override
    public DetectionResult detect(String input) {
        long start = System.currentTimeMillis();
        List<JFastText.ProbLabel> probLabel = jft.predictProba(input, 5);
        long end = System.currentTimeMillis();
        String res = "";
        for (JFastText.ProbLabel label : probLabel) {
            res += label.label + " : " + String.format("%.09f", Math.exp(label.logProb)) + ", ";
        }
        return new DetectionResult(res, end - start);
    }
}
