package f.chapter.linkedList;

public class ESumLists {
    public static void main(String[] args) {
        LinkedList numberOne = new LinkedList(new int[]{1, 7, 1, 2});
        LinkedList numberTwo = new LinkedList(new int[]{5, 9, 2});

        LinkedList result = add(numberOne, numberTwo);
        Node node = result.head;
        while (node.getNext() != null){
            System.out.println(""+node.getNext().getValue()+" -> ");
            node = node.getNext();
        }
    }

    public static LinkedList add(LinkedList numberOne, LinkedList numberTwo) {
        long tenPower = 0;
        double one = 0;
        double two = 0;

        Node headOne = numberOne.head.getNext();
        Node headTwo = numberTwo.head.getNext();

        while (headOne != null || headTwo != null) {
            if (headOne != null) {
                one = one + (headOne.getValue() * Math.pow(10, tenPower));
                headOne = headOne.getNext();
            }
            if (headTwo != null) {
                two = two + (headTwo.getValue() * Math.pow(10, tenPower));
                headTwo = headTwo.getNext();
            }
            tenPower++;
        }

        int result = (int) Math.round(one+two);
        LinkedList list = new LinkedList();
        while (result > 0) {
            int digit = result % 10;
            list.insert(digit, list.head);
            result = result / 10;
        }
        return list;
    }
}
