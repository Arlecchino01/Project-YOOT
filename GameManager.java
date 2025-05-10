import java.util.*;

public class GameManager {
    int playerNum;
    List<Player> players;
    int sides;
    List<Board> boards;
    Scanner sc = new Scanner(System.in);
    int turn = 0;
    
    
    

    private InputPanel inputPanel;
    private OutputWriter writer; // OutputPanel이 이걸 구현함

    public GameManager(InputPanel inputPanel, OutputWriter writer) {
        this.inputPanel = inputPanel;
        this.writer = writer;

    }

    public void runGame() {
    writer.print("게임 시작...");

    writer.print("첫 번째 숫자를 입력하세요");
    inputPanel.requestInput(value1 -> {
        writer.print("입력 1: " + value1);

        writer.print("두 번째 숫자를 입력하세요");
        inputPanel.requestInput(value2 -> {
            writer.print("입력 2: " + value2);

            writer.print("세 번째 숫자를 입력하세요");
            inputPanel.requestInput(value3 -> {
                writer.print("입력 3: " + value3);

                writer.print("모든 입력 완료!");
            });
        });
    });
}


    public void addPlayer(){
        System.out.println("플레이어의 수를 입력하세요: ");        

        playerNum = sc.nextInt();
        sc.nextLine(); 

        for(int i = 0; i<playerNum; i++){
            Player player = createPlayer();
            System.out.println("이름을 입력하세요: ");
            String thisName = sc.nextLine();
            player.name = thisName;
            System.out.println("플레이어: " + thisName + "추가 완료");
            players.add(player);
        }
    }
    
    public Player createPlayer(){
        Player p = new Player();
        return p;
    }

    
    public void createBoard(){
        System.out.println("보드의 변의 수를 입력하세요:");
        Scanner sc = new Scanner(System.in);
        sides = sc.nextInt();
        sc.nextLine();
        Board board1 = new Board(sides);
        sc.close();
        boards.add(board1);
    }

    public void updateGame(){

    }


}
