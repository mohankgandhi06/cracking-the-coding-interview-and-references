package r.chapter.hardDifficulty;

public class BShuffle {

    public Deck deck;

    public BShuffle(Deck deck) {
        this.deck = deck;
    }

    public static void main(String[] args) {
        BShuffle game = new BShuffle(new Deck(52));
        game.showDeck();
        game.shuffle();
        System.out.println();
        System.out.println("After Shuffle");
        game.showDeck();
    }

    private void shuffle() {
        for (int i = 0; i < this.deck.cards.length; i++) {
            /*Random random = new Random();
            int x = random.nextInt(52);*/
            int x = fairRandom(0, i);
            Card temp = this.deck.cards[ x ];
            this.deck.cards[ x ] = this.deck.cards[ i ];
            this.deck.cards[ i ] = temp;
        }
    }

    private int fairRandom(int lower, int higher) {
        return lower + (int) (Math.random() * (higher - lower + 1));
    }

    private void showDeck() {
        for (int i = 0; i < this.deck.cards.length; i++) {
            System.out.println(this.deck.cards[ i ].getFaceValue() + "  of   " + this.deck.cards[ i ].getSymbol());
        }
    }
}

class Deck {
    public Card[] cards;//Deck size is 52

    public String[] faceValue = new String[]{
            "CLOVER", "SPADE", "DIAMOND", "HEART"
    };
    public String[] symbol = new String[]{
            "ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT",
            "NINE", "TEN", "JACK", "QUEEN", "KING"
    };

    public Deck(int size) {
        this.cards = new Card[ size ];
        build(size);
    }

    private boolean build(int size) {//Size will be multiples of 52
        if (size % 52 != 0) {
            System.out.println("Cannot create deck of the requested size");
            return false;
        }
        int l = 0;
        for (int i = 0; i < size / 52; i++) {
            for (int j = 0; j < this.faceValue.length; j++) {
                for (int k = 0; k < this.symbol.length; k++) {
                    this.cards[ l ] = new Card(this.faceValue[ j ], this.symbol[ k ]);
                    l++;
                }
            }
        }
        return true;
    }
}

class Card {
    private String faceValue;
    private String symbol;

    public Card(String faceValue, String symbol) {
        this.faceValue = faceValue;
        this.symbol = symbol;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public String getSymbol() {
        return symbol;
    }
}