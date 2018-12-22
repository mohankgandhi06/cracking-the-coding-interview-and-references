package j.chapter.java;

import java.util.ArrayList;
import java.util.Collections;

public class ZGenericsInCricket {
    public static void main(String[] args) {
        CricketLeague ipl = new CricketLeague("IPL");
        CricketTeam chennai = new CricketTeam("CSK");
        CricketTeam kolkata = new CricketTeam("KKR");
        CricketTeam delhi = new CricketTeam("DD");
        CricketTeam kerala = new CricketTeam("KK");
        CricketTeam mumbai = new CricketTeam("MI");
        ipl.addTeam(chennai);
        ipl.addTeam(kolkata);
        ipl.addTeam(delhi);
        ipl.addTeam(kerala);
        ipl.addTeam(mumbai);
        chennai.setPoints(3);
        kolkata.setPoints(1);
        delhi.setPoints(3);
        kerala.setPoints(1);
        mumbai.setPoints(3);
        chennai.setPoints(3);
        ipl.showTeams();
        ipl.standings();
    }
}

class CricketLeague<T extends CricketTeam> {
    public String leagueName;
    public ArrayList<T> teams;

    public CricketLeague(String leagueName) {
        this.leagueName = leagueName;
        teams = new ArrayList<>();
    }

    public void addTeam(T team) {
        this.teams.add(team);
    }

    public void showTeams() {
        System.out.println("League: " + this.leagueName);
        for (T team : teams) {
            System.out.println(".." + team.teamName + " Points: " + team.getPoints());
        }
    }

    public void standings() {
        Collections.sort(teams);
        System.out.println("Standings:");
        showTeams();
    }
}

/* In generics we are defining that the Cricket Team can contain only a Cricket Player with it*/
class CricketTeam<T extends CricketPlayer> implements Comparable<CricketTeam<T>> {
    public String teamName;
    public ArrayList<T> players;
    private int points;

    public CricketTeam(String teamName) {
        this.teamName = teamName;
        this.players = new ArrayList<>();
    }

    public void addPlayer(T player) {
        players.add(player);
        System.out.println("Added: " + player.getName() + " to the team: " + this.teamName);
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return this.points;
    }

    public void showTeam() {
        System.out.println("Team: " + this.teamName);
        for (T player : players) {
            System.out.println(".." + player.getName());
        }
    }

    @Override
    public int compareTo(CricketTeam<T> o) {
        if (this.points > o.points) {
            return -1;
        } else if (this.points < o.points) {
            return 1;
        } else {
            return 0;
        }
    }
}

class CricketPlayer extends Player {
    public CricketPlayer(String name, String age, String rating) {
        super(name, age, rating);
    }
}