public interface IGameView {
    void showBoard(Board board);
    void updateTurn(Player currentPlayer);
    void showYutResult(List<YutResult> results);
    void promptPieceSelection(List<Piece> movablePieces);
    void showVictory(Player winner);
    void bindController(GameController controller);  // 이벤트 연결
}