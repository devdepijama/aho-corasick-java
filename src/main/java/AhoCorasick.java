import java.util.*;
import java.util.stream.Collectors;

public class AhoCorasick {

    private final List<String> dictionary;
    private final Node root;
    private Map<String, Node> nodeByPath;

    public AhoCorasick(List<String> dictionary) {
        this.nodeByPath = new HashMap<>();
        this.root = Node.buildRoot();
        this.nodeByPath.put("", root);

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
        for(Map.Entry<String, Node> entry : nodeByPath.entrySet()) {
            // First level nodes have root as failure link
            String path = entry.getKey();
            Node nodeUnderAnalysis = entry.getValue();
            if (path.length() <= 1) {
                nodeUnderAnalysis.addFailureLink(root);
                continue;
            }

            // Other nodes should use suffix logic
            String candidate = path;
            do {
                candidate = candidate.substring(1);
            } while (candidate.length() > 0 && !nodeByPath.containsKey(candidate));

            nodeUnderAnalysis.addFailureLink(nodeByPath.get(candidate));
        }
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
            String path = "";
            for (char letter : word.toCharArray()) {
                reference = reference.tryGetOrAddChildren(letter);
                path = path + letter;
                nodeByPath.put(path, reference);
            }

            reference.markAsWordEnding(word);
        }
    }

    public Map<String, Integer> getMatchesByWord(String sequence) {
        return Collections.emptyMap();
    }
}
