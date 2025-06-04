public class Manager {
    private OutputPanel outputPanel;

    public Manager(OutputPanel outputPanel){
        this.outputPanel = outputPanel;
        outputPanel.showMessage("Manager in operation");
    }

    public void handleInput(String input){
        outputPanel.showMessage("Hi");
        outputPanel.showMessage("Your input = " + input);
    }
}
