public class ConsoleOutput implements OutputHandler{
    @Override
    public void show(String msg){
        System.out.println("ConsoleOutput (implements Handler) activated with: " + msg);
    }
}
