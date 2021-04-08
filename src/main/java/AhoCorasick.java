import java.util.*;
import java.util.stream.Collectors;

public class AhoCorasick {

    private final List<String> dictionary;
    private final Node root;

    public AhoCorasick(List<String> dictionary) {
        this.root = Node.buildRoot();
        this.dictionary = dictionary.stream()
                                    .sorted(Comparator.comparingInt(String::length))
                                    .collect(Collectors.toList());

        this.buildTrie();
        this.buildFailureLinks();
        this.buildDictionaryLinks();
    }

    private void buildDictionaryLinks() {

    }

    private void buildFailureLinks() {
    }

    private Node getNodeFor(String str) {
        Node reference = this.root;
        for (char letter : str.toCharArray()) {
            reference = reference.getChildren(letter);
        }

        return reference;
    }

    private void buildTrie() {
        for (String word : dictionary) {
            Node reference = this.root;
            for (char letter : word.toCharArray()) {
                reference = reference.tryGetOrAddChildren(letter);
            }

            reference.markAsWordEnding(word);
        }
    }

    public Map<String, Integer> getMatchesByWord(String sequence) {
        return Collections.emptyMap();
    }
}
