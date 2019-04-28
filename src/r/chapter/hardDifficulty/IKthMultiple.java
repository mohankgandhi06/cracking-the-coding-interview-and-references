package r.chapter.hardDifficulty;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class IKthMultiple {
    public static void main(String[] args) {
        IKthMultiple game = new IKthMultiple();
        int kth = 25;
        System.out.println(kth + " element: " + game.getLeastKthMultiple(kth)+"\n");

        kth = 125;
        System.out.println(kth + " element: " + game.getLeastKthMultiple(kth)+"\n");

        kth = 300;
        System.out.println(kth + " element: " + game.getLeastKthMultiple(kth)+"\n");
    }

    private int getLeastKthMultiple(int kth) {
        LinkedList<Integer> linkedList = new LinkedList();
        HashMap<Integer, Boolean> integerBooleanHashMap = new HashMap<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        integerBooleanHashMap.put(1, true);
        linkedList.addLast(1);
        integerBooleanHashMap.put(3, true);
        linkedList.addLast(3);
        integerBooleanHashMap.put(5, true);
        linkedList.addLast(5);
        integerBooleanHashMap.put(7, true);
        linkedList.addLast(7);
        int k = 4;
        if (kth < k && k >= 0) {
            return linkedList.get(k - 1);
        }
        priorityQueue.add(3);
        priorityQueue.add(5);
        priorityQueue.add(7);
        while (k < kth) {
            int current = priorityQueue.poll();
            int threeX = current * 3;
            int fiveX = current * 5;
            int sevenX = current * 7;
            if (!integerBooleanHashMap.containsKey(threeX)) {
                integerBooleanHashMap.put(threeX, true);
                priorityQueue.add(threeX);
                linkedList.addLast(threeX);
                k++;
            }
            if (!integerBooleanHashMap.containsKey(fiveX) && k < kth) {
                integerBooleanHashMap.put(fiveX, true);
                priorityQueue.add(fiveX);
                linkedList.addLast(fiveX);
                k++;
            }
            if (!integerBooleanHashMap.containsKey(sevenX) && k < kth) {
                int l = 0;
                integerBooleanHashMap.put(sevenX, true);
                priorityQueue.add(sevenX);
                linkedList.addLast(sevenX);
                k++;
            }
        }
        /*System.out.println("List of factors of 3, 5, 7 before sorting: ");
        for (Integer i : linkedList) {
            System.out.print(i + " ");
        }
        System.out.println();*/
        linkedList.sort(Integer::compareTo);
        /* There might be some values which are over the actual order */
        int largest = linkedList.get(kth - 1);
        while (!priorityQueue.isEmpty()) {
            int current = priorityQueue.poll();
            int threeX = current * 3;
            int fiveX = current * 5;
            int sevenX = current * 7;
            if (threeX > largest && fiveX > largest && sevenX > largest) {
                break;
            } else {
                if (!integerBooleanHashMap.containsKey(threeX) && threeX <= largest) {
                    insertInOrderedPosition(linkedList, threeX);
                }
                if (!integerBooleanHashMap.containsKey(fiveX) && fiveX <= largest) {
                    insertInOrderedPosition(linkedList, fiveX);
                }
                if (!integerBooleanHashMap.containsKey(sevenX) && sevenX <= largest) {
                    insertInOrderedPosition(linkedList, sevenX);
                }
            }
        }
        System.out.println("List of factors of 3, 5, 7");
        for (Integer i : linkedList) {
            System.out.print(i + " ");
        }
        System.out.println();
        return linkedList.get(kth - 1);
    }

    private void insertInOrderedPosition(LinkedList<Integer> linkedList, int element) {
        for (int i = linkedList.size() - 1; i >= 0; i--) {
            if (linkedList.get(i) <= element) {
                linkedList.add(i + 1, element);
                linkedList.removeLast();
                break;
            }
        }
    }
}