package e.chapter.stringArray;

public class DPalindromePermutation {
    public static void main(String[] args) {
        System.out.println("isPermutationAPalindrome: " + isPermutationAPalindrome("toc a ciiot"));
    }

    public static boolean isPermutationAPalindrome(String inputString) {
        String input = inputString.toLowerCase();
        PalindromeTable table = new PalindromeTable("26");
        int oddCount = 0;
        for (int i = 0; i < input.length(); i++) {
            int index = hashFunction(input.charAt(i));
            table.nodes[ index ].value++;
        }
        for (PalindromeNode node : table.nodes) {
            if (node.value % 2 == 1) {
                oddCount++;
                if (oddCount > 1) {
                    System.out.println("Palindrome Permutation Not Applicable since odd count is greater than 1");
                    return false;
                }
            }
        }
        return true;
    }

    private static int hashFunction(char character) {
        return character % 26;
    }
}

class PalindromeTable {
    public static int TABLE_SIZE;
    public PalindromeNode[] nodes;

    public PalindromeTable(String size) {
        this.TABLE_SIZE = Integer.parseInt(size);
        this.nodes = new PalindromeNode[ TABLE_SIZE ];
        for (int i = 0; i < TABLE_SIZE; i++) {
            nodes[ i ] = new PalindromeNode(0);
        }
    }
}

class PalindromeNode {
    public int value;
    public PalindromeNode next;

    public PalindromeNode() {

    }

    public PalindromeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public PalindromeNode getNext() {
        return next;
    }

    public void setNext(PalindromeNode next) {
        this.next = next;
    }
}