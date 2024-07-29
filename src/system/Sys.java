/**
 * Submitter: Matan Ofri
 * ID: 206919680
 */
package system;
import Olympics.Medal;
import animals.*;
import mobility.Point;

import java.util.Locale;
import java.util.Scanner;

/**
 * The Sys class contains the main method for running the animal system.
 */
public class Sys {
    /**
     * The main method, initializes the animal system and provides a menu for user interaction.
     * Users can add animals, view their information, and hear them make sounds.
     *
     * @param args command line arguments
     */
    public static void main(String[] args){
        int menu;
        System.out.print("Enter how many animals you would like to add: ");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        try {
            if (size < 1)
                throw new IllegalArgumentException("Input must be greater than or equal to 1.");
            Animal[] animals = new Animal[size];
            initializeArray(animals, sc);
            do {
                System.out.print((char)27 +"[4m\nMenu:\n" + (char)27 +"[0m[1] All animals information\n[2] Animals make a sound\n[3] Close system\n");
                menu = sc.nextInt();
                switch (menu){
                    case 1: {
                        printAllInfo(animals);
                        break;
                    }
                    case 2: {
                        allMakeSound(animals);
                        break;
                    }
                    case 3: {
                        System.out.println("Goodby");
                        break;
                    }
                    default: {
                        System.out.println("Input must be between 1 to 3. Try again");
                    }
                }
            } while (menu != 3);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        sc.close();
    }

    /**
     * Initializes the array of animals with user input.
     *
     * @param animals the array to initialize
     * @param sc the scanner to read user input
     */
    public static void initializeArray(Animal[] animals, Scanner sc){
        for(int i = 0; i < animals.length; i++)
        {
            System.out.print((char)27 +"[4m\nChoose animal type to add:\n" + (char)27 +"[0m[1] Aerial animal\n[2] Marine animal\n[3] Terrestrial animal\n");
            int selectedType = sc.nextInt();
            switch (selectedType) {
                case 1: { // creating an air animal
                    System.out.print((char) 27 + "[4m\nChoose animal to add:\n" + (char) 27 + "[0m[1] Eagle\n[2] Pigeon\n");
                    int selectedAnimal = sc.nextInt();
                    if (selectedAnimal > 2 || selectedAnimal < 1)
                        throw new IllegalArgumentException("Input must be 1 or 2.");
                    createAnimal(animals, i, (selectedType * 10) + selectedAnimal, sc);
                    break;
                }
                case 2: { // creating water animal
                    System.out.print((char) 27 + "[4m\nChoose animal to add:\n" + (char) 27 + "[0m[1] Alligator\n[2] Whale\n[3] Dolphin\n");
                    int selectedAnimal = sc.nextInt();
                    if (selectedAnimal > 3 || selectedAnimal < 1)
                        throw new IllegalArgumentException("Input must be between 1 to 3.");
                    createAnimal(animals, i, (selectedType * 10) + selectedAnimal, sc);
                    break;
                }
                case 3: { // creating terrestrial animal
                    System.out.print((char) 27 + "[4m\nChoose animal to add:\n" + (char) 27 + "[0m[1] Dog\n[2] Cat\n[3] Snake\n");
                    int selectedAnimal = sc.nextInt();
                    if (selectedAnimal > 3 || selectedAnimal < 1)
                        throw new IllegalArgumentException("Input must be between 1 to 3.");
                    createAnimal(animals, i, (selectedType * 10) + selectedAnimal, sc);
                    break;
                }
                default: { // illegal input
                    throw new IllegalArgumentException("Input must be between 1 to 3.");
                }
            }
        }
    }

    /**
     * Creates a specific animal based on the user's input and adds it to the array of animals.
     *
     * @param animals the array to add the animal to
     * @param index the index to add the animal at
     * @param animal a number that represent the type of animal to create
     * @param sc the scanner to read user input
     */
    public static void createAnimal(Animal[] animals, int index, int animal, Scanner sc){ // creates a specific animal based on the user's input and adds to the array
        System.out.print("Enter animals name: ");
        String fix = sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter animals gender(Male, Female, Hermaphrodite): ");
        String gen = sc.nextLine();
        if (!gen.equalsIgnoreCase("male") && !gen.equalsIgnoreCase("female") && !gen.equalsIgnoreCase("Hermaphrodite"))
            throw new IllegalArgumentException("Input must be 'Male', 'Female' or 'Hermaphrodite'.");
        Animal.Gender gender = Animal.Gender.valueOf(formatInput(gen));
        System.out.print("Enter animals weight: ");
        double weight = sc.nextDouble();
        System.out.print("Enter animals speed: ");
        double speed = sc.nextDouble();
        System.out.print("Enter how many medals the animal has: ");
        int medsize = sc.nextInt();
        if (medsize < 0)
            throw new IllegalArgumentException("Input must be bigger or equals to 0.");
        Medal[] medals = medalArray(medsize, sc);
        switch (animal) {
            case 11: { // creating an Eagle
                System.out.print("Enter Eagle's wingspan: ");
                double wingspan = sc.nextDouble();
                System.out.print("Enter Eagle's altitude of flight: ");
                double altitudeOfFlight = sc.nextDouble();
                //animals[index] = new Eagle(name, gender, weight, speed, medals, wingspan, altitudeOfFlight);
                break;
            }
            case 12: { // creating a Pigeon
                System.out.print("Enter Pigeon's wingspan: ");
                double wingspan = sc.nextDouble();
                System.out.print("Enter Pigeon's family: ");
                fix = sc.nextLine();
                String family = sc.nextLine();
                //animals[index] = new Pigeon(name, gender, weight, speed, medals, wingspan, family);
                break;
            }
            case 21: { // creating an Alligator
                System.out.print("Enter Alligator's area of living: ");
                fix = sc.nextLine();
                String areaOfLiving = sc.nextLine();
                //animals[index] = new Alligator(name, gender, weight, speed, medals, areaOfLiving);
                break;
            }
            case 22: { // creating a Whale
                System.out.print("Enter Whale's food type: ");
                fix = sc.nextLine();
                String foodType = sc.nextLine();
                //animals[index] = new Whale(name, gender, weight, speed, medals, foodType);
                break;
            }
            case 23: { // creating a Dolphin
                System.out.print("Enter Dolphin's water type(Sea, Sweet): ");
                fix = sc.nextLine();
                String water = sc.nextLine();
                if (!water.equalsIgnoreCase("sea") && !water.equalsIgnoreCase("sweet"))
                    throw new IllegalArgumentException("Input must be 'Sea' or 'Sweet'.");
                Dolphin.WaterType waterType = Dolphin.WaterType.valueOf(formatInput(water));
                //animals[index] = new Dolphin(name, gender, weight, speed, medals, waterType);
                break;
            }
            case 31: { // creating a Dog
                System.out.print("Enter Dog's breed: ");
                fix = sc.nextLine();
                String breed = sc.nextLine();
                //animals[index] = new Dog(name, gender, weight, speed, medals, breed);
                break;
            }
            case 32: { // creating a Cat
                System.out.print("Enter Cat's castration status(true or false): ");
                fix = sc.nextLine();
                String castStatus = sc.nextLine();
                boolean castrated;
                if (castStatus.equalsIgnoreCase("true"))
                    castrated = true;
                else if (castStatus.equalsIgnoreCase("false"))
                    castrated = false;
                else
                    throw new IllegalArgumentException("Input must be 'true' of 'false'.");
                //animals[index] = new Cat(name, gender, weight, speed, medals, castrated);
                break;
            }
            case 33: { // creating a Snake
                System.out.print("Enter Snake's toxicity level(Low, Medium, High): ");
                fix = sc.nextLine();
                String tox = sc.nextLine();
                if (!tox.equalsIgnoreCase("low") && !tox.equalsIgnoreCase("medium") && !tox.equalsIgnoreCase("high"))
                    throw new IllegalArgumentException("Input must be 'Not', 'Low', 'Medium' or 'High'.");
                Snake.Toxicity toxic = Snake.Toxicity.valueOf(formatInput(tox));
                System.out.print("Enter Snake's length: ");
                double length = sc.nextDouble();
                //animals[index] = new Snake(name, gender, weight, speed, medals, toxic, length);
                break;
            }
            default: { // illegal input
                throw new IllegalArgumentException("Illegal input for an animal.");
            }
        }
    }

    /**
     * Creates an array of medals based on user input.
     *
     * @param size the number of medals
     * @param sc the scanner to read user input
     * @return an array of medals if size>0, otherwise null
     */
    public static Medal[] medalArray(int size, Scanner sc){
        if (size == 0)
            return null;
        Medal[] medals = new Medal[size];
        for (int i = 0; i < size; i++){
            System.out.print("Enter medal type(bronze, silver, gold): ");
            String fix = sc.nextLine();
            String med = sc.nextLine();
            if (!med.equalsIgnoreCase("bronze") && !med.equalsIgnoreCase("silver") && !med.equalsIgnoreCase("gold"))
                throw new IllegalArgumentException("Input must be bronze, silver or gold.");
            Medal.Type medal = Medal.Type.valueOf(med.toLowerCase());
            System.out.print("Enter tournament's name: ");
            String name = sc.nextLine();
            System.out.print("Enter year: ");
            int year = sc.nextInt();
            medals[i] = new Medal(medal, name, year);
        }
        return medals;
    }

    /**
     * Formats the input string to have the first letter in uppercase and the rest in lowercase in order for
     * the string to fit the same as the enum's format.
     *
     * @param input the input string
     * @return the formatted string
     */
    public static String formatInput(String input){ // helping method for inputs of the animal's gender
        input = input.toLowerCase();
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * Prints information of all animals in the array.
     *
     * @param animals the array of animals
     */
    public static void printAllInfo(Animal[] animals) {
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }

    /**
     * Makes all animals in the array produce their respective sounds.
     *
     * @param animals the array of animals
     */
    public static void allMakeSound(Animal[] animals) {
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }
}
