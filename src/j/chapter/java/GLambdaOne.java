package j.chapter.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GLambdaOne {
    public static void main(String[] args) {
        /*  Method 1  */
        /*  Creating an object of the class which will consist of the method which needs to be run  */
        new Thread(new MediaPlayer()).start();
        System.out.println();

        /*  Method 2  */
        /*  Anonymous Class  */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside the Anonymous class run method");
            }
        }).start();
        System.out.println();

        /*  Method 3  */
        /*  Lambda Expressions  */
        /*  Usually these expressions are useful when we are using interface with single methods  */
        /*  these are called Functional Interfaces.  */
        /*  Argument List, Arrow Token, Body are the 3 parts of the Lambda function  */

        /*  Implementation One  */
        new Thread(() -> System.out.println("Inside the Lambda class run method")).start();
        System.out.println();

        new Thread(() -> {
            System.out.println("   ++++++++   ");
            System.out.println("  Multi Line  ");
            System.out.println("  Hello World ");
            System.out.println("   ++++++++   ");
        }).start();

        /*  Implementation Two  */
        /*  Using typical Collections  */
        Employee mohan = new Employee("Mohan", 27);
        Employee rick = new Employee("Rick", 33);
        Employee bobby = new Employee("Bobby", 29);
        Employee snow = new Employee("Snow", 34);

        List<Employee> employees = new ArrayList<>();
        employees.add(mohan);
        employees.add(rick);
        employees.add(bobby);
        employees.add(snow);

        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employeeOne, Employee employeeTwo) {
                return employeeOne.getName().compareTo(employeeTwo.getName());
            }
        });

        int i = 1;
        System.out.println();
        for (Employee employee : employees) {
            System.out.println(i + ") " + employee.getName());
            i++;
        }

        /*  Using Lambda Expressions  */
        employees.add(new Employee("Tom", 40));
        employees.add(new Employee("Holland", 22));
        employees.add(new Employee("Abby", 26));
        employees.add(new Employee("Andy", 18));

        Collections.sort(employees, (employeeOne, employeeTwo) -> employeeOne.getName().compareTo(employeeTwo.getName()));

        int j = 1;
        System.out.println();
        for (Employee employee : employees) {
            System.out.println(j + ") " + employee.getName());
            j++;
        }

        /*  Using Comparator Interface present in Java by Default  */
        Collections.sort(employees, Comparator.comparing(Employee::getAge).thenComparing(Employee::getName));

        /*  Implementation Three  */
        /*  Anonymous Class  */
        String birthdayMessage = prepareGreetingCard(new ConveyMessage() {
            @Override
            public String conveyMessageTo(String message, String toPerson) {
                return message + ", " + toPerson;
            }
        }, "Happy Birthday", "Lakshana");
        System.out.println();
        System.out.println(birthdayMessage);

        /*  Lambda Style  */
        /*ConveyMessage conveyMessage = (message, toPerson) -> message + ", " + toPerson;*/
        String message = prepareGreetingCard((messageToken, toPerson) -> messageToken + ", " + toPerson, "Hello", "world");
        System.out.println();
        System.out.println(message);
    }

    public final static String prepareGreetingCard(ConveyMessage conveyMessage, String message, String toPerson) {
        return conveyMessage.conveyMessageTo(message, toPerson);
    }

}

class MediaPlayer implements Runnable {
    @Override
    public void run() {
        System.out.println("Inside the MediaPlayer class run method...");
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

@FunctionalInterface
interface ConveyMessage {
    public String conveyMessageTo(String message, String toPerson);
}