package o.chapter.objectOrientedDesign;

import java.util.ArrayList;
import java.util.List;

public class ADeckOfCards {
    /* Question: Design a data structures for a generic deck of cards. Explain how you would subclass
     * the data structures to implement blackjack */
    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();
        blackJack.createDeck(2);

        for (Card card : blackJack.deck) {
            System.out.println("Card: ");
            System.out.println("Character: " + card.getCharacter());
            System.out.println("Face: " + card.getFaceValue());
            System.out.println("Alternate Face: " + card.getAlternateFaceValue());
            System.out.println("Color: " + card.getColor());
            System.out.println();
        }
    }
}

class Card {
    private String character;
    private int faceValue;
    private int alternateFaceValue;
    private Game.Color color;

    public Card(String character, int faceValue, int alternateFaceValue, Game.Color color) {
        this.character = character;
        this.faceValue = faceValue;
        this.alternateFaceValue = alternateFaceValue;
        this.color = color;
    }

    public String getCharacter() {
        return character;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public int getAlternateFaceValue() {
        return alternateFaceValue;
    }

    public Game.Color getColor() {
        return color;
    }
}

abstract class Game {
    /* Default Game is common for all Games and BlackJack is one of the type of Games */
    public enum Color {
        HEARTS, DIAMOND, SPADES, CLOVER
    }

    public enum FaceValue {
        ACE(1, 14), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

        private int faceValue;
        private int alternateFaceValue;

        FaceValue(int faceValue) {
            this.faceValue = faceValue;
        }

        FaceValue(int faceValue, int alternateFaceValue) {
            this.faceValue = faceValue;
            this.alternateFaceValue = alternateFaceValue;
        }

        public int getFaceValue() {
            return faceValue;
        }

        public int getAlternateFaceValue() {
            return alternateFaceValue;
        }
    }

    public List<Card> deck;

    /* Create Deck has been individually coded under the sub class since the enum values are not extendable from
     * base class like other variables */
    public abstract void createDeck(int numberOfDecks);

    class Player {
        private int cash;
        private String name;
        private int id;
        private int points;

        public Player(int cash, String name, int id, int points) {
            this.cash = cash;
            this.name = name;
            this.id = id;
            this.points = points;
        }

        public int getCash() {
            return cash;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getPoints() {
            return points;
        }
    }

    class Banker {
        private String name;
        private int id;
        private int cash;
        private int points;

        public Banker(String name, int id, int cash, int points) {
            this.name = name;
            this.id = id;
            this.cash = cash;
            this.points = points;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getCash() {
            return cash;
        }

        public int getPoints() {
            return points;
        }
    }
}

class BlackJack extends Game {
    public enum FaceValue {
        ACE(1, 11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

        private int faceValue;
        private int alternateFaceValue;

        FaceValue(int faceValue) {
            this.faceValue = faceValue;
        }

        FaceValue(int faceValue, int alternateFaceValue) {
            this.faceValue = faceValue;
            this.alternateFaceValue = alternateFaceValue;
        }

        public int getFaceValue() {
            return faceValue;
        }

        public int getAlternateFaceValue() {
            return alternateFaceValue;
        }
    }

    private List<Player> players;
    private Banker banker;
    private boolean gameOver;

    public void createDeck(int numberOfDecks) {
        deck = new ArrayList<>(numberOfDecks * 52);//This will initially itself create the total space
        // as needed and so the creation of new and moving of arraylist is avoided
        for (int k = 0; k < numberOfDecks; k++) {
            for (int i = 0; i < Color.values().length; i++) {
                for (int j = 0; j < FaceValue.values().length; j++) {
                    deck.add(new Card(FaceValue.values()[ j ].name(), FaceValue.values()[ j ].getFaceValue(), FaceValue.values()[ j ].getAlternateFaceValue(), Color.values()[ i ]));
                }
            }
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void playGame() {
        while (!gameOver) {
            //The rounds will proceed until either banker looses or the players get busted
            //The algorithm or designing the game is beyond the scope of this program
        }
    }
}

class Poker extends Game {
    public void createDeck(int numberOfDecks) {
        for (int i = 0; i < Color.values().length; i++) {
            System.out.println(Color.values()[ i ].name());
        }
    }
}

class Rummy extends Game {
    public void createDeck(int numberOfDecks) {
        for (int i = 0; i < Color.values().length; i++) {
            System.out.println(Color.values()[ i ].name());
        }
    }
}