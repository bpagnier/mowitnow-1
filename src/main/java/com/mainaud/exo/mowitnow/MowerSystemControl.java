package com.mainaud.exo.mowitnow;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * Control of a mower system.
 * <p>
 * Can manage several mowers in a same lawn.
 */
public class MowerSystemControl {
    private Lawn lawn;
    private List<Mower> mowers = newArrayList();
    /** The mower that currently receive orders. */
    private Mower currentMower;

    /**
     * Set that Lawn.
     * 
     * @param lawn
     *            Lawn.
     */
    public void setLawn(Lawn lawn) {
        this.lawn = lawn;
    }

    /**
     * Add a mower in the system.
     * 
     * @param location
     *            Initial location of the mower.
     * 
     */
    public void addMower(Location location) {
        checkState(lawn != null, "Lawn not setted");
        currentMower = new Mower(lawn, location);
        mowers.add(currentMower);
    }

    /**
     * Send an order to the current mower.
     * 
     * @param order
     *            Order to execute.
     */
    public void execute(MoverOrder order) {
        checkState(lawn != null, "Lawn not setted");
        checkState(currentMower != null, "currentMower not setted");
        currentMower.execute(order);
    }

    /**
     * @return the list of mowers locations.
     */
    public List<Location> mowerLocations() {
        return ImmutableList.copyOf(Lists.transform(mowers, new Function<Mower, Location>() {
            public Location apply(Mower mower) {
                return mower.getLocation();
            }
        }));
    }
}
