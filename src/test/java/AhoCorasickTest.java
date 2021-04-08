import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AhoCorasickTest {
    @Test
    public void testMatch() {
        final AhoCorasick matcher = new AhoCorasick(
            Arrays.asList(
                "an",
                "deca",
                "ant",
                "cant",
                "decant",
                "plant"
            )
        );
        final Map<String, Integer> matches = matcher.getMatchesByWord("decant");

        Assertions.assertEquals(5, matches.size());

        wordAppearsNTimes(matches, "an", 1);
        wordAppearsNTimes(matches, "ant", 1);
        wordAppearsNTimes(matches, "cant", 1);
        wordAppearsNTimes(matches, "deca", 1);
        wordAppearsNTimes(matches, "decant", 1);
    }

    private void wordAppearsNTimes(Map<String, Integer> matches, String word, int times) {
        Assertions.assertTrue(matches.containsKey(word));
        Assertions.assertEquals(times, matches.get(word));
    }
}