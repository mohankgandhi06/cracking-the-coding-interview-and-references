package r.chapter.hardDifficulty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KWordDistance {

    public HashMap<String, List<Integer>> map;

    public KWordDistance() {
        this.map = new HashMap<>();
    }

    public static void main(String[] args) {
        KWordDistance game = new KWordDistance();
        String[] words = new String[]{
                "Once", "upon", "a", "time", "there", "lived", "a", "boy", "named", "aladdin", ".", "He", "was", "very", "humble", "one", "and", "kind", ".",
                "He", "helped", "everyone", "and", "received", "a", "lot", "of", "gold", "from", "the", "king", "for", "his", "kind", "deeds", ".",
                "One", "day", "he", "set", "out", "on", "a", "journey", "to", "the", "cave", "to", "find", "a", "magic", "lamp", "."
        };

        String[] findDistance = new String[]{
                "he", "a"
        };

        System.out.println("Shortest distance between " + findDistance[ 0 ] + " and " + findDistance[ 1 ] + " is " + game.findShortest(words, findDistance, false));

        findDistance = new String[]{
                "a", "cave"
        };
        System.out.println("Shortest distance between " + findDistance[ 0 ] + " and " + findDistance[ 1 ] + " is " + game.findShortest(words, findDistance, true));

        findDistance = new String[]{
                "a", "."
        };
        System.out.println("Shortest distance between " + findDistance[ 0 ] + " and " + findDistance[ 1 ] + " is " + game.findShortest(words, findDistance, true));
    }

    private int findShortest(String[] words, String[] findDistance, boolean alreadyMapped) {
        if (!alreadyMapped) {
            prepareMap(words);
        }
        if (findDistance.length != 2) return -1;
        int difference = -1;
        if (this.map.containsKey(findDistance[ 0 ].toLowerCase()) && this.map.containsKey(findDistance[ 1 ].toLowerCase())) {
            difference = findMinimumDifference(this.map.get(findDistance[ 0 ].toLowerCase()), this.map.get(findDistance[ 1 ].toLowerCase()));
        }
        return difference;
    }

    private int findMinimumDifference(List<Integer> firstWordOccurence, List<Integer> secondWordOccurence) {
        int overallMin = Integer.MAX_VALUE;
        for (int integerOne : firstWordOccurence) {
            int eachIntegerMin = Integer.MAX_VALUE;
            for (int integerTwo : secondWordOccurence) {
                if (Math.abs(integerOne - integerTwo) > eachIntegerMin) {
                    break;
                } else {
                    eachIntegerMin = Math.abs(integerOne - integerTwo);
                }
            }
            if (overallMin > eachIntegerMin) {
                overallMin = eachIntegerMin;
            }
        }
        return overallMin;
    }

    private void prepareMap(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (this.map.containsKey(words[ i ].toLowerCase())) {
                /* Add to the list the i */
                this.map.get(words[ i ].toLowerCase()).add(i);
            } else {
                /* Create a new Hashmap entry and a Set and add those values */
                List<Integer> set = new ArrayList<>();
                set.add(i);
                this.map.put(words[ i ].toLowerCase(), set);
            }
        }
        /*for (Map.Entry<String, List<Integer>> entry : this.map.entrySet()) {
            System.out.print("Key: " + entry.getKey() + " Value: " + entry.getValue() + "\n");
        }*/
    }
}