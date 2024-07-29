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
 * The AirAnimal class represents an animal that can fly. It extends the Animal class.
 */
public class AirAnimal extends Animal {
    private double wingspan;

    /**
     * Creates an AirAnimal with the specified attributes.
     *
     * @param name the name of the air animal
     * @param gender the gender of the air animal
     * @param weight the weight of the air animal
     * @param speed the speed of the air animal
     * @param medals an array of medals the air animal has won
     * @param wingspan the wingspan of the air animal
     */
    public AirAnimal(String name, Gender gender, double weight, double speed, Medal[] medals, Point position, double wingspan,  int maxEnergy, int energyPerMeter){
        super(name, gender, weight, speed, medals, position, maxEnergy, energyPerMeter);
        this.wingspan = wingspan;
    }

    /**
     * Returns the wingspan of the AirAnimal.
     *
     * @return the wingspan
     */
    public double getWingspan(){
        return this.wingspan;
    }

    /**
     * Compares this AirAnimal with another Animal for equal attributes.
     *
     * @param a the Animal to compare with
     * @return true if the Animals are equal, false otherwise
     */
    public boolean equals(Animal a){
        if (!(a instanceof AirAnimal aa))
            return  false;
        return super.equals(aa) && this.wingspan == aa.getWingspan();
    }
    /**
     * Returns a string representation of the AirAnimal.
     *
     * @return a string representation of the AirAnimal
     */
    public String toString(){
        return super.toString() + "\nWingspan: " + this.wingspan;
    }
}
