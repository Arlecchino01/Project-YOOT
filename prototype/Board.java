public class Board {
    private int boardLength;

    public Board(int boardLength) {
        this.boardLength = boardLength;
    }

    public int getBoardLength() {
        return boardLength;
    }

    public void movePiece(Piece piece, int steps) {
        piece.move(steps, boardLength);
    }

    public boolean isAtGoal(Piece piece) {
        return piece.isFinished();
    }
}
