/**
 * Submitter: Matan Ofri
 * ID: 206919680
 */
package mobility;

/**
 * The Mobile class represents a mobile entity that can move from one point to another.
 * It implements the ILocatable interface.
 */
abstract public class Mobile implements ILocatable {
    private Point location;
    private double totalDistance;

    /**
     * Constructs a Mobile entity with the specified initial location.
     *
     * @param p the initial location
     */
    public Mobile(Point p){
        this.location = p;
        totalDistance = 0.0;
    }

    /**
     * Adds the specified distance to the total distance traveled.
     *
     * @param distance the distance to add
     */
    private void addTotalDistance(double distance){
        this.totalDistance += distance;
    }

    /**
     * Calculates the distance between the current location and the specified point.
     *
     * @param p the point to calculate the distance to
     * @return the distance
     */
    public double calcDistance(Point p){
        int DX = this.location.getX() - p.getX();
        int DY = this.location.getY() - p.getY();
        return Math.sqrt(DX * DX + DY * DY);
    }

    /**
     * Moves the entity to the specified point and returns the distance traveled.
     *
     * @param p the point to move to
     * @return the distance traveled
     */
    public double moveTo(Point p){
        double traveled = calcDistance(p);
        addTotalDistance(traveled);
        setLocation(p);
        return traveled;
    }

    /**
     * Returns the current location of the entity.
     *
     * @return the current location
     */
    public Point getLocation(){
        return this.location;
    }

    /**
     * Sets the location of the entity to the specified point.
     *
     * @param p the new location
     * @return true if the location was successfully set, false otherwise
     */
    public boolean setLocation(Point p){
        this.location = p;
        return true;
    }

    /**
     * Returns the total distance traveled by the entity.
     *
     * @return the total distance traveled
     */
    public double getTotalDistance(){
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    /**
     * Compares this mobile entity with another mobile entity for equality.
     *
     * @param m the mobile entity to compare with
     * @return true if the mobile entities are equal, false otherwise
     */
    public boolean equals(Mobile m){
        return this.location.equals(m.getLocation()) && this.totalDistance == m.getTotalDistance();
    }

    /**
     * Returns a string representation of the mobile entity.
     *
     * @return a string representation of the mobile entity
     */
    public String toString(){
        return "location: " + this.location.toString() + "\ntotal distance: " + this.totalDistance;
    }
}
