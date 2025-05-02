public class Piece {
    private int position;
    private boolean isFinished;

    public Piece() {
        this.position = 0;
        this.isFinished = false;
    }

    public int getPosition() {
        return position;
    }

    public boolean isFinished() {
        return isFinished;
    }

    // 말을 이동시킴
    public void move(int steps, int boardLength) {
        position += steps;
        if (position >= boardLength) {
            isFinished = true;
            position = boardLength; // 보드 끝 위치로 고정
        }
    }

    @Override
    public String toString() {
        return "Piece{position=" + position + ", finished=" + isFinished + "}";
    }
}
