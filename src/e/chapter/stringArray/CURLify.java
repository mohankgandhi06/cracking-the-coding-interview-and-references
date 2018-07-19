package e.chapter.stringArray;

public class CURLify {
    public static void main(String[] args) {
        urlify("Mr John Smit h      ", 14);
    }

    public static void urlify(String stringInput, int length) {
        char[] stringInputArray = stringInput.toCharArray();
        int variableLength = length;
        char temp1;
        char temp2;
        char temp3;
        for (int i = 0; i < variableLength; i++) {
            if (stringInputArray[ i ] == ' ') {
                stringInputArray[ i ] = '%';
                variableLength = variableLength + 2;
                temp1 = stringInputArray[ i + 1 ];
                stringInputArray[ i + 1 ] = '2';
                temp2 = stringInputArray[ i + 2 ];
                stringInputArray[ i + 2 ] = '0';
                for (int j = i + 3; j < variableLength; j++) {
                    if ((j - i) % 2 == 1) {
                        //TODO swap temp 3 with temp 1
                        temp3 = stringInputArray[ j ];
                        stringInputArray[ j ] = temp1;
                        temp1 = temp3;
                    } else {
                        //TODO swap temp 3 with temp 2
                        temp3 = stringInputArray[ j ];
                        stringInputArray[ j ] = temp2;
                        temp2 = temp3;
                    }
                }
            }
        }
        for (int k = 0; k < stringInputArray.length; k++) {
            System.out.println(k+" : "+stringInputArray[k]);
        }
    }
}
