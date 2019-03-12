package p.chapter.dynamicProgramming;

import java.util.*;

public class HPermutationsWithDuplicates {
    public static void main(String[] args) {
        HPermutationsWithDuplicates game = new HPermutationsWithDuplicates();
        String input = "abba";
        List<String> result = game.solve(input);
        for (String s : result) {
            System.out.println(s);
        }
    }

    private List<String> solve(String input) {
        List<String> stringList = new ArrayList<>();
        List<Character> characterList = new ArrayList<>();
        /* Converting it into the smaller character since we are sorting it by charValue below */
        input = input.toLowerCase();
        for (int i = 0; i < input.length(); i++) {
            characterList.add(input.charAt(i));
        }
        characterList.sort(Comparator.comparing(Character::charValue));
        for (Character c : characterList) {
            System.out.print(c + " ");
        }
        System.out.println();
        Set<String> duplicate = new HashSet<>();
        permutations(characterList, 0, new StringBuilder(), stringList, duplicate);
        /*permutations(characterList, new StringBuilder(), stringList);*/
        return stringList;
    }

    /*private List<String> permutations(List<Character> characterList, int currentIndex, StringBuilder currentString, List<String> stringList) {
        if (0 == characterList.size() || currentIndex == characterList.size()) {
            stringList.add(currentString.toString());
            return stringList;
        }
        StringBuilder tempStringBuilder = new StringBuilder(currentString);
        List<Character> tempCharacterList = new ArrayList<>(characterList);
        if (notDuplicate(tempStringBuilder, )) {
            tempCharacterList.remove(currentIndex);
            permutations(tempCharacterList, currentIndex, tempStringBuilder.append(characterList.get(currentIndex)), stringList);
        }
        permutations(characterList, currentIndex + 1, currentString, stringList);
        return stringList;
    }*/

    private List<String> permutations(List<Character> characterList, int currentIndex, StringBuilder currentString, List<String> stringList, Set<String> duplicate) {
        if (0 == characterList.size()) {
            stringList.add(currentString.toString());
            return stringList;
        }
        for (int i = 0; i < characterList.size(); i++) {
            StringBuilder test = new StringBuilder(currentString);
            test.append(characterList.get(i));
            if (duplicate.add(test.toString())) {
                StringBuilder tempStringBuilder = new StringBuilder(currentString);
                List<Character> tempCharacterList = new ArrayList<>(characterList);
                tempCharacterList.remove(i);
                tempStringBuilder.append(characterList.get(i));
                permutations(tempCharacterList, currentIndex, tempStringBuilder, stringList, duplicate);
            }
        }
        characterList.remove(currentIndex);
        permutations(characterList, currentIndex, currentString, stringList, duplicate);
        return stringList;
    }
}