package z.reference.dynamicProgramming;

import java.util.Stack;

public class PDynamicStock {

    public int[] price;
    public int transactions;
    public int[][] matrix;

    public PDynamicStock(int[] price, int transactions) {
        this.price = price;
        this.transactions = transactions;
        this.matrix = new int[ transactions + 1 ][ price.length ];
    }

    public static void main(String[] args) {
        int[] price = {2, 5, 7, 1, 4, 3, 1, 3};
        int transactions = 3;
        PDynamicStock game = new PDynamicStock(price, transactions);
        System.out.println("Optimal Profit will be: " + game.solve() + " $");
        System.out.println();

        for (int i = 0; i < game.matrix.length; i++) {
            for (int j = 0; j < game.matrix[ 0 ].length; j++) {
                System.out.print(game.matrix[ i ][ j ] + " ");
            }
            System.out.println();
        }

        game.showChoices();
    }

    public int solve() {
        //t - transactions array
        //d - day array
        //Math.max(notSelling, selling);
        //Assuming that the transaction is made on each day i.e. selling = Math.max(d0, d1, dn-1);
        //notSelling - matrix[t][d-1];
        //selling - purchase can be made on any day between the 0 and n-1
        for (int t = 1; t <= this.transactions; t++) {
            for (int d = 1; d < this.price.length; d++) {
                int notSelling = this.matrix[ t ][ d - 1 ];
                int selling = 0;
                for (int m = 0; m < d; m++) {
                    selling = Math.max(selling, ((price[ d ] - price[ m ]) + this.matrix[ t - 1 ][ m ]));
                }
                this.matrix[ t ][ d ] = Math.max(notSelling, selling);
            }
        }
        return this.matrix[ this.transactions ][ this.price.length - 1 ];
    }

    public void showChoices() {
        Stack<Message> stack = new Stack<>();
        int sellOrPurchase = 1;
        for (int t = this.transactions, d = this.price.length - 1; t > 0 && d > 0; d--) {
            if (this.matrix[ t ][ d ] != this.matrix[ t ][ d - 1 ]) {
                Message message = new Message();
                message.message = sellOrPurchase % 2 == 1 ? Message.SOLD_ON : Message.PURCHASED_ON;
                message.day = d;
                stack.push(message);
                sellOrPurchase++;
                int tempPrice = this.matrix[ t ][ d ] - price[ d ];
                t--;
                for (int z = d; z >= 0; z--) {
                    if (this.matrix[ t ][ z ] - price[ z ] == tempPrice) {
                        message = new Message();
                        message.message = sellOrPurchase % 2 == 1 ? Message.SOLD_ON : Message.PURCHASED_ON;
                        message.day = z;
                        stack.push(message);
                        sellOrPurchase++;
                        break;
                    }
                }
            }
        }

        System.out.println();
        System.out.println("Optimal Purchase and Sale day");
        while (!stack.isEmpty()) {
            Message m = stack.pop();
            System.out.println("Message: " + m.message + m.day + " (Day)");
        }
    }
}

class Message {
    public String message;
    public int day;

    public static final String PURCHASED_ON = "Purchased on ";
    public static final String SOLD_ON = "Sold on ";
}