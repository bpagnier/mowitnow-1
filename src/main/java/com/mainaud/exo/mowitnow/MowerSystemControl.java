package com.mainaud.exo.mowitnow;

import java.util.List;

/**
 * Control of a mower system.
 * <p>
 * Can manage several mowers in a same lawn.
 */
public interface MowerSystemControl {

    /**
     * Set that Lawn.
     * 
     * @param lawn
     *            Lawn.
     */
    void setLawn(Lawn lawn);

    /**
     * Add a mower in the system.
     * 
     * @param location
     *            Initial location of the mower.
     * 
     */
    void addMower(Location location);

    /**
     * Send an order to the current mower.
     * 
     * @param order
     *            Order to execute.
     */
    void execute(MowerOrder order);

    /**
     * @return the list of mowers locations.
     */
    List<Location> mowerLocations();

}
