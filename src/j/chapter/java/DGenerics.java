package j.chapter.java;

import java.util.ArrayList;
import java.util.Collections;

public class DGenerics {
    /* Question:
     Create a generic class to implement a league table for a sport.
     Class should allow teams to be added to the list, and store
     a list of teams that belong to the league.

     Your class should have a method to print out the teams in order,
     with the team at the top of the league printed first.

     Only teams of the same type should be added to any particular
     instance of the league class - the program should fail to compile
     if an attempt is made to add an incompatible team.*/

    public static void main(String[] args) {

        League<SpanishTeam> spanishLeague = new League("la liga");
        League<EnglishTeam> englishLeague = new League("english premier league");
        League<IndianTeam> indianLeague = new League("indian super league");

        SpanishTeam<FootballPlayer> barcelona = new SpanishTeam("barca ");
        SpanishTeam<FootballPlayer> madrid = new SpanishTeam("madrid");
        SpanishTeam<FootballPlayer> atletico = new SpanishTeam("atleti");
        SpanishTeam<FootballPlayer> girona = new SpanishTeam("girona");

        EnglishTeam<FootballPlayer> newcastle = new EnglishTeam("newcastle");
        EnglishTeam<FootballPlayer> manchester = new EnglishTeam("manchester");
        EnglishTeam<FootballPlayer> chelsea = new EnglishTeam("chelsea");

        IndianTeam<FootballPlayer> chennai = new IndianTeam("chennai");
        IndianTeam<FootballPlayer> mumbai = new IndianTeam("mumbai");
        IndianTeam<FootballPlayer> kolkata = new IndianTeam("kolkata");

        FootballPlayer messi = new FootballPlayer("messi", "31", "95");
        FootballPlayer xavi = new FootballPlayer("xavi", "31", "94");
        FootballPlayer iniesta = new FootballPlayer("iniesta", "31", "92");
        FootballPlayer ronaldinho = new FootballPlayer("ronaldinho", "31", "96");

        FootballPlayer deco = new FootballPlayer("deco", "31", "89");
        FootballPlayer villa = new FootballPlayer("villa", "31", "90");
        FootballPlayer beckham = new FootballPlayer("beckham", "31", "95");

        FootballPlayer ronaldo = new FootballPlayer("ronaldo", "31", "95");
        FootballPlayer pele = new FootballPlayer("pele", "31", "93");
        FootballPlayer cristiano = new FootballPlayer("cristiano", "31", "95");

        spanishLeague.addTeam(barcelona);
        spanishLeague.addTeam(madrid);
        spanishLeague.addTeam(atletico);
        spanishLeague.addTeam(girona);

        englishLeague.addTeam(newcastle);
        englishLeague.addTeam(manchester);
        englishLeague.addTeam(chelsea);
        englishLeague.addTeam(chelsea);

        indianLeague.addTeam(chennai);
        indianLeague.addTeam(mumbai);
        indianLeague.addTeam(kolkata);

        barcelona.addPlayer(messi);
        madrid.addPlayer(xavi);
        atletico.addPlayer(iniesta);
        girona.addPlayer(ronaldinho);

        newcastle.addPlayer(deco);
        manchester.addPlayer(villa);
        chelsea.addPlayer(beckham);

        chennai.addPlayer(ronaldo);
        mumbai.addPlayer(pele);
        kolkata.addPlayer(cristiano);

        System.out.println(" ------------- Results so far ------------- ");
        barcelona.matchResult(madrid, 0, 3);
        barcelona.matchResult(atletico, 0, 1);
        barcelona.matchResult(girona, 5, 0);

        madrid.matchResult(atletico, 6, 3);
        madrid.matchResult(girona, 4, 1);

        atletico.matchResult(girona, 5, 0);
        System.out.println(" ------------------------------------------ ");

        spanishLeague.leagueRankingTable();
    }
}

class League<T extends Team> {
    private String leagueName;
    private ArrayList<T> teams = new ArrayList<>();

    public League(String name) {
        this.leagueName = name;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public ArrayList<T> getTeams() {
        return teams;
    }

    public void addTeam(T team) {
        System.out.println();
        System.out.println("Team Signing");
        if (teams.contains(team)) {
            System.out.println("Already signed " + team.getTeamName() + " into the league...");
            return;
        } else {
            teams.add(team);
            System.out.println("Added " + team.getTeamName() + " successfully to the " + this.getLeagueName() + ".");
        }
    }

    public void leagueRankingTable() {
        System.out.println("**** " + this.getLeagueName() + " ****");
        Collections.sort(this.getTeams());
        int i = 1;
        System.out.println("S No.    Team       Played  Won Drawn Lost  Points");
        for (T t : this.getTeams()) {
            System.out.println(i + ")       " + t.getTeamName() + "     " + t.getPlayed() + "       " + t.getWon() + "   " + t.getDrawn() + "     " + t.getLost() + "     " + t.ranking());
            i++;
        }
    }
}

class Team<T extends Player> implements Comparable<Team<T>> {

    private String teamName;
    private int played;
    private int won;
    private int lost;
    private int drawn;
    private ArrayList<T> members;

    public Team(String teamName) {
        this.teamName = teamName;
        this.played = 0;
        this.won = 0;
        this.lost = 0;
        this.drawn = 0;
        this.members = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }

    public ArrayList<T> getMembers() {
        return members;
    }

    public void addPlayer(T player) {
        System.out.println();
        System.out.println("Player Draft");
        if (members.contains(player)) {
            System.out.println("Already recruited " + player.getName() + " into the team: " + this.getTeamName());
            return;
        } else {
            members.add(player);
            System.out.println("Recruited " + player.getName() + " successfully to the team: " + this.getTeamName());
        }
    }

    public void matchResult(Team<T> opponent, int teamsScore, int opponentsScore) {
        String message;
        if (teamsScore > opponentsScore) {
            this.won++;
            message = " beat ";
        } else if (teamsScore < opponentsScore) {
            this.lost++;
            message = " lost to ";
        } else {
            this.drawn++;
            message = " drew with ";
        }
        this.played++;
        if (opponent != null) {
            System.out.println(this.getTeamName() + message + opponent.getTeamName());
            opponent.matchResult(null, opponentsScore, teamsScore);
        }

    }

    public int ranking() {
        return (this.won * 3) + this.drawn;
    }

    @Override
    public int compareTo(Team<T> team) {
        if (this.ranking() > team.ranking()) {
            return -1;
        } else if (this.ranking() < team.ranking()) {
            return 1;
        } else {
            return 0;
        }
    }
}

class SpanishTeam<T extends Player> extends Team {
    public SpanishTeam(String name) {
        super(name);
    }
}

class EnglishTeam<T extends Player> extends Team {
    public EnglishTeam(String name) {
        super(name);
    }
}

class IndianTeam<T extends Player> extends Team {
    public IndianTeam(String name) {
        super(name);
    }
}

class Player {
    private String name;
    private String age;
    private String rating;

    public Player(String name, String age, String rating) {
        this.name = name;
        this.age = age;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

class FootballPlayer extends Player {

    public FootballPlayer(String name, String age, String rating) {
        super(name, age, rating);
    }
}