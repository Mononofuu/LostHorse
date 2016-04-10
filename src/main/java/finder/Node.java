package finder;

public class Node {

    private final static int BASICMOVEMENTCOST = 10;

    private int x;
    private int y;
    private boolean walkable;
    private Node previous;
    private int gCosts;
    private int hCosts;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.walkable = true;
    }

    public int getfCosts() {
        return gCosts + hCosts;
    }

    public void setgCosts(Node previousNode, int basicCost) {
        setgCosts(previousNode.getgCosts() + basicCost);
    }

    public void setgCosts(Node previousNode) {
        setgCosts(previousNode, BASICMOVEMENTCOST);
    }

    public int calculategCosts(Node previousNode) {
        return (previousNode.getgCosts()
                + BASICMOVEMENTCOST);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.x;
        hash = 17 * hash + this.y;
        return hash;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
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
        this.sethCosts((absolute(this.getX() - endNode.getX())
                + absolute(this.getY() - endNode.getY()))
                * BASICMOVEMENTCOST);
    }

    private int absolute(int a) {
        return a > 0 ? a : -a;
    }
}
