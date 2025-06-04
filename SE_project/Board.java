public class Board {
    private Map<String, Node> nodes = new HashMap<>();
    private Path mainPath;
    private List<Path> sidePaths = new ArrayList<>();
    private Node startNode;
    private Node endNode;
    private Node centerNode;

    public Board() {
        // 기본 사각형 경로 구성 (기본값)
        setupSquareBoard();
    }

    public void setupSquareBoard() {
        nodes.clear();
        mainPath = new Path("Main");
        sidePaths.clear();

        // 주요 노드 생성
        Node s = new Node("S");
        Node a1 = new Node("A1");
        Node a2 = new Node("A2");
        Node c = new Node("C"); // 중심
        Node b1 = new Node("B1");
        Node b2 = new Node("B2");
        Node e = new Node("E");

        // 경로 연결
        s.setNext(a1);
        a1.setNext(a2);
        a2.setNext(c);
        c.setNext(b1);
        b1.setNext(b2);
        b2.setNext(e);

        // Path에 추가
        mainPath.addNode(s);
        mainPath.addNode(a1);
        mainPath.addNode(a2);
        mainPath.addNode(c);
        mainPath.addNode(b1);
        mainPath.addNode(b2);
        mainPath.addNode(e);

        // Map에도 등록
        nodes.put("S", s);
        nodes.put("A1", a1);
        nodes.put("A2", a2);
        nodes.put("C", c);
        nodes.put("B1", b1);
        nodes.put("B2", b2);
        nodes.put("E", e);

        // 설정
        this.startNode = s;
        this.endNode = e;
        this.centerNode = c;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public Node getCenterNode() {
        return centerNode;
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public Node getPreviousNode(Node current) {
        // 단방향 구조라 역추적이 어려움 → 개선 필요 (이중 연결 리스트 등)
        // 임시 구현: 전체 검색
        for (Node node : nodes.values()) {
            if (node.getNext() == current) {
                return node;
            }
        }
        return null;
    }

    public void setupPentagonBoard() {
        // TODO: 오각형 판 경로 구성
    }

    public void setupHexagonBoard() {
        // TODO: 육각형 판 경로 구성
    }
}
