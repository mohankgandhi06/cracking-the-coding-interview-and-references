package r.chapter.hardDifficulty;

import java.util.Arrays;

public class ELettersAndNumbers {
    public static void main(String[] args) {
        ELettersAndNumbers game = new ELettersAndNumbers();
        Character[] input = new Character[]{
                '7', '6', 'A', 'B', '1', '2', '3', 'C', 'D', '4', '5', '6', 'B', '7', '8'
        };
        game.longestSubarray(input);

        System.out.println();
        input = new Character[]{
                '7', '6', 'A', 'B', '1', '2', '3', 'C', 'D', '4', '5', 'B', '7', '8'
        };
        game.longestSubarray(input);

        System.out.println();
        input = new Character[]{
                '7', '6', 'A', '6', '1', '2', '3', 'C', 'D', '4', '5', 'B', 'F', 'G'
        };
        game.longestSubarray(input);

        System.out.println();
        input = new Character[]{
                '7', '6', 'A', '6', '1', '2', '3', 'C', 'D', '4', '5', 'B', 'F', 'G', 'L'
        };
        game.longestSubarray(input);

        System.out.println();
        input = new Character[]{
                '7', '6', 'A', '6', '1', '2', '3', 'C', 'D', '4', '5', 'B', 'F', 'G', 'K', 'L', 'A'
        };
        game.longestSubarray(input);

        System.out.println();
        input = new Character[]{
                '7', '6', '8', '6', '1', '2', '3', 'C', 'D', '4', '5', 'B', 'F', 'L', 'A'
        };
        game.longestSubarray(input);

        System.out.println();
        input = new Character[]{
                '2', '3', 'C', 'D', '4', '5', 'B', 'F', 'L', 'A'
        };
        game.longestSubarray(input);
    }

    private void longestSubarray(Character[] input) {
        Arrays.stream(input).forEach(System.out::print);
        System.out.println();
        System.out.println("Maximum Subarray Length: " + solve(input, 0, 0, 0, 0, false));
    }

    private int solve(Character[] input, int currentIndex, int characterCount, int digitCount, int subarrayLength, boolean reset) {
        if (currentIndex == input.length) {
            if (characterCount == digitCount && characterCount + digitCount > subarrayLength) {
                subarrayLength = characterCount + digitCount;
                return subarrayLength;
            }
            return subarrayLength;
        }
        int include = 0;
        /* INCLUDE */
        /* Here we are going to reset the count only if we are excluding the previous character and including any subsequent character
         * In case we are going to skip every other subsequent character then we can retain the older count */
        if (!reset) {
            include = solve(input, currentIndex + 1, Character.isLetter(input[ currentIndex ]) ? characterCount + 1 : characterCount, Character.isDigit(input[ currentIndex ]) ? digitCount + 1 : digitCount, subarrayLength, reset);
        } else {
            include = solve(input, currentIndex, 0, 0, subarrayLength, false);
        }
        /* EXCLUDE */
        int exclude = solve(input, currentIndex + 1, characterCount, digitCount, subarrayLength, true);
        return Math.max(include, exclude);
    }
}