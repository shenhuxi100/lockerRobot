package com.tw.lockerrobot.model;

import com.tw.lockerrobot.model.Locker;

import java.util.HashMap;
import java.util.Map;

public class SLocker extends Locker {
    private int capacity;

    private Map<STicket, Bag> lockerMap = new HashMap<>();

    public Ticket saveBag(Bag bag) {
        if (bag instanceof SBag) {
            STicket ticket = new STicket();
            lockerMap.put(ticket, bag);
            return ticket;
        }

        return null;
    }
}
