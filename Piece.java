class Piece {
    private Tile currentTile;

    public Piece(Tile startTile) {
        this.currentTile = startTile;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile tile) {
        this.currentTile = tile;
    }

    public void testing(){
        System.out.println("yes i am here");
    }
}