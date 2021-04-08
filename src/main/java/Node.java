import java.util.*;

public class Node {
    private static final Character ROOT_CONTENT = '*';

    private final Character letter;
    private Map<Character, Node> children;
    private Node failureLink;
    private Node dictionaryLink;

    // Metadata
    private String endingWord;

    public Node(Character letter) {
        this.letter = letter;
        this.children = new HashMap<>();
        this.failureLink = null;
        this.endingWord = "";
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

    public void setFailureLink(Node node) {
        this.failureLink = node;
    }

    public Node getFailureLink() {
        return failureLink;
    }

    public Node getDictionaryLink() {
        return dictionaryLink;
    }

    public void setDictionaryLink(Node dictionaryLink) {
        this.dictionaryLink = dictionaryLink;
    }

    public Node getChildren(char letter) {
        return children.get(letter);
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
