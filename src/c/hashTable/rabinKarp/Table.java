package c.hashTable.rabinKarp;

public class Table {
    public static int TABLE_SIZE;
    public Node[] nodes;
    public int HASH_DIVISOR;

    public Table(String size) {
        this.TABLE_SIZE = Integer.parseInt(size);
        this.nodes = new Node[ TABLE_SIZE ];
        //This below lines of code is not needed. Just for the clarity
        for (int i = 0; i < TABLE_SIZE; i++) {
            nodes[ i ] = null;
        }
        this.HASH_DIVISOR = primeNumber(TABLE_SIZE);
        if (this.HASH_DIVISOR == -1) {
            this.HASH_DIVISOR = this.TABLE_SIZE;
        }
    }

    private static int primeNumber(int size) {
        while (size < 99999999) {
            if (isPrime(size)) {
                return size;
            } else {
                size++;
            }
        }
        return -1;
    }

    private static boolean isPrime(int size) {
        for (int i = 2; i * i <= size; i++) {
            if (size % i == 0) {
                return false;
            }
        }
        return true;
    }
}
