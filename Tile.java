import java.util.*;

class Tile {
    int id;
    boolean isOrigin;
    List<Tile> nextTiles = new ArrayList<>();

    public int getId() {
        return id;
    }
}
