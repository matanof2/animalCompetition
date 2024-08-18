package animals;
import Olympics.Medal;
import graphics.CompetitionFrame;
import mobility.Point;

import java.awt.image.BufferedImage;

/**
 * The Pigeon class represents a pigeon, a type of air animal.
 * It extends the AirAnimal class.
 */
public class Pigeon extends AirAnimal {
    private String family;

    /**
     * Creates a Pigeon with the specified attributes.
     *
     * @param name the name of the pigeon
     * @param gender the gender of the pigeon
     * @param weight the weight of the pigeon
     * @param speed the speed of the pigeon
     * @param medals an array of medals the pigeon has won
     * @param wingspan the wingspan of the pigeon
     * @param family the family of the pigeon
     */
    public Pigeon(String name, Gender gender, double weight, double speed, Medal[] medals, Point position, double wingspan, String family, int maxEnergy, int energyPerMeter, String imgName){
        super(name, gender, weight, speed, medals, position, wingspan, maxEnergy, energyPerMeter);
        this.family = family;
        loadImages(imgName);
    }

    /**
     * Returns the family of the pigeon.
     *
     * @return the family
     */
    public String getFamily(){
        return this.family;
    }

    /**
     * Compares this pigeon with another animal for equal attributes.
     *
     * @param a the animal to compare with
     * @return true if the animals are equal, false otherwise
     */
    public boolean equals(Animal a){
        if (!(a instanceof Pigeon p))
            return false;
        return super.equals(p) && this.family.equals(p.getFamily());
    }
    /**
     * Returns a string representation of the pigeon.
     *
     * @return a string representation of the pigeon
     */
    public String toString(){
        return (char)27 +"[4m\nPigeon:\n" + (char)27 + "[0m" + super.toString() + "\nFamily: " + this.family + "\n";
    }
}
