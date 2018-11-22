package n.chapter.sortingAndSearching;

public class KPeaksAndValleys {

    /* Peaks and Valleys
     * Question: In an array of integers, a "peak" is an element which is greater tan or equal to the adjacent integers
     * and a "valley" is an element which is less than or equal to the adjacent integers. For example, in the array
     * {5, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {5, 2} are valley. Given an array of integers, sort the array into
     * an alternating sequence of peaks and valleys.
     * EXAMPLE
     * Input {5, 3, 1, 2, 3}
     * Output {5, 1, 3, 2, 3}*/

    public static void main(String[] args) {
        /*int[] array = new int[]{5, 3, 1, 2, 3};*/
        /*int[] array = new int[]{5, 5, 3, 1, 2, 6, 1};*/
        /*int[] array = new int[]{5, 3, 1, 2, 4, 1, 3};*/
        /*int[] array = new int[]{5, 5, 3, 2, 1, 1, 1};*/
        /*int[] array = new int[]{5, 8, 6, 2, 3, 4, 6};*/
        int[] array = new int[]{1, 1, 5, 8, 6, 2, 3, 4, 6};
        peaksAndValleys(array);
    }

    private static void peaksAndValleys(int[] array) {
        Element[] elements = new Element[ array.length ];
        int max = 0;
        int min = 0;
        for (int i = 0; i < array.length; i++) {
            elements[ i ] = new Element(array[ i ]);
            if (array[ i ] > max) {
                max = array[ i ];
            }
            if (array[ i ] < min) {
                min = array[ i ];
            }
        }
        int j = 0;
        while (j < elements.length) {
            if (j == 0) {
                if (elements[ j ].getValue() >= elements[ j + 1 ].getValue()) {
                    if (elements[ j ].getValue() == elements[ j + 1 ].getValue()) {
                        //These inner if and else logic is needed to find how close are we to the peak or valley since
                        // we need to decide which we should expect next
                        int maxDiff = (max - elements[ j ].getValue());
                        int minDiff = (min - elements[ j ].getValue());
                        if (maxDiff < minDiff || maxDiff == 0) {
                            elements[ j ].setPeakNeeded(false);
                            elements[ j + 1 ].setPeakNeeded(false);
                        } else if (maxDiff > minDiff || minDiff == 0) {
                            elements[ j ].setPeakNeeded(true);
                            elements[ j + 1 ].setPeakNeeded(true);
                        }
                    } else {
                        elements[ j ].setPeakNeeded(false);
                        elements[ j + 1 ].setPeakNeeded(true);
                    }
                } else if (elements[ j ].getValue() < elements[ j + 1 ].getValue()) {
                    elements[ j ].setPeakNeeded(true);
                    elements[ j + 1 ].setPeakNeeded(false);
                }
            } else {
                if ((!(j + 1 >= elements.length) && elements[ j ].isPeakNeeded() && elements[ j ].getValue() < elements[ j + 1 ].getValue())
                        || (!(j + 1 >= elements.length) && !elements[ j ].isPeakNeeded() && elements[ j ].getValue() > elements[ j + 1 ].getValue())) {
                    //Element stays were it is and its flag is set alternate to the current
                    elements[ j + 1 ].setPeakNeeded(!elements[ j ].isPeakNeeded());
                } else {
                    if ((!(j + 1 >= elements.length) && elements[ j ].isPeakNeeded() && elements[ j ].getValue() > elements[ j + 1 ].getValue())
                            || (!(j + 1 >= elements.length) && !elements[ j ].isPeakNeeded() && elements[ j ].getValue() < elements[ j + 1 ].getValue())) {
                        //Swap the elements and set the flag's properly
                        Element temp = elements[ j ];
                        elements[ j ] = elements[ j + 1 ];
                        elements[ j + 1 ] = temp;
                        elements[ j ].setPeakNeeded(elements[ j + 1 ].isPeakNeeded());
                        elements[ j + 1 ].setPeakNeeded(!elements[ j + 1 ].isPeakNeeded());
                    } else if (!(j + 1 >= elements.length)) {
                        int k = j + 1;
                        if (elements[ j ].isPeakNeeded()) {
                            while (!(k >= elements.length) && elements[ j ].getValue() < elements[ k ].getValue()) {
                                k++;
                            }
                            if ((k >= elements.length)) {
                                if (j < elements.length) {
                                    System.out.println("Array is incomplete. It cannot be obtained");
                                }
                            } else if (elements[ j ].getValue() > elements[ k ].getValue()) {
                                elements[ j + 1 ] = elements[ k ];
                                elements[ j + 1 ].setPeakNeeded(!elements[ j ].isPeakNeeded());
                            }
                        } else {
                            while (!(k >= elements.length) && elements[ j ].getValue() > elements[ k ].getValue()) {
                                k++;
                            }
                            if ((k >= elements.length)) {
                                if (j < elements.length) {
                                    System.out.println("Array is incomplete. It cannot be obtained");
                                }
                            } else if (elements[ j ].getValue() < elements[ k ].getValue()) {
                                elements[ j + 1 ] = elements[ k ];
                                elements[ j + 1 ].setPeakNeeded(elements[ j ].isPeakNeeded());
                            }
                        }
                    }
                }
            }
            j++;
        }

        for (Element e : elements) {
            System.out.println("Element : " + e.getValue());
        }
    }
}

class Element {
    private int value;
    private boolean peakNeeded;

    public Element(int value) {
        this.value = value;
        this.peakNeeded = false;
    }

    /*public Element(int value, boolean peakNeeded) {
        this.value = value;
        this.peakNeeded = peakNeeded;
    }*/

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isPeakNeeded() {
        return peakNeeded;
    }

    public void setPeakNeeded(boolean peakNeeded) {
        this.peakNeeded = peakNeeded;
    }
}