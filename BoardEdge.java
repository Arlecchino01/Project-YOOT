import java.util.*;

class BoardEdge {
    Tile start;
    List<Tile> tiles;
    Tile end;
    String name;

    public BoardEdge(Tile start, Tile end, int length) {
        this.start = start;
        this.end = end;
        this.tiles = new ArrayList<>();

        tiles.add(start);

        Tile prev = start;
        for (int i = 0; i < length-2; i++) {
            Tile t = new Tile();
            t.id = (i+1);
            prev.nextTiles.add(t);
            tiles.add(t);
            prev = t;
        }
        prev.nextTiles.add(end);
        tiles.add(end);
        
    }

    public Tile getEndTile(){
        return end;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
    
    public Tile getStartTile(){
        return start;
    }
    
}
