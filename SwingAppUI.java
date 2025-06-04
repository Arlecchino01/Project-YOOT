public class SwingAppUI{
    
    private UI_Connection_Interface hub;
    private LogicEngine logicEngine;
    private Window window;
    
    public SwingAppUI(){
        
        //허브 생성
        hub = new UI_Connection_Interface();

        //로직 생성 + 허브 연결
        logicEngine = new LogicEngine();
        hub.setLogicEngine(logicEngine);

        //윈도우 생성 + 허브 연결
        window = new Window();
        hub.setUI(window);

        hub.connectSwingUI();
        hub.launchYut();
    }
}
