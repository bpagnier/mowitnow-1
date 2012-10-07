package com.mainaud.exo.mowitnow.parser;

import java.io.IOException;
import java.io.Reader;

import com.mainaud.exo.mowitnow.Direction;
import com.mainaud.exo.mowitnow.Lawn;
import com.mainaud.exo.mowitnow.Location;
import com.mainaud.exo.mowitnow.MowerOrder;
import com.mainaud.exo.mowitnow.MowerSystemControl;

/**
 * Parser for MowItNowFiles. This parser read content from reader.
 * <p>
 * It is not thread-safe.
 */
public class MowItNowFileParser {
    /*
     * With a more complex file, we would use a parser library with a grammar
     * language. (ie ANTLR, xText) This would be overkill for this simple file,
     * so we parse it by hand.
     */

    private MowerSystemControl msc;

    /*
     * Parser state.
     */
    private Reader reader;
    private boolean empty;
    private char c;

    /**
     * Create a parser from a reader that throws orders to a mower system
     * control.
     * 
     * @param msc
     *            Mower system control.
     */
    public MowItNowFileParser(MowerSystemControl msc) {
        this.msc = msc;
    }

    /**
     * Parse data from a reader.
     * 
     * @param reader
     *            reader.
     * @throws IOException
     *             If IO error occurs.
     * @throws MowItNowParseException
     *             If the file is not in the expected format.
     */
    public void parse(Reader reader) throws IOException, MowItNowParseException {
        init(reader);
        parseLawn();
        while (!empty) {
            parseMower();
        }
    }

    private void parseLawn() throws IOException, MowItNowParseException {
        int eastLimit = parseInteger();
        int northLimit = parseInteger();
        msc.setLawn(new Lawn(northLimit, eastLimit));
        parseEndOfLine();
    }

    private void parseMower() throws IOException, MowItNowParseException {
        Location location = parseLocation();
        msc.addMower(location);
        parseEndOfLine();
        parseOrders();
    }

    private Location parseLocation() throws IOException, MowItNowParseException {
        int x = parseInteger();
        int y = parseInteger();
        Direction direction = parseDirection();
        return new Location(x, y, direction);
    }

    private void parseOrders() throws IOException, MowItNowParseException {
        while (!empty && !endOfLine()) {
            ignoreSpaces();
            switch (c) {
                case 'G':
                    msc.execute(MowerOrder.TURN_LEFT);
                    forward();
                    break;
                case 'D':
                    msc.execute(MowerOrder.TURN_RIGHT);
                    forward();
                    break;
                case 'A':
                    msc.execute(MowerOrder.MOVE_FORWARD);
                    forward();
                    break;
                default:
                    throw new MowItNowParseException("Expecting an order G, D or A");
            }
        }
        if (!empty)
            parseEndOfLine();
    }

    private Direction parseDirection() throws MowItNowParseException, IOException {
        ignoreSpaces();
        if (empty)
            throw new MowItNowParseException("Direction expected");
        switch (c) {
            case 'N':
                forward();
                return Direction.NORTH;
            case 'E':
                forward();
                return Direction.EAST;
            case 'S':
                forward();
                return Direction.SOUTH;
            case 'W':
                forward();
                return Direction.WEST;
            default:
                throw new MowItNowParseException("Direction expected");
        }
    }

    private int parseInteger() throws IOException, MowItNowParseException {
        ignoreSpaces();
        if (empty || !Character.isDigit(c))
            throw new MowItNowParseException("integer expected");
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(c);
            forward();
        } while (!empty && Character.isDigit(c));
        return Integer.parseInt(sb.toString());
    }

    private void parseEndOfLine() throws IOException, MowItNowParseException {
        ignoreSpaces();
        switch (c) {
            case '\r':
                forward();
                // Case of DOS EOL
                if (c == '\n')
                    forward();
                break;
            case '\n':
                forward();
                break;
            default:
                throw new MowItNowParseException("End of line expected");
        }
    }

    private boolean endOfLine() {
        return c == '\r' || c == '\n';
    }

    private void ignoreSpaces() throws IOException {
        while (!empty && !endOfLine() && Character.isWhitespace(c))
            forward();
    }

    private void init(Reader reader) throws IOException {
        this.reader = reader;
        forward();
    }

    private void forward() throws IOException {
        int r = reader.read();
        if (r == -1)
            empty = true;
        else
            c = (char) r;
    }
}
