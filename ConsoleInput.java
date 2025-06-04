public class ConsoleInput implements InputHandler{

    private InputEngine inputEngine;

    public ConsoleInput(){
        
    }

    public void setInputEngine(InputEngine inputEngine){
        this.inputEngine = inputEngine;
    }
    
    @Override
    public void handle(String input){
        inputEngine.handleInput(input);
    };
}
