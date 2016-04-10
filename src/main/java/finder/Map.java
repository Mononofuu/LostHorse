package finder;

import java.util.ArrayList;
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
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        openList.add(start);

        done = false;
        Node current;
        while (!done) {
            current = lowestFInOpen();
            closedList.add(current);
            openList.remove(current);

            if (current.equals(finish)) {
                return calcPath(start, current);
            }

            List<Node> surroundNodes = getSurroundNodes(current);
            for (Node surround : surroundNodes) {
                if (!openList.contains(surround)) { // node is not in openList
                    surround.setPrevious(current); // set current node as previous for this node
                    surround.sethCosts(finish); // set h costs of this node (estimated costs to goal)
                    surround.setgCosts(current); // set g costs of this node (costs from start to this node)
                    openList.add(surround); // add node to openList
                } else {
                    if (surround.getgCosts() > surround.calculategCosts(current)) { // costs from current node are cheaper than previous costs
                        surround.setPrevious(current); // set current node as previous for this node
                        surround.setgCosts(current); // set g costs of this node (costs from start to this node)
                    }
                }
            }

            if (openList.isEmpty()) {
                return new ArrayList<>();
            }
        }
        return null;
    }

    private List<Node> calcPath(Node start, Node goal) {
        LinkedList<Node> path = new LinkedList<>();

        Node curr = goal;
        boolean done = false;
        while (!done) {
            path.addFirst(curr);
            curr = curr.getPrevious();

            if (curr.equals(start)) {
                done = true;
            }
        }
        return path;
    }

    private Node lowestFInOpen() {
        Node cheapest = openList.get(0);
        for (Node anOpenList : openList) {
            if (anOpenList.getfCosts() < cheapest.getfCosts()) {
                cheapest = anOpenList;
            }
        }
        return cheapest;
    }

    private List<Node> getSurroundNodes(Node node) {
        int x = node.getX();
        int y = node.getY();
        List<Node> nodes = new ArrayList<>(8);

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
