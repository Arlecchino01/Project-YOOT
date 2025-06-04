import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1. 게임 설정
        int playerCount = 2;
        int piecePerPlayer = 3;

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++) {
            players.add(new Player("플레이어 " + i, piecePerPlayer));
        }

        // 2. 보드 생성
        Board board = new Board();  // 기본: 사각형
        // board.setupPentagonBoard(); // ← 필요시 오각형 적용
        // board.setupHexagonBoard();  // ← 필요시 육각형 적용

        // 3. 컨트롤러 생성
        GameController controller = new GameController(players, board);

        // 4. UI 생성
        SwingUtilities.invokeLater(() -> {
            IGameView view = new SwingGameView();
            view.bindController(controller);
            controller.throwYut(true, null); // 첫 턴 자동 시작
            view.updateTurn(controller.getCurrentPlayer());
            view.showBoard(board);
        });
    }
}
