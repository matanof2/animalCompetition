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
 * The Eagle class represents an eagle, a type of air animal.
 * It extends the AirAnimal class.
 */
public class Eagle extends AirAnimal {
    private double altitudeOfFlight;
    static final double MAX_ALTITUDE = 1000;

    /**
     * Creates an Eagle with the specified attributes.
     *
     * @param name the name of the eagle
     * @param gender the gender of the eagle
     * @param weight the weight of the eagle
     * @param speed the speed of the eagle
     * @param medals an array of medals the eagle has won
     * @param wingspan the wingspan of the eagle
     * @param altitudeOfFlight the altitude of flight of the eagle
     */
    public Eagle(String name, Gender gender, double weight, double speed, Medal[] medals, Point position, double wingspan, double altitudeOfFlight, int maxEnergy, int energyPerMeter, String imgName){
        super(name, gender, weight, speed, medals, position, wingspan, maxEnergy, energyPerMeter);
        this.altitudeOfFlight = altitudeOfFlight;
        loadImages(imgName);
    }

    /**
     * Returns the altitude of flight of the eagle.
     *
     * @return the altitude of flight
     */
    public double getAltitudOfFlight(){
        return this.altitudeOfFlight;
    }

    /**
     * Compares this eagle with another animal for equal attributes.
     *
     * @param a the animal to compare with
     * @return true if the animals are equal, false otherwise
     */
    public boolean equals(Animal a){
        if (!(a instanceof Eagle e))
            return false;
        return super.equals(e) && this.altitudeOfFlight == e.getAltitudOfFlight();
    }
    /**
     * Returns a string representation of the eagle.
     *
     * @return a string representation of the eagle
     */
    public String toString(){
        return (char)27 +"[4m\nEagle:\n" + (char)27 + "[0m" + super.toString() + "\nAltitude of flight: " + this.altitudeOfFlight + "\n";
    }
}
