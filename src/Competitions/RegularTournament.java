package Competitions;

import animals.Animal;
import animals.AnimalThread;
import graphics.ZooPanel;

public class RegularTournament extends Tournament {
    private ZooPanel zooPanel;
    private String competitionType;
    private String tournamentType;

    public RegularTournament(Animal[][] groups, ZooPanel zooPanel, String competitionType, String tournamentType){
        super(groups);
        this.zooPanel = zooPanel;
        this.competitionType = competitionType;
        this.tournamentType = tournamentType;
    }


    @Override
    public void setup(Animal[][] groups) {
        Boolean startFlag = false;
        Scores scores = new Scores();
        double width = zooPanel.getWidth(), height = zooPanel.getHeight();
        double distanceNeeded = 2 * width + 2 * height;
        for(int i = 0; i < groups.length; i++){
            Boolean finishFlag = false;
            AnimalThread animalThread = new AnimalThread(groups[i][0], distanceNeeded, startFlag, finishFlag, zooPanel, competitionType, tournamentType);
            Thread thread = new Thread(animalThread);
            thread.start();
            Referee ref = new Referee(String.valueOf(i), scores, finishFlag);
            thread = new Thread(ref);
            thread.start();
            TournamentThread tThread = new TournamentThread(scores, startFlag, groups.length);
            thread = new Thread(tThread);
            thread.start();
        }
    }
}