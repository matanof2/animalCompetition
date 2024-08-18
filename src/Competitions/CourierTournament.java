package Competitions;
import animals.Animal;
import animals.AnimalThread;
import graphics.ZooPanel;
import java.util.Arrays;

public class CourierTournament extends Tournament {
    private ZooPanel zooPanel;
    private String competitionType;
    private String tournamentType;

    public CourierTournament(Animal[][] groups, ZooPanel zooPanel, String competitionType, String tournamentType){
        super(groups);
        this.zooPanel = zooPanel;this.zooPanel = zooPanel;
        this.competitionType = competitionType;
        this.tournamentType = tournamentType;
    }

    public void setup(Animal[][] groups){
        Boolean startFlag = false;
        Scores scores = new Scores();
        Boolean[] flags;
        double distanceNeeded;
        double width = zooPanel.getWidth(), height = zooPanel.getHeight();
        for(int i = 0; i < groups.length; i++) {
            flags = new Boolean[groups[i].length];
            Arrays.fill(flags, false);
            distanceNeeded = (2 * (width - 65) + 2 * (height - 65)) / groups[i].length;
            AnimalThread animalThread = new AnimalThread(groups[i][0], distanceNeeded, startFlag, flags[0], zooPanel, competitionType, tournamentType);
            Thread thread = new Thread(animalThread);
            thread.start();
            for (int j = 1; j < groups[i].length; j++){
                animalThread = new AnimalThread(groups[i][j], distanceNeeded, flags[j - 1], flags[j], zooPanel, competitionType, tournamentType);
                thread = new Thread(animalThread);
                thread.start();
            }
            Referee ref = new Referee(String.valueOf(i), scores, flags[flags.length - 1]);
            thread = new Thread(ref);
            thread.start();
            TournamentThread tThread = new TournamentThread(scores, startFlag, groups.length);
            thread = new Thread(tThread);
            thread.start();
        }
    }
}