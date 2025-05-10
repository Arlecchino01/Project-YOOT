import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    boolean isFinished;

    List<Piece> pieces;

    public Player(){
        this.pieces = new ArrayList<>();
    }
}
