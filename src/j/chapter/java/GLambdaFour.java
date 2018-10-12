package j.chapter.java;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class GLambdaFour {
    /*  Flat Map  */
    public static void main(String[] args) {
        GamesStore gamesStore = new GamesStore();
        gamesStore.setPcGamesList(Arrays.asList(
                new PCGames("Witcher 3", new BigDecimal(2000), 20, Arrays.asList(
                        "John", "Matt", "Lima", "Portu"
                )),
                new PCGames("Batman: Arkham Origins", new BigDecimal(2500), 25, Arrays.asList(
                        "Mary", "Melinda", "Joseph", "Christopher"
                )),
                new PCGames("Gears of War", new BigDecimal(1800), 30, Arrays.asList(
                        "John", "Arya", "Sansa", "Tyrion"
                )),
                new PCGames("Fallout 4", new BigDecimal(2200), 25, Arrays.asList(
                        "Natalie", "Snow", "Kira", "Simba"
                )),
                new PCGames("Dishonoured", new BigDecimal(1799), 10, Arrays.asList(
                        "Monty", "Maanik", "Roger", "Steve"
                ))
        ));

        long count = gamesStore.getPcGamesList().stream().flatMap(s -> s.getDeveloper().stream()).filter(t -> t.startsWith("M")).peek(System.out::println).count();
        System.out.println("Count of M: " + count);

        System.out.println("Smallest Game in Size: ");
        gamesStore.getPcGamesList().stream().reduce(((pcGamesOne, pcGamesTwo) -> pcGamesOne.getSizeInGB() < pcGamesTwo.getSizeInGB() ? pcGamesOne : pcGamesTwo)).ifPresent(a -> System.out.println(a.getName() + " Size: " + a.getSizeInGB()));
    }
}

class GamesStore {
    List<PCGames> pcGamesList;
    List<PSGames> psGamesList;
    List<XBOXGames> xboxGamesList;

    public List<PCGames> getPcGamesList() {
        return pcGamesList;
    }

    public void setPcGamesList(List<PCGames> pcGamesList) {
        this.pcGamesList = pcGamesList;
    }

    public List<PSGames> getPsGamesList() {
        return psGamesList;
    }

    public void setPsGamesList(List<PSGames> psGamesList) {
        this.psGamesList = psGamesList;
    }

    public List<XBOXGames> getXboxGamesList() {
        return xboxGamesList;
    }

    public void setXboxGamesList(List<XBOXGames> xboxGamesList) {
        this.xboxGamesList = xboxGamesList;
    }
}

class Games {
    private String name;
    private BigDecimal price;
    private int sizeInGB;
    private List<String> developer;

    public Games(String name, BigDecimal price, int sizeInGB, List<String> developer) {
        this.name = name;
        this.price = price;
        this.sizeInGB = sizeInGB;
        this.developer = developer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSizeInGB() {
        return sizeInGB;
    }

    public void setSizeInGB(int sizeInGB) {
        this.sizeInGB = sizeInGB;
    }

    public List<String> getDeveloper() {
        return developer;
    }

    public void setDeveloper(List<String> developer) {
        this.developer = developer;
    }
}

class PCGames extends Games {
    public PCGames(String name, BigDecimal price, int sizeInGB, List<String> developer) {
        super(name, price, sizeInGB, developer);
    }
}

class PSGames extends Games {
    public PSGames(String name, BigDecimal price, int sizeInGB, List<String> developer) {
        super(name, price, sizeInGB, developer);
    }
}

class XBOXGames extends Games {
    public XBOXGames(String name, BigDecimal price, int sizeInGB, List<String> developer) {
        super(name, price, sizeInGB, developer);
    }
}