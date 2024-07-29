/**
 * Submitter: Matan Ofri
 * ID: 206919680
 */
package animals;
import Olympics.Medal;
import graphics.CompetitionFrame;

import java.awt.image.BufferedImage;

/**
 * The Cat class represents a cat, a type of terrestrial animal.
 * It extends the TerrestrialAnimal class.
 */
public class Cat extends TerrestrialAnimal {
    private boolean castrated;

    /**
     * Creates a Cat with the specified attributes.
     *
     * @param name the name of the cat
     * @param gender the gender of the cat
     * @param weight the weight of the cat
     * @param speed the speed of the cat
     * @param medals an array of medals the cat has won
     * @param castrated whether the cat is castrated
     */
    public Cat(String name, Gender gender, double weight, double speed, Medal[] medals, boolean castrated, int maxEnergy, int energyPerMeter, String imgName){
        super(name, gender, weight, speed, medals, maxEnergy, energyPerMeter, 4);
        this.castrated = castrated;
        loadImages(imgName);
    }

    /**
     * Returns whether the cat is castrated.
     *
     * @return true if the cat is castrated, false otherwise
     */
    public boolean getCastrated(){
        return this.castrated;
    }

    /**
     * Compares this cat with another animal for equal attributes.
     *
     * @param a the animal to compare with
     * @return true if the animals are equal, false otherwise
     */
    public boolean equals(Animal a){
        if (!(a instanceof Cat c))
            return false;
        return super.equals(c) && this.castrated == c.getCastrated();
    }
    /**
     * Returns a string representation of the cat.
     *
     * @return a string representation of the cat
     */
    public String toString(){
        return (char)27 +"[4m\nCat:\n" + (char)27 + "[0m" + super.toString() + "\nCastrated: " + this.castrated + "\n";
    }
}
