public class ConsoleApp {
    

    public ConsoleApp(){
        OutputHandler consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();

        LogicEngine engine = new LogicEngine(consoleInput, consoleOutput);
        consoleInput.setInputEngine(engine.getInputEngine());   //인풋핸들러를 인풋엔진에 연결

        //consoleInput.handle("TEST FROM ConsoleApp ");
    }
}
