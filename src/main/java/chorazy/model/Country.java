package chorazy.model;

import com.google.common.base.Objects;

import java.util.LinkedList;

public class Country {

    private String name;
    private Potential potential;
    private Last5Seasons last5Seasons = new Last5Seasons();
    private Season currentSeason = new Season();
    private int totalScore = 1200;

    public Country(String name, Potential potential) {
        this.name = name;
        this.potential = potential;
    }

    public void advanceToNextSeason() {
        last5Seasons.add(currentSeason);
        currentSeason = new Season();
        totalScore = last5Seasons.avg();
    }

    public void addWin(double d, MatchResult matchResult) {
        currentSeason.addWin(d, matchResult);
    }

    public void addDraw(double d, MatchResult matchResult) {
        currentSeason.addDraw(d, matchResult);
    }

    public void addLose(double d, MatchResult matchResult) {
        currentSeason.addLose(d, matchResult);
    }

    public String getName() {
        return name;
    }

    public Potential getPotential() {
        return potential;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public LinkedList<Season> getLast5Seasons() {
        return last5Seasons.getList();
    }

    public Season getCurrentSeason() {
        return currentSeason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return potential == country.potential &&
                totalScore == country.totalScore &&
                Objects.equal(name, country.name) &&
                Objects.equal(last5Seasons, country.last5Seasons) &&
                Objects.equal(currentSeason, country.currentSeason);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, potential, last5Seasons, currentSeason, totalScore);
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", potential=" + potential +
                ", last5Seasons=" + last5Seasons +
                ", currentSeason=" + currentSeason +
                ", totalScore=" + totalScore +
                '}';
    }
}
