public class Path {
    private String name;
    private List<Node> nodes;

    public Path(String name) {
        this.name = name;
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Node getStartNode() {
        return nodes.isEmpty() ? null : nodes.get(0);
    }

    public String getName() {
        return name;
    }

    // 거리 기반으로 특정 Node 반환 (필요시)
    public Node getNodeAt(int index) {
        if (index < 0 || index >= nodes.size()) return null;
        return nodes.get(index);
    }

    public int size() {
        return nodes.size();
    }
}
