package j.chapter.java;

import java.util.*;

public class EMapsInJava {
    /* TreeMap vs HashMap vs LinkedHashMap */
    public static void main(String[] args) {
        /*TreeMap treeMap = new TreeMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();*/
        HashMap<Song, Integer> hashMap = new HashMap();
        hashMap.put(new Song(56, "Best Song Ever", "Playlist One", "03:54", new ArrayList<>(Arrays.asList("Harry", "Liam", "Norah"))), 20);
        hashMap.put(new Song(45, "Kadhal Vaithu, Kadhal Vaithu", "Playlist One", "04:23", new ArrayList<>(Arrays.asList("Chinmayi Sripada", "Vijay Yesudas"))), 6);
        hashMap.put(new Song(100, "Oxygen, Thandhaye", "Playlist One", "04:23", new ArrayList<>(Arrays.asList("Hiphop Tamilzha"))), 12);
        hashMap.put(new Song(105, "Orasadha", "Playlist One", "04:23", new ArrayList<>(Arrays.asList("Independent Artists"))), 2);
        hashMap.put(new Song(105, "Orasadha", "Playlist One", "04:23", new ArrayList<>(Arrays.asList("Independent Artists"))), 2);

        for (Map.Entry a : hashMap.entrySet()) {
            Song song = (Song) a.getKey();
            System.out.println(a.getValue() + " " + song.getName());
        }

        /* Source : Geeks for Geeks */

        /*  HashMap: HashMap offers 0(1) lookup and insertion. If you iterate through the keys, though, the ordering of the keys is essentially arbitrary. It is implemented by an array of linked lists.
            Syntax:
            public class HashMap extends AbstractMap implements Map,Cloneable, Serializable

            A HashMap contains values based on the key.
            - It contains only unique elements.
            - It may have one null key and multiple null values.
            - It maintains no order.

            LinkedHashMap: LinkedHashMap offers 0(1) lookup and insertion. Keys are ordered by their insertion order. It is implemented by doubly-linked buckets.
            Syntax:
            public class LinkedHashMap extends HashMap implements Map

            A LinkedHashMap contains values based on the key.
            - It contains only unique elements.
            - It may have one null key and multiple null values.
            - It is same as HashMap instead maintains insertion order.

            TreeMap: TreeMap offers O(log N) lookup and insertion. Keys are ordered, so if you need to iterate through the keys in sorted order, you can. This means that keys must implement the Comparable interface. TreeMap is implemented by a Red-Black Tree.
            Syntax:
            public class TreeMap extends AbstractMap implements NavigableMap, Cloneable, Serializable

            A TreeMap contains values based on the key. It implements the NavigableMap interface and extends AbstractMap class.
            - It contains only unique elements.
            - It cannot have null key but can have multiple null values.
            - It is same as HashMap instead maintains ascending order(Sorted using the natural order of its key).  */

        /*  Real Life Applications
         1) Suppose you were creating a mapping of names to Person objects. You might want to periodically output the people in
            alphabetical order by name. A TreeMap lets you do this.
         2) A TreeMap also offers a way to, given a name, output the next 10 people. This could be useful for a “More”function in
            many applications.
         3) A LinkedHashMap is useful whenever you need the ordering of keys to match the ordering of insertion. This might be useful in
            a caching situation, when you want to delete the oldest item.
         4) Generally, unless there is a reason not to, you would use HashMap. That is, if you need to get the keys back in insertion
            order, then use LinkedHashMap. If you need to get the keys back in their true/natural order, then use TreeMap.
            Otherwise, HashMap is probably best. It is typically faster and requires less overhead.  */
    }
}

class Song {
    private int id;
    private String name;
    private String source;
    private String time;
    private List<String> singers;

    public Song(int id, String name, String source, String time, List<String> singers) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.time = time;
        this.singers = singers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getSingers() {
        return singers;
    }

    public void setSingers(List<String> singers) {
        this.singers = singers;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Song) obj).getName() == this.getName();
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}