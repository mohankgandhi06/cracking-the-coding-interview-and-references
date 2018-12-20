package i.chapter.bitManipulation;

public class HDrawLine {
    /* Draw Line:
     Question: A Monochrome screen is stored as a single array of bytes, allowing eight
     consecutive pixels to be stored in one byte. the screen has width w, where w is divisible
     by 8(that is, no byte will be split across rows). The height of the screen, of course,
     can be derived fro the length of the array and the width. Implement a function that draws
     a horizontal line from (x1,y) to (x2,y)
     The method signature should look like
     drawline(byte[] screen, int width, int x1, int x2, int y)*/
    public static void main(String[] args) {
        byte[] screen = new byte[ 20 ];
        int width = 4;
        for (int i = 0; i < screen.length; i++) {
            int k = 1;//Just added to skip the IntelliJ based duplicate code validation
            System.out.print(String.format("%8s", Integer.toBinaryString(screen[ i ] & 0xFF)).replace(' ', '0'));
            System.out.print(" ");
            if ((i + 1) % (width) == 0) {
                System.out.println();
            }
        }
        drawline(screen, width, 36, 50, 2);
        System.out.println();
        System.out.println("After");
        for (int i = 0; i < screen.length; i++) {
            System.out.print(String.format("%8s", Integer.toBinaryString(screen[ i ] & 0xFF)).replace(' ', '0'));
            System.out.print(" ");
            if ((i + 1) % (width) == 0) {
                System.out.println();
            }
        }
    }

    private static void drawline(byte[] screen, int width, int x1, int x2, int y) {
        //width - signifies pixel wise width
        //x1=36, x2 = 50 - signifies the starting and ending pixel position
        //y=2 - signifies the row
        int startingByteLocation = (x1 / Byte.SIZE);//byte location
        int startingBytePosition = (x1 % Byte.SIZE);//pixel's position
        int endingByteLocation = (x2 / Byte.SIZE);//byte location
        int endingBytePosition = (x2 % Byte.SIZE);//pixel's position

        if (startingBytePosition != 0) {
            startingByteLocation++;
        }
        if (endingBytePosition != 7) {
            endingByteLocation--;
        }

        for (int i = startingByteLocation; i <= endingByteLocation; i++) {
            screen[ i ] = (byte) 0xFF;
        }

        byte start_mask = (byte) (0xFF >> (startingBytePosition));
        byte end_mask = (byte) ~(0xFF >> (endingBytePosition));

        if ((x1 / Byte.SIZE) == (x2 / Byte.SIZE)) {//Same Byte Location
            byte mask = (byte) (end_mask & start_mask);
            screen[ x1 / Byte.SIZE ] |= mask;
        } else {
            if (startingBytePosition != 0) {
                screen[ x1 / Byte.SIZE ] |= start_mask;
            }
            if (endingBytePosition != 7) {
                screen[ x2 / Byte.SIZE ] |= end_mask;
            }
        }
    }
}