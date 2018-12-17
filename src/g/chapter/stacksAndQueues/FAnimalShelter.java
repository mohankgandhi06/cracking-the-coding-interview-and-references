package g.chapter.stacksAndQueues;

import java.util.LinkedList;

public class FAnimalShelter {
    /* Question:
     Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly "first in first out" basis.
     People must adopt either the "oldest" based on arrival time of all the animals at the shelter, or they can select
     whether tey would prefer a dog or a cat (and will receive the oldest animal of that type). They cannot select which specific
     animal they would like. Create the data structures to maintain this system and implement operations such as enqueue,
     dequeueAny, dequeueDog, dequeueCat. You may use built-in linked list data structure.*/
    public static void main(String[] args) {
        AnimalShelter animalShelter = new AnimalShelter(8);
        animalShelter.getQueue().enqueue("Penny", "Cat");
        animalShelter.getQueue().enqueue("Persian", "Cat");
        animalShelter.getQueue().enqueue("Danny", "Dog");
        animalShelter.getQueue().enqueue("Jhonny", "Dog");
        animalShelter.getQueue().enqueue("Felix", "Cat");
        animalShelter.getQueue().enqueue("Scooby", "Dog");
        //animalShelter.getQueue().dequeue();
        animalShelter.getQueue().enqueue("Bony", "Dog");
        animalShelter.getQueue().enqueue("K", "Dog");
        animalShelter.dequeueDog();
        animalShelter.getQueue().enqueue("Stevy", "Cat");
        animalShelter.getQueue().enqueue("Mikey", "Cat");
        //animalShelter.getQueue().dequeue();
        //animalShelter.getQueue().dequeue();
        animalShelter.dequeueCat();
        animalShelter.show();
        System.out.println("Peek Value: " + animalShelter.getQueue().peek().getName() + " (" + animalShelter.getQueue().peek().getType() + ")");
    }
}

/* Optimal Implementation */
class ShelterOptimal {
    LinkedList<Dog> dogs = new LinkedList<>();
    LinkedList<Cat> cats = new LinkedList<>();
    private int order = 0;

    public void enqueue(Animal animal) {
        animal.setOrder(order);
        order++;
        if (animal instanceof Dog) {
            dogs.addLast((Dog) animal);
        } else if (animal instanceof Cat) {
            cats.addLast((Cat) animal);
        }
    }

    public Animal dequeAny() {
        //Since we can dequeue any and it has to be the oldest
        // we can peek through each list and check which one is old
        // before we do this we are also checking if one of the list is
        // empty. If so we can just return the other queue's first element
        if (dogs.isEmpty()) {
            return dequeueCats();
        } else if (cats.isEmpty()) {
            return dequeDogs();
        }

        Dog dog = dogs.peek();
        Cat cat = cats.peek();
        if (dog.isOlderThan(cat)) {
            return dequeDogs();
        } else {
            return dequeueCats();
        }
    }

    public Dog dequeDogs() {
        return dogs.poll();
    }

    public Cat dequeueCats() {
        return cats.poll();
    }
}

abstract class Animal {
    private int order;
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isOlderThan(Animal animal) {
        //For the animal to be older its order has to be less than the other
        return this.order < animal.getOrder();
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
}

/* Earlier Implementation */
class AnimalShelter {
    private PetQueue queue;
    private PetQueue temporaryQueue;

    public AnimalShelter(int size) {
        this.queue = new PetQueue(size);
        this.temporaryQueue = new PetQueue(size);
    }

    public void show() {
        Pet node = this.getQueue().getQueueFront();
        while (node != null) {
            System.out.println("- " + " Name: " + node.getName() + " | Type: " + node.getType());
            node = node.getPrevious();
        }
        if (this.getQueue().getQueueSize() == 0) {
            System.out.println("The Queue is Empty");
        }
    }

    public Pet dequeueDog() {
        int count = 1;
        Pet oldPet = null;
        Pet tempPet = null;
        while (!this.queue.isEmpty()) {
            if (this.queue.getQueueFront().getType().equalsIgnoreCase("Dog") && count != 0) {
                oldPet = this.queue.dequeue();
                count--;
            } else {
                tempPet = this.queue.dequeue();
                this.temporaryQueue.enqueue(tempPet.getName(), tempPet.getType());
            }
        }
        while (!this.temporaryQueue.isEmpty()) {
            tempPet = this.temporaryQueue.dequeue();
            this.queue.enqueue(tempPet.getName(), tempPet.getType());
        }
        return oldPet;
    }

    public Pet dequeueCat() {
        //dequeueCat method and dequeueDog can be combined with the normal dequeue, but since in the question it is given
        // to implement, we are doing them separately
        int count = 1;
        Pet oldPet = null;
        Pet tempPet = null;
        while (!this.queue.isEmpty()) {
            if (this.queue.getQueueFront().getType().equalsIgnoreCase("Cat") && count != 0) {
                oldPet = this.queue.dequeue();
                count--;
            } else {
                tempPet = this.queue.dequeue();
                this.temporaryQueue.enqueue(tempPet.getName(), tempPet.getType());
            }
        }
        while (!this.temporaryQueue.isEmpty()) {
            tempPet = this.temporaryQueue.dequeue();
            this.queue.enqueue(tempPet.getName(), tempPet.getType());
        }
        return oldPet;
    }

    public PetQueue getQueue() {
        return queue;
    }
}

class PetQueue {
    private Pet queueFront;
    private Pet queueRear;
    private int queueSize;
    private int max;

    public PetQueue(int size) {
        this.max = size;
        this.queueSize = 0;
    }

    public void enqueue(String name, String type) {
        if (!this.isFull()) {
            Pet newNode = new Pet(name, type);
            newNode.setNext(this.getQueueRear());
            newNode.setPrevious(null);
            if (!this.isEmpty()) {
                this.getQueueRear().setPrevious(newNode);
            } else {
                this.setQueueFront(newNode);
            }
            this.setQueueRear(newNode);
            this.queueSize++;
        } else {
            System.out.println("The Animal Shelter is full. Cannot add " + name + " (" + type + ")");
        }
    }

    public Pet dequeue() {
        Pet oldPet = null;
        if (!this.isEmpty()) {
            oldPet = this.queueFront;
            this.setQueueFront(this.getQueueFront().getPrevious());
            if (this.getQueueFront() != null) {
                this.getQueueFront().setNext(null);
            }
            this.queueSize--;
        } else {
            System.out.println("The Animal Shelter is empty");
        }
        return oldPet;
    }

    public Pet peek() {
        return this.getQueueFront();
    }

    public boolean isEmpty() {
        return this.queueSize == 0;
    }

    public boolean isFull() {
        return this.queueSize == this.max;
    }

    public Pet getQueueFront() {
        return queueFront;
    }

    public void setQueueFront(Pet queueFront) {
        this.queueFront = queueFront;
    }

    public Pet getQueueRear() {
        return queueRear;
    }

    public void setQueueRear(Pet queueRear) {
        this.queueRear = queueRear;
    }

    public int getQueueSize() {
        return queueSize;
    }
}

class Pet {
    private String name;
    private String type;
    private Pet next;
    private Pet previous;

    public Pet(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Pet getNext() {
        return next;
    }

    public void setNext(Pet next) {
        this.next = next;
    }

    public Pet getPrevious() {
        return previous;
    }

    public void setPrevious(Pet previous) {
        this.previous = previous;
    }
}