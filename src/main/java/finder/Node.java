package finder;

public class Node implements Comparable<Node>{

    private final int[] coords;

    private int x;
    private int y;
    private boolean walkable;
    private Node previous;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.coords = new int[]{x,y};
        this.walkable = true;
    }

    public int[] getCoords() {
        return coords;
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

    @Override
    public int compareTo(Node o) {
        if (this.getX()-o.getX()!=0) return this.getX()-o.getX();
        else return this.getY()-o.getY();
    }
}
