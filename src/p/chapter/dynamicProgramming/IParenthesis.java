package p.chapter.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class IParenthesis {

    public static void main(String[] args) {
        int numOfPair = 4;
        IParenthesis game = new IParenthesis();
        game.solve(numOfPair);
    }

    private void solve(int numOfPair) {
        char[] array = new char[ numOfPair * 2 ];
        for (int i = 0; i < numOfPair; i++) {
            array[ i ] = '(';
            array[ (numOfPair * 2) - i - 1 ] = ')';
        }

        List<Character> arrayList = new ArrayList<>(numOfPair * 2);
        for (int i = 0; i < array.length; i++) {
            arrayList.add(i, array[ i ]);
        }

        System.out.println("Input: ");
        for (char a : arrayList) {
            System.out.print(a + " ");
        }
        System.out.println();

        List<String> list = new ArrayList<>();
        StringBuilder currentString = new StringBuilder();
        currentString.append(arrayList.get(0));
        arrayList.remove(0);
        combinationOfParenthesis(arrayList, list, currentString);
        System.out.println("Valid Combinations which can be framed using the number of parenthesis: ");
        for (String s : list) {
            System.out.println(s);
        }
        return;
    }

    private List<String> combinationOfParenthesis(List<Character> parenthesis, List<String> list, StringBuilder currentString) {
        if (parenthesis.size() == 0) {
            list.add(currentString.toString());
            return list;
        }
        boolean[][] duplicates = new boolean[ currentString.length() + 2 ][currentString.length() + 2];
        for (int i = 0; i < parenthesis.size(); i++) {
            if (isValidAndNonRepetitive(i, parenthesis, currentString, duplicates)) {
                StringBuilder builder = new StringBuilder(currentString);
                List<Character> current = new ArrayList<>(parenthesis);
                builder.append(current.get(i));
                current.remove(i);
                combinationOfParenthesis(current, list, builder);
            }
        }
        return list;
    }

    private boolean isValidAndNonRepetitive(int currentIndex, List<Character> parenthesis, StringBuilder currentString, boolean[][] duplicates) {
        Count count = new Count();
        StringBuilder tempBuilder = new StringBuilder(currentString);
        tempBuilder.append(parenthesis.get(currentIndex));/* For Counting the number of openBraces and closeBraces */
        count(count, tempBuilder);
        if (count.closeBrace > count.openBrace) return false;
        if (duplicates[ count.openBrace ][ count.closeBrace ]) {
            return false;
        } else {
            duplicates[ count.openBrace ][ count.closeBrace ] = true;
        }
        return true;
    }

    private void count(Count count, StringBuilder tempBuilder) {
        for (int i = 0; i < tempBuilder.length(); i++) {
            if (tempBuilder.charAt(i) == '(') {
                count.openBrace++;
            } else {
                count.closeBrace++;
            }
        }
    }
}

class Count {
    public int openBrace;
    public int closeBrace;
}