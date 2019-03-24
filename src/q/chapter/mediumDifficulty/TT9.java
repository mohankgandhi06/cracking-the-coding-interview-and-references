package q.chapter.mediumDifficulty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TT9 {

    public Trie words;
    public HashMap<Character, Integer> mapping;

    public TT9() {
        this.words = new Trie();
        this.words.root = new TrieNode("", false);
        this.mapping = new HashMap<>();
        this.mapping.put('a', 2);
        this.mapping.put('b', 2);
        this.mapping.put('c', 2);
        this.mapping.put('d', 3);
        this.mapping.put('e', 3);
        this.mapping.put('f', 3);
        this.mapping.put('g', 4);
        this.mapping.put('h', 4);
        this.mapping.put('i', 4);
        this.mapping.put('j', 5);
        this.mapping.put('k', 5);
        this.mapping.put('l', 5);
        this.mapping.put('m', 6);
        this.mapping.put('n', 6);
        this.mapping.put('o', 6);
        this.mapping.put('p', 7);
        this.mapping.put('q', 7);
        this.mapping.put('r', 7);
        this.mapping.put('s', 7);
        this.mapping.put('t', 8);
        this.mapping.put('u', 8);
        this.mapping.put('v', 8);
        this.mapping.put('w', 9);
        this.mapping.put('x', 9);
        this.mapping.put('y', 9);
        this.mapping.put('z', 9);
    }

    public static void main(String[] args) {
        TT9 dictionary = new TT9();
        String word = new String("a");
        dictionary.insertWord(word);
        word = new String("an");
        dictionary.insertWord(word);
        word = new String("air");
        dictionary.insertWord(word);
        word = new String("ale");
        dictionary.insertWord(word);
        word = new String("age");
        dictionary.insertWord(word);
        word = new String("apple");
        dictionary.insertWord(word);
        word = new String("amber");
        dictionary.insertWord(word);
        word = new String("baby");
        dictionary.insertWord(word);
        word = new String("ball");
        dictionary.insertWord(word);
        word = new String("balloon");
        dictionary.insertWord(word);
        word = new String("bat");
        dictionary.insertWord(word);
        word = new String("bet");
        dictionary.insertWord(word);
        word = new String("bed");
        dictionary.insertWord(word);
        word = new String("be");
        dictionary.insertWord(word);
        word = new String("bee");
        dictionary.insertWord(word);
        word = new String("boy");
        dictionary.insertWord(word);
        word = new String("bill");
        dictionary.insertWord(word);
        word = new String("bicycle");
        dictionary.insertWord(word);
        word = new String("many");
        dictionary.insertWord(word);
        word = new String("man");
        dictionary.insertWord(word);
        word = new String("navy");
        dictionary.insertWord(word);
        word = new String("nice");
        dictionary.insertWord(word);
        word = new String("tree");
        dictionary.insertWord(word);
        word = new String("treetop");
        dictionary.insertWord(word);
        word = new String("trie");
        dictionary.insertWord(word);
        word = new String("toll");
        dictionary.insertWord(word);
        word = new String("tall");
        dictionary.insertWord(word);
        word = new String("teddy");
        dictionary.insertWord(word);
        word = new String("troll");
        dictionary.insertWord(word);
        word = new String("tell");
        dictionary.insertWord(word);
        word = new String("train");
        dictionary.insertWord(word);
        word = new String("umbrella");
        dictionary.insertWord(word);
        word = new String("under");
        dictionary.insertWord(word);
        word = new String("used");
        dictionary.insertWord(word);
        word = new String("user");
        dictionary.insertWord(word);

        TrieNode node = dictionary.words.root;
        HashMap<Integer, HashMap<Character, TrieNode>> hashmap = node.children;

        /*dictionary.showTrie(hashmap);*/
        List<String> wordSuggestion = dictionary.searchWords(8733);
        System.out.println("Word Suggestions: for the input keypad: " + 8733 + " is: ");
        for (String possibleWord : wordSuggestion) {
            System.out.println(possibleWord);
        }

        wordSuggestion = dictionary.searchWords(233);
        System.out.println("\nWord Suggestions: for the input keypad: " + 233 + " is: ");
        for (String possibleWord : wordSuggestion) {
            System.out.println(possibleWord);
        }
    }

    private void showTrie(HashMap<Integer, HashMap<Character, TrieNode>> words) {
        for (Map.Entry<Integer, HashMap<Character, TrieNode>> set : words.entrySet()) {
            HashMap<Character, TrieNode> characterHashMap = set.getValue();
            for (Map.Entry<Character, TrieNode> characterSet : characterHashMap.entrySet()) {
                System.out.println(characterSet.getValue().word + "     Is this a valid word: " + characterSet.getValue().isWordComplete);
                showTrie(characterSet.getValue().children);
            }
        }
    }

    private void insertWord(String word) {
        TrieNode node = this.words.root;
        for (int i = 0; i < word.length(); i++) {
            if (node.children.get(mapping.get(word.charAt(i))) == null) {/* Children Not Available */
                HashMap<Character, TrieNode> trieNodes = new HashMap();
                TrieNode trieNode = new TrieNode(node.word + String.valueOf(word.charAt(i)), i == word.length() - 1 ? true : false);
                trieNodes.put(word.charAt(i), trieNode);
                node.children.put(mapping.get(word.charAt(i)), trieNodes);
                node = node.children.get(mapping.get(word.charAt(i))).get(word.charAt(i));
            } else {/* Children Available */
                if (node.children.get(mapping.get(word.charAt(i))).get(word.charAt(i)) == null) {/*  */
                    TrieNode trieNode = new TrieNode(node.word + String.valueOf(word.charAt(i)), i == word.length() - 1 ? true : false);
                    node.children.get(mapping.get(word.charAt(i))).put(word.charAt(i), trieNode);
                    node = node.children.get(mapping.get(word.charAt(i))).get(word.charAt(i));
                } else {/* Update if this is a valid word alone */
                    /*node.children.get(mapping.get(word.charAt(i))).get(word.charAt(i)).isWordComplete = (i == word.length() - 1 ? true : false);*/
                    node = node.children.get(mapping.get(word.charAt(i))).get(word.charAt(i));
                }
            }
        }
    }

    private List<String> searchWords(int number) {
        String numString = String.valueOf(number);
        int length = numString.length();
        /*System.out.println(length);*/
        double divisor = Math.pow(10, (length - 1));
        /*System.out.println("Divisor: " + divisor);*/
        int digit = 0;
        TrieNode node = this.words.root;
        List<TrieNode> nodeList = new ArrayList<>();
        nodeList.add(node);
        while (number > 0) {
            digit = number / ((int) divisor);
            number = number % ((int) divisor);
            divisor = divisor / 10;
            /*System.out.println("Digit: " + digit);*/
            List<TrieNode> result = filter(digit, nodeList);
            nodeList = new ArrayList<>(result);
        }
        List<String> list = new ArrayList<>();
        for (TrieNode nodeValue : nodeList) {
            if (nodeValue.isWordComplete) {
                list.add(nodeValue.word);
            }
        }
        return list;
    }

    private List<TrieNode> filter(int digit, List<TrieNode> nodeList) {
        List<TrieNode> list = new ArrayList<>();
        for (TrieNode node : nodeList) {
            if (node.children.get(digit) != null) {
                for (Map.Entry<Character, TrieNode> entry : node.children.get(digit).entrySet()) {
                    list.add(entry.getValue());
                    /*System.out.println(entry.getValue().word);*/
                }
            }
        }
        return list;
    }
}

class Trie {
    public TrieNode root;
}

class TrieNode {
    public String word;
    public boolean isWordComplete;
    public HashMap<Integer, HashMap<Character, TrieNode>> children;

    public TrieNode(String word, boolean isWordComplete) {
        this.word = word;
        this.isWordComplete = isWordComplete;
        this.children = new HashMap<>();
    }
}