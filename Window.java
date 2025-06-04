import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Window {
    private InputPanel inputPanel;
    private OutputPanel outputPanel;
    private BoardPanel boardPanel;
    private PlayerPanel playerPanel;
    private YutPanel yutPanel;

    
    
    public Window(){
        inputPanel = new InputPanel();
        outputPanel = new OutputPanel();
        Board nullboard = new Board(4);
        boardPanel = new BoardPanel(nullboard);
        playerPanel = new PlayerPanel();
        yutPanel = new YutPanel();

        MaineFrame a = new MaineFrame(inputPanel, outputPanel, boardPanel, playerPanel, yutPanel);
    }

    public InputPanel getInputPanel(){
        return inputPanel;
    }

    public OutputPanel getOutputPanel(){
        return outputPanel;
    }

    public BoardPanel getBoardPanel(){
        return boardPanel;
    }

    public PlayerPanel getPlayerPanel(){
        return playerPanel;
    }

    public YutPanel getYutPanel(){
        return yutPanel;
    }

    
}

class MaineFrame extends JFrame{
    private GamePanel gamePanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel mainPanel1;
    private JPanel mainPanel2;
    private InputOutputSection inOutSection;

    public MaineFrame(InputPanel inputPanel, OutputPanel outputPanel, BoardPanel boardPanel, PlayerPanel playerPanel, YutPanel yutPanel){
        setTitle("Testing");
        setSize(1600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //인스턴스 생성
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel2 = new JPanel();
        inOutSection = new InputOutputSection(inputPanel, outputPanel, yutPanel);

        this.gamePanel = new GamePanel(inOutSection, boardPanel, playerPanel);


        
        mainPanel2.add(gamePanel);
   
        
        mainPanel.add(mainPanel2, "main2");

        add(mainPanel);
        cardLayout.show(mainPanel, "main2");
        setLocationRelativeTo(null);
        setVisible(true);
    }    
}


class GamePanel extends JPanel{
    

    JPanel gamePanel;
    InputOutputSection inout;


    public GamePanel(InputOutputSection inout, BoardPanel boardPanel, PlayerPanel playerPanel){
        this.inout = inout;
        gamePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0; // 한 줄짜리이므로 y좌표는 모두 0
        gbc.weighty = 1.0;

        // 왼쪽 패널 (30%)
        
        inout.setBackground(Color.BLACK);
        gbc.gridx = 0;
        gamePanel.add(inout, gbc);

        // 가운데 패널 (40%)    
        
        //Board a = new Board(3);
        //boardPanel = new BoardPanel(a);
        
        gbc.gridx = 1;
        gamePanel.add(boardPanel, gbc);

        // 오른쪽 패널 (30%)
        //JPanel rightPanel = new JPanel();
        //rightPanel.setBackground(Color.RED);
        gbc.gridx = 2;
        gamePanel.add(playerPanel, gbc);
        inout.setPreferredSize(new Dimension(400, 900));
        boardPanel.setPreferredSize(new Dimension(800, 900));
        playerPanel.setPreferredSize(new Dimension(300, 900));
        
        add(gamePanel);
        
    }

    
}

class BoardPanel extends JPanel implements Board_Interface{
    private int N; // N각형
    private final int RADIUS = 250;
    private final int TILE_SIZE = 16;
    private final int CENTER_X = 400;
    private final int CENTER_Y = 400;
    //======================================

    private Board board;
    private Map<Tile, Point> tilePointMap = new HashMap<>();
    private List<Player> playerList;

    @Override
    public void setBoard(Board board){
        this.board = board;
        this.N = board.getSideNum();
        computeTilePositions();
        Tile startTile = board.getDiagonalsFromCenter().get(1).getTiles().get(1);
        
        SwingUtilities.invokeLater(() -> repaint());
        //computeTilePositions(); // ← 여기서 연결 처리
    }
    @Override
    public void update(){
        SwingUtilities.invokeLater(() -> repaint());
    }

    public void setPlayerList(List<Player> playerList){
        this.playerList = playerList;
    }

    public BoardPanel(Board board) {
        this.board = board;
        this.playerList = null;
    }

    

    public void initializeBoardPanel(){
        
        
        N = board.getSideNum();
        computeTilePositions(); // ← 여기서 연결 처리

        // 테스트: 첫 번째 변의 첫 번째 타일 위에 말 생성
        
    }

    private void computeTilePositions() {

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            double angle = 2 * Math.PI * i / N;
            int x = (int) (CENTER_X + RADIUS * Math.cos(angle));
            int y = (int) (CENTER_Y + RADIUS * Math.sin(angle));
            points[i] = new Point(x, y);
        }

        // 변 타일 좌표 매핑
        List<BoardEdge> edges = board.getEdges();
        for (int i = 0; i < N; i++) {
            Point a = points[i];
            Point b = points[(i + 1) % N];
            List<Tile> tiles = edges.get(i).getTiles();

            for (int j = 0; j < tiles.size(); j++) {
                double t = j / (double)(tiles.size() - 1);
                int x = (int)(a.x + t * (b.x - a.x));
                int y = (int)(a.y + t * (b.y - a.y));
                tilePointMap.put(tiles.get(j), new Point(x, y));
            }
        }

        // 중심 타일
        tilePointMap.put(board.getCenter(), new Point(CENTER_X, CENTER_Y));

        // 대각선 (꼭짓점 → 중심)
        List<BoardEdge> toCenter = board.getDiagonalsToCenter();
        for (int i = 0; i < toCenter.size(); i++) {
            BoardEdge edge = toCenter.get(i);
            List<Tile> tiles = edge.getTiles();
            Point from = points[findCornerIndex(edge.getStartTile(), edges)];

            //System.out.println(findCornerIndex(edge.getStartTile(), edges));

            for (int j = 0; j < tiles.size(); j++) {
                double t = j / (double)(tiles.size() - 1);
                int x = (int)(from.x + t * (CENTER_X - from.x));
                int y = (int)(from.y + t * (CENTER_Y - from.y));
                tilePointMap.put(tiles.get(j), new Point(x, y));
            }
        }

        // 대각선 (중심 → 꼭짓점)
        List<BoardEdge> fromCenter = board.getDiagonalsFromCenter();
        for (int i = 0; i < fromCenter.size(); i++){
            BoardEdge edge = fromCenter.get(i);
            List<Tile> tiles = edge.getTiles();
            Point to = points[(findThisIsEnd(edge.getEndTile(), edges)+1)%N];

            for (int j = 0; j < tiles.size(); j++) {
                double t = j / (double)(tiles.size() - 1);
                int x = (int)(CENTER_X + t * (to.x - CENTER_X));
                int y = (int)(CENTER_Y + t * (to.y - CENTER_Y));
                tilePointMap.put(tiles.get(j), new Point(x, y));
            }
        
        }
    }


    private int findCornerIndex(Tile tile, List<BoardEdge> edges) {
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getStartTile() == tile) {
                return i;
            }
        }
        return -1; // 못 찾으면 -1
    }

    private int findThisIsEnd(Tile tile, List<BoardEdge> edges) {
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getEndTile() == tile) {
                return i;
            }
        }
        return -1; // 못 찾으면 -1
    }

    //======================================
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 중심점
        g2.setColor(Color.BLACK);
        g2.fillOval(CENTER_X - TILE_SIZE / 2, CENTER_Y - TILE_SIZE / 2, TILE_SIZE, TILE_SIZE);

        // 꼭짓점 위치 계산 및 저장
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            double angle = 2 * Math.PI * i / N;
            int x = (int) (CENTER_X + RADIUS * Math.cos(angle));
            int y = (int) (CENTER_Y + RADIUS * Math.sin(angle));
            points[i] = new Point(x, y);
        }

        // 변 타일과 선 그리기 (4개씩)
        g2.setColor(Color.BLUE);
        for (int i = 0; i < N; i++) {
            Point a = points[i];
            Point b = points[(i + 1) % N];

            // 선 그리기
            g2.setColor(Color.BLACK);
            g2.drawLine(a.x, a.y, b.x, b.y);

            // 점 그리기
            g2.setColor(Color.BLUE);
            for (int j = 1; j <= 4; j++) {
                double t = j / 5.0;
                int x = (int) (a.x + t * (b.x - a.x));
                int y = (int) (a.y + t * (b.y - a.y));
                g2.fillOval(x - TILE_SIZE / 2, y - TILE_SIZE / 2, TILE_SIZE, TILE_SIZE);
            }
        }

        // 대각선 타일과 선 그리기 (2개씩)
        for (int i = 0; i < N; i++) {
            Point p = points[i];

            // 선 그리기
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(p.x, p.y, CENTER_X, CENTER_Y);

            // 점 그리기
            g2.setColor(Color.GRAY);
            for (int j = 1; j <= 2; j++) {
                double t = j / 3.0;
                int x = (int) (p.x + t * (CENTER_X - p.x));
                int y = (int) (p.y + t * (CENTER_Y - p.y));
                g2.fillOval(x - TILE_SIZE / 2, y - TILE_SIZE / 2, TILE_SIZE, TILE_SIZE);
            }
        }

        // 꼭짓점 그리기 (첫 번째는 노란색)
        for (int i = 0; i < N; i++) {
            Point p = points[i];
            g2.setColor(i == 0 ? Color.MAGENTA : Color.BLUE);
            g2.fillOval(p.x - TILE_SIZE / 2, p.y - TILE_SIZE / 2, TILE_SIZE, TILE_SIZE);
        }


        //============= 타일 그리기 =============
        

        //============= Piece들 그리기 =============
        if (playerList != null){
            for (Player player : playerList){
                List<Piece> pieces = player.getPieces();
                for (Piece piece : pieces){
                    Tile tile = piece.getCurrentTile();
                    Point p = tilePointMap.get(tile);
                    if (p != null) {
                        String color = player.returnColor();
                        if(color == "Red"){
                            g2.setColor(Color.RED);
                        }else if(color == "Orange"){
                            g2.setColor(Color.ORANGE);
                        }else if(color == "Yellow"){
                            g2.setColor(Color.YELLOW);
                        }else if(color == "Green"){
                            g2.setColor(Color.GREEN);
                        }else if(color == "Blue"){
                            g2.setColor(Color.BLUE);
                        }
                        g2.fillOval(p.x - 8, p.y - 8, 16, 16); // 말의 위치, 크기
                    }
                }
            }
        }

        // 대각선 분류용 셋 만들기
        Set<Tile> toCenterTiles = new HashSet<>();
        for (BoardEdge edge : board.getDiagonalsToCenter()) {
            toCenterTiles.addAll(edge.getTiles());
        }
        Set<Tile> fromCenterTiles = new HashSet<>();
        for (BoardEdge edge : board.getDiagonalsFromCenter()) {
            fromCenterTiles.addAll(edge.getTiles());
        }

        // 타일 ID 출력
        for (Map.Entry<Tile, Point> entry : tilePointMap.entrySet()) {
            Point p = entry.getValue();
            Tile tile = entry.getKey();
        
            if (toCenterTiles.contains(tile)) {
                g2.setColor(Color.GREEN); // 꼭짓점 → 중심
            } else if (fromCenterTiles.contains(tile)) {
                g2.setColor(Color.RED); // 중심 → 꼭짓점
            } else {
                g2.setColor(Color.BLACK); // 나머지
            }
        
            g2.drawString(tile.getId() + "", p.x + 5, p.y - 5);
        }
    }
}