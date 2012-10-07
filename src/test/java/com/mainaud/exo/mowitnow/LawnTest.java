package com.mainaud.exo.mowitnow;

import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Tests of the Lawn object.
 */
public class LawnTest {
    @DataProvider(name = "insideDataTest")
    public Object[][] providesInsideTestData() {
        // @formatter:off
        return new Object[][] {
                { 0, 0, true },
                { 0, 4, true },
                { 2, 2, true },
                { 4, 0, true },
                { 4, 4, true },
                { -1, -1, false },
                { -1, 2, false },
                { 2, -1, false },
                { 2, 5, false },
                { 5, 2, false },
                { 5, 5, false },
        };
        // @formatter:on
    }

    @Test(dataProvider = "insideDataTest")
    public void testInside(int x, int y, boolean expected) {
        assertThat(new Lawn(4, 4).inside(new Location(x, y, Direction.NORTH))).isEqualTo(expected);
    }
}
