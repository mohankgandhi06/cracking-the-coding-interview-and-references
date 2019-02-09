package z.reference.topCoder;

public class BOneDLongestNonDecreasing {

    public int[] numbers;
    public SequenceLengthAndIndex[] sequenceLengthAndIndices;

    public BOneDLongestNonDecreasing(int[] numbers) {
        this.numbers = numbers;
        this.sequenceLengthAndIndices = new SequenceLengthAndIndex[ numbers.length ];
    }

    public static void main(String[] args) {
        int[] numbers = {5, 3, 4, 8, 6, 7};
        BOneDLongestNonDecreasing game = new BOneDLongestNonDecreasing(numbers);
        System.out.println("Longest non-Decreasing: " + game.solve());
        game.showArray();
        game.showChoices();
    }

    private int solve() {
        this.sequenceLengthAndIndices[ 0 ] = new SequenceLengthAndIndex(1, -1);
        for (int n = 1; n < numbers.length; n++) {
            int length = 1;
            int temp = this.sequenceLengthAndIndices[ n - 1 ].length;
            int sequence = Math.max((length + (this.numbers[ n ] > this.numbers[ n - 1 ] ? temp : 0)), temp);
            SequenceLengthAndIndex s = null;
            if (sequence == temp && temp != 1) {//Previous element is the non decreasing
                s = new SequenceLengthAndIndex(sequence, this.sequenceLengthAndIndices[ n - 1 ].index);
            } else {
                s = new SequenceLengthAndIndex(sequence, (temp == sequence && temp == 1) ? -1 : n - 1);
            }
            this.sequenceLengthAndIndices[ n ] = s;
        }
        return this.sequenceLengthAndIndices[ this.numbers.length - 1 ].length;
    }

    private void showArray() {
        System.out.println();
        System.out.println("Index(Num)   Length   Index to Previous");
        for (int i = 0; i < sequenceLengthAndIndices.length; i++) {
            System.out.println(i + "(" + this.numbers[ i ] + ")         " + this.sequenceLengthAndIndices[ i ].length + "        " + this.sequenceLengthAndIndices[ i ].index);
        }
    }

    private void showChoices() {
        System.out.println();
        System.out.println("Choices are as mentioned below: ");
        int index = this.numbers.length - 1;
        while (index != -1) {
            if (index == 0) {
                System.out.println("Choice : " + this.numbers[ index ]);
                index--;
            } else if (this.sequenceLengthAndIndices[ index ].length != this.sequenceLengthAndIndices[ index - 1 ].length && this.sequenceLengthAndIndices[ index ].index != index) {
                System.out.println("Choice : " + this.numbers[ index ]);
                index = this.sequenceLengthAndIndices[ index ].index;
            } else if (this.numbers[ index ] < this.numbers[ index - 1 ] && this.sequenceLengthAndIndices[ index ].index != index) {
                System.out.println("Choice : " + this.numbers[ index ]);
                index = this.sequenceLengthAndIndices[ index ].index;
            } else {
                index--;
            }
        }
    }
}

class SequenceLengthAndIndex {
    public int length;
    public int index;

    public SequenceLengthAndIndex(int length, int index) {
        this.length = length;
        this.index = index;
    }
}