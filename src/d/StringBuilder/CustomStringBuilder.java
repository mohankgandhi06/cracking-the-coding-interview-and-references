package d.StringBuilder;

public class CustomStringBuilder {

    public String[] wordsArray = new String[ 1 ];
    public int currentEntryCount = 0;

    public CustomStringBuilder() {

    }

    public CustomStringBuilder(String str) {
        if (this.isEmpty()) {
            this.wordsArray[ 0 ] = str;
            this.currentEntryCount++;
        }
    }

    public static void main(String[] args) {
        String[] array = new String[]{"Mohan ", "stays ", "in ", "PG ", "in ", "Chennai"};
        String[] array2 = new String[]{"Charan ", "stays ", "in ", "Home ", "in ", "Erode"};
        CustomStringBuilder csb = new CustomStringBuilder("First Sentence: ");
        csb.build(array);
        csb.append(" Added Additionally");
        System.out.println("OUTPUT 1: "+csb.toStringConversion());
        CustomStringBuilder csbEmptyConstructor = new CustomStringBuilder();
        csbEmptyConstructor.build(array2);
        System.out.println("OUTPUT 2: "+csbEmptyConstructor.toStringConversion());
    }

    private void build(String[] string) {
        for (int i = 0; i < string.length; i++) {
            this.append(string[ i ]);
        }
    }

    public void append(String str) {
        if (this.isFull()) {
            increaseSize();
        }
        this.wordsArray[ this.currentEntryCount ] = str;
        this.currentEntryCount++;

    }

    public String toStringConversion() {
        String outputString = new String();
        for (int j = 0; j < currentEntryCount; j++) {
            outputString = outputString + this.wordsArray[ j ];
        }
        return outputString;
    }

    public boolean isFull() {
        if (currentEntryCount == this.wordsArray.length) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return currentEntryCount == 0;
    }

    private void increaseSize() {
        int newLength = this.wordsArray.length + this.wordsArray.length;
        String[] newArray = new String[ newLength ];
        for (int i = 0; i < this.wordsArray.length; i++) {
            newArray[ i ] = this.wordsArray[ i ];
        }
        this.setWordsArray(newArray);
    }

    private void setWordsArray(String[] wordsArray) {
        this.wordsArray = new String[ wordsArray.length ];
        this.wordsArray = wordsArray;
    }
}