package mobility;

/**
 * The Point class represents a point in a 2D coordinate system.
 * The coordinates must be non-negative integers.
 */
public class Point implements Cloneable {
    private int x;
    private int y;

    /**
     * Constructs a Point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public  Point(int x, int y){
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates can't be negative.");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the point.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
            * Returns the y-coordinate of the point.
            *
            * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Compares this point with another point for equality.
     *
     * @param p the point to compare with
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point p){
        return this.x == p.getX() && this.y == p.getY();
    }

    /**
     * Returns a string representation of the point.
     *
     * @return a string representation of the point
     */
    public String toString() {
        return "{" + x + "," + y + "}";
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
