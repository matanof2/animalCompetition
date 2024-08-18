package Competitions;

import animals.Animal;

public abstract class Tournament {
    protected TournamentThread tournamentThread;
    public Tournament(Animal[][] groups){
        setup(groups);
    }

    public abstract void setup(Animal[][] groups);

    public TournamentThread getTournamentThread(){
        return tournamentThread;
    }
}
