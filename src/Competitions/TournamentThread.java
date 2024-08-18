package Competitions;
import java.util.Date;
import java.util.Map;

public class TournamentThread implements Runnable {
    private Scores scores;
    private Boolean startSignal;
    private int groups;

    public TournamentThread(Scores scores, Boolean startFlag, int groups) {
        this.scores = scores;
        startSignal = startFlag;
        this.groups = groups;
    }

    @Override
    public void run() {
        synchronized (startSignal) {
            startSignal = true;
            startSignal.notifyAll();
        }

        while (true) {
            synchronized (scores) {
                Map<String, Date> allScores = scores.getAll();
                // Update display with allScores
            }

            try {
                Thread.sleep(1000); // Update interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
