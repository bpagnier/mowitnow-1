package com.mainaud.exo.mowitnow;

/**
 * Order that can be send to a mover.
 */
public enum MoverOrder {
    TURN_RIGHT {
        @Override
        public Location move(Location current) {
            return current.turnRight();
        }

    },
    TURN_LEFT {
        @Override
        public Location move(Location current) {
            return current.turnLeft();
        }

    },
    MOVE_FORWARD {
        @Override
        public Location move(Location current) {
            return current.moveForward();
        }
    };

    /**
     * Change the location to the one the mower would have after the order is
     * executed.
     * 
     * @param current
     *            Current location.
     */
    public abstract Location move(Location current);
}
