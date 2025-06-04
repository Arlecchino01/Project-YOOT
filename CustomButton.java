import javax.swing.JButton;

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