import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AhoCorasickTest {
    @Test
    public void testMatch() {
        final AhoCorasick matcher = new AhoCorasick(Arrays.asList("pip", "par", "lel", "dido", "do", "pipedo"));
        final Map<String, Integer> matches = matcher.getMatchesByWord("paralelepipedo");

        Assertions.assertEquals(5, matches.size());

        wordAppearsNTimes(matches, "par", 1);
        wordAppearsNTimes(matches, "lel", 1);
        wordAppearsNTimes(matches, "pip", 1);
        wordAppearsNTimes(matches, "do", 1);
        wordAppearsNTimes(matches, "pipedo", 1);
    }

    private void wordAppearsNTimes(Map<String, Integer> matches, String word, int times) {
        Assertions.assertTrue(matches.containsKey(word));
        Assertions.assertEquals(times, matches.get(word));
    }
}