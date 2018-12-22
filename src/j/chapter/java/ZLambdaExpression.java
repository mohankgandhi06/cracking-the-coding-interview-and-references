package j.chapter.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ZLambdaExpression {

    public static List<Country> countries = new ArrayList<>();

    public static void main(String[] args) {
        /* Streams */
        List<String> bingoLot = Arrays.asList(
                "N40", "N36", "B12", "B6", "G53", "G49", "G60", "G50", "g64",
                "I26", "I17", "I29", "O71"
        );

        /* Printing Numbers with G */
        bingoLot.forEach(number -> {
            if (number.toUpperCase().startsWith("G")) System.out.println(number);
        });

        /* Printing Number with G in sorted order */
        List<String> gNumbers = new ArrayList<>();//Here gNumbers is used even though it is not
        // looking like effectively final since we are adding the numbers to the list.
        // However what we are actually doing is that we are not changing the reference of the
        // variable. we are just keeping the reference untouched and only adding the elements
        // which is acceptable as effectively final
        bingoLot.forEach(number -> {
            if (number.toUpperCase().startsWith("G")) gNumbers.add(number);
        });

        gNumbers.sort((String numberOne, String numberTwo) -> numberOne.compareTo(numberTwo));
        System.out.println();
        System.out.println("Sorted Order");
        gNumbers.forEach(number -> System.out.println(number.toUpperCase()));

        bingoLot
                .stream()
                .map(String::toUpperCase)//.map(s -> s.toUpperCase())
                .filter(number -> number.startsWith("G"))
                .sorted()
                .forEach(System.out::println);
        //System.out.println accepts arguments but doesn't return
        // so it can be considered as consumer

        Stream<String> streamOne = Stream.of("I26", "I17", "I29", "O71");
        Stream<String> streamTwo = Stream.of("I26", "I17", "I29", "N40", "N36", "B12", "B6");
        Stream<String> collection = Stream.concat(streamOne, streamTwo);
        System.out.println();
        System.out.println(collection.distinct().peek(System.out::println).count());

        Employee john = new Employee("John Travolta", 50);
        Employee amanda = new Employee("Amanda Sky", 30);
        Employee elliot = new Employee("Elliot L", 26);
        Employee steve = new Employee("Steve Roger", 20);
        Employee jacky = new Employee("Jacky Chan", 55);
        Employee alba = new Employee("Alba Smith", 35);

        Department hr = new Department("Human Resource");
        hr.addEmployee(john);
        hr.addEmployee(amanda);
        Department accounting = new Department("Accounting");
        accounting.addEmployee(elliot);
        accounting.addEmployee(steve);
        accounting.addEmployee(jacky);
        accounting.addEmployee(alba);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(employee -> System.out.println(employee.getName()));

        /* Since lambda are only a temporary process, once we come out of it is not available
         * we need to store it into some list we go for collect */

        System.out.println("====================================================");
        List<String> stringList = bingoLot
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        stringList.forEach(System.out::println);

        /* Reduce */
        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce(((employeeOne, employeeTwo) -> employeeOne.getAge() < employeeTwo.getAge() ? employeeOne : employeeTwo))
                .ifPresent(employee -> System.out.println(employee.getName() + " is " + employee.getAge() + " Younger"));

        /* Challange One */
        Runnable runnable = () -> {
            String myString = "Let's split this up into an array";
            String[] parts = myString.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };

        SecondCharacter secondCharacter = (String source) -> {
            StringBuilder returnValue = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnValue.append(source.charAt(i));
                }
            }
            return returnValue.toString();
        };

        System.out.println("Lambda Challenge");
        String s = secondChar(secondCharacter, "Smoking is injurious to health");
        System.out.println(s);


        Function<String, String> evenCharacterFunction = (String source) -> {
            StringBuilder returnValue = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnValue.append(source.charAt(i));
                }
            }
            return returnValue.toString();
        };

        String result = evenCharacterFunction.apply("1234567890");
        System.out.println("Result is: " + result);

        Supplier<String> iLoveJava = () -> {
            return "I Love Java";
        };

        String supplierResult = iLoveJava.get();
        System.out.println(supplierResult);


        List<String> topName2015 = Arrays.asList(
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

        System.out.println("===================================================================");
        Function<String, String> camelCase = (String character) -> {
            return character.substring(0, 1).toUpperCase() + character.substring(1);
        };
        topName2015.stream().map(camelCase).sorted(String::compareTo).forEach(System.out::println);

        System.out.println();
        System.out.println("Names Beginning with A");
        System.out.println(topName2015.stream().map(camelCase).filter(name -> name.startsWith("A")).peek(System.out::println).count());// forEach(System.out::println);


        /* CTCI problems */
        countries.add(new Country("India", "Asia", 100000));
        countries.add(new Country("China", "Asia", 90000));
        countries.add(new Country("Japan", "Asia", 70000));
        countries.add(new Country("South Africa", "Africa", 5000));
        countries.add(new Country("Zambia", "Africa", 5000));
        countries.add(new Country("Cote D Ivory", "Africa", 5000));
        countries.add(new Country("Spain", "Europe", 5000));
        countries.add(new Country("Switzerland", "Europe", 4000));
        countries.add(new Country("France", "Europe", 7060));
        countries.add(new Country("Italy", "Europe", 6000));
        countries.add(new Country("Germany", "Europe", 7800));
        countries.add(new Country("USA", "North America", 78890));
        countries.add(new Country("Mexico", "North America", 5600));
        countries.add(new Country("Argentina", "South America", 5320));
        countries.add(new Country("Brazil", "South America", 6899));
        countries.add(new Country("Chile", "South America", 7890));
        countries.add(new Country("Uruguay", "South America", 7500));

        BiFunction<List<Country>, String, Integer> getPopulation = (countriesList, continent) -> {
            int population = 0;
            for (Country country : countriesList) {
                if (country.getContinent().equalsIgnoreCase(continent)) {
                    population += country.getPopulation();
                }
            }
            return population;
        };

        System.out.println("Population of Asia: " + getPopulation.apply(countries, "Asia"));

        int population = getPopulationOfContinent(countries, "North America");
        System.out.println("Population of North America is: " + population);

        List<Integer> integerList = Arrays.asList(
                100, 200, 300, 303, 305, 400, 401, 500, 607, 609, 800, 801, 809, 904, 906, 911, 958, 999
        );

        Random random = new Random();
        int randomSize = random.nextInt(integerList.size());
        List<Integer> subset = integerList.stream().limit(randomSize).collect(Collectors.toList());
        System.out.println("Size: " + randomSize);
        System.out.println(subset);
    }

    /* Copied from main to clear out space for other chapters */
    private static void lambda() {
        UpperAndConcat upperAndConcat = (stringOne, stringTwo) -> {
            return stringOne.toUpperCase() + stringTwo.toUpperCase();
        };
        /*System.out.println(joinString(upperAndConcat, "Hello ", "world"));*/

        Employee john = new Employee("John Douglas", 20);
        Employee mathew = new Employee("Mathew McConaghy", 23);
        Employee oliver = new Employee("Oliver Twist", 22);
        Employee jean = new Employee("Jean Roger", 36);
        Employee jason = new Employee("Jason Stathom", 40);
        Employee johnny = new Employee("Johnny Deep", 33);
        Employee daniel = new Employee("Daniel Radcliffe", 30);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(mathew);
        employees.add(oliver);
        employees.add(jean);
        employees.add(jason);
        employees.add(johnny);
        employees.add(daniel);

        /*employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });*/

        Predicate<Employee> greaterThan30Predicate = employee -> employee.getAge() > 30;
        Predicate<Employee> lesserOrEqualTo30Predicate = employee -> employee.getAge() <= 30;

        showEmployees(employees, "Showing Employee Greater than 30", greaterThan30Predicate);
        System.out.println();
        showEmployees(employees, "Showing Employee Lesser than or Equal to 30", lesserOrEqualTo30Predicate);
        System.out.println();
        showEmployees(employees, "Showing Employee Lesser  than 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 25;
            }
        });

        IntPredicate startRange = integer -> integer > 12;
        IntPredicate endRange = integer -> integer < 51;
        int[] array = new int[]{11, 8, 20, 13, 6, 50, 70, 1, 3, 51};
        for (int a : array) {
            if (startRange.and(endRange).test(a)) {
                System.out.println(a);
            }
        }

        System.out.println();
        System.out.println("Random Number: ");
        Random randomNumber = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(randomNumber.nextInt(1000));
        }


        System.out.println();
        System.out.println("Random Number using Supplier");
        Random randomNumberTwo = new Random();
        Supplier<Integer> randomNumberSupplier = () -> randomNumberTwo.nextInt(100);
        for (int i = 0; i < 10; i++) {
            System.out.println(randomNumberSupplier.get());
        }

        /* Function */
        System.out.println();
        System.out.println("=========================================================");
        for (Employee employee : employees) {
            String lastname = employee.getName().substring(employee.getName().indexOf(" ") + 1);
            System.out.println("Last name is: " + lastname);
        }

        /* Functions will accept a parameter and return a parameter which is sufficient for our
         * scenario */
        Function<Employee, String> getLastName = (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(" ") + 1);
        };

        Function<Employee, String> getFirstName = (Employee employee) -> {
            return employee.getName().substring(0, employee.getName().indexOf(" "));
        };

        System.out.println();
        for (Employee employee : employees) {
            System.out.println("First Name: " + getName(getFirstName, employee) + " Last Name: " + getName(getLastName, employee));
        }

        /* Function Chaining */
        Function<Employee, String> toUpperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> printMessage = name -> "Hi " + name + ", How are you Doing?";

        BiFunction<String, Employee, String> concatAge = (String name, Employee employee) -> {
            return name + " " + employee.getAge();
        };

        /* Chaining BiFunction with function */
        System.out.println();
        System.out.println("Chaining BiFunction with function");
        System.out.println("=============================================================");
        for (Employee employee : employees) {
            System.out.println(concatAge.apply((toUpperCase.apply(employee)), employee));
        }

        /*for (Employee employee : employees) {
            System.out.println(toUpperCase.andThen(printMessage).apply(employee));
        }*/

        IntUnaryOperator incBy5 = input -> input + 5;

        System.out.println();
        System.out.println("After Five Years");
        System.out.println("=============================================================");
        for (Employee employee : employees) {
            System.out.println("Name: " + employee.getName() + " Age: " + incBy5.applyAsInt(employee.getAge()));
        }
    }

    private static String joinString(UpperAndConcat uc, String inputOne, String inputTwo) {
        return uc.upperAndConcat(inputOne, inputTwo);
    }

    private static void showEmployees(List<Employee> employeeList, String bannerMessage, Predicate<Employee> predicate) {
        System.out.println(bannerMessage);
        System.out.println("=============================================");
        for (Employee employee : employeeList) {
            if (predicate.test(employee)) {
                System.out.println(employee.getName());
                System.out.println(employee.getAge());
                System.out.println();
            }
        }
    }

    private static String getName(Function<Employee, String> getName, Employee employee) {
        return getName.apply(employee);
    }

    private final static String secondChar(SecondCharacter sc, String source) {
        return sc.everySecondChar(source);
    }

    public static class Country {
        public String name;
        public String continent;
        public int population;

        public Country(String name, String continent, int population) {
            this.name = name;
            this.continent = continent;
            this.population = population;
        }

        public String getContinent() {
            return continent;
        }

        public int getPopulation() {
            return population;
        }
    }

    private static int getPopulationOfContinent(List<Country> countriesList, String continent) {
        int population = countriesList
                .stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                .map(country -> country.getPopulation())
                .reduce(0, (a, b) -> a + b);
        return population;
    }
}

@FunctionalInterface
interface UpperAndConcat {
    public abstract String upperAndConcat(String input1, String input2);
}

class Department {
    public String name;
    public List<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

@FunctionalInterface
interface SecondCharacter {
    public abstract String everySecondChar(String source);
}