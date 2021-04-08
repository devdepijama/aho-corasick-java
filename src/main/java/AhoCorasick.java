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
        for(Map.Entry<String, Node> entry : nodeByPath.entrySet()) {
            Node nodeUnderAnalysis = entry.getValue();

            // Travels through the failure links until reach root or a word ending
            // if finds root, than there's no dictionary link
            // if some failure link points to a word ending node, than create dictionary link
            Node reference = nodeUnderAnalysis.getFailureLink();
            while (!reference.isWordEnding() && !reference.isRoot()) {
                reference = reference.getFailureLink();
            }

            if (reference.isRoot()) continue;
            nodeUnderAnalysis.setDictionaryLink(reference);
        }
    }

    private void buildFailureLinks() {
        for(Map.Entry<String, Node> entry : nodeByPath.entrySet()) {
            // First level nodes have root as failure link
            String path = entry.getKey();
            Node nodeUnderAnalysis = entry.getValue();
            if (path.length() <= 1) {
                nodeUnderAnalysis.setFailureLink(root);
                continue;
            }

            // For other nodes should use suffix logic
            String candidate = path;
            do {
                // if the path exists, good! That's the one
                // if it doesn't, than keep on reducing for getting smaller suffixes
                // Eventually it's so reduced that it becomes root
                candidate = candidate.substring(1);
            } while (candidate.length() > 0 && !nodeByPath.containsKey(candidate));

            nodeUnderAnalysis.setFailureLink(nodeByPath.get(candidate));
        }
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

            reference.setEndingWord(word);
        }
    }

    public Map<String, Integer> getMatchesByWord(String sequence) {
        return Collections.emptyMap();
    }
}
