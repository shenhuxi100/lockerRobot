package com.tw.lockerrobot.model;

import com.tw.lockerrobot.exception.NoCapacityException;

import java.util.List;

public class Storage {
    private List<SLocker> sLockers;

    public Storage(List<SLocker> lockers) {
        this.sLockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        for (SLocker sLocker : sLockers) {
            if (sLocker.getRemainingCapacity() > 0) {
                return sLocker.saveBag(bag);
            }
        }

        throw new NoCapacityException();
    }
}
