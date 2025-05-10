import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Yut");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 버튼 패널
        JPanel buttonPanel = new JPanel();

        JButton startButton = new JButton("게임 시작");
        JButton exitButton = new JButton("종료");

        startButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "게임을 시작합니다!");
        });

        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
