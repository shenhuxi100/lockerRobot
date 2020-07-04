package com.tw.lockerrobot.locker;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.ticket.STicket;
import com.tw.lockerrobot.ticket.Ticket;

import java.util.HashMap;
import java.util.Map;

public class Locker {
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
}
