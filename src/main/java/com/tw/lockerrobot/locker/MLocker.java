package com.tw.lockerrobot.locker;

import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.ticket.MTicket;

import java.util.HashMap;
import java.util.Map;

public class MLocker extends Locker{

    public MLocker(int capacity) {
        super(capacity);
    }

    public MTicket saveBag(Bag bag) {
        MTicket ticket = new MTicket();
        lockerMap.put(ticket, bag);
        return ticket;
    }
}
