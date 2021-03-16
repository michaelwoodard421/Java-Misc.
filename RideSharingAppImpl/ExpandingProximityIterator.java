package com.comp301.a05driver;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExpandingProximityIterator implements Iterator<Driver> {
    private Iterable<Driver> driverPool;
    private Position clientPosition;
    private int expansionStep;
    private Iterator<Driver> iterator;
    private Driver nextDriver;
    private int i;
    private int size;
    private int passes;

    public ExpandingProximityIterator(Iterable<Driver> driverPool, Position clientPosition, int expansionStep) {
        if (driverPool == null) {
            throw new NullPointerException();
        }
        if (clientPosition == null) {
            throw new NullPointerException();
        }
        this.driverPool = driverPool;
        this.clientPosition = clientPosition;
        this.expansionStep = expansionStep;

        nextDriver = null;
        iterator = driverPool.iterator();


        passes = 0;
        size = 0;
        while (this.iterator.hasNext()) {
            iterator.next();
            size++;
        }
        iterator = driverPool.iterator();
    }

    @Override
    public Driver next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        passes++;

        while (nextDriver == null) {
            if (iterator.hasNext()) {
                Driver curDriver = iterator.next();

                int distance = clientPosition.getManhattanDistanceTo(curDriver.getVehicle().getPosition());
                if ((distance > (1 + (i - 1) * expansionStep) && distance <= 1 + (i * expansionStep))){
                    return curDriver;
                }
            } else {
                    iterator = driverPool.iterator();
                    i++;

            }
        }
        return null;
    }
    @Override
    public boolean hasNext() {
        return passes < size;
    }
}