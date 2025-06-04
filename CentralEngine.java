//YutEngine입장에서는 CentralEngine이 Callee다.
//YutEngine이 Central을 부르는 거다.

public class CentralEngine {
    private InputEngine inputEngine;
    private OutputEngine outputEngine;
    private YutEngine yutEngine;
    private String handlingString;
    private YutCallback callback;

    public interface YutCallback {
        public void onInput(CentralEngine centralEngine);
    }

    
    
    public CentralEngine(InputEngine inputEngine, OutputEngine outputEngine){
        this.inputEngine = inputEngine;
        this.outputEngine = outputEngine;
        handlingString = "nullString";
        this.callback = null;
    }

    public void handleInputFromInputEngine(String input){
        this.handlingString = input;
    }

    
    public void sendStringToOutputEngine(String string){
        outputEngine.showMessage(string);
    }

    

    
    //============= Communication with YutEngine =============

    public void initializeGame(){
        //sendStringToOutputEngine("YutEngine을 구동합니다.");
        yutEngine = new YutEngine();
        yutEngine.setCentralEngine(this);
        //yutEngine.launch();
    }

    public void launchYut(){
        yutEngine.launch();
    }

    public void setYutCallBack(YutCallback callback){
        this.callback = callback;
    }

    public String getString(){
        return this.handlingString;
    }

    public void executeYut(){
        if (this.callback != null) {
            this.callback.onInput(CentralEngine.this);
        }
    }

    public YutEngine getYutEngine(){
        return yutEngine;
    }



}