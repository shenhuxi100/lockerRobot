package com.tw.lockerRobot;


import com.tw.lockerrobot.exception.NoCapacityException;
import com.tw.lockerrobot.model.Bag;
import com.tw.lockerrobot.model.SBag;
import com.tw.lockerrobot.model.SLocker;
import com.tw.lockerrobot.model.STicket;
import com.tw.lockerrobot.model.Storage;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LockerRobotBagAdminTest {
    /*

Given VIP用户S包，Manager有1个S Locker未满
When Manager存包
Then 存入S Locker，返回S类型票据

Given VIP用户S包，Manager有1个S Locker已满
When Manager存包
Then 无法存入，提示No Capacity
     */
    @Test
    void should_get_s_ticket_when_xiaoying_save_bag_given_common_user_s_bag_1_unfilled_s_locker() {
        Bag bag = new SBag();
        SLocker sLocker = new SLocker(1);
        Storage xiaoying = new Storage(singletonList(sLocker));

        STicket ticket = (STicket) xiaoying.saveBag(bag);

        assertNotNull(ticket);
    }

    @Test
    void should_throw_NoCapacityException_when_xiaoying_save_bag_given_common_user_s_bag_1_fullFilled_locker() {
        Bag bag = new SBag();
        SLocker sLocker = new SLocker(1);
        Storage xiaoying = new Storage(singletonList(sLocker));

        xiaoying.saveBag(bag);

        assertThrows(NoCapacityException.class, () -> xiaoying.saveBag(bag));
    }

    @Test
    void should_throw_NoCapacityException_when_xiaoying_save_bag_given_common_user_s_bag_1_fullFilled_locker_and_2nd_unfilled_locker() {
        Bag bag = new SBag();
        SLocker firstSLocker = new SLocker(1);
        SLocker secondSLocker = new SLocker(1);
        Storage xiaoying = new Storage(Arrays.asList(firstSLocker, secondSLocker));

        xiaoying.saveBag(bag);
        STicket secondTicket = (STicket) xiaoying.saveBag(bag);

        assertNotNull(secondTicket);
    }
}
