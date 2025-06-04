public class InputEngine {
    private CentralEngine central;
    private InputHandler handler;

    public void setCentralEngine(CentralEngine central){
        this.central = central;
    }

    public void setInputHandler(InputHandler handler){
        this.handler = handler;
    }

    public void handleInput(String input){
        central.handleInputFromInputEngine(input);
        central.executeYut();
    }

    

}
