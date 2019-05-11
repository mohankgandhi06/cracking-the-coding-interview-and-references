package r.chapter.hardDifficulty;

import java.util.*;

public class OLongestWord {

    public HashMap<Character, List<String>> map;

    public OLongestWord() {
        this.map = new HashMap<>();
    }

    public static void main(String[] args) {
        OLongestWord game = new OLongestWord();
        List<String> input = new ArrayList<>();
        input.add("cat");
        input.add("banana");
        input.add("dog");
        input.add("nana");
        input.add("b");
        input.add("a");
        input.add("na");
        input.add("walk");
        input.add("walker");
        input.add("dogwalker");
        input.add("dogwalkers");

        Word word = game.findLongestWord(input);
        System.out.println("Longest Fully formed word is: " + word.getWord() + " count: " + word.getCount());
    }

    private Word findLongestWord(List<String> input) {
        input.sort(Comparator.comparing(String::length).reversed());
        convertToHash(input);
        int wordCount = 0;
        String word = null;
        for (String s : input) {
            int count = findCount(s);
            if (count >= wordCount) {
                wordCount = count;
                word = s;
            }
            /*System.out.println("Word: " + s + " Count: " + count);*/
        }
        show(input);
        return new Word(word, wordCount);
    }

    private void convertToHash(List<String> input) {
        for (String in : input) {
            if (!this.map.containsKey(in.charAt(0))) {
                List<String> list = new ArrayList<>();
                list.add(in);
                this.map.put(in.charAt(0), list);
            } else {
                this.map.get(in.charAt(0)).add(in);
            }
        }
        /*show(this.map);*/
    }

    private int findCount(String s) {
        return findWords(s, 0, 0);
    }

    private int findWords(String s, int startIndex, int wordLength) {
        if (startIndex >= s.length() || startIndex + wordLength + 1 > s.length()) return 0;
        int include = 0;
        if (isWordFound(s.substring(startIndex, startIndex + wordLength + 1))) {
            include = 1 + findWords(s, startIndex + wordLength + 1, 0);
        }
        int exclude = findWords(s, startIndex, wordLength + 1);
        return Math.max(include, exclude);
    }

    private boolean isWordFound(String word) {
        if (this.map.containsKey(word.charAt(0))) {
            for (String s : this.map.get(word.charAt(0))) {
                if (s.equalsIgnoreCase(word)) return true;
            }
        }
        return false;
    }

    private void show(List<String> input) {
        for (String s : input) {
            System.out.println(s);
        }
    }

    private void show(HashMap<Character, List<String>> map) {
        System.out.println("Map: ");
        for (Map.Entry<Character, List<String>> s : map.entrySet()) {
            System.out.println("Key: " + s.getKey());
            for (String in : s.getValue()) {
                System.out.print(in + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Word {
    private String word;
    private int count;

    public Word(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }
}