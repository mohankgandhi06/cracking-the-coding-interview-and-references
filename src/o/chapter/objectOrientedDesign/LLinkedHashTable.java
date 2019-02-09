package o.chapter.objectOrientedDesign;

public class LLinkedHashTable {

    /* Question: Design and Implement a hash table which uses chaining (linked lists) to handle collisions */

    public static void main(String[] args) {

    }

    class HashTable {
        public Entry[] entries;
        public final int size;

        public HashTable(int hashtableSize) {
            this.entries = new Entry[ hashtableSize ];
            this.size = hashtableSize;
        }

        private void addEntry() {
            /* Calculate the hash value and then go the specific index
             * Check if already entry is available
             * If not then add a new entry then go to the node and traverse until when the
             * next node is null and then insert the new entry */
        }

        private void findEntry() {
            /* Calculate the hash value and then go the specific index
             * Check in the Node if the value is available and traverse until the end
             * If not present then it will not be present anywhere else. We can be sure */
        }

        private int hash() {
            /* For getting the hash value it has to be as unique as possible to avoid more collision
             * So if we are going to use the prime numbers to be the divisor then the same value will not be
             * repeated often. We need to consider the hash table size and then decide a nearby prime number
             * and keep it as the table size if possible */
            return -1;
        }
    }

    class Entry {
        public int key;
        public Node value;

        public Entry(int key, Node value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public Node getValue() {
            return value;
        }

        public void setValue(Node value) {
            this.value = value;
        }
    }

    class Node {
        public Object data;
        public Node next;

        public Node(Object data) {
            this.data = data;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}