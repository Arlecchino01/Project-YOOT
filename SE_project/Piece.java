public class Piece {
    private int id;
    private Player owner;
    private Node currentPosition;
    private boolean isFinished;
    private List<Piece> stackedPieces = new ArrayList<>();

    public Piece(int id, Player owner) {
        this.id = id;
        this.owner = owner;
        this.isFinished = false;
    }

    public void moveTo(Node newPosition) {
        this.currentPosition = newPosition;
    }

    public void finish() {
        this.isFinished = true;
        this.currentPosition = null;
    }

    public void stackWith(Piece other) {
        if (!stackedPieces.contains(other)) {
            stackedPieces.add(other);
        }
    }

    public void unstack() {
        stackedPieces.clear();
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Node getCurrentPosition() {
        return currentPosition;
    }

    public List<Piece> getStackedPieces() {
        return stackedPieces;
    }

    public int getId() {
        return id;
    }
}
