package q.chapter.mediumDifficulty;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BWordFrequencies {

    public Map<String, Map<String, Integer>> index;
    public LinkedList[] bucket;

    public BWordFrequencies() {
        this.index = new HashMap<>();
        this.bucket = new LinkedList[ 26 ];
        char alpha = 'a';
        for (int count = 0; count < 26; count++) {
            char current = (char) (alpha + count);
            /*System.out.println(current);*/
            this.index.put(String.valueOf(current), null);
        }
    }

    public static void main(String[] args) {
        BWordFrequencies wordFrequency = new BWordFrequencies();

        String[] book = new String[]{
                "Once", "there", "lived", "a", "mage", "who", "was",
                "very", "powerful", "than", "the", "gods", ".", "He",
                "happened", "to", "be", "a", "kind", "hearted", "person",
                "who", "wander", "the", "realm", "to", "help", "the",
                "people", "in", "dire", "need", ".", "He", "got", "the",
                "power", "by", "working", "hard", "and", "diligence", "which",
                "made", "him", "better", "and", "better", "day", "by", "day", "."
        };
        wordFrequency.frameBuckets(book);

        String word = "by";
        int occurence = wordFrequency.findOccurences(book, word);
        System.out.println("'" + word + "' word occurs " + occurence + " times in the book");
        System.out.println();

        word = "init";
        occurence = wordFrequency.findOccurences(book, word);
        System.out.println("'" + word + "' word occurs " + occurence + " times in the book");
        System.out.println();

        word = "got";
        occurence = wordFrequency.findOccurences(book, word);
        System.out.println("'" + word + "' word occurs " + occurence + " times in the book");
        System.out.println();

        word = "better";
        occurence = wordFrequency.findOccurences(book, word);
        System.out.println("'" + word + "' word occurs " + occurence + " times in the book");
        System.out.println();
    }

    private int findOccurences(String[] book, String word) {
        char alpha = 'a';
        int location = word.toLowerCase().charAt(0) - alpha;
        if (location >= 0 && location < this.bucket.length) {
            LinkedList<String> arrayList = this.bucket[ location ];
            String[] wordsStartingWith = arrayList.toArray(new String[ arrayList.size() ]);
            if (this.index.get(String.valueOf(word.charAt(0))) == null) {
                System.out.println("pre-process: " + word.charAt(0) + " words being processed: " + wordsStartingWith.length);
                preprocessBook(wordsStartingWith);
            } else {
                System.out.println("Already pre-processed: " + word.charAt(0));
            }
        }
        return getOccurence(word);
    }

    private int getOccurence(String word) {
        if (this.index.get(String.valueOf(word.charAt(0))).containsKey(word)) {
            return this.index.get(String.valueOf(word.charAt(0))).get(word);
        }
        return 0;
    }

    private void frameBuckets(String[] book) {
        char alpha = 'a';
        for (int i = 0; i < book.length; i++) {
            int location = book[ i ].toLowerCase().charAt(0) - alpha;
            if (location >= 0 && location < this.bucket.length) {
                if (this.bucket[ location ] == null) {
                    this.bucket[ location ] = new LinkedList();
                }
                this.bucket[ location ].add(book[ i ]);
            }
        }
    }

    private void preprocessBook(String[] book) {
        for (int i = 0; i < book.length; i++) {
            String tempString = book[ i ].toLowerCase();
            char tempCharacter = tempString.charAt(0);
            Map<String, Integer> tempMap = this.index.get(String.valueOf(tempCharacter));
            if (tempMap != null) {
                if (tempMap.containsKey(tempString)) {
                    this.index.get(String.valueOf(tempCharacter)).put(tempString, this.index.get(String.valueOf(tempCharacter)).get(tempString) + 1);
                } else {
                    tempMap.put(tempString, 1);
                }
            } else {
                this.index.put(String.valueOf(tempCharacter), new HashMap<>());
                tempMap = this.index.get(String.valueOf(tempCharacter));
                tempMap.put(tempString, 1);
            }
        }
        /*this.index.forEach((s, stringIntegerMap) -> {
            System.out.println("Key: " + s);
            if (stringIntegerMap != null) {
                stringIntegerMap.forEach((s1, integer) -> {
                    if (s1 != null) {
                        System.out.println("Sub-Key: " + s1 + " Count: " + integer);
                    }
                });
            }
        });*/
    }
}