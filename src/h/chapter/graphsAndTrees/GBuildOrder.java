package h.chapter.graphsAndTrees;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class GBuildOrder {
    /* Build Order
    Question: You are given a list of projects and list of dependencies(which is a list of pairs of projects,
    where the second project is dependent on the first project). All of the project's dependencies must be built
     before the project is. Find a build order that will allow the projects tobe built.If there is no valid order,
     return an error
     Example:
     Input: a, b, c, d, e, f
     Dependencies: (a,d), (f,b), (b,d), (f,a), (d,c)
     Output: f, e, a, b, d, c */

    private static Queue projectQueue = new LinkedList();
    private static Hashtable hashtable = new Hashtable();

    public static void main(String[] args) {
        String[] projectList = new String[]{"a", "b", "c", "d", "e", "f"};
        Dependency[] dependencies = new Dependency[]{new Dependency("d", "a"),
                new Dependency("b", "f"),
                new Dependency("d", "b"),
                new Dependency("a", "f"),
                new Dependency("c", "d")};
        buildOrder(projectList, dependencies);
    }

    private static void buildOrder(String[] projectList, Dependency[] dependencies) {
        prepareHashTable(projectList);
        addDependencies(dependencies);
        showHashTable(projectList);
        fetchOrder(projectList);
        showBuildOrder();
    }

    private static void prepareHashTable(String[] projectList) {
        for (int i = 0; i < projectList.length; i++) {
            hashtable.put(projectList[ i ], new Project(projectList[ i ]));
        }
    }

    private static void addDependencies(Dependency[] dependencies) {
        for (int i = 0; i < dependencies.length; i++) {
            //Adding Parent Project
            Project dependentProject = (Project) hashtable.get(dependencies[ i ].getProject());
            dependentProject.getParentProjects().add(dependencies[ i ].getDependentOn());
            //Adding Dependency
            Project parentProject = (Project) hashtable.get(dependencies[ i ].getDependentOn());
            parentProject.getChildProjects().add(dependencies[ i ].getProject());
        }
    }

    private static void fetchOrder(String[] projectList) {
        Queue tempQueue = new LinkedList();
        for (int i = 0; i < projectList.length; i++) {
            Project project = (Project) hashtable.get(projectList[ i ]);
            if (project.getParentProjects().isEmpty()) {
                tempQueue.add(project);
                projectQueue.add(project.getProjectId());
            }
        }
        while (!tempQueue.isEmpty()) {
            Project tempProject = (Project) tempQueue.poll();
            LinkedList tempLinkedList = tempProject.getChildProjects();
            while (!tempLinkedList.isEmpty()) {
                Project childProject = (Project) hashtable.get((String) tempLinkedList.pollLast());
                projectQueue.add(childProject.getProjectId());
                LinkedList childProjectList = childProject.getChildProjects();
                while (!childProjectList.isEmpty()) {
                    tempQueue.add(hashtable.get((String) childProjectList.pollLast()));
                }
            }
        }
    }

    private static void showHashTable(String[] projectList) {
        for (int i = 0; i < projectList.length; i++) {
            Project project = (Project) hashtable.get(projectList[ i ]);
            System.out.println("Project " + project.getProjectId() + " Details: ");
            System.out.println("Project's Parents (Dependent On): " + project.getParentProjects());
            System.out.println("Project's Children (Depended By): " + project.getChildProjects());
            System.out.println();
        }
    }

    private static void showBuildOrder() {
        Queue queue = new LinkedList(projectQueue);
        System.out.println("Order is as follows");
        while (!queue.isEmpty()) {
            System.out.print(" " + queue.poll() + " ");
        }
    }
}

class Dependency {
    private String project;
    private String dependentOn;

    public Dependency(String project, String dependentOn) {
        this.project = project;
        this.dependentOn = dependentOn;
    }

    public String getProject() {
        return project;
    }

    public String getDependentOn() {
        return dependentOn;
    }
}

class Project {
    private String projectId;
    private LinkedList childProjects;
    private LinkedList parentProjects;

    public Project(String projectId) {
        this.projectId = projectId;
        this.childProjects = new LinkedList();
        this.parentProjects = new LinkedList();
    }

    public String getProjectId() {
        return projectId;
    }

    public LinkedList getChildProjects() {
        return childProjects;
    }

    public LinkedList getParentProjects() {
        return parentProjects;
    }
}