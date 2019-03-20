package q.chapter.mediumDifficulty;

import java.util.ArrayList;
import java.util.List;

public class JLivingPeople {

    public int[] year = new int[ 101 ];/* 100  Years from 1900 to 2000 */

    public static void main(String[] args) {
        JLivingPeople livingPeople = new JLivingPeople();
        List<Person> people = new ArrayList<Person>();
        Person john = new Person(1900, 1945);
        Person ben = new Person(1933, 1975);
        Person adams = new Person(1945, 1945);
        Person jhones = new Person(1950, 1990);
        Person rody = new Person(1956, 1965);
        Person jessi = new Person(1920, 1949);
        Person katana = new Person(1956, 1990);
        Person mandy = new Person(1930, 2000);
        Person glen = new Person(1900, 1978);
        Person shaun = new Person(1930, 1976);
        Person eric = new Person(1903, 1970);
        Person chelsea = new Person(1941, 1989);
        Person mary = new Person(1938, 1969);
        Person steve = new Person(1906, 1990);
        Person roger = new Person(1925, 1991);

        people.add(john);
        people.add(ben);
        people.add(adams);
        people.add(jhones);
        people.add(rody);
        people.add(jessi);
        people.add(katana);
        people.add(mandy);
        people.add(glen);
        people.add(shaun);
        people.add(eric);
        people.add(chelsea);
        people.add(mary);
        people.add(steve);
        people.add(roger);

        System.out.println("Year with Maximum Population: " + livingPeople.calculateMaxPopulationYear(people));
        for (int i = 0; i < livingPeople.year.length; i++) {
            System.out.println((1900 + i) + " : " + livingPeople.year[ i ]);
        }
    }

    private int calculateMaxPopulationYear(List<Person> people) {
        int maxPopulation = 0;
        int year = -1;
        for (Person person : people) {
            int birthYearIndex = (person.birthYear % 100) == 0 ? ((person.birthYear / 1000) == 1 ? 0 : 100) : person.birthYear % 100;
            int deathYearIndex = (person.deathYear % 100) == 0 ? ((person.deathYear / 1000) == 1 ? 0 : 100) : person.deathYear % 100;
            if (birthYearIndex == deathYearIndex) {
                this.year[ birthYearIndex ] += 1;
                if (this.year[ birthYearIndex ] > maxPopulation) {
                    maxPopulation = this.year[ birthYearIndex ];
                    year = birthYearIndex;
                }
                continue;
            }
            for (int index = birthYearIndex; index <= deathYearIndex; index++) {
                this.year[ index ] += 1;
                if (this.year[ index ] > maxPopulation) {
                    maxPopulation = this.year[ index ];
                    year = index;
                }
            }
        }
        return year == -1 ? -1 : (1900 + year);
    }
}

class Person {
    protected int birthYear;
    protected int deathYear;

    public Person(int birthYear, int deathYear) {
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }
}