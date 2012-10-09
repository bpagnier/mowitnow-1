package com.mainaud.exo.mowitnow;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Mower.
 */
public class Mower {
    private Lawn lawn;
    private Location location;

    /**
     * Create a new Mower on the given lawn at given location.
     * 
     * @param lawn
     *            Mower's lawn.
     * @param location
     *            Mower's location.
     */
    public Mower(Lawn lawn, Location location) {
        this.lawn = checkNotNull(lawn);
        this.location = checkNotNull(location);
        checkArgument(lawn.contains(location), "The initial location of the mower should be in the lawn.");
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Execute given order. If the next location is outside of the lawn, then
     * the order is ignored.
     * 
     * @param order
     *            Order to execute.
     */
    public void execute(MowerOrder order) {
        Location next = order.move(location);
        if (lawn.contains(next))
            location = next;
    }
}
