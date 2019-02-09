package o.chapter.objectOrientedDesign;

import java.util.*;

public class ICircularArray {
    public static void main(String[] args) {
        CircularList<Integer> listA = new CircularList();

        CircularList<Integer> listB = new CircularList(10);

        CircularList<Integer> listC = new CircularList(new Integer[]{1, 2, 3, 4, 5, 6, 7});

        System.out.println(listA.size());

        System.out.println(listB.size());

        System.out.println(listC.size());

        listB.add(new Integer(4));
        listB.add(new Integer(2));
        listB.add(new Integer(6));
        listB.add(new Integer(16));
        listB.add(new Integer(18));/*
        listB.add(new Integer(28));
        listB.add(new Integer(12));
        listB.add(new Integer(13));*/
        listB.add(new Integer(14));
        listB.add(new Integer(25));

        /*Object[] a = listB.toArray();*/

        /*for (Object q : a) {
            System.out.println(" - " + q);
        }*/

        /*for (Object i : listC) {
            System.out.println((Integer) i);
        }*/

        /*System.out.println(listC.get(1));

        listC.rotate(2);

        System.out.println(listC.get(0));

        System.out.println(listC.get(listC.size() - 2));*/

        //listB.rotate(4);

        for (int i = 0; i < listB.getCapacity(); i++) {
            System.out.println(" - " + listB.get(i));
        }

        listB.add(new Integer(50));

        System.out.println("After Adding");
        for (int i = 0; i < listB.getCapacity(); i++) {
            System.out.println(" - " + listB.get(i));
        }

        System.out.println("'Index of 4 is: " + listB.indexOf(16));

        listB.remove(new Integer(16));

        System.out.println("After Removing 16");
        for (int i = 0; i < listB.getCapacity(); i++) {
            System.out.println(" - " + listB.get(i));
        }

        System.out.println("'Index of 4 is: " + listB.indexOf(16));

    }
}

class CircularList<E extends Object> implements List, Collection {

    private int capacity;

    private int size;

    transient private Object[] objects;

    private int first;//first position

    private final static int DEFAULT_SIZE = 10;

    public CircularList() {
        objects = new Object[ DEFAULT_SIZE ];
        size = 0;
    }

    public CircularList(int capacity) {
        objects = new Object[ capacity ];
        size = 0;
    }

    public CircularList(Object[] collection) {
        objects = new Object[ collection.length ];
        Object[] temp = Arrays.copyOf(collection, collection.length, collection.getClass());
        size = 0;
        first = 0;
        for (int i = 0; i < objects.length; i++) {
            objects[ i ] = temp[ i ];
            size++;
        }
        /*for (int i = 0; i < objects.length; i++) {
            System.out.println(" - " + objects[ i ]);
        }*/
    }

    public int getCapacity() {
        return objects.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        objects = new Object[ objects.length ];
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < objects.length; i++) {
                if (objects[ (first + i) % objects.length ] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < objects.length; i++) {
                if (objects[ i ] != null) {
                    if (objects[ (first + i) % objects.length ].equals(o)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index >= objects.length) {
            System.out.println("Index out of reach");
            return null;
        }
        return (E) objects[ (first + index) % objects.length ];
    }

    @Override
    public boolean contains(Object o) {
        int location = indexOf(o);
        if (location == -1) {
            System.out.println("The List does not contain the object you have given");
            return false;
        }
        return true;
    }

    @Override
    public boolean add(Object o) {
        if (size() >= objects.length) {
            System.out.println("Didn't Add the value: " + (E) o);
            return false;
        }
        objects[ firstFreeIndex() ] = o;
        size++;
        return true;
    }

    private int firstFreeIndex() {
        for (int i = 0; i < objects.length; i++) {
            if (objects[ i ] == null) {
                return i;
            }
        }
        return -1;
    }


    //TODO Needs a revisit for Circular Approach
    @Override
    public boolean remove(Object o) {
        int location = indexOf(o);
        if (location == -1) {
            System.out.println("Didn't find the data. Could not remove");
            return false;
        }
        objects[ location ] = null;
        return true;
    }

    //TODO This method will add the value or it will overwrite the element
    @Override
    public void add(int index, Object element) {
        if (index >= objects.length) {
            System.out.println("Could not add: Index is greater than expected");
            return;
        }
        objects[ (first + index) % objects.length ] = element;
    }

    @Override
    public E remove(int index) {
        if (index >= objects.length) {
            System.out.println("Could not remove: Index is greater than expected");
            return null;
        }
        E object = (E) objects[ (first + index) % objects.length ];
        objects[ (first + index) % objects.length ] = null;
        return object;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(objects, size);
    }

    @Override
    public Iterator iterator() {
        Iterator itr = Arrays.asList(this.objects).iterator();
        return itr;
    }

    public void rotate(int position) {
        first = (objects.length) - position;
        /*first = position;*/
    }

    //TODO Since this is same as the Add index, element Method this is neglected as of now
    @Override
    public Object set(int index, Object element) {
        System.out.println("This method is not implemented, Please use add(index,element) instead");
        return null;
    }

    //TODO Below methods are neglected as of now
    @Override
    public Object[] toArray(Object[] a) {
        return a;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    /* Not Needed right now... */
    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }
}