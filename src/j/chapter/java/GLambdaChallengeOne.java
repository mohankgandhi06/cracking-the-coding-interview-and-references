package j.chapter.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GLambdaChallengeOne {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            String myString = "Let's split this up into an array";
            String[] parts = myString.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };
        runnable.run();

        Function<String, String> result = (String source) -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };
        result.apply("1234567890");
        /*result.everySecondCharacter("Hello World");*/

        String resultString = everySecondCharacter(result, "1234567890");
        System.out.println(resultString);

        Supplier<String> iLoveJava = () -> {
            return "I Love Java";
        };

        String supplierResult = iLoveJava.get();
        System.out.println(supplierResult);

        Callable callable = () -> {
            System.out.println("Called");
            return null;
        };

        try {
            callable.call();
        } catch (Exception e) {
            System.out.println("Exception Thrown while calling the Callable Interface");
        }

        List<String> topNames2018 = Arrays.asList(
                "Amelia",
                "Olivia",
                "emily",
                "Isla",
                "Ava",
                "oliver",
                "Jack",
                "Charlie",
                "harry",
                "Jacob"
        );

        List<String> firstUpperCaseList = new ArrayList<>();
        topNames2018.forEach(name -> firstUpperCaseList.add(name.substring(0, 1).toUpperCase() + name.substring(1)));
        firstUpperCaseList.sort(String::compareTo);
        firstUpperCaseList.forEach(System.out::println);

        List<String> sortedFirstLetterUpperCasedNames = topNames2018.stream()
                .map(s -> {
                    return s.substring(0, 1).toUpperCase() + s.substring(1);
                })
                .sorted().collect(Collectors.toList());

        System.out.println();
        System.out.println("-------------------------------------------");
        for (String name : sortedFirstLetterUpperCasedNames) {
            System.out.println(name);
        }

        System.out.println();
        System.out.println("Names that begin with A:");
        List<String> namesThatBeginWithA = topNames2018
                .stream()
                .map(s -> {
                    return s.substring(0, 1).toUpperCase() + s.substring(1);
                })
                .filter(s -> s.startsWith("A"))
                .collect(Collectors.toList());
        for (String name : namesThatBeginWithA) {
            System.out.println(name);
        }
        System.out.println("Count: " + namesThatBeginWithA.size());

        topNames2018
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    public static String everySecondCharacter(Function<String, String> function, String input) {
        return function.apply(input);
    }
}

/*
@FunctionalInterface
interface EverySecondCharacter {
    public String everySecondCharacter(String source);
}
*/