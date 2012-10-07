package com.mainaud.exo.mowitnow;

import static com.mainaud.exo.mowitnow.Direction.EAST;
import static com.mainaud.exo.mowitnow.Direction.NORTH;
import static com.mainaud.exo.mowitnow.MoverOrder.MOVE_FORWARD;
import static com.mainaud.exo.mowitnow.MoverOrder.TURN_LEFT;
import static com.mainaud.exo.mowitnow.MoverOrder.TURN_RIGHT;
import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.Test;

/**
 * Tests of MowerSystemControl.
 */
public class MowerSystemControlTest {
    @Test(expectedExceptions = IllegalStateException.class)
    public void lawnShouldBeSettedFirst() {
        new MowerSystemControl().addMower(new Location(0, 0, NORTH));
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void mowerShouldBeAddedFirst() {
        MowerSystemControl systemControl = new MowerSystemControl();
        systemControl.setLawn(new Lawn(5, 5));
        systemControl.execute(MOVE_FORWARD);
    }

    @Test
    public void exerciceTestSequence() {
        MowerSystemControl control = new MowerSystemControl();
        control.setLawn(new Lawn(5, 5));
        control.addMower(new Location(1, 2, NORTH));
        control.execute(TURN_LEFT);
        control.execute(MOVE_FORWARD);
        control.execute(TURN_LEFT);
        control.execute(MOVE_FORWARD);
        control.execute(TURN_LEFT);
        control.execute(MOVE_FORWARD);
        control.execute(TURN_LEFT);
        control.execute(MOVE_FORWARD);
        control.execute(MOVE_FORWARD);
        control.addMower(new Location(3, 3, EAST));
        control.execute(MOVE_FORWARD);
        control.execute(MOVE_FORWARD);
        control.execute(TURN_RIGHT);
        control.execute(MOVE_FORWARD);
        control.execute(MOVE_FORWARD);
        control.execute(TURN_RIGHT);
        control.execute(MOVE_FORWARD);
        control.execute(TURN_RIGHT);
        control.execute(TURN_RIGHT);
        control.execute(MOVE_FORWARD);

        assertThat(control.mowerLocations()).containsExactly(new Location(1, 3, NORTH), new Location(5, 1, EAST));
    }

}
