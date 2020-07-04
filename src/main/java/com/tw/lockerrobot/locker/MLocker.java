package com.tw.lockerrobot.locker;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.ticket.MTicket;
import com.tw.lockerrobot.ticket.Ticket;

public class MLocker extends Locker{
    public MLocker(int capacity) {
        super(capacity);
    }

    public Ticket saveBag(Bag bag) {
        MTicket ticket = new MTicket();
        lockerMap.put(ticket, bag);
        return ticket;
    }
}
