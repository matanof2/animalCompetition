/**
 * Submitter: Matan Ofri
 * ID: 206919680
 */
package Olympics;

import java.util.Objects;

/**
 * The Medal class represents a medal won in a tournament.
 */
public class Medal {
    public enum Type {
        bronze, silver, gold
    }
    private Type type;
    private String tournament;
    private int year;

    /**
     * Constructs a Medal with the specified\default type, tournament, and default year.
     *
     * @param type the type of the medal
     * @param tournament the name of the tournament
     */
    public Medal(Type type, String tournament){
        if(type.equals(Type.gold) || type.equals(Type.silver))
            this.type = type;
        else
            this.type = Type.bronze;
        if (tournament.isEmpty())
            this.tournament = "General tournament";
        else
            this.tournament = tournament;
        this.year = 2023;
    }

    /**
     * Constructs a Medal with the specified type, tournament, and year.
     *
     * @param type the type of the medal
     * @param tournament the name of the tournament
     * @param year the year the medal was won
     */
    public Medal(Type type, String tournament, int year) {
        this.type = type;
        this.tournament = tournament;
        this.year = year;
    }

    /**
     * Returns the type of the medal.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns the name of the tournament.
     *
     * @return the tournament name
     */
    public String getTournament() {
        return tournament;
    }

    /**
     * Returns the year the medal was won.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Compares this medal with another medal for equality.
     *
     * @param medal the medal to compare with
     * @return true if the medals are equal, false otherwise
     */
    public boolean equals(Medal medal){
        return this.type == medal.getType() && this.tournament.equals(medal.getTournament()) && this.year == medal.getYear();
    }

    /**
     * Returns a string representation of the medal.
     *
     * @return a string representation of the medal
     */
    public String toString() {
        return type + " medal, won at the " + tournament + " tournament in " + year;
    }
}
