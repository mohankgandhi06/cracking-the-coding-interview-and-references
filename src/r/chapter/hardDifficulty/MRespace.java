package r.chapter.hardDifficulty;

import java.util.HashMap;

public class MRespace {

    public HashMap<String, String> dictionary;

    public MRespace() {
        this.dictionary = new HashMap<>();
    }

    public static void main(String[] args) {
        MRespace game = new MRespace();
        String spaceRemoved = new String("iresetthecomputeritstilldidntboot");
        game.dictionary.put("i", "i");
        game.dictionary.put("reset", "reset");
        game.dictionary.put("the", "the");
        game.dictionary.put("computer", "computer");
        game.dictionary.put("it", "it");
        game.dictionary.put("still", "still");
        game.dictionary.put("didnt", "didnt");
        game.dictionary.put("boot", "boot");
        System.out.println("Line: " + spaceRemoved);
        Result result = game.insertSpaces(spaceRemoved);
        System.out.println("Final String: " + result.getBestSplitString() + " count of unrecognized character: " + result.getUnrecognizedCharacterCount());

        spaceRemoved = new String("jesslookedjustliketimherbrother");
        /*game.dictionary.put("jess", "jess");*/
        game.dictionary.put("looked", "looked");
        game.dictionary.put("just", "just");
        game.dictionary.put("like", "like");
        /*game.dictionary.put("tim", "tim");*/
        game.dictionary.put("her", "her");
        game.dictionary.put("brother", "brother");
        game.dictionary.put("he", "he");
        System.out.println("Line: " + spaceRemoved);
        result = game.insertSpaces(spaceRemoved);
        System.out.println("Final String: " + result.getBestSplitString() + "\ncount of unrecognized character: " + result.getUnrecognizedCharacterCount());
    }

    private Result insertSpaces(String spaceRemoved) {
        Result[] memo = new Result[ spaceRemoved.length() ];
        Result result = solve(spaceRemoved, 0, memo);
        return result;
    }

    private Result solve(String spaceRemoved, int index, Result[] memo) {
        if (index >= spaceRemoved.length()) return new Result("", 0);
        if (memo[ index ] != null) return memo[ index ];
        int bestInvalid = Integer.MAX_VALUE;
        String bestParse = null;
        int tempIndex = index;
        String partial = "";
        while (tempIndex < spaceRemoved.length()) {
            partial += spaceRemoved.charAt(tempIndex);
            int invalid = this.dictionary.containsKey(partial) ? 0 : partial.length();
            if (invalid < bestInvalid) {
                Result result = solve(spaceRemoved, tempIndex + 1, memo);
                if (invalid + result.getUnrecognizedCharacterCount() < bestInvalid) {
                    bestInvalid = invalid + result.getUnrecognizedCharacterCount();
                    bestParse = partial + " " + result.getBestSplitString();
                }
            }
            tempIndex++;
        }
        memo[ index ] = new Result(bestParse, bestInvalid);
        return memo[ index ];
    }
}

class Result {
    private String bestSplitString;
    private int unrecognizedCharacterCount;

    public Result(String bestSplitString, int unrecognizedCharacterCount) {
        this.bestSplitString = bestSplitString;
        this.unrecognizedCharacterCount = unrecognizedCharacterCount;
    }

    public String getBestSplitString() {
        return bestSplitString;
    }

    public int getUnrecognizedCharacterCount() {
        return unrecognizedCharacterCount;
    }
}