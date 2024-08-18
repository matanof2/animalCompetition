package Competitions;
import java.util.Date;

public class Referee implements Runnable {
    private String name;
    private Scores scores;
    private Boolean finishFlag;

    public Referee(String name, Scores scores, Boolean finishFlag){
        this.name = name;
        this.scores = scores;
        this.finishFlag = finishFlag;
    }

    @Override
    public void run() {
        synchronized (finishFlag) {
            try {
                while (!finishFlag) {
                    finishFlag.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        scores.add(name, new Date());
    }
}
