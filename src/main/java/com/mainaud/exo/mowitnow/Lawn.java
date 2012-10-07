package com.mainaud.exo.mowitnow;

/**
 * Lawn where mowers can evolve.
 * <p>
 * Lawn is defined by it's north and east limits.
 */
public final class Lawn {
    private final int northLimit;
    private final int eastLimit;

    /**
     * Create a new Lawn.
     * 
     * @param northLimit
     *            North limit.
     * @param eastLimit
     *            East limit.
     */
    public Lawn(int northLimit, int eastLimit) {
        this.northLimit = northLimit;
        this.eastLimit = eastLimit;
    }

    /**
     * Test if a location is inside the lawn.
     * 
     * @param location
     *            Location to test.
     * @return <code>true</code> if the location is inside the lawn.
     */
    public boolean inside(Location location) {
        int lx = location.getX();
        int ly = location.getY();
        return 0 <= lx && lx <= northLimit && 0 <= ly && ly <= eastLimit;
    }
}
