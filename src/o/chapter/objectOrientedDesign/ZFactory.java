package o.chapter.objectOrientedDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ZFactory {
    public static void main(String[] args) {
        /* Here the Factory Pattern is the class Creator where the static method createPlayer is invoked to return the
         * instance of the object which we require. The Factory creates an instance of the object and returns */
        List<Player> playerRooster = new ArrayList<>();
        playerRooster.add(Creator.createPlayer(Creator.Type.CRICKET.toString(), "Sachin", 24, 100, Player.Emotion.HAPPY));
        playerRooster.add(Creator.createPlayer(Creator.Type.FOOTBALL.toString(), "Messi", 20, 100, Player.Emotion.ENTHUSIASTIC));
        playerRooster.add(Creator.createPlayer(Creator.Type.RUGBY.toString(), "Dwanye Johnson", 25, 100, Player.Emotion.ANGRY));

        System.out.println("Rooster: ");
        System.out.println("================================================================");
        AtomicInteger i = new AtomicInteger(1);
        for (Player player : playerRooster) {
            System.out.println(i + ") ");
            System.out.println("Name: " + player.getName());
            System.out.println("Age: " + player.getAge());
            System.out.println("Energy: " + player.getEnergy());
            System.out.println("Emotion: " + player.getEmotion());
            System.out.println("");
            i.incrementAndGet();
        }
        System.out.println("================================================================");
    }
}

class Creator {

    public enum Type {FOOTBALL, CRICKET, RUGBY}

    public static Player createPlayer(String type, String name, int age, int energy, Player.Emotion emotion) {
        System.out.println("Type.FOOTBALL.name(): " + Type.FOOTBALL.name());
        if (type.equalsIgnoreCase(Type.FOOTBALL.name())) {
            return new FootballPlayer(name, age, energy, emotion);
        } else if (type.equalsIgnoreCase(Type.CRICKET.name())) {
            return new CricketPlayer(name, age, energy, emotion);
        } else if (type.equalsIgnoreCase(Type.RUGBY.name())) {
            return new RugbyPlayer(name, age, energy, emotion);
        }
        return null;
    }
}

class Player {
    enum Emotion {HAPPY, SAD, ANGRY, FRUSTRATED, ENTHUSIASTIC, TEARS, HAPPYTEARS}

    private String name;
    private int age;
    private int energy;
    private Emotion emotion;

    public Player(String name, int age, int energy, Emotion emotion) {
        this.name = name;
        this.age = age;
        this.energy = energy;
        this.emotion = emotion;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getEnergy() {
        return energy;
    }

    public Emotion getEmotion() {
        return emotion;
    }
}

interface Actions {
    public abstract void running();

    public abstract void walking();

    public abstract void talking();

    public abstract void celebrating();

    public abstract void showingDisappointment();

    public abstract void screamingAtOpponent();
}

class FootballPlayer extends Player implements Actions {

    public FootballPlayer(String name, int age, int energy, Emotion emotion) {
        super(name, age, energy, emotion);
    }

    private int status;
    private short kickPower;
    private short shortPassAccuracy;
    private short longPassAccuracy;
    private short faintSkillLevel;
    private short dribbleLevel;
    private short tackleAccuracy;

    public void kick() {

    }

    public void shortPass() {

    }

    public void longPass() {

    }

    public void faint() {

    }

    public void dribble() {

    }

    public void tackle() {

    }

    @Override
    public void running() {

    }

    @Override
    public void walking() {

    }

    @Override
    public void talking() {

    }

    @Override
    public void celebrating() {

    }

    @Override
    public void showingDisappointment() {

    }

    @Override
    public void screamingAtOpponent() {

    }
}

class CricketPlayer extends Player implements Actions {

    public CricketPlayer(String name, int age, int energy, Emotion emotion) {
        super(name, age, energy, emotion);
    }

    @Override
    public void running() {

    }

    @Override
    public void walking() {

    }

    @Override
    public void talking() {

    }

    @Override
    public void celebrating() {

    }

    @Override
    public void showingDisappointment() {

    }

    @Override
    public void screamingAtOpponent() {

    }
}

class RugbyPlayer extends Player implements Actions {

    public RugbyPlayer(String name, int age, int energy, Emotion emotion) {
        super(name, age, energy, emotion);
    }

    @Override
    public void running() {

    }

    @Override
    public void walking() {

    }

    @Override
    public void talking() {

    }

    @Override
    public void celebrating() {

    }

    @Override
    public void showingDisappointment() {

    }

    @Override
    public void screamingAtOpponent() {

    }
}