package q.chapter.mediumDifficulty;

public class HEnglishInt {
    public static void main(String[] args) {
        HEnglishInt toString = new HEnglishInt();
        int integer = 1351011416;
        String englishInt = toString.conversion(integer);
        System.out.println("English Int of " + integer + " is: " + englishInt);
    }

    private String conversion(int integer) {
        int positiveOrNegative = (integer & (1 << 31)) >>> 31;
        StringBuilder stringBuilder = new StringBuilder();
        String sign = "";
        String number = Integer.toString(integer);
        if (positiveOrNegative == 1) {
            sign = "Minus ";
            number = number.substring(1);
        }
        /*System.out.println(number);*/
        /*System.out.println(number.length() - ((number.length() / 3) * 3));*/
        int intValue = Integer.parseInt(number);
        int position = 0;
        while (intValue > 0) {
            int threeDigit = intValue % 1000;
            /*System.out.println(threeDigit);*/
            int units = threeDigit % 10;
            int tens = ((threeDigit % 100) / 10) % 100;
            int hundreds = (threeDigit / 100) % 1000;
            /*System.out.println("Hundreds: " + hundreds + " Tens: " + tens + " Units: " + units);*/
            StringBuilder intermediary = new StringBuilder();
            intermediary.append(hundreds > 0 ? (unitsDigit[ hundreds ] + " " + hundred + " ") : "");
            intermediary.append(tens > 0 ? tens == 1 ? (elevenToNineteen[ units ] + " ") : (tensDigit[ tens ] + " ") : "");
            intermediary.append(units > 0 ? tens != 1 ? (unitsDigit[ units ] + " ") : "" : "");
            intermediary.append(greatestSignificantDigits[ position ]);
            stringBuilder.insert(0, position != 0 ? intermediary.append(", ") : intermediary);
            position++;
            /*System.out.println(stringBuilder);*/
            intValue = intValue / 1000;
        }
        stringBuilder.insert(0, sign);
        return stringBuilder.toString();
    }

    /* MAPPING */
    public String hundred = "hundred";

    public String[] unitsDigit = new String[]{
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };

    public String[] tensDigit = new String[]{
            "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninty"
    };

    public String[] greatestSignificantDigits = new String[]{
            "", "thousand", "million", "billion"
    };

    public String[] elevenToNineteen = new String[]{
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "ninteen"
    };
}