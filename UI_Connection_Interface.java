import java.util.*;

public class UI_Connection_Interface {
    private LogicEngine logicEngine;
    private InputEngine inputEngine;
    private OutputEngine outputEngine;
    private CentralEngine centralEngine;
    private YutEngine yutEngine;
    private Board board;
    private List<Player> playerList;

    private Window ui;
    private InputPanel inputPanel;
    private OutputPanel outputPanel;
    private BoardPanel boardPanel;
    private PlayerPanel playerPanel;
    private YutPanel yutPanel;


    public UI_Connection_Interface(){
    }

    

    public void setLogicEngine(LogicEngine logicEngine){
        this.logicEngine = logicEngine;
        setLogicComponent();
    }

    public void setLogicComponent(){
        //InputEngine
        this.inputEngine = logicEngine.getInputEngine();

        //outputEngine
        this.outputEngine = logicEngine.getOutputEngine();

        //centralEngine
        this.centralEngine = logicEngine.getCentralEngine();
        
        //YutEngine
        this.yutEngine = centralEngine.getYutEngine();

        //board
        //this.board = yutEngine.getBoard();

        //PlayerInfo
        this.playerList = yutEngine.getPlayerList();
        //
    }

    public void setUI(Window window){
        this.ui = window;
        setUIComponent();
    }

    public void setUIComponent(){
        //inputPanel
        this.inputPanel = ui.getInputPanel();

        //outputPanel
        this.outputPanel = ui.getOutputPanel();

        //BoardPanel
        this.boardPanel = ui.getBoardPanel();
        
        //PlayerPanel
        this.playerPanel = ui.getPlayerPanel();

        //YutPanel
        this.yutPanel = ui.getYutPanel();

    }

    public void connectSwingUI(){
        //inputEngine <==> inputPanel
        inputEngine.setInputHandler(inputPanel);
        inputPanel.setInputEngine(inputEngine);

        //outputEngine <===> outputPanel
        outputEngine.setOutputHandler(outputPanel);
        //outputPanel이 outputEngine을 알 필요는 없다.
        //inputPanel은 inputEngine을 알아야 한다. 그래야 입력한 input값이 엔진에 들어가니까
        //하지만 output이 outputEngine에 들어가진 않기 때문에 없어도 된다.
        //엔진이 패널을 알기만 하면 된다.

        //YutEngine <==> BoardPanel
        //YutEngine이 BoardPanel을 알기만 하면 된다.
        yutEngine.setBoardUI(boardPanel);

        //PlayerPanel <==> YutEngine
        //YutEngine이 PlayerPanel을 알기만 하면 된다.
        yutEngine.setPlayerPanel(playerPanel);

        //playerlist(yutengine) <==> playerlist(window)
        boardPanel.setPlayerList(playerList);

        //YutPanel
        yutEngine.setYutPanel(yutPanel);
        yutPanel.setYutEngine(yutEngine);
    }
 


    

    public void launchYut(){
        centralEngine.launchYut();
    }
}
