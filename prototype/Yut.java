public class Yut {
    private String result; // 도, 개, 걸, 윷, 모 중 하나

    // 생성자 (수동 입력)
    public Yut(String result) {
        if (isValidResult(result)) {
            this.result = result;
        } else {
            throw new IllegalArgumentException("유효하지 않은 윷 결과입니다.");
        }
    }

    // 결과값 반환
    public String getResult() {
        return result;
    }

    // 이동 칸수 반환
    public int getStepCount() {
        switch (result) {
            case "do": return 1;
            case "개": return 2;
            case "걸": return 3;
            case "윷": return 4;
            case "모": return 5;
            default: return 0;
        }
    }

    // 유효한 입력인지 확인dd
    private boolean isValidResult(String r) {
        return r.equals("do") || r.equals("개") || r.equals("걸") || r.equals("윷") || r.equals("모");
    }

    // 콘솔에서 직접 입력받기 위한 static 함수 (선택적 사용)
    public static Yut fromInput() {
        System.out.print("윷 결과를 입력하세요 (도/개/걸/윷/모): ");
        String input = InputManager.scanner.nextLine().trim();
        return new Yut(input);
    }
}
