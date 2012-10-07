package com.mainaud.exo.mowitnow;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.mainaud.exo.mowitnow.Direction.EAST;
import static com.mainaud.exo.mowitnow.Direction.NORTH;
import static com.mainaud.exo.mowitnow.Direction.SOUTH;
import static com.mainaud.exo.mowitnow.Direction.WEST;

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

    public Location turnRight() {
        Direction newDirection;
        switch (getDirection()) {
            case NORTH:
                newDirection = EAST;
                break;
            case EAST:
                newDirection = SOUTH;
                break;
            case SOUTH:
                newDirection = WEST;
                break;
            case WEST:
                newDirection = NORTH;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction given");
        }
        return new Location(x, y, newDirection);
    }

    public Location turnLeft() {
        Direction newDirection;
        switch (direction) {
            case NORTH:
                newDirection = WEST;
                break;
            case WEST:
                newDirection = SOUTH;
                break;
            case SOUTH:
                newDirection = EAST;
                break;
            case EAST:
                newDirection = NORTH;
                break;
            default:
                // Should not happen
                throw new IllegalArgumentException("Invalid direction given");
        }
        return new Location(x, y, newDirection);
    }

    public Location moveForward() {
        switch (direction) {
            case NORTH:
                return new Location(x, y + 1, direction);
            case EAST:
                return new Location(x + 1, y, direction);
            case SOUTH:
                return new Location(x, y - 1, direction);
            case WEST:
                return new Location(x - 1, y, direction);
            default:
                // Should not happen
                throw new IllegalArgumentException("Invalid direction given");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Location))
            return false;
        Location that = (Location) o;
        return this.x == that.x && this.y == that.y && this.direction == that.direction;
    }

    @Override
    public int hashCode() {
        int hash = x;
        hash = hash * 31 + y;
        hash = hash * 31 + direction.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return Integer.toString(x) + ' ' + y + ' ' + direction.name().substring(0, 1);
    }
}
