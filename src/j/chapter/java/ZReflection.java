package j.chapter.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ZReflection {
    public static void main(String[] args) {
        GooglePlay googlePlay = new GooglePlay("M");
        googlePlay.addApp(new App(1, "Games", 100));
        googlePlay.addApp(new App(3, "Games", 60));
        App a = new App(5, "Games", 60);
        googlePlay.addApp(a);
        //googlePlay.removeApp(a);
        googlePlay.showApps();
        try {
            Class gp = googlePlay.getClass();
            Constructor[] constructors = gp.getConstructors();
            for (Constructor constructor : constructors) {
                System.out.println("Constructor: " + constructor);
            }
            System.out.println();
            Method[] methods = gp.getMethods();
            for (Method method : methods) {
                System.out.println("Method: " + method);
            }
            System.out.println();
            Method addApp = gp.getMethod("addApp", App.class);
            System.out.println("Got it: " + addApp);

            Field username = gp.getDeclaredField("username");
            username.setAccessible(true);
            System.out.println("Got it: " + username);
            System.out.println();
            addApp.invoke(googlePlay, new App(7, "Music", 80));
            Method showMethod = gp.getDeclaredMethod("showAppsPrivate");
            showMethod.setAccessible(true);
            showMethod.invoke(googlePlay);
        } catch (Exception e) {
            System.out.println("Exceptions occured");
        }
        //googlePlay.showApps("Hello");
    }
}

class GooglePlay<T extends App> {
    private String username;
    private ArrayList<T> appList;

    public GooglePlay() {

    }

    public GooglePlay(String username) {
        this.username = username;
        this.appList = new ArrayList<>();
    }

    private GooglePlay(String username, ArrayList<T> appList) {
        this.username = username;
        this.appList = appList;
    }

    public void addApp(T app) {
        appList.add(app);
    }

    public void removeApp(T app) {
        appList.remove(app);
    }

    public void showApps() {
        for (App app : appList) {
            System.out.println(app.getId());
        }
    }

    private void showAppsPrivate() {
        for (App app : appList) {
            System.out.println("ID: " + app.getId() + " Category" + app.getCategory() + " Price: " + app.getPrice());
        }
    }
}

class App {
    private int id;
    private String category;
    private int price;

    public App(int id, String category, int price) {
        this.id = id;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}