package j.chapter.java;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GLambdaExpressions {
    /* Lambda Expressions:
     * Question: There is a class Country that has methods getContinent() and getPopulation().
     * Write a function int getPopulation(List<Country> countries, String continent) that computes the total
     * population of a given continent, given a list of all countries and the name of a continent.*/

    public static void main(String[] args) {
        Country india = new Country("Asia", BigDecimal.valueOf(300032000));
        Country china = new Country("Asia", BigDecimal.valueOf(280000230));
        Country japan = new Country("Asia", BigDecimal.valueOf(10000050));

        Country southAfrica = new Country("Africa", BigDecimal.valueOf(8004500));
        Country tanzania = new Country("Africa", BigDecimal.valueOf(70006700));
        Country nigeria = new Country("Africa", BigDecimal.valueOf(10600000));
        Country cameroon = new Country("Africa", BigDecimal.valueOf(7050000));
        Country coteDIvory = new Country("Africa", BigDecimal.valueOf(70800000));

        Country brazil = new Country("South America", BigDecimal.valueOf(10000000));
        Country argentina = new Country("South America", BigDecimal.valueOf(10000000));
        Country uruguay = new Country("South America", BigDecimal.valueOf(10450000));
        Country paraguay = new Country("South America", BigDecimal.valueOf(8000400));
        Country chile = new Country("South America", BigDecimal.valueOf(7000403));

        Country usa = new Country("North America", BigDecimal.valueOf(1056000));
        Country canada = new Country("North America", BigDecimal.valueOf(104000));
        Country mexico = new Country("North America", BigDecimal.valueOf(102000));
        Country westIndies = new Country("North America", BigDecimal.valueOf(100000));

        List<Country> world = new ArrayList<>();
        world.add(india);
        world.add(china);
        world.add(japan);

        world.add(southAfrica);
        world.add(tanzania);
        world.add(nigeria);
        world.add(cameroon);
        world.add(coteDIvory);

        world.add(brazil);
        world.add(argentina);
        world.add(uruguay);
        world.add(paraguay);
        world.add(chile);

        world.add(usa);
        world.add(canada);
        world.add(mexico);
        world.add(westIndies);

        Function<List<Country>, BigDecimal> continentsPopulation = (countries) -> {
            List<BigDecimal> populationList = new ArrayList<>();
            countries.forEach(country -> {
                        System.out.println(" - " + country.getPopulation());
                        populationList.add(country.getPopulation());
                    }
            );
            return populationList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        };

        ContinentalPopulation continentalPopulation = (countries, continent) -> {
            List<Country> countryList = countries.stream().filter(s -> s.getContinent().equalsIgnoreCase(continent)).collect(Collectors.toList());
            BigDecimal population = continentsPopulation.apply(countryList);
            return population;
        };

        String continent = "Asia";
        System.out.println("Population of " + continent + " is: " + continentalPopulation.getPopulation(world, continent));

        BigDecimal population = getPopulationOfContinent(world, "North America");
        System.out.println("Population of North America is: " + population);
    }

    private static BigDecimal getPopulationOfContinent(List<Country> countriesList, String continent) {
        BigDecimal temp = new BigDecimal(0);
        BigDecimal population = countriesList
                .stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                .map(country -> country.getPopulation())
                .reduce(temp, (a, b) -> a.add(b));
        return population;
    }
}

interface ContinentalPopulation {
    public BigDecimal getPopulation(List<Country> countries, String continent);
}

class Country {
    private String continent;
    private BigDecimal population;

    public Country(String continent, BigDecimal population) {
        this.continent = continent;
        this.population = population;
    }

    public String getContinent() {
        return continent;
    }

    public BigDecimal getPopulation() {
        return population;
    }
}