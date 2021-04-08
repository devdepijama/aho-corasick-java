import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AhoCorasick {

    private final List<String> dictionary;
    private final Node root;

    public AhoCorasick(List<String> dictionary) {
        this.root = new Node('*');
        this.dictionary = dictionary.stream()
                                    .sorted(Comparator.comparingInt(String::length))
                                    .collect(Collectors.toList());
    }

    public Map<String, Integer> getMatchesByWord(String sequence) {
        return Collections.emptyMap();
    }
}
