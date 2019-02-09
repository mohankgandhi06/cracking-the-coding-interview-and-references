package o.chapter.objectOrientedDesign;

import java.util.LinkedList;
import java.util.List;

public class BCallCenter {
    /* Question: Imagine you have a call center with three levels of employees: respondent, manager, director.
    An incoming telephone call must be first allocated to a respondent who is free. If the respondent can't handle
    the call, he or she must escalate the call to a manager. If the manager is not free or not able to handle it,
    then the call should be escalated to a director. Design the classes and data structures for this problem.
    Implement a method dispatchCall() which assigns a call to first available employee */

    public static final List<Respondent> respondents = new LinkedList<>();

    public static void main(String[] args) {
        /* Since a manager is one for a certain person and so he can be assigned to a Respondent's class while creation
         * and like wise the Director will be assigned to a manager */

        /* In this way we can maintain a queue and then try to assign the queue in a circular way
         * i.e.: Take a Respondent and make him try to attend the call if not */

        Director michael = new Director(1, "Michael");
        Director steve = new Director(2, "Steve");

        Manager richard = new Manager(3, "Richard", michael);
        Manager stone = new Manager(4, "Stone", michael);
        Manager murphy = new Manager(5, "Murphy", michael);

        Manager ryan = new Manager(6, "Ryan", steve);
        Manager renold = new Manager(7, "Renold", steve);
        Manager murray = new Manager(8, "Murray", steve);

        Respondent john = new Respondent(9, "John", richard);
        Respondent jennifer = new Respondent(9, "Jennifer", stone);
        Respondent natalie = new Respondent(9, "Natalie", murphy);
        Respondent gadot = new Respondent(9, "Gadot", ryan);
        Respondent ronny = new Respondent(9, "Ronny", renold);
        Respondent rice = new Respondent(9, "Rice", murray);

        respondents.add(john);
        respondents.add(jennifer);
        respondents.add(natalie);
        respondents.add(gadot);
        respondents.add(ronny);
        respondents.add(rice);

        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();
        CallCenter.dispatchCall();

        /* This has been implemented as a simple one. We could implement the same in the Thread
         * but since it is not currently what we are trying to achieve. To again make the Respondent available once
         * the he/she is available to take calls. Call Center could run from day to night for a interval and
         * Repondent available will take call and proceed further */
    }
}

class CallCenter {
    public static void dispatchCall() {
        Respondent respondent = BCallCenter.respondents.remove(0);
        respondent.attendCall();
        BCallCenter.respondents.add(BCallCenter.respondents.size() - 1, respondent);
    }
}

class Employee {
    /* In this implementation since the only thing that the employee does is the attending of calls it has been
     * created in the Parent method in order to avoid duplication. If there are multiple implementations then we could make
     * this class abstract and then keep an abstract method to make it necessary to implement for all Employee.
     * If in future some employee are not going to take calls then we could refactor this class to something only call
     * takers will use and create a super class which includes all type of employee */
    public enum Role {
        RESPONDENT, MANAGER, DIRECTOR
    }

    protected String name;
    protected int id;
    protected boolean isAvailable = true;
    protected Employee superior;
    protected Role role;

    public void attendCall() {
        if (this.isAvailable) {
            System.out.println(this.role + ": " + this.name + " is attending the call");
            this.isAvailable = false;
        } else {
            if (this.superior != null) {
                this.superior.attendCall();
            } else {
                System.out.println("Everyone seems to be busy, please try later");
            }
        }

    }
}

class Respondent extends Employee {
    public Respondent(int id, String name, Manager manager) {
        this.id = id;
        this.name = name;
        this.role = Role.RESPONDENT;
        this.superior = manager;
    }
}

class Manager extends Employee {
    public Manager(int id, String name, Director director) {
        this.id = id;
        this.name = name;
        this.role = Role.MANAGER;
        this.superior = director;
    }
}

class Director extends Employee {
    public Director(int id, String name) {
        this.id = id;
        this.name = name;
        this.role = Role.DIRECTOR;
        this.superior = null;
    }
}

/*
abstract class Employee {
    */
/*public enum Role {RESPONDENT, MANAGEER, DIRECTOR}*//*

    protected String name;
    protected int id;
    protected boolean isAvailable;
    */
/*private Role role;*//*


    public abstract void attendCall();
}

class Respondent extends Employee {
    private Manager manager;

    public Respondent(Manager manager) {
        this.manager = manager;
    }

    public void attendCall() {
        if (this.isAvailable) {
            System.out.println("Respondent: " + this.name + " is attending the call");
        } else {
            manager.attendCall();
        }
    }
}

class Manager extends Employee {
    private Director director;

    public Manager(Director director) {
        this.director = director;
    }

    public void attendCall() {
        if (this.isAvailable) {
            System.out.println("Manager: " + this.name + " is attending the call");
        } else {
            director.attendCall();
        }
    }
}

class Director extends Employee {

    public void attendCall() {
        if (this.isAvailable) {
            System.out.println("Director: " + this.name + " is attending the call");
        } else {
            System.out.println("Everyone seems to be busy, please try later");
        }
    }
}*/