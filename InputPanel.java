import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    private JTextField textField;
    private JButton submitButton;
    private InputCallback currentCallback;
    private OutputWriter writer;

    public InputPanel() {
        // 패널 레이아웃 설정
        setLayout(new FlowLayout());

        // 텍스트 필드 만들기
        JLabel label = new JLabel("입력: ");
        textField = new JTextField(10);
        submitButton = new JButton("확인");

        add(label);
        add(textField);
        add(submitButton);

        submitButton.addActionListener(e -> {
            if (currentCallback != null) {
                String value = textField.getText().trim();
                textField.setText("");
                currentCallback.onInput(value);
                currentCallback = null;
            }
        });
    }

    public String getInput() {
        return textField.getText();
    }


    public void requestInput(InputCallback callback) {
        this.currentCallback = callback;
    }
}