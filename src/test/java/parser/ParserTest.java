package parser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ParserTest {
    private static final Logger LOG = LoggerFactory.getLogger(ParserTest.class);

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

    @Test
    public void parse() throws Exception {
        int[][] matrix = Parser.parse();
    }


}