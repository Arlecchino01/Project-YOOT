import java.util.*;

public class GameController {
    private List<Player> players;
    private Board board;
    private int currentPlayerIndex = 0;
    private Queue<YutResult> yutQueue = new LinkedList<>();
    private boolean isGameOver = false;

    public GameController(List<Player> players, Board board) {
        this.players = players;
        this.board = board;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    // 윷 던지기
    public void throwYut(boolean isRandom, List<YutResult> manualResults) {
        yutQueue.clear();
        if (isRandom) {
            yutQueue.addAll(Yut.throwSequence());
        } else {
            yutQueue.addAll(manualResults);
        }
    }

    // 말 이동 처리
    public void movePiece(Piece piece) {
        while (!yutQueue.isEmpty()) {
            YutResult result = yutQueue.poll();
            int moveCount = result.getMoveCount();

            Node current = piece.getCurrentPosition();
            Node next = getNextNode(current, moveCount);

            // 이동 처리
            if (next != null) {
                current.removePiece(piece);
                next.addPiece(piece);
                piece.moveTo(next);

                // 잡기
                for (Piece other : next.getPieces()) {
                    if (!other.getOwner().equals(piece.getOwner())) {
                        // 상대말 잡기
                        other.moveTo(board.getStartNode());
                        System.out.println("상대 말 잡음!");
                        break;
                    }
                }

                // 완주 처리
                if (next == board.getEndNode()) {
                    piece.finish();
                    System.out.println("말 완주!");
                }
            }
        }

        // 업기 로직은 별도 구현 필요 (추후 추가)

        // 턴 넘기기
        if (!checkWinCondition(getCurrentPlayer())) {
            nextTurn();
        } else {
            isGameOver = true;
            System.out.println("🎉 승리자: " + getCurrentPlayer().getName());
        }
    }

    private Node getNextNode(Node start, int moveCount) {
        Node current = start;
        int count = Math.abs(moveCount);
        boolean isBackward = moveCount < 0;

        while (count-- > 0 && current != null) {
            current = isBackward ? board.getPreviousNode(current) : current.getNext();
        }
        return current;
    }

    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private boolean checkWinCondition(Player player) {
        return player.checkWinCondition();
    }
}
