import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Yut {
    private static final Random random = new Random();

    // 윷 하나 던지기
    public static YutResult throwRandom() {
        int value = random.nextInt(16);  // 0~15

        if (value == 0) return YutResult.BACK_DO;
        else if (value <= 4) return YutResult.DO;
        else if (value <= 8) return YutResult.GAE;
        else if (value <= 11) return YutResult.GEOL;
        else if (value <= 13) return YutResult.YUT;
        else return YutResult.MO;
    }

    // 테스트용: 지정 던지기
    public static YutResult throwManual(YutResult result) {
        return result;
    }

    // 연속 던지기 지원 (ex. 윷 or 모 나올 때)
    public static List<YutResult> throwSequence() {
        List<YutResult> results = new ArrayList<>();
        while (true) {
            YutResult r = throwRandom();
            results.add(r);
            if (r != YutResult.YUT && r != YutResult.MO) break;
        }
        return results;
    }
}
