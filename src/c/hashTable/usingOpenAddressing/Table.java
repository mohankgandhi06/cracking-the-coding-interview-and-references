package c.hashTable.usingOpenAddressing;

public class Table {
    public int TABLE_SIZE;
    public int CURRENT_ENTRIES_COUNT;
    public KeyValue[] entries;

    public Table(String arraySize){
        this.TABLE_SIZE = Integer.parseInt(arraySize);
        this.CURRENT_ENTRIES_COUNT = 0;
        entries = new KeyValue[this.TABLE_SIZE];
    }

}
