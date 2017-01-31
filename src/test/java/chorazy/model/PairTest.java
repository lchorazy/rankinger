package chorazy.model;

import chorazy.Simulation;
import org.junit.Test;

import java.util.List;

public class PairTest {

    @Test
    public void test() {
        List<Pair> pairs = Pair.createPairs(new Simulation().countries);
    }
}