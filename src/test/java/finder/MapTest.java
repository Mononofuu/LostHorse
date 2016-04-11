package finder;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.Parser;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MapTest {
    private static final Logger LOG = LoggerFactory.getLogger(MapTest.class);
    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        private void logInfo(Description description, long nanos) {
            LOG.info(String.format("+++ Test %s spent %d ms",
                    description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos)));
        }

        @Override
        protected void finished(long nanos, Description description) {
            logInfo(description, nanos);
        }
    };
    private static Map map;

    @BeforeClass
    public static void init() throws IOException {
        map = new Map(Parser.parse());
    }

    @Test
    public void findPathFromParsedTable() throws Exception {
//        init();
        List<Node> path = map.findPath();

        for (Node aPath : path) {
            System.out.print("(" + aPath.getX() + ", " + aPath.getY() + ") -> ");
        }

        System.out.println("\nNumber of steps: " + path.size());

    }

    @Test
    public void findPathSimple() throws Exception {
        int[][] matrix = new int[][]{
                {-2, -1, -1, 0, 0},
                {0, -1, 0, -1, -3},
                {0, 0, 0, -1, 0},
        };
        Map map = new Map(matrix);
        List<Node> path = map.findPath();

        for (Node aPath : path) {
            System.out.print("(" + aPath.getX() + ", " + aPath.getY() + ") -> ");
        }

        System.out.println("\nNumber of steps: " + path.size());

    }

}