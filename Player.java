import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Piece> pieces;
    boolean isFinished;
    private String color;

    public Player(String name){
        this.name = name;
        pieces = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public void generatePieces(int num, Tile origin){
        for(int i = 0; i < num; i++){
            Piece piece = new Piece(origin);
            pieces.add(piece);
        }
    }

    public void testing(int i){
        pieces.get(i-1).testing();
    }

    public void setColor(String color){
        this.color = color;
    }

    public String returnColor(){
        return color;
    }

    public List<Piece> getPieces(){
        return pieces;
    }
}
