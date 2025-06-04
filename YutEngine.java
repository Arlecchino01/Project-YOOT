import java.util.*;

import javax.swing.SwingUtilities;

import YutPanel.YutPanelCallBack;

public class YutEngine {
    private CentralEngine central;

    private String testingStr;
    
    //launch()에서 스위치 조작용 변수
    private int switchnum;

    //플레이어 정보 저장 변수
    private int PlayerNum;
    private List<Player> players;

    //보드 전용 변수
    private int sideNum;
    private Board board;

    private Board_Interface BoardUi;
    private PlayerPanel_Interface playerPanel;

    private YutPanel yutPanel;

    private Yut yut;
    



    public YutEngine(){
        switchnum = 0;
        players = new ArrayList<>();
    }

    public void launch(){
        
        switch (switchnum) {
            case 0:
                //setting game
                //윷 던지기 버튼 disabled
                central.sendStringToOutputEngine("==== 윷놀이 Player Setting ====");
                getPlayers();
                break;
            case 1:
                //게임 세팅
                central.sendStringToOutputEngine("==== 윷놀이 Game Setting ====");
                setGame();
                break;
            case 2:
                central.sendStringToOutputEngine("==== 말의 수 설정 ====");
                setPieces();
                break;
            case 3:
                central.sendStringToOutputEngine("==== 게임 시작 ====");
                break;
            case 109:
                central.sendStringToOutputEngine("==== Entered Testing Mode ====");
                test();
                break;
            default:
                initializeCallback();
                break;
        }
    }

    //================ 각 스테이지에 맞게 오버라이딩한 콜백 함수들  ================

    public void test(){
        central.setYutCallBack(new CentralEngine.YutCallback() {
            @Override
            public void onInput(CentralEngine centralEngine){
                String str1 = central.getString();
                central.sendStringToOutputEngine(str1);

                centralEngine.setYutCallBack(new CentralEngine.YutCallback() {
                    @Override
                    public void onInput(CentralEngine centralEngine){
                        central.sendStringToOutputEngine("제대로 되냐...");
                    }
                });

            }
        });
    }


    
    public void initializeCallback(){
        central.setYutCallBack(new CentralEngine.YutCallback() {
            @Override
            public void onInput(CentralEngine centralEngine){
            }
        });
    }

    public void getPlayers(){
        central.sendStringToOutputEngine("플레이어 수를 입력하세요");

        central.setYutCallBack(new CentralEngine.YutCallback() {
            @Override
            public void onInput(CentralEngine centralEngine){
                try{
                    int tempInt = Integer.parseInt(central.getString());
                    if(tempInt <= 1){
                        central.sendStringToOutputEngine("2 이상의 수를 입력하세요");
                        return;
                    }
                    central.sendStringToOutputEngine("플레이어 수: " + tempInt);
                    PlayerNum = tempInt;
                    addPlayers(centralEngine, 0, PlayerNum);
                } catch (NumberFormatException e){
                    central.sendStringToOutputEngine("정수를 입력하세요.");
                }
            }
        });
    }

    public void addPlayers(CentralEngine centralEngine, Integer index, Integer pNum){
        if (index >= pNum) {
            central.sendStringToOutputEngine("모든 플레이어 입력이 완료되었습니다.");
            for (int i = 0; i < index; i++){
                central.sendStringToOutputEngine(players.get(i).getName());
            }
            setSwitchNum(1);
            launch();
            return;
        }

        central.sendStringToOutputEngine((index + 1) + "번째 플레이어의 이름을 입력하세요: ");
        
        centralEngine.setYutCallBack(new CentralEngine.YutCallback() {
            @Override
            public void onInput(CentralEngine cAdd1){
                //생성자로 플레이어 생성 + 리스트에 플레이어 추가하는 거 구현
                
                
                Player p = new Player(central.getString());
                players.add(p);
                playerPanel.updatePlayerPanel(players);
                central.sendStringToOutputEngine((index + 1)+ "번 플레이어 \""+p.getName() + "\" 추가 완료.");
                addPlayers(cAdd1, index+1, pNum);
                
            }
        });
    }

    public void setGame(){
        central.sendStringToOutputEngine("변의 수를 입력하세요");

        central.setYutCallBack(new CentralEngine.YutCallback() {
            @Override
            public void onInput(CentralEngine c1){
                try{
                    int tempInt = Integer.parseInt(central.getString());
                    if(tempInt <= 2){
                        central.sendStringToOutputEngine("3 이상의 수를 입력하세요");
                        return;
                    }
                    central.sendStringToOutputEngine("변의 수: " + tempInt);
                    sideNum = tempInt;
                    drawBoard(c1, sideNum);
                    
                } catch (NumberFormatException e){
                    central.sendStringToOutputEngine("정수를 입력하세요.");
                }
                
                
                
            }
        });
    }

    public void drawBoard(CentralEngine centralEngine, Integer sideNum){

        this.board = new Board(sideNum);
        BoardUi.setBoard(board);
        central.sendStringToOutputEngine("게임보드가 생성되었습니다.");
        setSwitchNum(2);
        launch();
    }

    public void setPieces(){
        central.sendStringToOutputEngine("말의 수를 입력하세요");
        central.setYutCallBack(new CentralEngine.YutCallback() {
            @Override
            public void onInput(CentralEngine c1){                
                try{
                    int tempInt = Integer.parseInt(central.getString());
                    if(tempInt <= 1){
                        central.sendStringToOutputEngine("2 이상의 수를 입력하세요");
                        return;
                    }
                    central.sendStringToOutputEngine("말의 수: " + tempInt);

                    for(int i = 0; i < PlayerNum; i++){
                        Player player = players.get(i);
                        player.generatePieces(tempInt, board.getOrigin());

                        if(i%5 == 0){
                            player.setColor("Red");
                        }else if(i%5 == 1){
                            player.setColor("Orange");
                        }else if(i%5 == 2){
                            player.setColor("Yellow");
                        }else if(i%5 == 3){
                            player.setColor("Green");
                        }else if(i%5 == 4){
                            player.setColor("Blue");
                        }
                        central.sendStringToOutputEngine(player.getName() + "'s Color: " + player.returnColor());
                    }
                    BoardUi.update();
                    

                } catch (NumberFormatException e){
                    central.sendStringToOutputEngine("정수를 입력하세요.");
                }
            }
        });
    }

    public void startGame(){
        central.sendStringToOutputEngine("게임을 시작합니다. 윷을 던지세요");
        
    }

    public void throwYut(){
        yut.throwYut();
    }

    public void start_Game(){
        yutPanel.setYutCallBack(new YutPanel().YutPanelCallback() {
            @Override
            public void 
        });

        central.setYutCallBack(new CentralEngine.YutCallback() {
            @Override
            public void onInput(CentralEngine centralEngine){
                String str1 = central.getString();
                central.sendStringToOutputEngine(str1);

                centralEngine.setYutCallBack(new CentralEngine.YutCallback() {
                    @Override
                    public void onInput(CentralEngine centralEngine){
                        central.sendStringToOutputEngine("제대로 되냐...");
                    }
                });

            }
        });
    }
    //================ 필요한 함수들 ================

    public void setCentralEngine(CentralEngine centralEngine){
        this.central = centralEngine;
    }

    public void setSwitchNum(Integer number){
        this.switchnum = number;
    }

    public Board getBoard(){
        return board;
    }
    
    public void setBoardUI(Board_Interface BoardUI){
        this.BoardUi = BoardUI;
    }

    public void setPlayerPanel(PlayerPanel_Interface playerPanel){
        this.playerPanel = playerPanel;
    }

    public List<Player> getPlayerList(){
        return players;
    }

    public void setYutPanel(YutPanel yutPanel){
        this.yutPanel = yutPanel;
    }

    
}