import javax.swing.*;
import java.awt.*;

public class InputOutputSection extends JPanel {
    private InputPanel inputPanel;
    private OutputPanel outputPanel;
    private YutPanel yutPanel;

    public InputOutputSection(InputPanel inputPanel, OutputPanel outputPanel, YutPanel yutPanel){
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();

        this.inputPanel = inputPanel;
        this.outputPanel = outputPanel;
        this.yutPanel = yutPanel;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(outputPanel,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(inputPanel,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(yutPanel, gbc);

        inputPanel.setPreferredSize(new Dimension(300,70));
        outputPanel.setPreferredSize(new Dimension(300,400));
        yutPanel.setPreferredSize(new Dimension(300, 50));
        // 버튼에 액션 붙이기
        inputPanel.getSubmitButton().addActionListener(e -> {
            String text = inputPanel.getInputText();
            if (!text.isEmpty()) {
                inputPanel.handle(text);
                //outputPanel.showMessage(text);
                inputPanel.clearInput();
            }
        });
    }
}

class YutPanel extends JPanel{
    private RandomButton randomButton;
    private CustomButton customButton;
    private YutEngine yutEngine;
    private YutPanelCallBack callback;
    
    public interface YutPanelCallBack {
        public void onYut(YutPanel yutPanel);
    }

    public void setYutCallBack(YutPanelCallBack callback){
        this.callback = callback;
    }

    public void setYutEngine(YutEngine yutEngine){
        this.yutEngine = yutEngine;
    }

    public YutPanel(){
        randomButton = new RandomButton();
        randomButton.addActionListener(e -> {
            
        });

        customButton = new CustomButton();
        


        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(randomButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(customButton, gbc);
    }
}

class RandomButton extends JButton implements ToggleButton{
    private boolean toggle;

    @Override
    public void turnOn(){
        this.toggle = true;
    }
    @Override
    public void turnOff(){
        this.toggle = false;
    }

    public RandomButton(){
        super("Random");
    }
}

class CustomButton extends JButton implements ToggleButton{
    private boolean toggle;

    @Override
    public void turnOn(){
        this.toggle = true;
    }
    @Override
    public void turnOff(){
        this.toggle = false;
    }
    
    public CustomButton(){
        super("Custom");
    }
}