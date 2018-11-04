package z.reference.threads;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CCustomException {

    private static class KilledException implements Thread.UncaughtExceptionHandler {
        ExecutionException exception;

        public ExecutionException getException() {
            return exception;
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(e.getMessage());
            System.out.println("Poor " + t.getName() + " has been killed while Sleeping");
            this.exception = new ExecutionException(e.getMessage(), e.getCause());
        }
    }

    public static void main(String[] args) {
        Person jonny = new Person("Jonny", "Male", 20);
        KilledException killedException = new KilledException();
        jonny.setUncaughtExceptionHandler(killedException);
        jonny.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            //No Use
        }
        jonny.interrupt();
        try {
            jonny.join();
        } catch (InterruptedException e) {
            //No Use
        }
        System.out.println("Motto for Killing: ");
        System.out.println(killedException.getException().getCause());
    }
}

class Person extends Thread {
    private String personName;
    private String sex;
    private int age;
    private String modeOfDeath;
    private List<Person> primeSuspects;

    public Person(String personName, String sex, int age) {
        super(personName);
        this.personName = personName;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        System.out.println(current.getName() + ": Hello World");
        while (!current.isInterrupted()) {
            System.out.println("I am still alive");
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
                current.interrupt();
            }
        }
        throw new RuntimeException(this.personName + " have been killed", new Throwable("Poisoning"));
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getModeOfDeath() {
        return modeOfDeath;
    }

    public void setModeOfDeath(String modeOfDeath) {
        this.modeOfDeath = modeOfDeath;
    }

    public List<Person> getPrimeSuspects() {
        return primeSuspects;
    }

    public void setPrimeSuspects(List<Person> primeSuspects) {
        this.primeSuspects = primeSuspects;
    }
}