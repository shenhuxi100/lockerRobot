package com.tw.lockerrobot.model;

import java.util.List;

public class Storage {
    private List<SLocker> sLockers;

    public Storage(List<SLocker> lockers) {
        this.sLockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        return sLockers.get(0).saveBag(bag);
    }
}
