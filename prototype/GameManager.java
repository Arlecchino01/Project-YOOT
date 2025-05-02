import java.util.*;

public class GameManager {
    private List<Player> players;
    private int currentPlayerIndex;
    private Board board;
    private Scanner scanner;

    public GameManager() {
        this.players = new ArrayList<>();
        this.board = new Board(20); // 20칸짜리 직선 보드
        this.currentPlayerIndex = 0;
        this.scanner = new Scanner(System.in);
    }

    public void setupGame() {
        System.out.print("플레이어 수를 입력하세요: ");
        int playerCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < playerCount; i++) {
            System.out.print((i + 1) + "번 플레이어 이름: ");
            String name = scanner.nextLine();
            players.add(new Player(name, 2)); // 말 2개씩
        }
    }

    public void playGame() {
        while (true) {
            Player player = players.get(currentPlayerIndex);
            System.out.println("\n[" + player.getName() + "]님의 차례입니다.");

            Yut yut = Yut.fromInput();
            int steps = yut.getStepCount();

            List<Piece> movable = getMovablePieces(player);
            if (movable.isEmpty()) {
                System.out.println("움직일 수 있는 말이 없습니다. 턴을 넘깁니다.");
            } else {
                Piece chosen = choosePiece(movable);
                board.movePiece(chosen, steps);
                System.out.println("이동 결과: " + chosen);
            }

            if (player.hasAllPiecesFinished()) {
                System.out.println("\n🎉 " + player.getName() + "님이 승리하셨습니다!");
                break;
            }

            // 다음 플레이어로 이동
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        scanner.close();
    }

    private List<Piece> getMovablePieces(Player player) {
        List<Piece> result = new ArrayList<>();
        for (Piece p : player.getPieces()) {
            if (!p.isFinished()) {
                result.add(p);
            }
        }
        return result;
    }

    private Piece choosePiece(List<Piece> pieces) {
        System.out.println("움직일 말을 선택하세요:");
        for (int i = 0; i < pieces.size(); i++) {
            System.out.println(i + ": " + pieces.get(i));
        }
        int index = Integer.parseInt(scanner.nextLine());
        return pieces.get(index);
    }
}
