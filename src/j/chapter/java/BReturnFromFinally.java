package j.chapter.java;

public class BReturnFromFinally {

    public static void main(String[] args) {
        try {
            System.out.println("Executing Try Block");
            Exception e = ImplicitException.throwException();
            if (e != null) {
                throw new Exception(e);
            }
            return;
        } catch (Exception e) {
            System.out.println("Executing Catch Block");
        } finally {
            System.out.println("Executing Finally Block");
        }
    }
}

class ImplicitException extends Exception {
    public static Exception throwException() {
        Exception e = new Exception();
        return e;
    }
}