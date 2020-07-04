package com.tw.lockerrobot.robot;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.locker.Locker;
import com.tw.lockerrobot.ticket.Ticket;

import java.util.List;

public abstract class BaseLockerRobot {
    protected List<Locker> lockers;

    public BaseLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public boolean isValidTicket(Ticket ticket) {
        return lockers.stream().anyMatch(locker -> locker.isValidTicket(ticket));
    }

    public int getRemainingCapacity() {
        return lockers.stream().mapToInt(Locker::getRemainingCapacity).sum();
    }

    public Bag takeBag(Ticket ticket) {
        for (Locker locker : lockers) {
            return locker.takeBag(ticket);
        }

        throw null;
    }

    protected abstract Ticket saveBag(Bag bag);
}
