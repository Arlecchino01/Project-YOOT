import javax.swing.JButton;

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