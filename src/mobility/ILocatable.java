/**
 * Submitter: Matan Ofri
 * ID: 206919680
 */
package mobility;

/**
 * The ILocatable interface represents an entity that has a location.
 */
public interface ILocatable {
    /**
     * Returns the location of the entity.
     *
     * @return the location
     */
    Point getLocation();

    /**
     * Sets the location of the entity.
     *
     * @param p the new location
     * @return true if the location was successfully set, false otherwise
     */
    boolean setLocation(Point p);
}
