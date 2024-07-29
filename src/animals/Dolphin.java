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
 * The Dolphin class represents a dolphin, a type of water animal.
 * It extends the WaterAnimal class.
 */
public class Dolphin extends WaterAnimal {
    public enum WaterType{
        Sea, Sweet
    }
    private WaterType waterType;

    /**
     * Coreates a Dolphin with the specified attributes.
     *
     * @param name the name of the dolphin
     * @param gender the gender of the dolphin
     * @param weight the weight of the dolphin
     * @param speed the speed of the dolphin
     * @param medals an array of medals the dolphin has won
     * @param waterType the type of water the dolphin lives in
     */
    public Dolphin(String name, Animal.Gender gender, double weight, double speed, Medal[] medals, Point position, WaterType waterType, int maxEnergy, int energyPerMeter, String imgName){
        super(name, gender, weight, speed, medals, position, maxEnergy, energyPerMeter);
        this.waterType = waterType;
        loadImages(imgName);
    }

    /**
     * Returns the type of water the dolphin lives in.
     *
     * @return the water type
     */
    public WaterType getWaterType(){
        return this.waterType;
    }

    /**
     * Compares this dolphin with another animal for equal attributes.
     *
     * @param a the animal to compare with
     * @return true if the animals are equal, false otherwise
     */
    public boolean equals(Animal a){
        if (!(a instanceof Dolphin d))
            return false;
        return super.equals(d) && this.waterType == d.getWaterType();
    }
    /**
     * Returns a string representation of the dolphin.
     *
     * @return a string representation of the dolphin
     */
    public String toString(){
        return (char)27 +"[4m\nDolphin:\n" + (char)27 + "[0m" + super.toString() + "\nWater type: " + this.waterType + "\n";
    }
}
