package o.chapter.objectOrientedDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CJukebox {
    /* Question: Design a Musical Jukebox using Object Oriented Principles */

    public static final LinkedBlockingQueue<Song> songList = new LinkedBlockingQueue<>();
    public static final List<Song> availableSongsList = new ArrayList<>();
    public static volatile boolean exit;

    public static void main(String[] args) {
        songList.offer(new Song("Boomi Enne Sothuthe", 10, new String[]{"Anirudh"}, "Anirudh", "Ethir Neechal"));
        songList.offer(new Song("Saradaga", 6, new String[]{"Shreya Goshal"}, "Yuvan Shankar Raja", "Oy!"));
        songList.offer(new Song("Manmathan", 5, new String[]{"Sadhana Sargam"}, "Yuvan Shankar Raja", "Manmathan"));

        availableSongsList.add(new Song("Kathal Valarthen", 7, new String[]{"Simbu"}, "Yuvan Shankar Raja", "Manmathan"));
        availableSongsList.add(new Song("Vanamuna Uyiram Kaatu", 7, new String[]{"Simbu"}, "Yuvan Shankar Raja", "Manmathan"));
        availableSongsList.add(new Song("O Maargire", 7, new String[]{"Simbu"}, "Yuvan Shankar Raja", "Manmathan"));
        availableSongsList.add(new Song("Pista", 7, new String[]{"Many"}, "Shambu", "Neram"));

        Thread jukePlayerThread = new Thread(new JukePlayer(), "Jukebox Player");
        Thread userPanelThread = new Thread(new UserPanel(jukePlayerThread), "Jukebox User Panel");

        jukePlayerThread.start();
        userPanelThread.start();
    }
}

class Song {
    private String name;
    private int length;
    private String[] singers;
    private String composer;
    private String album;

    public Song(String name, int length, String[] singers, String composer, String album) {
        this.name = name;
        this.length = length;
        this.singers = singers;
        this.composer = composer;
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public String[] getSingers() {
        return singers;
    }

    public String getComposer() {
        return composer;
    }

    public String getAlbum() {
        return album;
    }
}

class JukePlayer implements Runnable {
    private volatile Song currentSong;

    @Override
    public void run() {
        while (!CJukebox.exit) {
            synchronized (CJukebox.songList) {
                if (CJukebox.songList.isEmpty()) {
                    System.out.println("Songs are over..... Please add new songs to the queue");
                    try {
                        CJukebox.songList.wait();
                    } catch (InterruptedException e) {
                        System.out.println("I Guess the songs are added");
                    }
                }
                currentSong = CJukebox.songList.poll();
            }
            if (currentSong != null) {
                System.out.println("Currently playing: " + currentSong.getName() + " : Length: " + currentSong.getLength() + " seconds.");
                try {
                    TimeUnit.SECONDS.sleep(currentSong.getLength());
                } catch (InterruptedException e) {
                    System.out.println("Interrupted the song");
                    return;
                }
            }
        }
    }
}

class UserPanel implements Runnable {
    public volatile Scanner inputSelection = new Scanner(System.in);
    protected Thread jukePlayerThread;

    public UserPanel(Thread jukePlayerThread) {
        this.jukePlayerThread = jukePlayerThread;
    }

    @Override
    public void run() {
        while (!CJukebox.exit) {
            synchronized (CJukebox.availableSongsList) {
                System.out.println("Please enter the selection");
                AtomicInteger counter = new AtomicInteger();
                counter.incrementAndGet();
                for (Song song : CJukebox.availableSongsList) {
                    System.out.println(counter + ") " + song.getName());
                    counter.incrementAndGet();
                }
            }
            String selectedOption = inputSelection.nextLine();
            if (selectedOption != null && !selectedOption.isEmpty()) {
                //TODO Currently the user inputs except the number will throw the Exception. Handle it
                if (selectedOption.equalsIgnoreCase("-1")) {
                    System.out.println("Exiting the Application");
                    CJukebox.exit = true;
                    jukePlayerThread.interrupt();
                    return;
                }
                if (Integer.valueOf(selectedOption) <= CJukebox.availableSongsList.size()) {
                    addSong(CJukebox.availableSongsList.get(Integer.valueOf(selectedOption) - 1), selectedOption);
                } else {
                    System.out.println("Your selection seems to be unavailable. Please select again");
                }
            }
        }
    }

    private void addSong(Song song, String selectedOption) {
        synchronized (CJukebox.songList) {
            CJukebox.songList.add(CJukebox.availableSongsList.get(Integer.valueOf(selectedOption) - 1));
            System.out.println("Added the song: " + CJukebox.availableSongsList.get(Integer.valueOf(selectedOption) - 1).getName());
            CJukebox.songList.notify();
        }
    }
}