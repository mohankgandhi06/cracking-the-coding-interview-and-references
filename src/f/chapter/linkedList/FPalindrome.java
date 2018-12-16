package f.chapter.linkedList;

import java.util.Stack;

public class FPalindrome {
    /* Question:
     Palindrome: Implement a function to check if the linked list is a palindrome */

    public static int inputLength = 0;

    public static void main(String[] args) {
        //Modify your input here
        //String inputString = "madam";
        //String inputString = "olive";
        /*String inputString = "loiol";*/
        //String inputString = "dassuttttttussad";
        String inputString = "dassasd";
        inputLength = inputString.length();
        /*
        LinkedStringList list = new LinkedStringList(inputString.toLowerCase());
        LinkedNode node = list.getHead().getNext();
        while (node != null) {
            System.out.println("- " + node.getData());
            node = node.getNext();
        }
        System.out.println("Is the List " + inputString + " a Palindrome: " + isPalindrome(list.getHead()));
        */
        LinkedStringList list = new LinkedStringList(inputString.toLowerCase(), "reverseAndCompare");
        LinkedNode node = list.getHead();
        while (node != null) {
            System.out.println("- " + node.getData());
            node = node.getNext();
        }
        /*System.out.println("Is the List " + inputString + " a Palindrome: " + isPalindromeReverseAndCompare(list.getHead()));*/
        /*System.out.println("Is the List " + inputString + " a Palindrome: " + isPalindromeIterative(list.getHead()));*/
        System.out.println("Is the List " + inputString + " a Palindrome: " + isPalindromeRecursive(list.getHead()));
    }

    /* Optimal Implementations */
    /* Reverse and Compare */
    private static boolean isPalindromeReverseAndCompare(LinkedNode head) {
        LinkedNode reverse = reverse(head);
        return isPalindromeUsingReverseAndCompare(head, reverse);
    }

    private static LinkedNode reverse(LinkedNode node) {
        LinkedNode head = null;
        while (node != null) {
            LinkedNode n = new LinkedNode(node.getData());
            n.setNext(head);
            head = n;
            node = node.getNext();
        }
        return head;
    }

    private static boolean isPalindromeUsingReverseAndCompare(LinkedNode head, LinkedNode reverse) {
        while (head != null && reverse != null) {
            if (Character.valueOf(head.getData().charAt(0)) != Character.valueOf(reverse.getData().charAt(0))) {
                return false;
            }
            head = head.getNext();
            reverse = reverse.getNext();
        }
        return head == null && reverse == null;
    }

    /* Iterative */
    private static boolean isPalindromeIterative(LinkedNode head) {
        /* In the above method we are iterating through the whole linked list. This can be reduced to half of the length.
         * If we know the size of it then we can create a stack with only half the length and compare the other half directly
         * if not we can use the runner technique one will jump two steps than the other. Once the fast reach the end
         * we start with the stack comparing the rest of the linked list */
        LinkedNode slow = head;
        LinkedNode fast = head;
        Stack<Character> stack = new Stack<>();
        while (fast != null && fast.getNext() != null) {
            stack.push(Character.valueOf(slow.getData().charAt(0)));
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        if (fast != null) {//Skipping the middle term for odd number of elements
            slow = slow.getNext();
        }
        while (slow != null) {
            if (slow.getData().charAt(0) != stack.pop()) {
                return false;
            }
            slow = slow.getNext();
        }
        return true;
    }

    /* Recursive */
    private static boolean isPalindromeRecursive(LinkedNode head) {
        int length = getLength(head);
        Result result = isPalindromeRecursiveCall(head, length);
        return result.isPalindrome;
    }

    private static int getLength(LinkedNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.getNext();
        }
        return count;
    }

    private static Result isPalindromeRecursiveCall(LinkedNode node, int length) {
        if (node == null || length <= 0) {
            return new Result(node, true);
        } else if (length == 1) {
            return new Result(node.getNext(), true);
        }

        Result result = isPalindromeRecursiveCall(node.getNext(), length - 2);

        if (!result.isPalindrome || result.node == null) {
            return result;
        }

        result.isPalindrome = Character.valueOf(node.getData().charAt(0)) == Character.valueOf(result.node.getData().charAt(0));
        result.node = result.node.getNext();

        return result;
    }

    /* Earlier Implementations */
    public static boolean isPalindrome(LinkedNode head) {
        /* Using Hashtable we are iterating through the linked list and saving the location
         * and the value until we iterate all the elements. Once we have gone through the
         * list each hashtable it iterated in turn to determine if the position is proper in order to
         * be a palindrome. */
        HashTable hashTable = new HashTable("26");
        hashTable.inputLength = inputLength;
        LinkedNode node = head.getNext();
        int index = 0;
        int position = 0;
        //STEP 1: Create a hash table with the linked list
        while (node != null) {
            index = hashFunction(node.getData());
            if (index < 0) {
                index = node.getData().hashCode() % 26;
            }
            TableNode tableNode = hashTable.nodes[ index ];
            while (tableNode.getNext() != null) {
                tableNode = tableNode.getNext();
            }
            hashTable.nodes[ index ].setPosition(hashTable.nodes[ index ].getPosition() + 1);
            tableNode.setNext(new TableNode(position, node.getData()));
            position++;
            node = node.getNext();
        }
        //STEP Intermediary: Just to take a look at the table which has been created
        showHashTable(hashTable);

        //Below oddCount is a variable to check the overall odd characters. If it exceeds 1 then it cannot be a palindrome
        // ex: madeam -> 'd' and 'e' are odd characters that are occurring which makes the string not a palindrome
        int oddCount = 0;
        for (int i = 0; i < hashTable.nodes.length; i++) {
            //STEP 2: Iterate through each hashtable
            TableNode iterateNode = hashTable.nodes[ i ];
            if (iterateNode.getNext() != null) {
                //Variable to hold the middle variables position. this is because the middle variable alone be occurring
                // in odd count ex: lol here 'o' alone can occur as odd, if foo 'f' occurs in any other position than
                // centre then it cannot be considered a palindrome
                int middleVariablesPostition = -1;
                int positionCount = -1;
                int counter = 0;
                //total variable is used to add up the characters position and take the modulo finally with the (total length of the string - 1)
                // If it returns 0 then it is fine, else it means that the string is not a palindrome
                // ex: liril - here the i occurs in position 1 and 3
                // if we total it and take modulo with the total length (5) - 1 i.e. 4 we get 0
                // Verdict: May be a palindrome if everything satisfies
                // ex: goofg - here the g occurs correctly but if we take the o the position is 1 and 2.
                // so modulo of total length -1 (4) and the characters position is not equal to 0.
                // Verdict: Not a palindrome
                int total = 0;
                //In the below if loop iterateNode is the head node which will contain the count of occurrence
                // of the character. So we are checking if the character has occurred odd or even no. of times
                // If it is odd then we are taking the position in which it should be occurring
                if (iterateNode.getPosition() % 2 == 1) {
                    oddCount++;
                    positionCount = iterateNode.getPosition() / 2;
                }
                if (oddCount > 1) {
                    return false;
                }
                while (iterateNode.getNext() != null) {
                    total = total + iterateNode.getNext().getPosition();
                    if (counter == positionCount) {
                        middleVariablesPostition = iterateNode.getNext().getPosition();
                    }
                    counter++;
                    iterateNode = iterateNode.getNext();
                }
                if (middleVariablesPostition != -1) {
                    if (total % (hashTable.inputLength - 1) != 0 && total % (hashTable.inputLength - 1) != middleVariablesPostition) {
                        return false;
                    }
                } else {
                    if (total % (hashTable.inputLength - 1) != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static void showHashTable(HashTable hashTable) {
        for (int i = 0; i < hashTable.nodes.length; i++) {
            TableNode iterateNode = hashTable.nodes[ i ];
            while (iterateNode.getNext() != null) {
                System.out.print("Count: " + hashTable.nodes[ i ].getPosition() + " Table Index: " + i + ": Value -> " + iterateNode.getNext().getValue() + " Position -> " + iterateNode.getNext().getPosition());
                System.out.println();
                iterateNode = iterateNode.getNext();
            }
        }
    }

    public static int hashFunction(String data) {
        return (data.hashCode() % 1009) % 26;
    }
}

class LinkedStringList {
    private LinkedNode head;

    public LinkedStringList(String inputString) {
        this.head = new LinkedNode(null);
        for (int i = 0; i < inputString.length(); i++) {
            insert(String.valueOf(inputString.charAt(i)), this.head);
        }
    }

    public LinkedStringList(String inputString, String mode) {
        if (mode.equalsIgnoreCase("reverseAndCompare")) {
            for (int i = 0; i < inputString.length(); i++) {
                if (this.head == null) {
                    String datum = String.valueOf(inputString.charAt(i));
                    head = new LinkedNode(datum);
                    continue;
                }
                insert(String.valueOf(inputString.charAt(i)), this.head, mode);
            }
        }
    }

    public void insert(String data, LinkedNode head) {
        LinkedNode node = head;
        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(new LinkedNode(data));
    }

    public void insert(String data, LinkedNode head, String mode) {
        if (mode.equalsIgnoreCase("reverseAndCompare")) {
            LinkedNode node = head;
            while (node.getNext() != null) {
                node = node.getNext();
            }
            node.setNext(new LinkedNode(data));
        }
    }

    public LinkedNode getHead() {
        return head;
    }
}

class LinkedNode {
    private String data;
    private LinkedNode next;

    public LinkedNode() {


    }

    public LinkedNode(String data) {
        this.data = data;
        this.next = null;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public LinkedNode getNext() {
        return next;
    }

    public void setNext(LinkedNode next) {
        this.next = next;
    }
}

class HashTable {
    public int TABLE_SIZE;
    public TableNode[] nodes;
    public int inputLength;

    public HashTable(String size) {
        this.TABLE_SIZE = Integer.parseInt(size);
        this.nodes = new TableNode[ TABLE_SIZE ];
        //This below lines of code is not needed. Just for the clarity
        for (int i = 0; i < TABLE_SIZE; i++) {
            nodes[ i ] = new TableNode(0, "-9999");
        }
    }
}

class TableNode {
    private int position;
    private String value;
    private TableNode next;

    public TableNode(int position, String value) {
        this.position = position;
        this.value = value;
        this.next = null;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TableNode getNext() {
        return next;
    }

    public void setNext(TableNode next) {
        this.next = next;
    }
}

class Result {
    public LinkedNode node;
    public boolean isPalindrome;

    public Result(LinkedNode node, boolean isPalindrome) {
        this.node = node;
        this.isPalindrome = isPalindrome;
    }
}