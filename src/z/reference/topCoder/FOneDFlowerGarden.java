package z.reference.topCoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class FOneDFlowerGarden {

    private int[] height;
    private int[] bloom;
    private int[] wilt;
    private LinkedList<Integer> order;
    private Flower[] flowers;
    private LinkedList<Flower> flowersList;

    public FOneDFlowerGarden(int[] height, int[] bloom, int[] wilt) {
        this.height = height;
        this.bloom = bloom;
        this.wilt = wilt;
        this.order = new LinkedList<>();

        /* Adding the three different int array into a single object Flower */
        this.flowers = new Flower[ this.height.length ];
        for (int i = 0; i < this.height.length; i++) {
            this.flowers[ i ] = new Flower(this.height[ i ], this.bloom[ i ], this.wilt[ i ]);
        }

        this.flowersList = new LinkedList<>();
    }

    public static void main(String[] args) {
        /* https://github.com/irpap/TopCoder/blob/master/FlowerGarden/src/FlowerGarden.java */

        /*int[] height = {5, 4, 3, 2, 1};
        int[] bloom = {1, 1, 1, 1, 1};
        int[] wilt = {365, 365, 365, 365, 365};*/
        /*{ 1,  2,  3,  4,  5 }*/

        /*int[] height = {5, 4, 3, 2, 1};
        int[] bloom = {1, 5, 10, 15, 20};
        int[] wilt = {4, 9, 14, 19, 24};*/
        /*{ 5,  4,  3,  2,  1 }*/

        /*int[] height = {5, 4, 3, 2, 1};
        int[] bloom = {1, 5, 10, 15, 20};
        int[] wilt = {5, 10, 15, 20, 25};*/
        /*{ 1,  2,  3,  4,  5 }*/

        int[] height = {5, 4, 3, 2, 1};
        int[] bloom = {1, 5, 10, 15, 20};
        int[] wilt = {5, 10, 14, 20, 25};
        /*{ 3,  4,  5,  1,  2 }*/

        /*int[] height = {1, 2, 3, 4, 5, 6};
        int[] bloom = {1, 3, 1, 3, 1, 3};
        int[] wilt = {2, 4, 2, 4, 2, 4};*/
        /*{ 2,  4,  6,  1,  3,  5 }*/

        /*int[] height = {3, 2, 5, 4};
        int[] bloom = {1, 2, 11, 10};
        int[] wilt = {4, 3, 12, 13};*/
        /*{ 4,  5,  2,  3 }*/

        /*int[] height = {689, 929, 976, 79, 630, 835, 721, 386, 492, 767, 787, 387, 193, 547, 906, 642, 3, 920, 306, 735, 889, 663, 295, 892, 575, 349, 683, 699, 584, 149, 410, 710, 459, 277, 965, 112, 146, 352, 408, 432};
        int[] bloom = {4, 123, 356, 50, 57, 307, 148, 213, 269, 164, 324, 211, 249, 355, 110, 280, 211, 106, 277, 303, 63, 326, 285, 285, 269, 144, 331, 15, 296, 319, 89, 243, 254, 159, 185, 158, 81, 270, 219, 26};
        int[] wilt = {312, 158, 360, 314, 323, 343, 267, 220, 347, 197, 327, 334, 250, 360, 350, 323, 291, 323, 315, 320, 355, 334, 286, 293, 362, 181, 360, 328, 322, 344, 173, 248, 284, 301, 215, 230, 226, 331, 355, 81};*/
        /*{149, 3, 79, 193, 112, 146, 432, 277, 386, 349, 410, 295, 306, 352, 387, 408, 547, 459, 492, 575, 663, 683, 976, 584, 630, 642, 689, 699, 787, 735, 835, 710, 721, 767, 889, 892, 906, 920, 965, 929}*/

        /*int[] height = {2, 1};
        int[] bloom = {10, 5};
        int[] wilt = {20, 30};*/
        /*{1, 2}*/

        FOneDFlowerGarden game = new FOneDFlowerGarden(height, bloom, wilt);
        game.showMatrix();
        game.solve();
        game.showOrder();
    }

    private void solve() {
        /* Sorting based on the flower height before processing */
        Arrays.sort(this.flowers, Comparator.comparing(Flower::getHeight));
        /* This below method is alternative to the Comparator.comparing */
        /* Arrays.sort(this.flowers, (f1, f2) -> {
            return f1.height - f2.height;
        }); */

        this.flowersList.add(this.flowers[ 0 ]);
        for (int i = 1; i < this.height.length; i++) {
            Flower flower = this.flowers[ i ];
            int insertIndex = 0;
            for (int j = 0; j < this.flowersList.size(); j++) {
                Flower orderedFlower = this.flowersList.get(this.flowersList.size() - 1 - j);
                if (overlap(flower, orderedFlower)) {
                    insertIndex = this.flowersList.size() - j;
                    break;
                }
            }
            this.flowersList.add(insertIndex, flower);
        }

        /* TODO Order the Flowers Linked List back into the normal array/list as list of heights alone */
        for (Flower flower : this.flowersList) {
            this.order.add(flower.height);
        }
        return;
    }

    private boolean overlap(Flower flower, Flower orderedFlower) {
        //Comparing the Minimum of the wilting with the Maximum of the blooming we will be able to find the overlaps
        return Math.min(flower.wilt, orderedFlower.wilt) >= Math.max(flower.bloom, orderedFlower.bloom);
    }

    private void showOrder() {
        System.out.println();
        System.out.println("Order is as follows: ");
        System.out.print("{");
        int size = this.order.size();
        for (int i = 0; i < size - 1; i++) {
            System.out.print(this.order.get(i) + " ");
        }
        System.out.print(this.order.get(this.order.size() - 1) + "} \n");
    }

    private void showMatrix() {
        System.out.println("Height    Bloom     Wilt");
        for (int i = 0; i < this.height.length; i++) {
            /* Spacing for the Height */
            int indexSpace = 9;
            int indexSpaceValue = this.height[ i ];
            while (indexSpaceValue / 10 > 0) {
                indexSpace--;
                indexSpaceValue = indexSpaceValue / 10;
            }
            StringBuilder indexSpaceSB = new StringBuilder();
            for (int space = 0; space < indexSpace; space++) {
                indexSpaceSB.append(" ");
            }

            /* Spacing for the Bloom */
            int numberSpace = 9;
            int numberSpaceValue = this.bloom[ i ];
            while (numberSpaceValue / 10 > 0) {
                numberSpace--;
                numberSpaceValue = numberSpaceValue / 10;
            }
            StringBuilder numberSpaceSB = new StringBuilder();
            for (int space = 0; space < numberSpace; space++) {
                numberSpaceSB.append(" ");
            }
            System.out.println(this.height[ i ] + indexSpaceSB.toString() + this.bloom[ i ] + numberSpaceSB.toString() + this.wilt[ i ]);
        }
    }

    private void recap() {
        /* Add all the int[] into a common array Flower[] */
        /*for (int i = 0; i < this.height.length; i++) {
            this.flowers[ i ] = new Flower(this.height[ i ], this.bloom[ i ], this.wilt[ i ]);
        }*/

        /* Sort the array based on the heights of the flowers */
        /* Either below code */
        /*Arrays.sort(this.flowers, Comparator.comparing(Flower::getHeight));*/
        /* or */
        /*Arrays.sort(this.flowers, (flowerOne, flowerTwo) -> {
            return flowerOne.height - flowerTwo.height;
        });*/

        /* Take the elements from 0 to end and check for their valid positions
         * i.e. there should be no overlap of the wilt and the bloom and then insert
         * in the position accordingly */
        /* overlap method is like we are returning Math.min(flower.wilt, orderedFlower.wilt) >= Math.max(flower.bloom, orderedFlower.bloom)
         * Here if there is any intersection then it will return true or else false */
        /*this.flowersList.add(this.flowers[ 0 ]);
        for (int i = 1; i < this.flowers.length; i++) {
            Flower flower = this.flowers[ i ];
            int insertIndex = 0;
            for (int j = 0; j < this.flowersList.size(); j++) {
                Flower orderedFlower = this.flowersList.get(this.flowersList.size() - 1 - j);
                if (overlap(flower, orderedFlower)) {
                    insertIndex = this.flowersList.size() - j;
                    break;
                }
            }
            this.flowersList.add(insertIndex, flower);
        }*/

        /* Linked List is used for the above process and then finally once everything is
         * done then move it to the desired int[] or list and then return */
    }

}

class Flower {
    protected int height;
    protected int bloom;
    protected int wilt;

    public Flower(int height, int bloom, int wilt) {
        this.height = height;
        this.bloom = bloom;
        this.wilt = wilt;
    }

    public int getHeight() {
        return height;
    }
}