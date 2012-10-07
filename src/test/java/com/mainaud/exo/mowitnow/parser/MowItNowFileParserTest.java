package com.mainaud.exo.mowitnow.parser;

import static com.mainaud.exo.mowitnow.Direction.EAST;
import static com.mainaud.exo.mowitnow.Direction.NORTH;
import static org.fest.assertions.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

import com.google.common.base.Charsets;
import com.mainaud.exo.mowitnow.Location;
import com.mainaud.exo.mowitnow.MowItNowSystemControl;
import com.mainaud.exo.mowitnow.MowerSystemControl;

/**
 * Test the MowItNowFileParser.
 */
public class MowItNowFileParserTest {
    @Test
    public void exerciceTestWithMock() throws IOException, MowItNowParseException {
        MowerSystemControlMock msc = new MowerSystemControlMock();
        doParse(msc);
        Assertions.assertThat(msc.toString()).isEqualTo("5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA");
    }

    @Test
    public void exerciceTestWithRealSystem() throws IOException, MowItNowParseException {
        MowerSystemControl msc = new MowItNowSystemControl();
        doParse(msc);
        assertThat(msc.mowerLocations()).containsExactly(new Location(1, 3, NORTH), new Location(5, 1, EAST));
    }

    private void doParse(MowerSystemControl msc) throws IOException, MowItNowParseException {
        MowItNowFileParser parser = new MowItNowFileParser(msc);
        InputStream in = getClass().getResourceAsStream("test.mow");
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, Charsets.US_ASCII));
            try {
                parser.parse(reader);
            } finally {
                reader.close();
            }
        } finally {
            in.close();
        }
    }
}
