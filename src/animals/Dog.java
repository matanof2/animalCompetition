/**
 * Submitter: Matan Ofri
 * ID: 206919680
 */
package animals;
import Olympics.Medal;
import graphics.CompetitionFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The Dog class represents a dog, a type of terrestrial animal.
 * It extends the TerrestrialAnimal class.
 */
public class Dog extends TerrestrialAnimal {
    private String breed;


    /**
     * Creates a Dog with the specified attributes.
     *
     * @param name the name of the dog
     * @param gender the gender of the dog
     * @param weight the weight of the dog
     * @param speed the speed of the dog
     * @param medals an array of medals the dog has won
     * @param breed the breed of the dog
     */

    public Dog(String name, Gender gender, double weight, double speed, Medal[] medals, String breed, int maxEnergy, int energyPerMeter, String imgName){
        super(name, gender, weight, speed, medals, maxEnergy, energyPerMeter, 4);
        this.breed = breed;
        loadImages(imgName);
    }

    /**
     * Returns the breed of the dog.
     *
     * @return the breed
     */
    public String getBreed(){
        return this.breed;
    }

    /**
     * Compares this dog with another animal for equal attributes.
     *
     * @param a the animal to compare with
     * @return true if the animals are equal, false otherwise
     */
    public boolean equals(Animal a){
        if (!(a instanceof Dog d))
            return false;
        return super.equals(d) && this.breed.equals(d.getBreed());
    }
    /**
     * Returns a string representation of the dog.
     *
     * @return a string representation of the dog
     */
    public String toString(){
        return (char)27 +"[4m\nDog:\n" + (char)27 + "[0m" + super.toString() + "\nDog breed: " + this.breed + "\n";
    }
}
