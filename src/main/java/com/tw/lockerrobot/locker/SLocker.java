package com.tw.lockerrobot.locker;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.ticket.STicket;
import com.tw.lockerrobot.ticket.Ticket;

public class SLocker extends Locker {
    public SLocker(int capacity) {
        super(capacity);
    }

    public Ticket saveBag(Bag bag) {
        STicket ticket = new STicket();
        lockerMap.put(ticket, bag);
        return ticket;
    }
}
