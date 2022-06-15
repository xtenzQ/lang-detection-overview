public class Main {

    private static final String[] input = new String[]{
            "hello it's me",
            "Привет как дела?",
            "Сколько стоит iPhone 12?",
            "iPhone 12 的價格是多少？",
            "你好你好嗎？",
            "היי, מה שלומך?",
            "iPhone 12 ราคาเท่าไหร่?",
            "Скільки коштує iPhone 12?",
            "Привет вітання"
    };

    public static void main(String[] args) {
        OptimaizeDetector optimaizeDetector = new OptimaizeDetector();
        LinguaDetector linguaDetector = new LinguaDetector();
        CybozuDetector cybozuDetector = new CybozuDetector();
        for (String row : input) {
            System.out.println("Input string: " + row + "\n");
            optimaizeDetector.detect(row);
            linguaDetector.detect(row);
            cybozuDetector.detect(row);
            System.out.println("===============\n");
        }
    }
}
