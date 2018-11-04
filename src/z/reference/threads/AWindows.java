package z.reference.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AWindows {

    public static class Player extends Thread {
        private List<Song> playlist = new ArrayList<>();

        public Player(List<Song> playlist) {
            this.playlist = playlist;
        }

        public void setPlaylist(List<Song> playlist) {
            this.playlist = playlist;
        }

        @Override
        public void run() {
            try {
                System.out.println("Inside a Player Thread Class");
                for (Song song : playlist) {
                    System.out.println("Playing Song: " + song.getSongName());
                    System.out.println(song.getSongNotes());
                    TimeUnit.SECONDS.sleep(song.getSongLength());
                    System.out.println();
                }
            } catch (InterruptedException interruptedException) {
                System.out.println(interruptedException.getMessage() + " Inside Player");
            }
        }
    }

    public static class Game implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Inside a Game Runnable Interface");
                System.out.println("Started Witcher Game");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Stage One");
            } catch (InterruptedException interruptedException) {
                System.out.println(interruptedException.getMessage() + "Inside Game");
            }
        }
    }

    public static void main(String[] args) {
        List<Song> tamilPlaylist = Arrays.asList(
                new Song("Nilayoo", 3, "Nilaaayooooo......"),
                new Song("Chakarai Nilave", 5, "Chakarai Nilavae......"),
                new Song("Nenjam Ellam", 2, "Nenjam Ellam Kadhal......"),
                new Song("Break the Rules", 4, "Break the Rules......"),
                new Song("Eppadi Irunthe", 6, "..... Malai Varuthe..."),
                new Song("Kannan Varum", 1, "Nilaaayooooo......"),
                new Song("Lets take a Selfie Pulla ", 8, "...Facebookil Pichikidum Like'u Share'u thaaan...")
        );
        Player mediaPlayer = new Player(tamilPlaylist);
        mediaPlayer.start();
        /*Game witcher = new Game();
        Thread gameThread = new Thread(witcher);
        gameThread.start();*/
    }
}

class Song {
    private String songName;
    private int songLength;
    private String songNotes;

    public Song(String songName, int songLength, String songNotes) {
        this.songName = songName;
        this.songLength = songLength;
        this.songNotes = songNotes;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getSongLength() {
        return songLength;
    }

    public void setSongLength(int songLength) {
        this.songLength = songLength;
    }

    public String getSongNotes() {
        return songNotes;
    }

    public void setSongNotes(String songNotes) {
        this.songNotes = songNotes;
    }
}