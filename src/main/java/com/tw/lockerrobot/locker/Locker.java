package com.tw.lockerrobot.locker;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.ticket.MTicket;
import com.tw.lockerrobot.ticket.Ticket;

import java.util.HashMap;
import java.util.Map;

public abstract class Locker {
    protected int capacity;

    protected Map<Ticket, Bag> lockerMap = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public int getRemainingCapacity() {
        return capacity - lockerMap.size();
    }

    public Bag takeBag(Ticket ticket) {
        return lockerMap.get(ticket);
    }

    public boolean isValidTicket(Ticket ticket) {
        return lockerMap.get(ticket) != null;
    }

    protected abstract Ticket saveBag(Bag bag);
}
