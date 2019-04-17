package q.chapter.mediumDifficulty;

public class WRand7FromRand5 {

    public int[] randomFiveOccurence;
    public int[] randomSevenOccurence;

    public WRand7FromRand5() {
        this.randomFiveOccurence = new int[ 5 ];
        this.randomSevenOccurence = new int[ 7 ];
    }

    public static void main(String[] args) {
        WRand7FromRand5 randGenerator = new WRand7FromRand5();
        System.out.println("Random Number of 7 (0-6) ");
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println(randGenerator.rand7());
        System.out.println("Please wait... while we calculate the rest 200000000 times");

        for (int i = 0; i < 200000000; i++) {
            randGenerator.rand7();
        }

        System.out.println("\nEach number's Occurence: \nNOTE: EVEN THOUGH THEY SEEM TO BE OF VARIED PROBABILITY IF WE TRY FOR LARGE DATASET WE WILL GET IT IN THE END");
        for (int i = 0; i < randGenerator.randomSevenOccurence.length; i++) {
            System.out.print(i + ": " + randGenerator.randomSevenOccurence[ i ] + "\n");
        }
    }

    private int rand5() {
        int rand = fairRandom(0, 4);
        this.randomFiveOccurence[ rand ]++;
        return rand;
    }

    private int fairRandom(int lower, int higher) {
        return lower + (int) (Math.random() * (higher - lower + 1));
    }

    private int rand7() {
        while (true) {
            int value = rand5() * 5 + rand5();
            if (value < 21) {
                int rand = value % 7;
                this.randomSevenOccurence[ rand ]++;
                return rand;
            }
        }
    }
}