package n.chapter.sortingAndSearching;

public class ESparseSearch {
    /* Sparse Search
    Question: Given a sorted array of strings that is interspersed with empty strings, write a method to find the
    location of a given string. */

    /* Algorithm Followed:
     * 1) This is a slight modification to the binary search. Here since the elements
     * could be empty strings, we are going to use recursive algorithm to narrow down
     * the array size when ever possible.
     * 2) We take a midpoint and check if it is equal to the element
     *         If so return the index
     *         If not check whether it is a empty space or a word
     *              If it is a empty space. then navigate to the right side until
     *              a word is reached. Check if the element is what we are searching for
     *                  If not then check whether it is after the target or before the target
     *                  and call the recursively with the reduced index positions
     * 3) Follow this operation until the value is found */

    public static void main(String[] args) {
        String[] inputArray = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String elementToSearch = "ball";
        /*
        String elementToSearch = "dad";
        String elementToSearch = "at";
        String elementToSearch = "mat";
        */
        int startIndex = 0;
        int endIndex = inputArray.length - 1;
        System.out.println(elementToSearch + " occurs at the index: " + sparseSearch(inputArray, elementToSearch, startIndex, endIndex));
    }

    private static int sparseSearch(String[] inputArray, String elementToSearch, int startIndex, int endIndex) {
        int index = (startIndex + endIndex) / 2;
        if (startIndex <= endIndex) {
            if (!inputArray[ index ].equalsIgnoreCase(elementToSearch) && !inputArray[ index ].isEmpty()) {
                //First String > Second String = Positive -> Search to the left of the array index
                //First String < Second String = Negative -> Search to the right of the array index
                if (inputArray[ index ].compareTo(elementToSearch) == -1) {
                    startIndex = index + 1;
                } else {
                    endIndex = index - 1;
                }
                return sparseSearch(inputArray, elementToSearch, startIndex, endIndex);
            } else if (inputArray[ index ].isEmpty()) {
                //Empty String - So go through the right side until the non-empty string is obtained
                int i = index + 1;
                while (i <= endIndex && inputArray[ i ].isEmpty()) {
                    i++;
                }
                if (!inputArray[ i ].isEmpty()) {
                    if (inputArray[ i ].compareTo(elementToSearch) == 0) {
                        return i;
                    } else if (inputArray[ i ].compareTo(elementToSearch) == -1) {
                        startIndex = i + 1;
                    } else {
                        //Here index - 1 is used since all the elements to the right of it are already tested and
                        // they are empty
                        endIndex = index - 1;
                    }
                    return sparseSearch(inputArray, elementToSearch, startIndex, endIndex);
                }
            } else {
                return index;
            }
        }
        return -1;
    }
}