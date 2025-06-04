public class LogicEngine {
    private InputEngine inputEngine;
    private OutputEngine outputEngine;
    private CentralEngine centralEngine;

    public LogicEngine(){
        this.inputEngine = new InputEngine();
        this.outputEngine = new OutputEngine();
        //엔진들을 생성

        //this.inputEngine.setInputHandler(inputHandler);
        //this.outputEngine.setOutputHandler(outputHandler);
        //각 인/아웃풋 엔진에 핸들러를 연결
        //이 핸들러들은 각각
        //콘솔 모드: ConsoleInput (implements Input/OutputHandler)
        //Swing모드: InputPanel (implements Input/OutputHandler)
        //에 연결된다.

        this.centralEngine = new CentralEngine(inputEngine, outputEngine);
        
        
        this.inputEngine.setCentralEngine(centralEngine);
        this.outputEngine.setCentralEngine(centralEngine);
        centralEngine.initializeGame();
        //  인/아웃풋 엔진들에 중앙엔진을 연결
    }

    public InputEngine getInputEngine(){
        return inputEngine;
    }

    public OutputEngine getOutputEngine(){
        return outputEngine;
    }

    public CentralEngine getCentralEngine(){
        return centralEngine;
    }

    public void test(){
        System.out.println("test");
    }


}
