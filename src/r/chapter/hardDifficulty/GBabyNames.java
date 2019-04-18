package r.chapter.hardDifficulty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GBabyNames {
    public static void main(String[] args) {
        GBabyNames game = new GBabyNames();
        List<NameAndFrequency> nameAndFrequencies = new ArrayList<>();
        List<List<String>> namesSoundingSame = new ArrayList<>();

        NameAndFrequency john = new NameAndFrequency("John", 15);
        NameAndFrequency chris = new NameAndFrequency("Chris", 13);
        NameAndFrequency christopher = new NameAndFrequency("Christopher", 19);
        NameAndFrequency jade = new NameAndFrequency("Jade", 5);
        NameAndFrequency jon = new NameAndFrequency("Jon", 12);
        NameAndFrequency jaden = new NameAndFrequency("Jaden", 6);
        NameAndFrequency steve = new NameAndFrequency("Steve", 8);
        NameAndFrequency kris = new NameAndFrequency("Kris", 4);
        nameAndFrequencies.add(john);
        nameAndFrequencies.add(chris);
        nameAndFrequencies.add(christopher);
        nameAndFrequencies.add(jon);
        nameAndFrequencies.add(jade);
        nameAndFrequencies.add(jaden);
        nameAndFrequencies.add(steve);
        nameAndFrequencies.add(kris);

        List<String> johnjon = new ArrayList<>();
        johnjon.add("John");
        johnjon.add("Jon");
        List<String> johnjhonny = new ArrayList<>();
        johnjhonny.add("John");
        johnjhonny.add("Jhonny");
        List<String> chriskris = new ArrayList<>();
        chriskris.add("Chris");
        chriskris.add("Kris");
        List<String> chrischristopher = new ArrayList<>();
        chrischristopher.add("Chris");
        chrischristopher.add("Christopher");
        List<String> jadejaden = new ArrayList<>();
        jadejaden.add("Jade");
        jadejaden.add("Jaden");
        List<String> stevesteven = new ArrayList<>();
        jadejaden.add("Steve");
        jadejaden.add("Steven");
        namesSoundingSame.add(johnjon);
        namesSoundingSame.add(johnjhonny);
        namesSoundingSame.add(chriskris);
        namesSoundingSame.add(chrischristopher);
        namesSoundingSame.add(jadejaden);

        game.calculateCombinedFrequency(nameAndFrequencies, namesSoundingSame);
    }

    private void calculateCombinedFrequency(List<NameAndFrequency> nameAndFrequencies, List<List<String>> namesSoundingSame) {
        HashMap<String, Integer> nameAndGroup = new HashMap<>();
        int groupId = 0;
        for (List<String> names : namesSoundingSame) {
            if (nameAndGroup.containsKey(names.get(0))) {
                nameAndGroup.put(names.get(1), nameAndGroup.get(names.get(0)));
            } else if (nameAndGroup.containsKey(names.get(1))) {
                nameAndGroup.put(names.get(0), nameAndGroup.get(names.get(1)));
            } else {//Create the new groupId for these and insert the two in the nameAndGroup and increment the groupId
                nameAndGroup.put(names.get(0), groupId);
                nameAndGroup.put(names.get(1), groupId);
                groupId++;
            }
        }

        for (NameAndFrequency name : nameAndFrequencies) {
            if (!nameAndGroup.containsKey(name.getName())){
                nameAndGroup.put(name.getName(), groupId);
                groupId++;
            }
        }

        /*for (Map.Entry entry : nameAndGroup.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }*/

        NameAndFrequency[] names = new NameAndFrequency[ groupId ];
        for (NameAndFrequency name : nameAndFrequencies) {
            if (nameAndGroup.get(name.getName()) != null && names[ nameAndGroup.get(name.getName()) ] != null) {
                names[ nameAndGroup.get(name.getName()) ].setFrequency(names[ nameAndGroup.get(name.getName()) ].getFrequency() + name.getFrequency());
            } else {
                names[ nameAndGroup.get(name.getName()) ] = new NameAndFrequency(name.getName(), name.getFrequency());
            }
        }

        for (NameAndFrequency name : names) {
            System.out.println(name.getName() + " (" + name.getFrequency() + ")");
        }
    }

}

class NameAndFrequency {
    private String name;
    private int frequency;

    public NameAndFrequency(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}