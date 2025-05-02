import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Piece> pieces;

    public Player(String name, int pieceCount) {
        this.name = name;
        this.pieces = new ArrayList<>();
        for (int i = 0; i < pieceCount; i++) {
            pieces.add(new Piece());
        }
    }

    public String getName() {
        return name;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public boolean hasAllPiecesFinished() {
        for (Piece p : pieces) {
            if (!p.isFinished()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', pieces=" + pieces + "}";
    }
}
