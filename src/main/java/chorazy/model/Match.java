package chorazy.model;

import java.util.Random;

public class Match {

    private final Random RANDOM = new Random();

    private final Country countryHome;
    private final Country countryAway;

    private int homeGoals = 0;
    private int awayGoals = 0;

    public Match(Pair pair) {
        this.countryHome = pair.element1();
        this.countryAway = pair.element2();
    }

    public MatchResult play() {
        int sumOfPotential = countryHome.getPotential().getSupport() + countryAway.getPotential().getSupport();
        double rangeOfStatusQuo = (sumOfPotential * 0.5) / 2;
        boolean play = true;
        double iterations = 0;
        while (play) {
            int result = RANDOM.nextInt(sumOfPotential);
            if (result < countryHome.getPotential().getSupport() - rangeOfStatusQuo) {
                if (homeAttack()) {
                    homeGoals++;
                }
            } else if (result <= countryHome.getPotential().getSupport() + rangeOfStatusQuo) {
            } else {
                if (awayAttack()) {
                    awayGoals++;
                }
            }
            if (RANDOM.nextInt(10) > 12 - iterations) {
                play = false;
            } else {
                iterations++;
            }
        }
        return new MatchResult(countryHome, countryAway, homeGoals, awayGoals);
    }

    private boolean homeAttack() {
        int sumOfPotential = countryHome.getPotential().getAttack() + countryAway.getPotential().getDefense();
        int result = RANDOM.nextInt(sumOfPotential);
        return result < countryHome.getPotential().getAttack();
    }

    private boolean awayAttack() {
        int sumOfPotential = countryAway.getPotential().getAttack() + countryHome.getPotential().getDefense();
        int result = RANDOM.nextInt(sumOfPotential);
        return result < countryAway.getPotential().getAttack();
    }
}
