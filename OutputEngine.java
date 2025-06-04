public class OutputEngine {
    private CentralEngine central;
    private OutputHandler handler;

    public void setCentralEngine(CentralEngine central){
        this.central = central;
    }

    public void setOutputHandler(OutputHandler handler){
        this.handler = handler;
    }

    public void showMessage(String msg){
        handler.show(msg);
    }
}
