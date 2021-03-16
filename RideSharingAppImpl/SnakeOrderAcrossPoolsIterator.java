package com.comp301.a05driver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {
    private int numFilledPools;
    private ArrayList<Iterator<Driver>> poolList = new ArrayList<>();
    private int curPool;
    private boolean isIncreasing = true;

    public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driverPools) {
        for (int i = 0; i < driverPools.size(); i++) {
            poolList.add(driverPools.get(i).iterator());
        }
        curPool = 0;
    }


    @Override
    public boolean hasNext() {
        for (int i = 0; i < poolList.size(); i++) {
            if (poolList.get(i).hasNext()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Driver next() {
        Driver nextDriver = null;
        while (nextDriver == null) {
            if (poolList.get(curPool).hasNext()) {
                nextDriver = poolList.get(curPool).next();
                Snake();
                return nextDriver;
            } else {
                Snake();
            }
        }
        return null;
    }

    public void Snake() {
        if (!isIncreasing) {
            if (curPool == 0) {
                isIncreasing = true;
                return;
            }
            curPool--;
        } else {
            if (curPool == (poolList.size() - 1)) {
                isIncreasing = false;
                return;
            }
            curPool++;
        }
    }
}