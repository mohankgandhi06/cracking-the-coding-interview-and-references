package r.chapter.hardDifficulty;

import java.util.*;

public class CRandomSet {
    public static void main(String[] args) {
        int[] array = new int[]{
                2, 4, 6, 1, 3, 5, 7, 8, 11, 9, 10, 15, 16, 13
        };
        int m = 5;
        CRandomSet game = new CRandomSet();
        int[] result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);

        result = game.generateSubset(array, m);
        game.showSubset(result);
    }

    private int[] generateSubset(int[] array, int m) {
        int[] result = new int[ m ];
        for (int i = 0; i < m; i++) {
            result[ i ] = array[ i ];
        }
        for (int j = m; j < array.length; j++) {
            int k = fairRandom(0, j);
            if (k < m) {//Like Tossing a coin it can be one or the other 50-50 probability
                result[ k ] = array[ j ];
            }
        }
        return result;
    }

    private int fairRandom(int lower, int higher) {
        return lower + (int) (Math.random() * (higher - lower + 1));
    }

    private void showSubset(int[] result) {
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /* Even though this may seem to be fair when the list is iterated
     * we don't have the fair choice. like the head or tail which has a
     * fair chance for both */
    private int[] generateSubsetInitial(int[] array, int m) {
        List<Integer> list = new LinkedList<>();
        for (int i : array) {
            ((LinkedList<Integer>) list).addLast(i);
        }

        Iterator i = list.iterator();
        System.out.println("**************** Linked List ***************");
        while (i.hasNext()) {
            System.out.print(i.next() + " ");
        }
        System.out.println();

        List<Integer> result = new ArrayList<>();
        generate(list, m, result);
        for (int j : result) {
            System.out.print(j + " ");
        }
        System.out.println();
        return null;
    }

    private List<Integer> generate(List<Integer> list, int m, List<Integer> result) {
        if (m == 0) return result;
        Random random = new Random();
        int x = random.nextInt(list.size());
        result.add(list.get(x));
        list.remove(x);
        return generate(list, m - 1, result);
    }
}