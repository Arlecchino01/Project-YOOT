public class Node {
    private String name;  // 예: "S", "A1", "C", "P" 등
    private Node next;    // 다음 위치 (단순 경로용)
    private List<Piece> pieces = new ArrayList<>();  // 현재 이 위치에 있는 말들

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        return name;
    }
}
