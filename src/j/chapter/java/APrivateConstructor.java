package j.chapter.java;

public class APrivateConstructor {
    public static void main(String[] args) {
        System.out.println("First");
        Singleton singleton = Singleton.getInstance();

        singleton.setTheOne("Eve");

        //This is the implementation of Constructor Chaining
        // i.e. we are calling the private constructor from another public
        // parameterized constructor. In this way we can break the singleton
        // concept
        System.out.println();
        System.out.println("Second");
        Singleton changedSingleton = new Singleton("Adam");
        System.out.println("After Changed: " + changedSingleton.getTheOne());

        System.out.println();
        System.out.println("Third");
        Singleton afterChangedSingleton = Singleton.getInstance();
        System.out.println("afterChanged: " + afterChangedSingleton.getTheOne());

        System.out.println();
        System.out.println("Final");
        Singleton finalSingleton = Singleton.getInstance();
        System.out.println("Final Singleton: " + finalSingleton.getTheOne());

        System.out.println();
        System.out.println("After Final Again Calling Changed Singleton: " + changedSingleton.getTheOne());

        singleton.printOut("Finally I understand It :)");
    }
}

class Singleton {

    private static Singleton instance = null;
    private String theOne = "Neo";

    private Singleton() {
        System.out.println("Inside Private Constructor: " + theOne);
    }

    public Singleton(String name) {
        this();
        System.out.println("Inside Public Constructor (Constructor Chaining): " + theOne);
        theOne = name;
    }

    public void printOut(String printThis) {
        System.out.println(printThis);
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public String getTheOne() {
        return theOne;
    }

    public void setTheOne(String theOne) {
        this.theOne = theOne;
    }
}