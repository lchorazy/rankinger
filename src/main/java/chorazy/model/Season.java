package chorazy.model;

import java.util.ArrayList;
import java.util.List;

public class Season {

    private List<MatchResult> winningResults = new ArrayList<>();
    private List<MatchResult> drawingResults = new ArrayList<>();
    private List<MatchResult> losingResults = new ArrayList<>();

    private int wins = 0;
    private int draws = 0;
    private int losses = 0;

    private double score = 1200.0;

    void addWin(double d, MatchResult matchResult) {
        score += d;
        wins += 1;
        winningResults.add(matchResult);
    }

    void addDraw(double d, MatchResult matchResult) {
        score += d;
        draws += 1;
        drawingResults.add(matchResult);
    }

    void addLose(double d, MatchResult matchResult) {
        score += d;
        losses += 1;
        losingResults.add(matchResult);
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public double getScore() {
        return score;
    }

    public List<MatchResult> getWinningResults() {
        return winningResults;
    }

    public void setWinningResults(List<MatchResult> winningResults) {
        this.winningResults = winningResults;
    }

    public List<MatchResult> getDrawingResults() {
        return drawingResults;
    }

    public void setDrawingResults(List<MatchResult> drawingResults) {
        this.drawingResults = drawingResults;
    }

    public List<MatchResult> getLosingResults() {
        return losingResults;
    }

    public void setLosingResults(List<MatchResult> losingResults) {
        this.losingResults = losingResults;
    }

    @Override
    public String toString() {
        return "Season{" +
                "winningResults=" + winningResults +
                ", drawingResults=" + drawingResults +
                ", losingResults=" + losingResults +
                ", wins=" + wins +
                ", draws=" + draws +
                ", losses=" + losses +
                ", score=" + score +
                '}';
    }

    void roundScore() {
        score = Math.round(score);
    }
}
