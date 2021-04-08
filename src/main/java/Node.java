import java.util.*;

public class Node {
    private static final Character ROOT_CONTENT = '*';

    private final Character letter;
    private Map<Character, Node> children;
    private Node failureLink;
    private Optional<Node> dictionaryLink;

    // Metadata
    private String endingWord;

    public Node(Character letter) {
        this.letter = letter;
        this.children = new HashMap<>();
        this.failureLink = null;
        this.endingWord = "";
        this.dictionaryLink = Optional.empty();
    }

    static Node buildRoot() {
        return new Node(ROOT_CONTENT);
    }

    public Node tryGetOrAddChildren(char letter) {
        return getChildren(letter).orElseGet(() -> addNode(letter));
    }

    public Node addNode(char letter) {
        Node result = new Node(letter);
        children.put(letter, result);
        return result;
    }

    public void setFailureLink(Node node) {
        this.failureLink = node;
    }

    public Node getFailureLink() {
        return failureLink;
    }

    public Optional<Node> getDictionaryLink() {
        return dictionaryLink;
    }

    public void setDictionaryLink(Node dictionaryLink) {
        this.dictionaryLink = Optional.ofNullable(dictionaryLink);
    }

    public Optional<Node> getChildren(char letter) {
        return Optional.ofNullable(children.get(letter));
    }

    public void setEndingWord(String word) {
        this.endingWord = word;
    }

    public boolean isWordEnding() {
        return !this.endingWord.isEmpty();
    }

    public String getEndingWord() {
        return endingWord;
    }

    public boolean isRoot() {
        return ROOT_CONTENT.equals(letter);
    }
}
