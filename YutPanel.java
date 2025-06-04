import java.awt.*;

import javax.swing.JPanel;

public class YutPanel extends JPanel{
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