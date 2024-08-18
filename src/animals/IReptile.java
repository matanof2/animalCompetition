package animals;

/**
 * The IReptile interface provides methods for reptiles.
 */
public interface IReptile {
    static final int MAX_SPEED = 5;

    /**
     * Speeds up the reptile by the specified amount.
     *
     * @param speed the amount to speed up
     * @return true if the speed was successfully changed, false otherwise
     */
    public boolean speedUp(int speed);
}
