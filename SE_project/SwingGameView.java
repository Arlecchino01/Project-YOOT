import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SwingGameView extends JFrame implements IGameView {
    private JLabel turnLabel;
    private JTextArea yutResultArea;
    private JButton throwYutButton, throwManualButton;
    private JPanel boardPanel;

    private GameController controller;

    public SwingGameView() {
        setTitle("윷놀이 - Swing UI");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 상단
        JPanel topPanel = new JPanel();
        turnLabel = new JLabel("현재 차례: ");
        topPanel.add(turnLabel);

        // 중앙
        boardPanel = new JPanel();
        boardPanel.setBackground(Color.WHITE);
        boardPanel.setPreferredSize(new Dimension(600, 400));

        // 하단
        JPanel bottomPanel = new JPanel();
        throwYutButton = new JButton("랜덤 윷 던지기");
        throwManualButton = new JButton("지정 윷 던지기");

        yutResultArea = new JTextArea(3, 40);
        yutResultArea.setEditable(false);

        bottomPanel.add(throwYutButton);
        bottomPanel.add(throwManualButton);
        bottomPanel.add(new JScrollPane(yutResultArea));

        add(topPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // 이벤트 연결
        throwYutButton.addActionListener(e -> {
            controller.throwYut(true, null);
        });

        throwManualButton.addActionListener(e -> {
            // 테스트용: 걸 + 윷 예시
            controller.throwYut(false, List.of(YutResult.GEOL, YutResult.YUT));
        });

        setVisible(true);
    }

    @Override
    public void showBoard(Board board) {
        boardPanel.removeAll();
        boardPanel.repaint();
        boardPanel.revalidate();

        // TODO: 실제 노드와 말 배치를 그릴 예정
    }

    @Override
    public void updateTurn(Player currentPlayer) {
        turnLabel.setText("현재 차례: " + currentPlayer.getName());
    }

    @Override
    public void showYutResult(List<YutResult> results) {
        StringBuilder sb = new StringBuilder();
        for (YutResult r : results) {
            sb.append(r).append(" ");
        }
        yutResultArea.setText("윷 결과: " + sb.toString());
    }

    @Override
    public void promptPieceSelection(List<Piece> movablePieces) {
        // TODO: 말 선택 UI 구현
        JOptionPane.showMessageDialog(this, "말 선택 기능 구현 예정");
    }

    @Override
    public void showVictory(Player winner) {
        JOptionPane.showMessageDialog(this, "🎉 승리자: " + winner.getName());
    }

    @Override
    public void bindController(GameController controller) {
        this.controller = controller;
    }
}
