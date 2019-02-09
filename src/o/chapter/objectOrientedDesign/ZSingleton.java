package o.chapter.objectOrientedDesign;

public class ZSingleton {
    public static void main(String[] args) {
        Login login = Login.getInstance();
        System.out.println("Default: " + login.getName());
        login.setName("Mario");
        System.out.println("After Change: " + login.getName());
        login = null;//Here we are setting the local variable to point to null and then again ask for a new instance
        // hoping that we would get a new instance of Adam again. But since the instance variable in the Login is
        // initialized to Mario we got it getting shown as the current instance
        login = Login.getInstance();
        System.out.println("Second Instance: " + login.getName());
    }
}

class Login {
    private static Login instance = null;
    private String name;

    private Login(String name) {
        this.name = name;
    }

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login("Adam");
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}