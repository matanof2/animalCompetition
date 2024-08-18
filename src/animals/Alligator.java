package animals;
import Olympics.Medal;
import graphics.CompetitionFrame;
import mobility.Point;

import java.awt.image.BufferedImage;

/**
 * The Alligator class represents an alligator, a type of water animal and a reptile.
 * It extends the WaterAnimal class and implements the IReptile interface.
 */
public class Alligator extends Animal implements IWaterAnimal, ITerrestrialAnimal, IReptile {
    private String AreaOfLiving;
    private IWaterAnimal water;
    private ITerrestrialAnimal land;
    private String compType;

    /**
     * Creates an Alligator with the specified attributes.
     *
     * @param name the name of the alligator
     * @param gender the gender of the alligator
     * @param weight the weight of the alligator
     * @param speed the speed of the alligator
     * @param medals an array of medals the alligator has won
     * @param areaOfLiving the area where the alligator lives
     */
    public Alligator(String name, Animal.Gender gender, double weight, double speed, Medal[] medals, Point location, String areaOfLiving, int maxEnergy, int energyPerMeter,String imgName, String compType){
        super(name, gender, weight, speed, medals, location, maxEnergy, energyPerMeter);
        this.water = new WaterAnimal(name, gender, weight, speed, medals, location, maxEnergy, energyPerMeter);
        this.land = new TerrestrialAnimal(name, gender, weight, speed, medals, maxEnergy, energyPerMeter, 4);
        this.AreaOfLiving = areaOfLiving;
        this.compType = compType;
        loadImages(imgName);
    }

    /**
     * Uses water animal's delegator for the alligator to dive
     * @return true if the dive was successful, otherwise false
     */
    public boolean dive(double depth) {
        return water.dive(depth);
    }

    public String getCompType() {
        return compType;
    }

    /**
     * Returns the current depth of the alligator using water animal's delegator
     * @return depth
     */
    public double getDiveDept() {
        return water.getDiveDept();
    }

    /**
     * Returns the amount of legs the alligator has
     * @return amount of legs
     */
    public int getNoLegs() {
        return land.getNoLegs();
    }

    /**
     * Speeds up the alligator by the specified amount.
     *
     * @param speed the amount to speed up
     * @return true if the speed was successfully changed, false otherwise
     */
    public boolean speedUp(int speed) {
        return super.setSpeed(speed, MAX_SPEED);
    }

    /**
     * Returns the area where the alligator lives.
     *
     * @return the area of living
     */
    public String getAreaOfLiving(){
        return this.AreaOfLiving;
    }

    /**
     * Compares this alligator with another animal for equal attributes.
     *
     * @param a the animal to compare with
     * @return true if the animals are equal, false otherwise
     */
    public boolean equals(Animal a){
        if (!(a instanceof Alligator al))
            return false;
        return this.AreaOfLiving.equals(al.getAreaOfLiving())  && this.water.equals(al.water)  && this.land.equals(al.land);
    }

    /**
     * Returns a string representation of the alligator.
     *
     * @return a string representation of the alligator
     */
    public String toString(){
        return (char)27 +"[4m\nAlligator:\n" + (char)27 + "[0m" + super.toString() + "\nMax Dept: " + ((WaterAnimal)water).getMAX_DIVE() + "\nCurrent dept: " + water.getDiveDept() + "\nArea of living: " + this.AreaOfLiving + "\n" +
                "Number of legs: " + land.getNoLegs() + "\n";
    }
}
