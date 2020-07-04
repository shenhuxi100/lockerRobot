package com.tw.lockerrobot;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.bag.MBag;
import com.tw.lockerrobot.bag.SBag;
import com.tw.lockerrobot.exception.NoCapacityException;
import com.tw.lockerrobot.locker.SLocker;
import com.tw.lockerrobot.robot.PrimaryLockerRobot;
import com.tw.lockerrobot.robot.SuperLockerRobot;
import com.tw.lockerrobot.ticket.Ticket;

import java.util.List;

public class Storage {
    private List<SuperLockerRobot> superLockerRobot;
    private List<PrimaryLockerRobot> primaryLockerRobot;
    private List<SLocker> sLockers;

    public Storage(List<SLocker> lockers) {
        this.sLockers = lockers;
    }

    public Storage(List<SLocker> lockers, List<PrimaryLockerRobot> primaryLockerRobot, List<SuperLockerRobot> superLockerRobot) {
        this.sLockers = lockers;
        this.primaryLockerRobot = primaryLockerRobot;
        this.superLockerRobot = superLockerRobot;
    }

    public Ticket saveBag(Bag bag) {
        if (bag instanceof SBag) {
            for (SLocker sLocker : sLockers) {
                if (sLocker.getRemainingCapacity() > 0) {
                    return sLocker.saveBag(bag);
                }
            }
        }

        if (bag instanceof MBag) {
            for (PrimaryLockerRobot primaryLockerRobot : primaryLockerRobot) {
                return primaryLockerRobot.saveBag(bag);
            }
        }

        throw new NoCapacityException();
    }
}
