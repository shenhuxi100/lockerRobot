package com.tw.lockerrobot.robot;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.locker.LLocker;
import com.tw.lockerrobot.locker.Locker;
import com.tw.lockerrobot.locker.MLocker;
import com.tw.lockerrobot.ticket.Ticket;

import java.util.Comparator;
import java.util.List;

public class SuperLockerRobot extends BaseLockerRobot {
    public SuperLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    public Ticket saveBag(Bag bag) {
        lockers.sort(Comparator.comparing(Locker::getRemainingCapacity).reversed());
        for (Locker locker : lockers) {
            LLocker lLocker = (LLocker) locker;
            if (lLocker.getRemainingCapacity() > 0) {
                return lLocker.saveBag(bag);
            }
        }

        return null;
    }
}
