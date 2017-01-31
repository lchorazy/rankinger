package chorazy.model;

import java.util.Optional;

public class MatchResult {

    private final String countryHome;

    private final String countryAway;

    private final Optional<String> winner;

    private final int homeGoals;
    private final int awayGoals;

    public MatchResult(Country countryHome, Country countryAway, int homeGoals, int awayGoals) {
        Optional<String> winner = Optional.empty();
        if (homeGoals > awayGoals) {
            winner = Optional.of(countryHome.getName());

        } else if (awayGoals > homeGoals) {
            winner = Optional.of(countryAway.getName());
        }
        this.countryHome = countryHome.getName();
        this.countryAway = countryAway.getName();
        this.winner = winner;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public String getCountryHome() {
        return countryHome;
    }

    public String getCountryAway() {
        return countryAway;
    }

    public Optional<String> getWinner() {
        return winner;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "countryHome=" + countryHome +
                ", countryAway=" + countryAway +
                ", winner=" + winner +
                ", homeGoals=" + homeGoals +
                ", awayGoals=" + awayGoals +
                '}';
    }
}
