package finder;

import java.util.LinkedList;
import java.util.List;

public class Map {

    private int width;
    private int height;
    private Node[][] nodes;
    private List<Node> openList;
    private List<Node> closedList;
    private boolean done = false;
    private Node start;
    private Node finish;

    public Map(int[][] matrix) {
        int width = matrix[0].length;
        int height = matrix.length;
        nodes = new Node[width][height];
        this.width = width;
        this.height = height;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                nodes[i][j] = new Node(i, j);
                if (matrix[j][i] == -1) {
                    nodes[i][j].setWalkable(false);
                } else if (matrix[j][i] == -2) {
                    start = nodes[i][j];
                } else if (matrix[j][i] == -3) {
                    finish = nodes[i][j];
                }
            }
        }
    }

    public final Node getNode(int x, int y) {
        return nodes[x][y];
    }

    public void drawMap() {
        System.out.print("\n");

        for (int j = 0; j < height; j++) {
            System.out.print("|");
            for (int i = 0; i < width; i++) {
                if (nodes[i][j].isWalkable()) {
                    System.out.print("  ");
                } else {
                    System.out.print(" #");
                }
            }
            System.out.print("|\n");
        }

    }

    public final List<Node> findPath() {
        openList = new LinkedList<>();
        closedList = new LinkedList<>();
        openList.add(start);

        done = false;
        Node current;
        while (!done) {
            current = lowestFInOpen(); // get node with lowest fCosts from openList
            closedList.add(current); // add current node to closed list
            openList.remove(current); // delete current node from open list

            if (current.equals(finish)) { // found goal
                return calcPath(start, current);
            }

            // for all adjacent nodes:
            List<Node> surroundNodes = getSurroundNodes(current);
            for (int i = 0; i < surroundNodes.size(); i++) {
                Node surround = surroundNodes.get(i);
                if (!openList.contains(surround)) { // node is not in openList
                    surround.setPrevious(current); // set current node as previous for this node
                    surround.sethCosts(finish); // set h costs of this node (estimated costs to goal)
                    surround.setgCosts(current); // set g costs of this node (costs from start to this node)
                    openList.add(surround); // add node to openList
                } else { // node is in openList
                    if (surround.getgCosts() > surround.calculategCosts(current)) { // costs from current node are cheaper than previous costs
                        surround.setPrevious(current); // set current node as previous for this node
                        surround.setgCosts(current); // set g costs of this node (costs from start to this node)
                    }
                }
            }

            if (openList.isEmpty()) { // no path exists
                return new LinkedList<Node>(); // return empty list
            }
        }
        return null; // unreachable
    }

    /**
     * calculates the found path between two points according to
     * their given <code>previousNode</code> field.
     *
     * @param start
     * @param goal
     * @return
     */
    private List<Node> calcPath(Node start, Node goal) {
        LinkedList<Node> path = new LinkedList<>();

        Node curr = goal;
        boolean done = false;
        while (!done) {
            path.addFirst(curr);
            curr = (Node) curr.getPrevious();

            if (curr.equals(start)) {
                done = true;
            }
        }
        return path;
    }

    private Node lowestFInOpen() {
        // TODO currently, this is done by going through the whole openList!
        Node cheapest = openList.get(0);
        for (int i = 0; i < openList.size(); i++) {
            if (openList.get(i).getfCosts() < cheapest.getfCosts()) {
                cheapest = openList.get(i);
            }
        }
        return cheapest;
    }

    private List<Node> getSurroundNodes(Node node) {
        // TODO make loop
        int x = node.getX();
        int y = node.getY();
        List<Node> nodes = new LinkedList<>();

        Node temp;
        if (x + 1 < width & y - 2 >= 0) {
            temp = this.getNode((x + 1), (y - 2));
            if (temp.isWalkable() && !closedList.contains(temp)) {
                nodes.add(temp);
            }
        }

        if (x + 2 < width & y - 1 >= 0) {
            temp = this.getNode((x + 2), (y - 1));
            if (temp.isWalkable() && !closedList.contains(temp)) {
                nodes.add(temp);
            }
        }

        if (x + 1 < width & y + 2 < height) {
            temp = this.getNode((x + 1), (y + 2));
            if (temp.isWalkable() && !closedList.contains(temp)) {
                nodes.add(temp);
            }
        }

        if (x + 2 < width & y + 1 < height) {
            temp = this.getNode((x + 2), (y + 1));
            if (temp.isWalkable() && !closedList.contains(temp)) {
                nodes.add(temp);
            }
        }

        if (x - 1 >= 0 & y - 2 >= 0) {
            temp = this.getNode((x - 1), (y - 2));
            if (temp.isWalkable() && !closedList.contains(temp)) {
                nodes.add(temp);
            }
        }

        if (x - 2 >= 0 & y - 1 >= 0) {
            temp = this.getNode((x - 2), (y - 1));
            if (temp.isWalkable() && !closedList.contains(temp)) {
                nodes.add(temp);
            }
        }

        if (x - 1 >= 0 & y + 2 < height) {
            temp = this.getNode((x - 1), (y + 2));
            if (temp.isWalkable() && !closedList.contains(temp)) {
                nodes.add(temp);
            }
        }

        if (x - 2 >= 0 & y + 1 < height) {
            temp = this.getNode((x - 2), (y + 1));
            if (temp.isWalkable() && !closedList.contains(temp)) {
                nodes.add(temp);
            }
        }
        return nodes;
    }

}
