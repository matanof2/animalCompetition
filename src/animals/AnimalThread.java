package animals;
import graphics.ZooPanel;

public class AnimalThread implements Runnable {
    private Animal participant;
    private double neededDistance;
    private Boolean startFlag;
    private Boolean finishFlag;
    private ZooPanel zooPanel;
    private String competitionType;
    private String tournamentType;
    private static int sleepTime = 100;

    public AnimalThread(Animal participant, double neededDistance, Boolean startFlag, Boolean finishFlag, ZooPanel zooPanel, String competitionType, String tournamentType) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.zooPanel = zooPanel;
        this.competitionType = competitionType;
        this.tournamentType = tournamentType;
    }

    public void setSleepTime(int time){
        this.sleepTime = time;
    }

    @Override
    public void run() {
        synchronized (startFlag) {
            try {
                while (!startFlag) {
                    startFlag.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        while (!Thread.interrupted() && !finishFlag) {
            synchronized (participant) {
                // הנעת החיה קדימה
                zooPanel.moveAnimalWithAnimation(participant, competitionType);//, tournamentType);

                // בדיקה האם הגענו למרחק הנדרש
                if (participant.getDistance() >= neededDistance) {
                    synchronized (finishFlag) {
                        finishFlag = true;
                        finishFlag.notifyAll();
                    }
                    return;
                }
            }

            // שינה למשך זמן מסוים
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
