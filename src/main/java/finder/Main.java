package finder;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Map myMap = new Map(5, 3);
        myMap.setWalkable(1,0,false);
        myMap.setWalkable(1,1,false);
        myMap.setWalkable(3,1,false);
        myMap.setWalkable(3,2,false);
        myMap.drawMap();
        System.out.println();
        List<Node> path = myMap.findPath(0, 0, 4, 1);


        for (Node aPath : path) {
            System.out.print("(" + aPath.getxPosition() + ", " + aPath.getyPosition() + ") -> ");
        }

        System.out.println("\nNumber of steps: " + path.size());
    }
}
