package q.chapter.mediumDifficulty;

import java.util.ArrayList;
import java.util.List;

public class RPatternMatching {
    public static void main(String[] args) {
        RPatternMatching game = new RPatternMatching();
        String input = "gogocatcatgo";
        String pattern = "bbaab";
        System.out.println("Is '" + input + "' and the pattern '" + pattern + "' matching? " + game.isPatternMatching(input, pattern));

        System.out.println();
        input = new String("catcatgo");
        pattern = new String("bbaab");
        System.out.println("Is '" + input + "' and the pattern '" + pattern + "' matching? " + game.isPatternMatching(input, pattern));

        System.out.println();
        input = new String("gogocatcatgo");
        pattern = new String("aabba");
        System.out.println("Is '" + input + "' and the pattern '" + pattern + "' matching? " + game.isPatternMatching(input, pattern));

        System.out.println();
        input = new String("gogocatcatgo");
        pattern = new String("babab");
        System.out.println("Is '" + input + "' and the pattern '" + pattern + "' matching? " + game.isPatternMatching(input, pattern));

        System.out.println();
        input = new String("gocatgocatgo");
        pattern = new String("babab");
        System.out.println("Is '" + input + "' and the pattern '" + pattern + "' matching? " + game.isPatternMatching(input, pattern));

        System.out.println();
        input = new String("gogogo");
        pattern = new String("aaa");
        System.out.println("Is '" + input + "' and the pattern '" + pattern + "' matching? " + game.isPatternMatching(input, pattern));

        System.out.println();
        input = new String("gogogo");
        pattern = new String("aaaa");
        System.out.println("Is '" + input + "' and the pattern '" + pattern + "' matching? " + game.isPatternMatching(input, pattern));

        System.out.println();
        input = new String("gogogo");
        pattern = new String("aab");
        System.out.println("Is '" + input + "' and the pattern '" + pattern + "' matching? " + game.isPatternMatching(input, pattern));
    }

    private boolean isPatternMatching(String input, String pattern) {
        int aCount = 0;
        int bCount = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == 'a') aCount++;
            else bCount++;
        }
        int x = 0;
        int y = 0;
        List<Pair> combinations = new ArrayList<>();
        if (aCount != 0 && bCount != 0) {
            while (aCount * x <= input.length()) {
                if ((input.length() - (aCount * x)) % bCount == 0) {
                    y = (input.length() - (aCount * x)) / bCount;
                    combinations.add(new Pair(x, y));
                }
                x++;
            }
        }
        if (aCount == 0) {
            if (input.length() % bCount == 0) {
                y = input.length() / bCount;
                combinations.add(new Pair(x, y));
            } else {
                return false;
            }
        } else if (bCount == 0) {
            if (input.length() % aCount == 0) {
                y = input.length() / aCount;
                combinations.add(new Pair(x, y));
            } else {
                return false;
            }
        }

        /*System.out.println("Pairs....");*/
        for (Pair pair : combinations) {
            /*System.out.println(pair.x + " : " + pair.y);*/
            int currentStart = 0;
            List<String> substrings = new ArrayList<>();
            int aOccurence = 0;
            int bOccurence = 0;
            String[] map = new String[ 2 ];
            for (int i = 0; i < pattern.length(); i++) {
                if (pattern.charAt(i) == 'a') {
                    if (aOccurence == 0) {
                        map[ 0 ] = input.substring(currentStart, currentStart + pair.x);
                        aOccurence++;
                    } else if (!map[ 0 ].equalsIgnoreCase(input.substring(currentStart, currentStart + pair.x))) {
                        substrings = new ArrayList<>();
                        break;
                    }
                    substrings.add(input.substring(currentStart, currentStart + pair.x));
                    currentStart = currentStart + pair.x;
                } else {
                    if (bOccurence == 0) {
                        map[ 1 ] = input.substring(currentStart, currentStart + pair.y);
                        bOccurence++;
                    } else if (!map[ 1 ].equalsIgnoreCase(input.substring(currentStart, currentStart + pair.y))) {
                        substrings = new ArrayList<>();
                        break;
                    }
                    substrings.add(input.substring(currentStart, currentStart + pair.y));
                    currentStart = currentStart + pair.y;
                }
            }
            /*System.out.println(substrings.size());
            for (String string : substrings) {
                System.out.println(string);
            }*/
            if (substrings.size() > 0) return true;
        }
        return false;
    }
}

class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}