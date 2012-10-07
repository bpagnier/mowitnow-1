package com.mainaud.exo.mowitnow;

import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

/**
 * Test location object.
 */
public class LocationTest {
    @Test
    public void xCoordinateShouldBeTakenFromConstructor() {
        Assertions.assertThat(new Location(1, 2, Direction.NORTH).getX()).isEqualTo(1);
    }

    @Test
    public void yCoordinateShouldBeTakenFromConstructor() {
        Assertions.assertThat(new Location(1, 2, Direction.NORTH).getY()).isEqualTo(2);
    }

    @Test
    public void directionShouldBeTakenFromConstructor() {
        Assertions.assertThat(new Location(1, 2, Direction.NORTH).getDirection()).isEqualTo(Direction.NORTH);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void directionMustNotBeNull() {
        new Location(1, 2, null);
    }
}
