package com.tw.lockerrobot;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.bag.LBag;
import com.tw.lockerrobot.bag.MBag;
import com.tw.lockerrobot.bag.SBag;
import com.tw.lockerrobot.exception.NoCapacityException;
import com.tw.lockerrobot.locker.Locker;
import com.tw.lockerrobot.locker.MLocker;
import com.tw.lockerrobot.locker.SLocker;
import com.tw.lockerrobot.robot.PrimaryLockerRobot;
import com.tw.lockerrobot.robot.SuperLockerRobot;
import com.tw.lockerrobot.ticket.MTicket;
import com.tw.lockerrobot.ticket.STicket;
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
                if (primaryLockerRobot.getRemainingCapacity() > 0) {
                    return primaryLockerRobot.saveBag(bag);
                }
            }
        }

        if (bag instanceof LBag) {
            for (SuperLockerRobot superLockerRobot : superLockerRobot) {
                return superLockerRobot.saveBag(bag);
            }
        }

        throw new NoCapacityException();
    }

    public Bag takeBag(Ticket ticket) {
        if (ticket instanceof STicket) {
            for (SLocker locker : sLockers) {
                return locker.takeBag(ticket);
            }
        }

        if (ticket instanceof MTicket) {
            for (PrimaryLockerRobot primaryLockerRobot : primaryLockerRobot) {
                if (primaryLockerRobot.isValidTicket(ticket)) {
                    return primaryLockerRobot.takeBag(ticket);
                }
            }
        }

        return null;
    }
}
