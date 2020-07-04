package com.tw.lockerrobot.robot;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.exception.NoCapacityException;
import com.tw.lockerrobot.locker.Locker;
import com.tw.lockerrobot.locker.MLocker;
import com.tw.lockerrobot.ticket.Ticket;

import java.util.List;

public class PrimaryLockerRobot extends BaseLockerRobot {
    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    public Ticket saveBag(Bag bag) {
        for (Locker locker : lockers) {
            MLocker mLocker = (MLocker) locker;

            if (mLocker.getRemainingCapacity() > 0) {
                return mLocker.saveBag(bag);
            }
        }

        throw new NoCapacityException();
    }
}
