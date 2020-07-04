package com.tw.lockerrobot.robot;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.locker.MLocker;
import com.tw.lockerrobot.ticket.Ticket;

import java.util.List;

public class PrimaryLockerRobot {

    private List<MLocker> mLockers;

    public PrimaryLockerRobot(List<MLocker> mLockers) {
        this.mLockers = mLockers;
    }

    public Ticket saveBag(Bag bag) {
        for (MLocker locker : mLockers) {
            return locker.saveBag(bag);
        }

        return null;
    }
}
