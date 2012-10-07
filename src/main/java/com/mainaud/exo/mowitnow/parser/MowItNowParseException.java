package com.mainaud.exo.mowitnow.parser;

/**
 * Happens when a MowItNowFile is not valid.
 */
public class MowItNowParseException extends Exception {
    private static final long serialVersionUID = 9022970508395924213L;

    public MowItNowParseException(String message) {
        super(message);
    }
}
