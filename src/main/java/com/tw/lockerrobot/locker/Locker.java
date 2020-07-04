package com.tw.lockerrobot.locker;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.exception.InvalidTicketException;
import com.tw.lockerrobot.ticket.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        if (isValidTicket(ticket))
            return lockerMap.get(ticket);

        throw new InvalidTicketException();
    }

    public boolean isValidTicket(Ticket ticket) {
        return lockerMap.get(ticket) != null;
    }

    protected abstract Ticket saveBag(Bag bag);
}
