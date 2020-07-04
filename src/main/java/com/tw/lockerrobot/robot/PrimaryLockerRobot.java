package com.tw.lockerrobot.robot;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.locker.MLocker;
import com.tw.lockerrobot.ticket.Ticket;

import java.util.List;

public class PrimaryLockerRobot {

    private List<MLocker> lockers;

    public PrimaryLockerRobot(List<MLocker> lockers) {
        this.lockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        for (MLocker locker : lockers) {
            return locker.saveBag(bag);
        }

        return null;
    }

    public int getRemainingCapacity() {
        return lockers.stream().mapToInt(MLocker::getRemainingCapacity).sum();
    }
}
