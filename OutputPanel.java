import javax.swing.*;
import java.awt.*;

public class OutputPanel extends JPanel implements OutputWriter {
    private JTextArea textArea;

    public OutputPanel() {
        // 패널 레이아웃 설정
        setLayout(new BorderLayout());

        // 텍스트 영역 만들기
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);  // 편집 불가능하게 설정
        JScrollPane scrollPane = new JScrollPane(textArea);

        // 텍스트 영역을 스크롤 페인에 넣어서 패널에 추가
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void print(String message) {
        textArea.append(message + "\n");  // 텍스트에 메시지를 추가하고 줄 바꿈
    }
}
