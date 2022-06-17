package ru.rusetskii.lingua;

import net.steppschuh.markdowngenerator.table.Table;
import ru.rusetskii.lingua.detector.*;
import ru.rusetskii.lingua.model.DetectionResult;
import ru.rusetskii.lingua.model.InputSentence;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<InputSentence> input = new ArrayList<>();

    public static void main(String[] args) {
        input.add(new InputSentence("hello it's me", "en"));
        input.add(new InputSentence("Привет как дела?", "ru"));
        input.add(new InputSentence("Сколько стоит iPhone 12?", "ru, en"));
        input.add(new InputSentence("iPhone 12 的價格是多少？", "ch", "en"));
        input.add(new InputSentence("你好你好嗎？", "ch"));
        input.add(new InputSentence("כמה עולה האייפון 12?", "he"));
        input.add(new InputSentence("iPhone 12 ราคาเท่าไหร่?", "th", "en"));
        input.add(new InputSentence("Скільки коштує iPhone 12?", "uk", "en"));
        input.add(new InputSentence("Привет вітання", "uk", "ru"));

        Table.Builder tableBuilder = new Table.Builder().withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
                .addRow("Input text", "Input language", "Optimaize Detector", "ms", "Lingua Detector",
                        "ms", "Cybozu Detector", "ms", "fastText Detector", "ms", "OpenNLP", "ms");

        OptimaizeDetector optimaizeDetector = new OptimaizeDetector();
        LinguaDetector linguaDetector = new LinguaDetector();
        CybozuDetector cybozuDetector = new CybozuDetector();
        FastTextDetector fastTextDetector = new FastTextDetector();
        OpenNLPDetector openNLPDetector = new OpenNLPDetector();

        for (InputSentence sentence : input) {
            DetectionResult optimaizeResults = optimaizeDetector.detect(sentence.getSentence());
            DetectionResult linguaResults = linguaDetector.detect(sentence.getSentence());
            DetectionResult cybozyResults = cybozuDetector.detect(sentence.getSentence());
            DetectionResult fastTextResults = fastTextDetector.detect(sentence.getSentence());
            DetectionResult openNLPResults = openNLPDetector.detect(sentence.getSentence());
            tableBuilder.addRow(sentence.getSentence(), sentence.getLanguages(),
                                optimaizeResults.getResult(), optimaizeResults.getTime(),
                                linguaResults.getResult(), linguaResults.getTime(),
                                cybozyResults.getResult(), cybozyResults.getTime(),
                                fastTextResults.getResult(), fastTextResults.getTime(),
                                openNLPResults.getResult(), openNLPResults.getTime());
        }

        System.out.println(tableBuilder.build());
    }
}
