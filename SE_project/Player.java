public class Player {
    private String name;
    private List<Piece> pieces = new ArrayList<>();
    private boolean hasWon;

    public Player(String name, int pieceCount) {
        this.name = name;
        this.hasWon = false;

        for (int i = 0; i < pieceCount; i++) {
            pieces.add(new Piece(i, this));
        }
    }

    public String getName() {
        return name;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public boolean checkWinCondition() {
        for (Piece piece : pieces) {
            if (!piece.isFinished()) {
                return false;
            }
        }
        hasWon = true;
        return true;
    }

    public boolean hasWon() {
        return hasWon;
    }
}
