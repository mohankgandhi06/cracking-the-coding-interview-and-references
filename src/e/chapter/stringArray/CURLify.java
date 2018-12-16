package e.chapter.stringArray;

public class CURLify {
    /* Question: Write a method to replace all spaces in a string with '%20'.
    You may assume that the string has sufficient space at the end to hold the additional
    characters, and that you are given the "true" length of the string.
    (Note: if implementing in Java, please use a character array so that you
    can perform this operation in place)*/
    public static void main(String[] args) {
        //urlify("Mr John Smit h      ", 14);
        urlifyInReverse("MrJohnSmith          ".toCharArray(), 11);
    }

    /* Optimal Implementation */
    private static void urlifyInReverse(char[] string, int trueLength) {
        int spaceCount = 0;
        int index = 0;
        for (int i = 0; i < trueLength; i++) {
            if (string[ i ] == ' ') {
                spaceCount++;
            }
        }
        //Index variable is to determine from where we need to start in reverse
        index = trueLength + (spaceCount * 2);
        if (trueLength < string.length) {
            string[ trueLength ] = '\0';
        }
        for (int j = trueLength - 1; j >= 0; j--) {
            //Here we are checking if the character is a space.
            //If so then take the current index which has taken into account the total length
            if (string[ j ] == ' ') {
                string[ index - 1 ] = '0';
                string[ index - 2 ] = '2';
                string[ index - 3 ] = '%';
                index = index - 3;
            } else {
                string[ index - 1 ] = string[ j ];
                index--;
            }
        }
    }

    /* Earlier Implementations */
    public static void urlify(String stringInput, int length) {
        /* Since this is using the forward approach to calculate and move the characters
         * around it is a quite complex process were we could end up coding wrongly.
         * Whenever there is a space we are moving everything after it each time. */
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
            System.out.println(k + " : " + stringInputArray[ k ]);
        }
    }
}