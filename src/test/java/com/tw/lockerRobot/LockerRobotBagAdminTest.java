package com.tw.lockerRobot;


import com.tw.lockerrobot.model.Locker;
import com.tw.lockerrobot.model.Bag;
import com.tw.lockerrobot.model.SBag;
import com.tw.lockerrobot.model.SLocker;
import com.tw.lockerrobot.model.STicket;
import com.tw.lockerrobot.model.Storage;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class LockerRobotBagAdminTest {
    /*
Given 普通用户S包，小樱有1个S Locker未满
When 小樱存包
Then 存入S Locker，返回S类型票据

Given 普通用户S包，小樱有1个S Locker已满
When 小樱存包
Then 无法存入，提示No Capacity

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
        SLocker sLocker = new SLocker();
        Storage xiaoying = new Storage(Arrays.asList(sLocker));

        STicket ticket = (STicket) xiaoying.saveBag(bag);

        assertNotNull(ticket);
    }
}
