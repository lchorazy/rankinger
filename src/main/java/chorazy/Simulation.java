package chorazy;

import chorazy.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Simulation {

    public final List<Country> countries;

    public Simulation() {
        countries = new ArrayList<>();
        countries.add(new Country("Germany", new Potential(92, 92, 92)));
        countries.add(new Country("Spain", new Potential(90, 90, 90)));
        countries.add(new Country("Italy", new Potential(87, 87, 87)));
        countries.add(new Country("France", new Potential(83, 83, 83)));
        countries.add(new Country("Belgium", new Potential(82, 82, 82)));
        countries.add(new Country("Portugal", new Potential(81, 81, 81)));
        countries.add(new Country("England", new Potential(80, 80, 80)));
        countries.add(new Country("Croatia", new Potential(75, 75, 75)));
        countries.add(new Country("Wales", new Potential(72, 72, 72)));
        countries.add(new Country("Ukraine", new Potential(70, 70, 70)));
        countries.add(new Country("Russia", new Potential(65, 65, 65)));
        countries.add(new Country("Switzerland", new Potential(64, 64, 64)));
        countries.add(new Country("Poland", new Potential(65, 65, 65)));
        countries.add(new Country("Austria", new Potential(59, 59, 59)));
        countries.add(new Country("Slovenia", new Potential(58, 58, 58)));
        countries.add(new Country("Czech Republic", new Potential(56, 56, 56)));
        countries.add(new Country("Turkey", new Potential(55, 55, 55)));
        countries.add(new Country("Ireland", new Potential(46, 46, 46)));
        countries.add(new Country("Albania", new Potential(28, 28, 28)));
        countries.add(new Country("Israel", new Potential(20, 20, 20)));
        countries.add(new Country("Norway", new Potential(18, 18, 18)));
        countries.add(new Country("Bulgaria", new Potential(18, 18, 18)));
        countries.add(new Country("Lithuania", new Potential(15, 15, 15)));
        countries.add(new Country("Estonia", new Potential(9, 9, 9)));
        countries.add(new Country("San Marino", new Potential(5, 5, 5)));
    }

    List<Country> run() {
        List<Pair> pairs = Pair.createPairs(countries);
        for (int i = 0; i < 50; i++) {
            pairs.forEach(this::playMatch);
            countries.forEach(Country::advanceToNextSeason);
        }
        countries.sort((o1, o2) -> Double.compare(o2.getTotalScore(), o1.getTotalScore()));
        return countries;
    }

    private static final int MULTIPLIER = 32;

    private void playMatch(Pair pair) {
        MatchResult matchResult = new Match(pair).play();
        Country countryHome = pair.element1();
        Country countryAway = pair.element2();
        double impactHome = countryHome.getTotalScore() / (countryHome.getTotalScore() + countryAway.getTotalScore());
        double impactAway = 1d - impactHome;
        if (matchResult.getWinner().isPresent()) {
            if (matchResult.getWinner().get().equals(countryHome.getName())) {
                countryHome.addWin((1 - impactHome) * MULTIPLIER, matchResult);
                countryAway.addLose((0 - impactAway) * MULTIPLIER, matchResult);
            } else {
                countryHome.addLose((0 - impactHome) * MULTIPLIER, matchResult);
                countryAway.addWin((1 - impactAway) * MULTIPLIER, matchResult);
            }
        } else {
            countryHome.addDraw((0.5 - impactHome) * MULTIPLIER, matchResult);
            countryAway.addDraw((0.5 - impactAway) * MULTIPLIER, matchResult);
        }
    }
}
