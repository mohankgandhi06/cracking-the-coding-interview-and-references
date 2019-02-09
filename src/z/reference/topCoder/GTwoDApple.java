package z.reference.topCoder;

public class GTwoDApple {

    public int[][] questionMatrix;
    public int[][] answerMatrix;

    public GTwoDApple(int[][] questionMatrix) {
        this.questionMatrix = questionMatrix;
        this.answerMatrix = new int[ this.questionMatrix.length ][ this.questionMatrix[ 0 ].length ];
    }

    public static void main(String[] args) {
        int[][] questionMatrix = {
                {3, 2, 5, 6, 8, 9, 2, 1},
                {1, 7, 4, 3, 8, 1, 6, 2},
                {3, 5, 2, 4, 5, 8, 7, 6},
                {0, 4, 2, 1, 0, 2, 3, 4},
                {1, 5, 8, 6, 7, 4, 3, 2}
        };
        GTwoDApple game = new GTwoDApple(questionMatrix);
        System.out.println("Maximum Apple which can be obtained: " + game.solve());
        game.showAnswerMatrix();
        game.showChoices();
    }

    private int solve() {
        for (int row = 0; row < this.questionMatrix.length; row++) {
            for (int column = 0; column < this.questionMatrix[ 0 ].length; column++) {
                this.answerMatrix[ row ][ column ] = this.questionMatrix[ row ][ column ] + (Math.max((row - 1 >= 0 ? this.answerMatrix[ row - 1 ][ column ] : 0), (column - 1 >= 0 ? this.answerMatrix[ row ][ column - 1 ] : 0)));
            }
        }
        return this.answerMatrix[ this.questionMatrix.length - 1 ][ this.questionMatrix[ 0 ].length - 1 ];
    }

    private void showAnswerMatrix() {
        System.out.println();
        System.out.println("Answer Matrix is as follows: ");
        for (int row = 0; row < this.questionMatrix.length; row++) {
            for (int column = 0; column < this.questionMatrix[ 0 ].length; column++) {
                System.out.print(this.answerMatrix[ row ][ column ] + " ");
            }
            System.out.println();
        }
    }

    private void showChoices() {
        //TODO
        /* This can be achieved by taking the answerMatrix and then
         * taking the minus of that index from the questionMatrix which
         * is then compared if equal to the upper or the left and recorded */
    }
}
