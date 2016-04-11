package finder;

public class Node implements Comparable<Node> {

    private final static int BASIC_MOVEMENT_COST = 10;

    private int x;
    private int y;
    private Node previous;
    private int gCosts;
    private int hCosts;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getfCosts() {
        return gCosts + hCosts;
    }

    public void setgCosts(Node previousNode, int basicCost) {
        setgCosts(previousNode.getgCosts() + basicCost);
    }

    public void setgCosts(Node previousNode) {
        setgCosts(previousNode, BASIC_MOVEMENT_COST);
    }

    public int calculategCosts(Node previousNode) {
        return (previousNode.getgCosts() + BASIC_MOVEMENT_COST);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (x != node.x) return false;
        return y == node.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public int getgCosts() {
        return gCosts;
    }

    public void setgCosts(int gCosts) {
        this.gCosts = gCosts;
    }

    public void sethCosts(int hCosts) {
        this.hCosts = hCosts;
    }

    public void sethCosts(Node endNode) {
        this.sethCosts((this.getX() - endNode.getX())
                + (this.getY() - endNode.getY()));
    }

    @Override
    public int compareTo(Node o) {
        if (this.getfCosts() - o.getfCosts() != 0) {
            return this.getfCosts() - o.getfCosts();
        }
        if (this.getX() - o.getX() != 0) {
            return this.getX() - o.getX();
        } else return this.getY() - o.getY();
    }
}
