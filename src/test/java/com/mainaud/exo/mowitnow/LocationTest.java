package com.mainaud.exo.mowitnow;

import static com.mainaud.exo.mowitnow.Direction.EAST;
import static com.mainaud.exo.mowitnow.Direction.NORTH;
import static com.mainaud.exo.mowitnow.Direction.SOUTH;
import static com.mainaud.exo.mowitnow.Direction.WEST;
import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test location object.
 */
public class LocationTest {
    @Test
    public void xCoordinateShouldBeTakenFromConstructor() {
        assertThat(new Location(1, 2, Direction.NORTH).getX()).isEqualTo(1);
    }

    @Test
    public void yCoordinateShouldBeTakenFromConstructor() {
        assertThat(new Location(1, 2, Direction.NORTH).getY()).isEqualTo(2);
    }

    @Test
    public void directionShouldBeTakenFromConstructor() {
        assertThat(new Location(1, 2, Direction.NORTH).getDirection()).isEqualTo(Direction.NORTH);
    }

    @Test
    public void toString12NShouldMatch12N() {
        assertThat(new Location(1, 2, NORTH).toString()).isEqualTo("1 2 N");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void directionMustNotBeNull() {
        new Location(1, 2, null);
    }

    @DataProvider(name = "turnRightTestData")
    public Object[][] providesTurnRightTestData() {
        // @formatter:off
        return new Object[][] {
                { new Location(0, 0, NORTH), new Location(0, 0, EAST) },
                { new Location(2, 5, EAST), new Location(2, 5, SOUTH) },
                { new Location(-3, 2, SOUTH), new Location(-3, 2, WEST) },
                { new Location(-6, -5, WEST), new Location(-6, -5, NORTH) },
        };
        // @formatter:on
    }

    @Test(dataProvider = "turnRightTestData")
    public void testTurnRight(Location current, Location expected) {
        Location next = current.turnRight();
        assertThat(next).isEqualTo(expected);
    }

    @DataProvider(name = "turnLeftTestData")
    public Object[][] providesTurnLeftTestData() {
        // @formatter:off
        return new Object[][] {
                { new Location(0, 0, EAST), new Location(0, 0, NORTH) },
                { new Location(2, 5, SOUTH), new Location(2, 5, EAST) },
                { new Location(-3, 2, WEST), new Location(-3, 2, SOUTH) },
                { new Location(-6, -5, NORTH), new Location(-6, -5, WEST) },
        };
        // @formatter:on
    }

    @Test(dataProvider = "turnLeftTestData")
    public void testTurnLeft(Location current, Location expected) {
        assertThat(current.turnLeft()).isEqualTo(expected);
    }

    @DataProvider(name = "moveForwardTestData")
    public Object[][] providesMoveForwardTestData() {
        // @formatter:off
        return new Object[][] {
                { new Location(1, 1, NORTH), new Location(1, 2, NORTH) },
                { new Location(1, 1, EAST), new Location(2, 1, EAST) },
                { new Location(1, 1, SOUTH), new Location(1, 0, SOUTH) },
                { new Location(1, 1, WEST), new Location(0, 1, WEST) },
        };
        // @formatter:on
    }

    @Test(dataProvider = "moveForwardTestData")
    public void testMoveForward(Location current, Location expected) {
        Location next = current.moveForward();
        assertThat(next).isEqualTo(expected);
    }

}
