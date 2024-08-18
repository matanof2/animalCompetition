package animals;
import Olympics.Medal;
import graphics.*;
import mobility.*;
import mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Animal class represents a general animal with several attributes and behaviors.
 * It is an abstract class that extends the Mobile class.
 */
abstract public class Animal extends Mobile implements IAnimal, ILocatable, IMoveable, IDrawable, Cloneable {

    public enum Gender {
        Male, Female, Hermaphrodite
    }
    public enum Orientation {
        EAST, SOUTH, WEST, NORTH
    }

    private String name;
    private Gender gender;
    private double weight;
    private double speed;
    private Medal[] medals;
    private Point position;
    private static final int size = 65;
    private int id;
	private Orientation orien;
	private int maxEnergy;
	private int energyPerMeter;
    private int energyAmount;
    private int energyConsumption;
	private BufferedImage img1, img2, img3, img4;

    /**
     * Creates an Animal with the specified attributes.
     *
     * @param name the name of the animal
     * @param gender the gender of the animal
     * @param weight the weight of the animal
     * @param speed the speed of the animal
     * @param medals an array of medals the animal has won
     * @param position the position of the animal
     */
    public Animal(String name, Gender gender, double weight, double speed, Medal[] medals, Point position, int maxEnergy, int energyPerMeter) {
        super(position);
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.speed = speed;
        this.medals = medals;
        this.position = position;
        this.id = id;
        this.orien = Orientation.EAST;
        this.maxEnergy = maxEnergy;
        this.energyPerMeter = energyPerMeter;
        this.energyAmount = maxEnergy;
    }

    /**
     * Makes a sound specific to the animal.
     */
    public void makeSound(){
        String animalSound = getAnimalSound();
        Class<?> animalClass = this.getClass();
        System.out.println("Animal " + name + "(" +animalClass.getSimpleName() + ")" + " said " + animalSound);
    }

    /**
     * Function to find the sound of the specific animal.
     *
     * @return the sound specific to the animal
     */
    private String getAnimalSound(){
        Class<?> animalClass = this.getClass();

        if (animalClass == Alligator.class) {
            return "Roar";
        } else if (animalClass == Cat.class) {
            return "Meow";
        } else if (animalClass == Dog.class) {
            return "Woof Woof";
        }  else if (animalClass == Dolphin.class) {
            return "Click-click";
        } else if (animalClass == Eagle.class) {
            return "Clack-wack-chack";
        }else if (animalClass == Pigeon.class) {
            return "Arr-rar-rar-rar-raah";
        } else if (animalClass == Snake.class) {
            return "ssssssss";
        }  else if (animalClass == Whale.class) {
            return "Splash";
        }  else {
            return "Unknown sound";
        }
    }

    public double getDistance(){
        return super.getTotalDistance();
    }
    public void addTotalDistance(double distance) {
        if (distance > 0) {
            setTotalDistance(getTotalDistance() + distance);
        }
    }

    public double calcDistance(Point point) {
        return Math.hypot(getLocation().getX() - point.getX(), getLocation().getY() - point.getY());
    }

    /**
     * Returns the name of the animal.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the gender of the animal.
     *
     * @return the gender
     */
    protected Gender getGender() {
        return this.gender;
    }

    /**
     * Returns the weight of the animal.
     *
     * @return the weight
     */
    protected double getWeight() {
        return this.weight;
    }

    /**
     * Returns the speed of the animal.
     *
     * @return the speed
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Sets the speed of the animal, ensuring it does not exceed the maximum speed.
     *
     * @param speed the amount to change the speed
     * @param MAX_SPEED the maximum speed allowed
     * @return true if the speed was successfully changed, false otherwise
     */
    protected boolean setSpeed(double speed, int MAX_SPEED) {
        if(speed > 0) { // if the user wants the animal to speed up(int speed will be positive)
            if (speed + this.speed > MAX_SPEED) { //  if the new speed is bigger than the MAX_SPEED
                System.out.println("Reached maximum speed(5), cant go any faster");
                this.speed = MAX_SPEED;
            } else { //  if the new speed is not bigger than the MAX_SPEED
                this.speed += speed;
                System.out.println("New speed changed to " + this.speed);
            }
        }
        else { // if the user wants the animal to slow down(int speed will be negative)
            if (speed + this.speed < 0) { // if the new speed negative
                System.out.println("Changed the speed to 0, cant go any slower");
                this.speed = 0;
            } else {
                this.speed += speed;
                System.out.println("New speed changed to " + this.speed);
            }
        }
        return true;
    }

    /**
     * Returns the array of medals the animal has won.
     *
     * @return the array of medals
     */
    protected Medal[] getMedals() {
        return this.medals;
    }

    /**
     * Returns the position of the animal.
     *
     * @return the position
     */
    public Point getPosition() {
        return this.position;
    }

    public int getEnergyPerMeter() {
        return energyPerMeter;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public double getTotaldistance() {
        return getTotalDistance();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void loadImages(String nm){
        try {
            try {
                // Change this to use File for absolute path loading
                img1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/" + nm + ".png")));
            } catch (IOException e) {
                System.out.println("Cannot load image: " + nm);
                e.printStackTrace();
                img1 = null;
            }
            if (img1 != null) {
                img2 = flipImage(img1, true, false);  // Flip horizontally for West
                img3 = flipImage(img1, false, true);  // Flip vertically for South
                img4 = flipImage(img2, false, true);  // Flip vertically the horizontally flipped image for North
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getEnergyAmount() {
        return energyAmount;
    }
    public void setEnergyAmount(int energyAmount) {
        this.energyAmount = energyAmount;
    }

    private BufferedImage flipImage(BufferedImage img, boolean horizontal, boolean vertical) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage flippedImage = new BufferedImage(w, h, img.getType());
        Graphics2D g = flippedImage.createGraphics();

        if (horizontal && vertical) {
            g.drawImage(img, 0, 0, w, h, w, h, 0, 0, null);
        } else if (horizontal) {
            g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        } else if (vertical) {
            g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
        } else {
            g.drawImage(img, 0, 0, null);
        }

        g.dispose();
        return flippedImage;
    }

    public int getIntSpeed(){
        return (int)speed;
    }

    public void setImage() {
        switch (this.orien) {
            case EAST:
                this.img1 = img1;
                break;
            case SOUTH:
                this.img2 = flipImage(img1, false, true);
                break;
            case WEST:
                this.img3 = img1;
                break;
            case NORTH:
                this.img4 = flipImage(img1, true, true);
                break;
        }
    }


    public void setPosition(Point p) {
        super.setLocation(p);
        position = p;
    }

    public boolean move(Point p){
        if ((p == null) || (calcDistance(p) == 0)) {
            return false;
        } else {
            double distance = calcDistance(p);
            setPosition(p);
            addTotalDistance(distance);
            return true;
        }
    }

    public boolean eat(int energy){
        this.energyAmount += energy;
        if (this.energyAmount > this.maxEnergy) {
            this.energyConsumption += energy - (this.energyAmount - this.maxEnergy);
            this.energyAmount = this.maxEnergy;
        }
        else
            this.energyConsumption += energy;
        return true;
    }

    public Orientation getOrientation() {
        return orien;
    }

    public BufferedImage Getimage()
    {
        return img1;
    }

    public void setOrientation(Orientation orientation) {
        this.orien = orientation;
    }

    public void drawObject (Graphics g) {
        if(orien == Orientation.EAST) // animal move to the east side
			g.drawImage(img1, position.getX(), position.getY(), 65, 65, null);
		else if(orien == Orientation.SOUTH) // animal move to the south side
			g.drawImage(img2, position.getX(), position.getY(), 65, 65, null);
		else if(orien == Orientation.WEST) // animal move to the west side
			g.drawImage(img3, position.getX(), position.getY(), 65, 65, null);
		else if(orien == Orientation.NORTH) // animal move to the north side
            g.drawImage(img4, position.getX(), position.getY(), 65, 65, null);
    }

    public int getImageWidth() {
        return img1.getWidth();
    }
    public int getImageHeight() {
        return img1.getHeight();
    }

    /**
     * Compares this animal with another animal for equal attributes.
     *
     * @param a the animal to compare with
     * @return true if the animals are equal, false otherwise
     */
    public boolean equals(Animal a){
        return super.equals(a) && this.name.equals(a.getName()) && this.gender == a.getGender() && this.weight == a.getWeight() && this.speed == a.getSpeed()&& this.medals == a.getMedals() && this.position.equals(a.getPosition());
    }

    /**
     * Returns a string representation of the animal.
     *
     * @return a string representation of the animal
     */
    public String toString(){
        String medString = "\n\nMedals:\n";
        if (this.medals != null)
            for (Medal medal : medals) if (medal != null) medString += medal.toString() + "\n";
        return super.toString() + "\nName: " + this.name + "\nGender: " + this.gender + "\nWeighs " + this.weight + "\nSpeed: " + this.speed + medString + "\nPosition: " + this.position.toString();
    }
}
