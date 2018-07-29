package f.chapter.linkedList;

public class FPalindrome {
    /* Question:
     Palindrome: Implement a function to check if the linked list is a palindrome */

    public static int inputLength = 0;

    public static void main(String[] args) {
        //Modify your input here
        //String inputString = "madam";
        //String inputString = "olive";
        String inputString = "loiol";
        //String inputString = "dassuttttttussad";
        //String inputString = "dassutttttttussad";
        inputLength = inputString.length();
        LinkedStringList list = new LinkedStringList(inputString.toLowerCase());
        LinkedNode node = list.getHead().getNext();
        while (node != null) {
            System.out.println("- " + node.getData());
            node = node.getNext();
        }
        System.out.println("Is the List " + inputString + " a Palindrome: " + isPalindrome(list.getHead()));
    }

    public static boolean isPalindrome(LinkedNode head) {
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

    public void insert(String data, LinkedNode head) {
        LinkedNode node = head;
        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(new LinkedNode(data));
    }

    public LinkedNode getHead() {
        return head;
    }
}

class LinkedNode {
    private String data;
    private LinkedNode next;

    public LinkedNode(String data) {
        this.data = data;
        this.next = null;
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