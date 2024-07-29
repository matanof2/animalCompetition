package graphics;

import mobility.Point;

public interface IMoveable {
    public String getName();
	public int getIntSpeed();
	public boolean move(Point p);
}
