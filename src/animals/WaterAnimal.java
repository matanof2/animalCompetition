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
 * The WaterAnimal class represents a water animal.
 * It extends the Animal class.
 */
public class WaterAnimal extends Animal implements IWaterAnimal {
    private static final double MAX_DIVE = -800;
    private double diveDept;

    /**
     * Creates a WaterAnimal with the specified attributes.
     *
     * @param name the name of the water animal
     * @param gender the gender of the water animal
     * @param weight the weight of the water animal
     * @param speed the speed of the water animal
     * @param medals an array of medals the water animal has won
     */
    public WaterAnimal(String name, Gender gender, double weight, double speed, Medal[] medals, Point position, int maxEnergy, int energyPerMeter){
        super(name, gender, weight, speed, medals, position, maxEnergy, energyPerMeter);
        this.diveDept = 0; // default based on default position(y=0)
    }

    /**
     * Method that adjusts the dive depth of the water animal.
     *
     * @param dive the amount to dive (negative for diving, positive for ascending)
     * @return true if the dive depth was successfully adjusted, false otherwise
     */
    public boolean dive(double dive){
        if(dive > 0) { // if the user wants the animal to ascend(double dive will be positive)
            if (dive + this.diveDept > 0) { //  if the distance to ascend is bigger than the current dept
                System.out.println("Cant ascend higher than the water level");
                return false;
            } else {
                this.diveDept += dive;
                return true;
            }
        }
        else { // if the user wants the animal to dive(double dive will be negative)
            if (dive + this.diveDept <= MAX_DIVE) { // if the distance will get the animal to dive lower than the MAX_DIVE
                System.out.println("Reached maximum dept, cant dive any deeper");
                this.diveDept = MAX_DIVE;
                return true;
            } else {
                this.diveDept += dive;
                return true;
            }
        }
    }

    /**
     * returns the maximum depth the water animal can dive to
     * @return MAX_DIVE
     */
    protected double getMAX_DIVE(){
        return MAX_DIVE;
    }

    /**
     * Returns the dive depth of the water animal.
     *
     * @return the dive depth
     */
    public double getDiveDept(){
        return this.diveDept;
    }

    /**
     * Compares this water animal with another animal for equal attributes.
     *
     * @param a the animal to compare with
     * @return true if the animals are equal, false otherwise
     */
    public boolean equals(Animal a){
        if (!(a instanceof WaterAnimal w))
            return false;
        return super.equals(w) && this.diveDept == w.getDiveDept();
    }

    /**
     * Returns a string representation of the water animal.
     *
     * @return a string representation of the water animal
     */
    public String toString(){
        return super.toString() + "\nMax Dept: " + MAX_DIVE + "\nCurrent dept: " + diveDept;
    }
}
