package ru.rusetskii.lingua.detector;

import com.github.jfasttext.JFastText;
import ru.rusetskii.lingua.model.DetectionResult;

public class FastTextDetector extends AbstractDetector {

    private JFastText jft;

    public FastTextDetector() {
        jft = new JFastText();
        jft.loadModel("src/main/resources/lid.176.ftz");
    }

    @Override
    public DetectionResult detect(String input) {
        JFastText.ProbLabel probLabel = jft.predictProba(input);
        return new DetectionResult(probLabel.label + " " + Math.exp(probLabel.logProb), 0);
    }
}
