/**
 * Submitter: Matan Ofri
 * ID: 206919680
 */
package animals;
import Olympics.Medal;
import graphics.CompetitionFrame;
import mobility.Point;

import java.awt.image.BufferedImage;

/**
 * The TerrestrialAnimal class represents a terrestrial animal.
 * It extends the Animal class.
 */
public class TerrestrialAnimal extends Animal implements ITerrestrialAnimal {
    private int noLegs;

    /**
     * Creates a TerrestrialAnimal with the specified attributes.
     *
     * @param name the name of the terrestrial animal
     * @param gender the gender of the terrestrial animal
     * @param weight the weight of the terrestrial animal
     * @param speed the speed of the terrestrial animal
     * @param medals an array of medals the terrestrial animal has won
     * @param noLegs the number of legs the terrestrial animal has
     */
    public TerrestrialAnimal(String name, Gender gender, double weight, double speed, Medal[] medals, int noLegs, int maxEnergy, int energyPerMeter){
        super(name, gender, weight, speed, medals, new Point(0, 0), maxEnergy, energyPerMeter);
        this.noLegs = noLegs;
    }

    /**
     * Returns the number of legs the terrestrial animal has.
     *
     * @return the number of legs
     */
    public int getNoLegs(){
        return this.noLegs;
    }

    /**
     * Compares this terrestrial animal with another animal for equal attributes.
     *
     * @param a the animal to compare with
     * @return true if the animals are equal, false otherwise
     */
    public boolean equals(Animal a){
        if (!(a instanceof TerrestrialAnimal t))
            return false;
        return super.equals(t) && this.noLegs == t.getNoLegs();
    }

    /**
     * Returns a string representation of the terrestrial animal.
     *
     * @return a string representation of the terrestrial animal
     */
    public String toString(){
        return super.toString() + "\nNumber of legs: " + this.noLegs;
    }
}
