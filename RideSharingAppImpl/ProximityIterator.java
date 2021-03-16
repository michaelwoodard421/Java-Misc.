package com.comp301.a05driver;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ProximityIterator implements Iterator<Driver> {
    private Iterable<Driver> driverPool;
    private Position clientPosition;
    private int proximityRange;
    private Iterator<Driver> iterator;
    private Driver nextDriver;

    public ProximityIterator(Iterable<Driver> driverPool, Position clientPosition, int proximityRange){
        if (driverPool == null){
            throw new IllegalArgumentException();
        }
        if (clientPosition == null){
            throw new IllegalArgumentException();
        }
        this.driverPool = driverPool;
        this.clientPosition = clientPosition;
        this.proximityRange = proximityRange;

        nextDriver = null;
        iterator = driverPool.iterator();
    }

    private void loadNextDriver() {
        Driver curDriver;
        while (nextDriver == null && iterator.hasNext()) {
            curDriver = iterator.next();
            if (curDriver.getVehicle().getPosition().getManhattanDistanceTo(clientPosition) <= proximityRange) {
                nextDriver = curDriver;
                return;
            }
        }
    }


    @Override
    public boolean hasNext() {
        loadNextDriver();
        return nextDriver != null;
    }

    @Override
    public Driver next() {
        Driver local;
        if (!hasNext()){
            throw new NoSuchElementException();
        } else {
            local = nextDriver;
            nextDriver = null;
            return local;
        }

    }
}
