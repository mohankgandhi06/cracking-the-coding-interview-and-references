package r.chapter.hardDifficulty;

import java.util.*;

public class QMultiSearch {

    public Trie dictionary;

    public QMultiSearch() {
        this.dictionary = new Trie(new TrieNode('-'));
    }

    public static void main(String[] args) {
        QMultiSearch game = new QMultiSearch();
        String longerString = "george went on a expedition to find the albatroz. \n" +
                "he was taking his pet danny, and his friend andy. they\n" +
                "finally were able to catch the bird";
        List<String> searchWords = new ArrayList<>();
        searchWords.add("dhanush");
        searchWords.add("danny");
        searchWords.add("albatroz");
        searchWords.add("able");
        searchWords.add("andy");
        searchWords.add("alibi");

        System.out.println("Passage: ");
        System.out.println(longerString);

        System.out.println("\nWords to Find: ");
        searchWords.stream().forEach(System.out::println);
        System.out.println("\nWords Found: ");
        Set<String> wordsFound = game.searchWords(longerString, searchWords);
        for (String s : wordsFound) {
            System.out.println(s);
        }
    }

    private Set<String> searchWords(String longerString, List<String> searchWords) {
        buildTrie(searchWords);
        return search(longerString);
    }

    private Set<String> search(String longerString) {
        Set<String> matches = new HashSet<>();
        solve(longerString, 0, this.dictionary.root, matches, new StringBuilder());
        return matches;
    }

    private void solve(String longerString, int currentIndex, TrieNode currentNode, Set<String> matches, StringBuilder stringBuilder) {
        if (currentIndex >= longerString.length()) return;
        if (currentNode.getChildren().containsKey(longerString.charAt(currentIndex))) {
            /* Parameters - Trie Node for checking out the next character for matches */
            /* RESET the trie to root node if we are going to start checking from first */
            stringBuilder.append(longerString.charAt(currentIndex));
            if (currentNode.getChildren().get(longerString.charAt(currentIndex)).getChildren() == null || currentNode.getChildren().get(longerString.charAt(currentIndex)).getChildren().size() == 0) {
                matches.add(stringBuilder.toString());
                return;
            }
            solve(longerString, currentIndex + 1, currentNode.getChildren().get(longerString.charAt(currentIndex)), matches, stringBuilder);
        }
        solve(longerString, currentIndex + 1, this.dictionary.root, matches, new StringBuilder());
        return;
    }

    private void buildTrie(List<String> searchWords) {
        for (String s : searchWords) {
            insert(s);
        }
        /*show(this.dictionary);*/
    }

    private void insert(String s) {
        TrieNode root = this.dictionary.root;
        for (Character character : s.toCharArray()) {
            if (root.getChildren() != null) {
                if (!root.getChildren().containsKey(character)) {/* ADD THE NEW CHARACTER IN TRIE NODE */
                    root.getChildren().put(character, new TrieNode(character));
                }
            } else {/* CREATE A CHILDREN HASH MAP */
                root.setChildren(new HashMap<>());
                root.getChildren().put(character, new TrieNode(character));
            }
            root = root.getChildren().get(character);
        }
    }

    private void show(Trie dictionary) {
        System.out.println("Trie Nodes and their children");
        TrieNode root = dictionary.root;
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TrieNode node = queue.poll();
            System.out.println("Parent: " + node.getCharacter());
            if (node.getChildren() != null) {
                for (Map.Entry<Character, TrieNode> child : node.getChildren().entrySet()) {
                    queue.add(child.getValue());
                }
            }
        }
    }
}

class Trie {
    public TrieNode root;

    public Trie(TrieNode root) {
        this.root = root;
    }
}

class TrieNode {
    private Character character;
    private HashMap<Character, TrieNode> children;

    public TrieNode(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setChildren(HashMap<Character, TrieNode> children) {
        this.children = children;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }
}