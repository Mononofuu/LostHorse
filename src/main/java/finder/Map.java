package finder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Map {

    private int width;
    private int height;
    private Node[][] nodes;
    private TreeSet<Node> openList;
    private TreeSet<Node> closedList;
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
        try {
            return nodes[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
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
        long s = System.currentTimeMillis();
        openList = new TreeSet<>();
        closedList = new TreeSet<>();
        openList.add(start);

        done = false;
        Node current;
        while (!done) {
            current = openList.last();
            closedList.add(current);
            openList.remove(current);

            if (current.equals(finish)) {
                System.out.println(System.currentTimeMillis() - s + "FIND");
                return calcPath(start, current);
            }
            getSurroundNodes(current);
            if (openList.isEmpty()) {
                return new ArrayList<>();
            }
        }
        return null;
    }

    private List<Node> calcPath(Node start, Node goal) {
        long s = System.currentTimeMillis();
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
        System.out.println(System.currentTimeMillis() - s + "PATH");
        return path;
    }


    private void getSurroundNodes(Node node) {
        int x = node.getX();
        int y = node.getY();

        Node temp;
        temp = this.getNode((x + 1), (y - 2));
        if (temp != null && temp.isWalkable() && !closedList.contains(temp)) {
            openList.add(temp);
            temp.setPrevious(node);
        }

        temp = this.getNode((x + 2), (y - 1));
        if (temp != null && temp.isWalkable() && !closedList.contains(temp)) {
            openList.add(temp);
            temp.setPrevious(node);
        }

        temp = this.getNode((x + 1), (y + 2));
        if (temp != null && temp.isWalkable() && !closedList.contains(temp)) {
            openList.add(temp);
            temp.setPrevious(node);
        }

        temp = this.getNode((x + 2), (y + 1));
        if (temp != null && temp.isWalkable() && !closedList.contains(temp)) {
            openList.add(temp);
            temp.setPrevious(node);
        }

        temp = this.getNode((x - 1), (y - 2));
        if (temp != null && temp.isWalkable() && !closedList.contains(temp)) {
            openList.add(temp);
            temp.setPrevious(node);
        }

        temp = this.getNode((x - 2), (y - 1));
        if (temp != null && temp.isWalkable() && !closedList.contains(temp)) {
            openList.add(temp);
            temp.setPrevious(node);
        }

        temp = this.getNode((x - 1), (y + 2));
        if (temp != null && temp.isWalkable() && !closedList.contains(temp)) {
            openList.add(temp);
            temp.setPrevious(node);
        }

        temp = this.getNode((x - 2), (y + 1));
        if (temp != null && temp.isWalkable() && !closedList.contains(temp)) {
            openList.add(temp);
            temp.setPrevious(node);
        }
    }

}
