import net.steppschuh.markdowngenerator.table.Table;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<String[]> input = new ArrayList<>();

    public static void main(String[] args) {
        input.add(new String[] { "hello it's me", "en" });
        input.add(new String[] { "Привет как дела?", "ru" });
        input.add(new String[] { "Сколько стоит iPhone 12?", "ru, en" });
        input.add(new String[] { "iPhone 12 的價格是多少？", "ch, en"});
        input.add(new String[] { "你好你好嗎？", "ch"});
        input.add(new String[] { "היי, מה שלומך?", "he"});
        input.add(new String[] { "iPhone 12 ราคาเท่าไหร่?", "th"});
        input.add(new String[] { "Скільки коштує iPhone 12?", "uk, en" });
        input.add(new String[] { "Привет вітання", "uk, ru" });

        Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
                .addRow("Input text", "Input language", "Optimaize Detector", "ms", "Lingua Detector", "ms", "Cybozu Detector", "ms");

        OptimaizeDetector optimaizeDetector = new OptimaizeDetector();
        LinguaDetector linguaDetector = new LinguaDetector();
        CybozuDetector cybozuDetector = new CybozuDetector();

        for (String[] row : input) {
            String[] optimaizeResults = optimaizeDetector.detect(row[0]);
            String[] linguaResults = linguaDetector.detect(row[0]);
            String[] cybozyResults = cybozuDetector.detect(row[0]);
            tableBuilder.addRow(row[0], row[1], optimaizeResults[0], optimaizeResults[1], linguaResults[0], linguaResults[1], cybozyResults[0], cybozyResults[1]);
        }

        System.out.println(tableBuilder.build());
    }
}
