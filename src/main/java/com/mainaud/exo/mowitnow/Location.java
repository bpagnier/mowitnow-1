package com.mainaud.exo.mowitnow;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Preconditions;

/**
 * Location of a mower.
 * <p>
 * A mower is located by it's coordinates x and y and it's direction.
 */
public final class Location {
	private final int x;
	private final int y;
	private final Direction direction;

	/**
	 * Create a new Location object.
	 * 
	 * @param x
	 *            X coordinate.
	 * @param y
	 *            Y coordinate.
	 * @param direction
	 *            Direction.
	 */
	public Location(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = checkNotNull(direction);
	}

	/**
	 * @return the x coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the direction.
	 */
	public Direction getDirection() {
		return direction;
	}
	
	

}
