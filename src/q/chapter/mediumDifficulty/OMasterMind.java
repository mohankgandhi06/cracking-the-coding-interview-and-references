package q.chapter.mediumDifficulty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OMasterMind {

    enum Color {
        RED(0, 'R'), BLUE(1, 'B'), GREEN(2, 'G'), YELLOW(3, 'Y');

        public int value;
        public char character;

        Color(int number, char character) {
            this.value = number;
            this.character = character;
        }
    }

    public Color[] slot;
    public List<Integer>[] map;
    private static final int TOTAL_LENGTH = 4;

    public OMasterMind(Color[] slot) {
        this.slot = slot;
        this.map = new List[ 4 ];//0 - RED, 1 - BLUE, 2 - GREEN, 3 - YELLOW
        for (int i = 0; i < 4; i++) {
            if (this.map[ slot[ i ].value ] == null) {
                this.map[ slot[ i ].value ] = new ArrayList<>();
                this.map[ slot[ i ].value ].add(i);
            } else {
                this.map[ slot[ i ].value ].add(i);
            }
        }

        /*System.out.println("Just for Checking: ");
        for (int i = 0; i < 4; i++) {
            System.out.print(this.slot[ i ].character + ": ");
            System.out.print(this.map[ i ] != null ? this.map[ i ].toString() : "");
        }
        System.out.println();*/
    }

    public static void main(String[] args) {
        Color[] slot = new Color[]{Color.RED, Color.BLUE, Color.BLUE, Color.YELLOW};
        OMasterMind game = new OMasterMind(slot);
        System.out.println("Welcome to Master Mind Game: ");
        System.out.println("Our System has kept a random sequence of Color (R,G,B,Y). Let's see if you could guess.");
        System.out.println("When you guess a sequence please type in as (for example) RGBY without any spaces and comma or any character");
        System.out.println("If you want to give up type GIVEUP and then System will reveal the answer. Let's start");
        int guessNumber = 1;
        boolean won = false;
        while (!won) {
            System.out.println("Guess Number: #" + guessNumber + " Key in your guess");
            Scanner in = new Scanner(System.in);
            String guess = in.nextLine();
            if (!guess.equals("GIVEUP")) {
                System.out.println("Your Guess " + guess);
                won = game.checkHitAndMissAlternate(guess);
            } else {
                String convertedGuess = game.convert(slot);
                System.out.println("Given Up: The Answer is: " + convertedGuess);
                break;
            }
            guessNumber++;
        }
    }

    private boolean checkHitAndMiss(String guess) {
        int[] countOfColorGuessedBySystem = new int[ 4 ];
        int[] countOfColorGuessedByPlayer = new int[ 4 ];
        for (int i = 0; i < this.slot.length; i++) {
            countOfColorGuessedBySystem[ i ] = (this.map[ i ] != null) ? this.map[ i ].size() : 0;
        }
        /*System.out.println("System count");
        for (int i = 0; i < 4; i++) {
            Color[] colors = Color.values();
            System.out.println(colors[ i ].character + " : " + countOfColorGuessedBySystem[ i ]);
        }*/

        for (int i = 0; i < guess.length(); i++) {
            char character = guess.charAt(i);
            for (Color color : Color.values()) {
                if (color.character == character) {
                    countOfColorGuessedByPlayer[ color.value ] += 1;
                    break;
                }
            }
        }

        /*System.out.println("Player count");
        for (int i = 0; i < 4; i++) {
            Color[] colors = Color.values();
            System.out.println(colors[ i ].character + " : " + countOfColorGuessedByPlayer[ i ]);
        }*/

        int hit = 0;
        int pseudoHit = 0;
        for (int i = 0; i < this.map.length; i++) {
            char characterToMatch = 'X';
            for (Color color : Color.values()) {
                if (color.value == i) {
                    characterToMatch = color.character;
                    break;
                }
            }
            if (countOfColorGuessedBySystem[ i ] == countOfColorGuessedByPlayer[ i ] && countOfColorGuessedByPlayer[ i ] != 0) {
                if (this.map[ i ].size() > 0) {
                    for (Integer j : this.map[ i ]) {
                        if (guess.charAt(j) == characterToMatch) {
                            hit++;
                        } else {
                            pseudoHit++;
                        }
                    }
                }
            } else if (countOfColorGuessedBySystem[ i ] > countOfColorGuessedByPlayer[ i ] && countOfColorGuessedByPlayer[ i ] != 0) {
                int playerCount = countOfColorGuessedByPlayer[ i ];
                for (int l = 0; l < guess.length(); l++) {
                    if (guess.charAt(l) == characterToMatch) {
                        boolean flag = false;
                        for (Integer j : this.map[ i ]) {
                            if (l == j) {
                                hit++;
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            pseudoHit++;
                        }
                        playerCount--;
                        if (playerCount == 0) break;
                    }
                }
            } else if (countOfColorGuessedBySystem[ i ] < countOfColorGuessedByPlayer[ i ]) {
                if (this.map[ i ] != null) {
                    List<Integer> list = this.map[ i ];
                    for (Integer m : list) {
                        if (guess.charAt(m) == characterToMatch) {
                            hit++;
                        } else {
                            pseudoHit++;
                        }
                    }
                }
            }
        }
        System.out.println("Hit: " + hit + " Pseudo-Hit: " + pseudoHit);
        if (hit == 4) return true;
        return false;
    }

    private String convert(Color[] slot) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Color i : slot) {
            stringBuilder.append(i.character);
        }
        return stringBuilder.toString();
    }

    private boolean checkHitAndMissAlternate(String guess) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Color color : this.slot) {
            stringBuilder.append(color.character);
        }
        String solution = stringBuilder.toString();
        /*System.out.println(stringBuilder.toString());*/

        if (solution.length() != guess.length()) return false;

        /* Determine the Hits */
        int[] frequency = new int[ TOTAL_LENGTH ];
        Result result = new Result();
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == solution.charAt(i)) {
                result.hits++;
            } else {
                /* If it is a miss it can that the character could be potential pseudo-hit so
                we are saving the solutions character in the array. i.e. what character we missed hitting */
                frequency[ code(solution.charAt(i)) ]++;
            }
        }

        /* Determine the Pseudo-Hits */
        /* Now to determine if the pseudo-hit occured we are checking first if the character is not a illegal character
         * since this will cause out of bounds when we are trying to get the frequency array value */
        for (int i = 0; i < guess.length(); i++) {
            int code = code(guess.charAt(i));
            if (code >= 0 && frequency[ code ] > 0 && guess.charAt(i) != solution.charAt(i)) {
                result.pseudohits++;
                frequency[ code ]--;
            }
        }
        System.out.println(result.showResult());
        if (result.hits == TOTAL_LENGTH) return true;
        return false;
    }

    private int code(Character character) {
        switch (character) {
            case 'R':
                return 0;
            case 'G':
                return 1;
            case 'B':
                return 2;
            case 'Y':
                return 3;
            default:
                return -1;
        }
    }
}

class Result {
    public int hits;
    public int pseudohits;

    public String showResult() {
        return "Hits: " + hits + " and Pseudo-Hits: " + pseudohits;
    }
}