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
 * The Whale class represents a whale, a type of water animal.
 * It extends the WaterAnimal class.
 */
public class Whale extends WaterAnimal {
    private String foodType;

    /**
     * Creates a Whale with the specified attributes.
     *
     * @param name the name of the whale
     * @param gender the gender of the whale
     * @param weight the weight of the whale
     * @param speed the speed of the whale
     * @param medals an array of medals the whale has won
     * @param foodType the type of food the whale eats
     */
    public Whale(String name, Animal.Gender gender, double weight, double speed, Medal[] medals, Point position, String foodType, int maxEnergy, int energyPerMeter, String imgName){
        super(name, gender, weight, speed, medals, position, maxEnergy, energyPerMeter);
        this.foodType = foodType;
        loadImages(imgName);
    }

    /**
     * Returns the type of food the whale eats.
     *
     * @return the food type
     */
    public String getFoodType(){
        return this.foodType;
    }

    /**
     * Compares this whale with another animal for equal attributes.
     *
     * @param a the animal to compare with
     * @return true if the animals are equal, false otherwise
     */
    public boolean equals(Animal a){
        if (!(a instanceof Whale w))
            return false;
        return super.equals(w) && this.foodType.equals(w.getFoodType());
    }

    /**
     * Returns a string representation of the whale.
     *
     * @return a string representation of the whale
     */
    public String toString(){
        return (char)27 +"[4m\nWhale:\n" + (char)27 + "[0m" + super.toString() + "\nFood type: " + this.foodType + "\n";
    }
}
