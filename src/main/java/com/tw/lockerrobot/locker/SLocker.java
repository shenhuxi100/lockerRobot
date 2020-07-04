package com.tw.lockerrobot.locker;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.ticket.Ticket;
import com.tw.lockerrobot.ticket.STicket;

import java.util.HashMap;
import java.util.Map;

public class SLocker extends Locker {
    private int capacity;

    private Map<STicket, Bag> lockerMap = new HashMap<>();

    public SLocker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket saveBag(Bag bag) {
        STicket ticket = new STicket();
        lockerMap.put(ticket, bag);
        return ticket;
    }

    public int getRemainingCapacity() {
        return capacity - lockerMap.size();
    }
}
