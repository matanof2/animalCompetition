/**
 * Submitter: Matan Ofri
 * ID: 206919680
 */
package animals;
import Olympics.Medal;
import graphics.CompetitionFrame;

import java.awt.image.BufferedImage;

/**
 * The Snake class represents a snake, a type of terrestrial animal.
 * It extends the TerrestrialAnimal class and implements the IReptile interface.
 */
public class Snake extends TerrestrialAnimal implements IReptile {
    public enum Toxicity{
        Low, Medium, High
    }
    private Toxicity toxicityLvl;
    private double length;

    /**
     * Creates a Snake with the specified attributes.
     *
     * @param name the name of the snake
     * @param gender the gender of the snake
     * @param weight the weight of the snake
     * @param speed the speed of the snake
     * @param medals an array of medals the snake has won
     * @param toxicityLvl the toxicity level of the snake
     * @param length the length of the snake
     */
    public Snake(String name, Gender gender, double weight, double speed, Medal[] medals, Toxicity toxicityLvl, double length, int maxEnergy, int energyPerMeter, String imgName){
        super(name, gender, weight, speed, medals, maxEnergy, energyPerMeter, 0);
        this.toxicityLvl = toxicityLvl;
        this.length = length;
        loadImages(imgName);
    }

    /**
     * Speeds up the snake by the specified amount.
     *
     * @param speed the amount to speed up
     * @return true if the speed was successfully changed, false otherwise
     */
    public boolean speedUp(int speed) {
        return super.setSpeed(speed, MAX_SPEED);
    }

    /**
     * Returns the toxicity level of the snake.
     *
     * @return the toxicity level
     */
    public Toxicity getToxicityLevel(){
        return this.toxicityLvl;
    }

    /**
     * Returns the length of the snake.
     *
     * @return the length
     */
    public double getLength(){
        return this.length;
    }

    /**
     * Compares this snake with another animal for equal attributes.
     *
     * @param a the animal to compare with
     * @return true if the animals are equal, false otherwise
     */
    public boolean equals(Animal a){
        if (!(a instanceof Snake s))
            return false;
        return super.equals(s) && this.toxicityLvl == s.getToxicityLevel() && this.length == s.getLength();
    }

    /**
     * Returns a string representation of the snake.
     *
     * @return a string representation of the snake
     */
    public String toString(){
        return (char)27 +"[4m\nSnake:\n" + (char)27 + "[0m" + super.toString() + "\nToxicity level: " + this.toxicityLvl + "\nLength: " + this.length + "\n";
    }
}
