import java.util.*;

public class Node {
    private static final Character ROOT_CONTENT = '*';

    private final Character letter;
    private Map<Character, Node> children;
    private Node failureLink;

    // Metadata
    private List<String> endingWords;

    public Node(Character letter) {
        this.letter = letter;
        this.children = new HashMap<>();
        this.failureLink = null;
        this.endingWords = new ArrayList<>();
    }

    static Node buildRoot() {
        return new Node(ROOT_CONTENT);
    }

    public Node tryGetOrAddChildren(char letter) {
        return Optional.ofNullable(getChildren(letter))
                       .orElseGet(() -> addNode(letter));
    }

    public Node addNode(char letter) {
        children.put(letter, new Node(letter));
        return getChildren(letter);
    }

    public void addFailureLink(Node node) {
        this.failureLink = node;
    }

    public Node getChildren(char letter) {
        return children.get(letter);
    }

    public void markAsWordEnding(String word) {
        this.endingWords.add(word);
    }
}
