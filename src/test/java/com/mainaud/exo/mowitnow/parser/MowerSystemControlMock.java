package com.mainaud.exo.mowitnow.parser;

import java.util.Collections;
import java.util.List;

import com.mainaud.exo.mowitnow.Lawn;
import com.mainaud.exo.mowitnow.Location;
import com.mainaud.exo.mowitnow.MowerOrder;
import com.mainaud.exo.mowitnow.MowerSystemControl;

/**
 * A mock of a mower system control.
 */
public class MowerSystemControlMock implements MowerSystemControl {
    private StringBuilder sb = new StringBuilder();

    @Override
    public void setLawn(Lawn lawn) {
        sb.append(lawn.getEastLimit());
        sb.append(' ');
        sb.append(lawn.getNorthLimit());
    }

    @Override
    public void addMower(Location location) {
        sb.append('\n');
        sb.append(location.getX());
        sb.append(' ');
        sb.append(location.getY());
        sb.append(' ');
        sb.append(location.getDirection().name().substring(0, 1));
        sb.append('\n');
    }

    @Override
    public void execute(MowerOrder order) {
        switch (order) {
            case TURN_LEFT:
                sb.append('G');
                break;
            case TURN_RIGHT:
                sb.append('D');
                break;
            case MOVE_FORWARD:
                sb.append('A');
                break;
            default:
                break;
        }
    }

    @Override
    public List<Location> mowerLocations() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return sb.toString();
    }

}
