package j.chapter.java;

public class CFinalFinallyFinalize {
    /* Difference between Final Finally Finalize */

    private static final String WELCOME_MESSAGE = "Hello, How are you?";

    public static void main(String[] args) {
        /* Final Keyword */
        System.out.println(WELCOME_MESSAGE);
        /* Final Keyword is used for defining the constants which makes the variable immutable
         * The below code which tries to reassign the variable will throw the error at compile time */
        /* WELCOME_MESSAGE = "Modifying the Message"; */

        /* Finally Keyword */
        /* Finally is a method block which will run once all the process of the method is complete.
         * This will be used to close the connections to the Database, File connection */
        try {
            throw new Exception("Thrown Exception");
        } catch (Exception e) {
            System.out.println("Exception Cause: " + e.getLocalizedMessage());
        } finally {
            System.out.println("I will run all the time.... I am called the Finally (Last Man/Woman Standing)");
        }

        /* Finalize Keyword */
        /* This will be called just before the garbage collection */
        /* Note: This method is deprecated from Java 9 onwards */
        System.out.println();
        ToExplainFinalize spiderman = new ToExplainFinalize("I don't want to die...");
        spiderman = null;
        System.gc();
    }
}

class ToExplainFinalize {
    private String message;

    public ToExplainFinalize(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void finalize() {
        System.out.println("Just before the object goes into Garbage Collection, I was used to be called to perform any final rituals using the object like transferring the computed value");
        System.out.println("Final Words: " + this.getMessage());
    }
}