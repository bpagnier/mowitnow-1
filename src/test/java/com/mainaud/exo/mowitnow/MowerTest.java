package com.mainaud.exo.mowitnow;

import static com.mainaud.exo.mowitnow.Direction.EAST;
import static com.mainaud.exo.mowitnow.Direction.NORTH;
import static com.mainaud.exo.mowitnow.Direction.SOUTH;
import static com.mainaud.exo.mowitnow.Direction.WEST;
import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test of the Mower.
 */
public class MowerTest {
    @Test
    public void mowerInitialLocationShouldFitConstructorArgument() {
        Location initLocation = new Location(1, 1, NORTH);
        assertThat(new Mower(new Lawn(2, 2), initLocation).getLocation()).isEqualTo(initLocation);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void mowerInitialLocationShouldBeInTheLawn() {
        new Mower(new Lawn(2, 2), new Location(5, 1, NORTH));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void mowerInitialLocationMustNotBeNull() {
        new Mower(new Lawn(2, 2), null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void mowerLawnMustNotBeNull() {
        new Mower(null, new Location(5, 1, NORTH));
    }

    @DataProvider(name = "validOrderMoves")
    public Object[][] providesValidOrderMoves() {
        // @formatter:off
        return new Object[][] {
                { MowerOrder.TURN_LEFT, new Location(1, 1, WEST) },
                { MowerOrder.TURN_RIGHT, new Location(1, 1, EAST) },
                { MowerOrder.MOVE_FORWARD, new Location(1, 2, NORTH) },
        };
        // @formatter:on
    }

    @Test(dataProvider = "validOrderMoves")
    public void mowerShouldMoveWithValidOrder(MowerOrder order, Location expected) {
        Mower mower = new Mower(new Lawn(2, 2), new Location(1, 1, NORTH));
        mower.execute(order);
        assertThat(mower.getLocation()).isEqualTo(expected);
    }

    @DataProvider(name = "initialLocations")
    public Object[][] providesInitialLocations() {
        // @formatter:off
        return new Object[][] {
                { new Location(0, 1, WEST) },
                { new Location(2, 1, EAST) },
                { new Location(1, 2, NORTH) },
                { new Location(1, 0, SOUTH) },
        };
        // @formatter:on
    }

    @Test(dataProvider = "initialLocations")
    public void mowerShouldStayOnTheLawn(Location current) {
        Mower mower = new Mower(new Lawn(2, 2), current);
        mower.execute(MowerOrder.MOVE_FORWARD);
        assertThat(mower.getLocation()).isEqualTo(current);
    }
}
