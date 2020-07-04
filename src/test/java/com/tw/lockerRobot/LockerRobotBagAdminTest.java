package com.tw.lockerRobot;


import com.tw.lockerrobot.bag.MBag;
import com.tw.lockerrobot.exception.NoCapacityException;
import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.locker.LLocker;
import com.tw.lockerrobot.locker.MLocker;
import com.tw.lockerrobot.robot.PrimaryLockerRobot;
import com.tw.lockerrobot.bag.SBag;
import com.tw.lockerrobot.locker.SLocker;
import com.tw.lockerrobot.ticket.MTicket;
import com.tw.lockerrobot.ticket.STicket;
import com.tw.lockerrobot.Storage;
import com.tw.lockerrobot.robot.SuperLockerRobot;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LockerRobotBagAdminTest {
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
    void should_throw_get_s_ticket_when_xiaoying_save_bag_given_common_user_s_bag_1_fullFilled_locker_and_2nd_unfilled_locker() {
        Bag bag = new SBag();
        SLocker firstSLocker = new SLocker(1);
        SLocker secondSLocker = new SLocker(1);
        Storage xiaoying = new Storage(Arrays.asList(firstSLocker, secondSLocker));

        xiaoying.saveBag(bag);
        STicket secondTicket = (STicket) xiaoying.saveBag(bag);

        assertNotNull(secondTicket);
    }

    @Test
    void should_get_s_ticket_when_manager_save_bag_given_vip_user_s_bag_1_unfilled_s_locker() {
        SLocker sLocker = new SLocker(1);
        MLocker primaryLocker = new MLocker(1);
        LLocker superLocker = new LLocker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(primaryLocker));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(superLocker));
        Storage manager = new Storage(singletonList(sLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag bag = new SBag();
        STicket ticket = (STicket) manager.saveBag(bag);

        assertNotNull(ticket);
    }

    @Test
    void should_throw_NoCapacityException_when_manager_save_bag_given_vip_user_s_bag_1_fullFilled_locker_and_2nd_unfilled_locker() {
        SLocker sLocker = new SLocker(1);
        MLocker primaryLocker = new MLocker(1);
        LLocker superLocker = new LLocker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(primaryLocker));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(superLocker));
        Storage manager = new Storage(singletonList(sLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag bag = new SBag();
        manager.saveBag(bag);

        assertThrows(NoCapacityException.class, () -> manager.saveBag(new Bag()));
    }

    @Test
    void should_get_m_ticket_by_primary_locker_robot_when_manager_save_bag_given_vip_user_m_bag_1_unfilled_m_locker_and_s_unfilled_locker() {
        SLocker sLocker = new SLocker(1);
        MLocker primaryLocker = new MLocker(1);
        LLocker superLocker = new LLocker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(primaryLocker));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(superLocker));
        Storage manager = new Storage(singletonList(sLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag bag = new MBag();
        MTicket mTicket = (MTicket) manager.saveBag(bag);

        assertNotNull(mTicket);
    }

    /*
Given 普通用户M包，PrimaryLockerRobot管理有2个M Locker未满，第一个柜子已满
When 小樱存包
Then 通过PrimaryLockerRobot存入第2个柜子，返回M类型票据

Given 普通用户M包，PrimaryLockerRobot管理有1个M Locker已满
When 小樱存包
Then 无法存入，提示No Capacity

Given VIP用户M包，PrimaryLockerRobot管理有1个M Locker已满，并管理一个S的Locker
When manager存包
Then 无法存入，提示No Capacity
     */
    @Test
    void should_get_m_ticket_by_primary_locker_robot_2nd_locker_when_xiaoying_save_bag_given_common_user_m_bag_and_PrimaryLockerRobot_1_filled_m_locker_2nd_unfilled_locker() {
        SLocker sLocker = new SLocker(1);
        MLocker primaryLocker1 = new MLocker(1);
        MLocker primaryLocker2 = new MLocker(1);
        LLocker superLocker = new LLocker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(primaryLocker1, primaryLocker2));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(superLocker));
        Storage manager = new Storage(singletonList(sLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag bag = new MBag();
        manager.saveBag(bag);
        MTicket mTicket = (MTicket) manager.saveBag(bag);

        assertNotNull(mTicket);
    }
}
