package e.chapter.stringArray;

public class ZArrayList {
    /* List should create a dynamic array such that it can accept data of certain type and dynamically increase the size */
    /* Algorithm */
    /* Initially Create an Array of Objects with length 1 */
    /* List can do the following functions:
       - Insert (Check if the array is full ? increase the length by a factor or 2 and copy over)
       - Update
       - Get
       - Delete */
    private Object[] list;
    private int size;

    public ZArrayList() {
        this.list = new Object[ 1 ];
        this.size = 0;
    }

    public static void main(String[] args) {
        ZArrayList arrayList = new ZArrayList();
        arrayList.insert("John");
        arrayList.insert("Doe");
        arrayList.insert("Jennifer");
        arrayList.insert("Steven");
        arrayList.update("Frank", 1);
        System.out.println("Got the value: " + arrayList.get(1));
        arrayList.delete("John");
        System.out.println("Got the value: " + arrayList.get(2));
    }

    private void insert(Object data) {
        if (this.isFull()) {
            increaseSize();
        }
        this.list[ size ] = data;
        this.size++;
        System.out.println("Inserted: " + data);
    }

    private void update(Object data, int location) {
        if (location >= this.list.length) {
            System.out.println("Location is unavailable");
            return;
        }
        if (this.list[ location ] == null) {
            System.out.println("No value present in that location. So inserting the new value");
        }
        Object oldValue = this.list[ location ];
        this.list[ location ] = data;
        System.out.println("Updated '" + oldValue + "' to: " + this.list[ location ]);
    }

    private Object get(int location) {
        if (location >= size) {
            System.out.println("Location is unavailable");
            return null;
        }
        return this.list[ location ];
    }

    private void delete(Object data) {
        //Find if the element is actually present in the array
        //Delete if present
        //Display error if not present
        int location = -1;
        for (int i = 0; i < this.list.length; i++) {
            if (this.list[ i ] == data) {
                location = i;
                break;
            }
        }
        if (location == -1) {
            System.out.println("Element not found");
        } else {
            for (int j = location; j < this.list.length - 1; j++) {
                this.list[ j ] = this.list[ j + 1 ];
            }
            System.out.println("Removed: " + data + " from location: " + location);
            this.list[ this.list.length - 1 ] = null;
            this.size--;
        }
    }

    private boolean isFull() {
        return this.list.length == this.size;
    }

    private void increaseSize() {
        //Determine the size to be a factor or 2
        int futureSize = this.list.length * 2;
        //Copying over the values to the new array
        Object[] newList = new Object[ futureSize ];
        for (int i = 0; i < this.list.length; i++) {
            newList[ i ] = this.list[ i ];
        }
        this.list = new Object[ futureSize ];
        this.list = newList;
    }
}