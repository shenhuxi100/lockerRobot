package com.tw.lockerrobot.robot;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.locker.LLocker;
import com.tw.lockerrobot.locker.Locker;
import com.tw.lockerrobot.locker.MLocker;
import com.tw.lockerrobot.ticket.Ticket;

import java.util.Comparator;
import java.util.List;

public class SuperLockerRobot {
    private List<LLocker> lockers;

    public SuperLockerRobot(List<LLocker> lockers) {
        this.lockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        lockers.sort(Comparator.comparing(Locker::getRemainingCapacity).reversed());
        for (LLocker locker : lockers) {
            if (locker.getRemainingCapacity() > 0)
                return locker.saveBag(bag);
        }

        return null;
    }
}
