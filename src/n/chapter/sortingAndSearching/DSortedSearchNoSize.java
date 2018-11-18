package n.chapter.sortingAndSearching;

public class DSortedSearchNoSize {
    /* Sorted Search, No Size
    Question: You are given an array-like data structure Listy which lacks a size method. It does, however
    have an elementAt(i) method that returns the element at index i in 0(1) time. If i is beyond the bounds
    of the data structure, it returns -1.(For this reason, the data structure only supports positive integers)
    Given a Listy which contains sorted, positive integers, find the index at which an element x occurs. If x occurs
    multiple times, you may return any index. */

    public static void main(String[] args) {
        Listy list = new Listy(new int[]{2, 3, 4, 7, 8, 10, 15, 15, 18, 19, 24, 27, 28, 30});
        int elementToFind = 15;
        System.out.println("Element is found at index: (if not found then -1 is returned)");
        System.out.println(performSearch(list, elementToFind));
    }

    private static int performSearch(Listy list, int elementToFind) {
        int i = 0;
        while (list.elementAt(i) != -1 && list.elementAt(i) != elementToFind && list.elementAt(i) < elementToFind) {
            i = i + 5;
        }
        if (list.elementAt(i) == -1 || list.elementAt(i) > elementToFind) {//Take a step (4 values back) and iterate each till -1 is reached
            int j = (i - 5) + 1;
            while (list.elementAt(j) != -1 && list.elementAt(j) != elementToFind) {
                j++;
            }
            if (list.elementAt(j) == elementToFind) {
                return j;
            }
        } else if (list.elementAt(i) == elementToFind) {
            return i;
        }
        return -1;
    }
}

class Listy {
    //Assume that the length variable of the array is not available
    private int[] list;

    public Listy(int[] array) {
        this.list = array;
    }

    public int elementAt(int i) {
        if (i < list.length) {
            return list[ i ];
        } else {
            return -1;
        }
    }
}