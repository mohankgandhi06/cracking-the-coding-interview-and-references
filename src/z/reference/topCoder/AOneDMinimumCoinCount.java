package z.reference.topCoder;

public class AOneDMinimumCoinCount {

    public int[] coins;
    public int[] minCoins;
    public CoinAndRemaining[] coinAndRemaining;

    public AOneDMinimumCoinCount(int amount, int[] coins) {
        this.coins = coins;
        this.minCoins = new int[ amount + 1 ];
        this.coinAndRemaining = new CoinAndRemaining[ amount + 1 ];
    }

    public static void main(String[] args) {
        int amount = 11;
        int[] coins = {1, 3, 5};
        AOneDMinimumCoinCount game = new AOneDMinimumCoinCount(amount, coins);
        System.out.println("Minimum Coin Combination: " + game.solve());
        System.out.println();
        game.showArray(game.minCoins, game.coinAndRemaining);
        game.showChoices();
    }

    public int solve() {
        CoinAndRemaining zero = new CoinAndRemaining(0, 0);
        this.coinAndRemaining[ 0 ] = zero;
        for (int i = 1; i < this.minCoins.length; i++) {
            int min = Integer.MAX_VALUE;
            int coin = -1;
            for (int c = 0; c < this.coins.length; c++) {
                if (coins[ c ] <= i) {
                    int temp = min;
                    min = Math.min(min, 1 + (minCoins[ i - coins[ c ] ]));
                    if (temp != min) {
                        coin = coins[ c ];
                    }
                }
            }
            this.minCoins[ i ] = min;
            CoinAndRemaining c = new CoinAndRemaining(coin, i - coin);
            this.coinAndRemaining[ i ] = c;
        }
        return this.minCoins[ this.minCoins.length - 1 ];
    }

    public void showChoices() {
        System.out.println();
        System.out.println("Choices: ");
        CoinAndRemaining i = this.coinAndRemaining[ this.minCoins.length - 1 ];
        while (i.denomination != 0) {
            System.out.println("Choose: " + i.denomination);
            i = this.coinAndRemaining[ i.remaining ];
        }
        /*this.coinAndRemaining[]*/
    }

    public void showArray(int[] array, CoinAndRemaining[] coinArray) {
        System.out.println("Sum      Min Coins   Coin & Remaining");
        for (int i = 0; i < this.minCoins.length; i++) {
            System.out.print(i + (i / 10 >= 1 ? "       " : "        "));
            System.out.print(array[ i ] + "           ");
            System.out.println(coinArray[ i ].denomination + "(" + coinArray[ i ].remaining + ")");
        }
    }
}

class CoinAndRemaining {
    public int denomination;
    public int remaining;

    public CoinAndRemaining(int denomination, int remaining) {
        this.denomination = denomination;
        this.remaining = remaining;
    }
}