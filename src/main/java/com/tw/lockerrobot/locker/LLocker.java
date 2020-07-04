package com.tw.lockerrobot.locker;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.ticket.LTicket;
import com.tw.lockerrobot.ticket.Ticket;

public class LLocker extends Locker {
    public LLocker(int capacity) {
        super(capacity);
    }

    public Ticket saveBag(Bag bag) {
        LTicket ticket = new LTicket();
        lockerMap.put(ticket, bag);
        return ticket;
    }
}
