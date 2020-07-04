package com.tw.lockerRobot;


import com.tw.lockerrobot.bag.LBag;
import com.tw.lockerrobot.bag.MBag;
import com.tw.lockerrobot.exception.NoCapacityException;
import com.tw.lockerrobot.bag.Bag;
import com.tw.lockerrobot.locker.LLocker;
import com.tw.lockerrobot.locker.Locker;
import com.tw.lockerrobot.locker.MLocker;
import com.tw.lockerrobot.robot.PrimaryLockerRobot;
import com.tw.lockerrobot.bag.SBag;
import com.tw.lockerrobot.locker.SLocker;
import com.tw.lockerrobot.ticket.LTicket;
import com.tw.lockerrobot.ticket.MTicket;
import com.tw.lockerrobot.ticket.STicket;
import com.tw.lockerrobot.Storage;
import com.tw.lockerrobot.robot.SuperLockerRobot;
import com.tw.lockerrobot.ticket.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(bag, primaryLocker.takeBag(mTicket));
    }

    @Test
    void should_get_m_ticket_by_primary_locker_robot_2nd_locker_when_xiaoying_save_bag_given_common_user_m_bag_and_PrimaryLockerRobot_1_filled_m_locker_2nd_unfilled_locker() {
        SLocker sLocker = new SLocker(1);
        MLocker primaryLocker1 = new MLocker(1);
        MLocker primaryLocker2 = new MLocker(1);
        LLocker superLocker = new LLocker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(primaryLocker1, primaryLocker2));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(superLocker));
        Storage xiaoying = new Storage(singletonList(sLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag bag = new MBag();
        xiaoying.saveBag(bag);
        Bag myBag = new MBag();
        MTicket mTicket = (MTicket) xiaoying.saveBag(myBag);

        assertNotNull(mTicket);
        assertEquals(myBag, primaryLocker2.takeBag(mTicket));
    }

    @Test
    void should_throw_NoCapacityException_when_xiaoying_save_bag_given_common_user_m_bag_and_PrimaryLockerRobot_1_filled_m_locker() {
        SLocker sLocker = new SLocker(1);
        MLocker primaryLocker = new MLocker(1);
        LLocker superLocker = new LLocker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(primaryLocker));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(superLocker));
        Storage xiaoying = new Storage(singletonList(sLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag bag = new MBag();
        xiaoying.saveBag(bag);
        Bag myBag = new MBag();

        assertThrows(NoCapacityException.class, () -> xiaoying.saveBag(myBag));
    }

    @Test
    void should_throw_NoCapacityException_when_xiaoying_save_bag_given_vip_user_m_bag_and_PrimaryLockerRobot_1_filled_m_locker() {
        SLocker sLocker = new SLocker(1);
        MLocker primaryLocker = new MLocker(1);
        LLocker superLocker = new LLocker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(primaryLocker));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(superLocker));
        Storage manager = new Storage(singletonList(sLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag bag = new MBag();
        manager.saveBag(bag);
        Bag myBag = new MBag();

        assertThrows(NoCapacityException.class, () -> manager.saveBag(myBag));
    }

    @Test
    void should_get_l_ticket_by_superLockerRobot_when_manager_save_bag_given_vip_user_l_bag_1_unfilled_l_locker_and_1_unfilled_s_locker() {
        SLocker sLocker = new SLocker(1);
        MLocker primaryLocker = new MLocker(1);
        LLocker superLocker = new LLocker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(primaryLocker));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(superLocker));
        Storage manager = new Storage(singletonList(sLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag bag = new LBag();
        LTicket lTicket = (LTicket) manager.saveBag(bag);

        assertNotNull(lTicket);
        assertEquals(bag, superLocker.takeBag(lTicket));
    }

    @Test
    void should_get_l_ticket_by_superLockerRobot_first_locker_when_xiaoying_save_bag_given_common_user_l_bag_1st_unfilled_2_usable_l_locker_and_2nd_1_usable_locker() {
        SLocker sLocker = new SLocker(1);
        MLocker primaryLocker = new MLocker(1);
        LLocker firstSuperLocker = new LLocker(2);
        LLocker secondSuperLocker = new LLocker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(primaryLocker));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstSuperLocker, secondSuperLocker));
        Storage xiaoying = new Storage(singletonList(sLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag bag = new LBag();
        LTicket lTicket = (LTicket) xiaoying.saveBag(bag);

        assertNotNull(lTicket);
        assertEquals(bag, firstSuperLocker.takeBag(lTicket));
    }

    @Test
    void should_get_l_ticket_by_superLockerRobot_second_locker_when_xiaoying_save_bag_given_common_user_l_bag_1st_unfilled_1_usable_l_locker_and_2nd_2_usable_locker() {
        SLocker sLocker = new SLocker(1);
        MLocker primaryLocker = new MLocker(1);
        LLocker firstSuperLocker = new LLocker(1);
        LLocker secondSuperLocker = new LLocker(2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(primaryLocker));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstSuperLocker, secondSuperLocker));
        Storage xiaoying = new Storage(singletonList(sLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag bag = new LBag();
        LTicket lTicket = (LTicket) xiaoying.saveBag(bag);

        assertNotNull(lTicket);
        assertEquals(bag, secondSuperLocker.takeBag(lTicket));
    }

    @Test
    void should_throw_NoCapacityException_when_xiaoying_save_bag_given_common_user_l_bag_1_filled_locker() {
        SLocker sLocker = new SLocker(1);
        MLocker primaryLocker = new MLocker(1);
        LLocker firstSuperLocker = new LLocker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(primaryLocker));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(firstSuperLocker));
        Storage xiaoying = new Storage(singletonList(sLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag bag = new LBag();
        xiaoying.saveBag(bag);

        assertThrows(NoCapacityException.class, () -> xiaoying.saveBag(new Bag()));
    }

    @Test
    void should_throw_NoCapacityException_when_manager_save_bag_given_vip_user_l_bag_1_filled_locker() {
        SLocker sLocker = new SLocker(1);
        MLocker primaryLocker = new MLocker(1);
        LLocker firstSuperLocker = new LLocker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(primaryLocker));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstSuperLocker));
        Storage manager = new Storage(singletonList(sLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag bag = new LBag();
        manager.saveBag(bag);

        assertThrows(NoCapacityException.class, () -> manager.saveBag(new Bag()));
    }

    @Test
    void should_get_bag_when_xiaoying_take_bag_given_valid_s_ticket() {
        Bag bag = new SBag();
        SLocker sLocker = new SLocker(1);
        Storage xiaoying = new Storage(singletonList(sLocker));
        STicket ticket = (STicket) xiaoying.saveBag(bag);

        Bag returnBag = xiaoying.takeBag(ticket);

        assertEquals(bag, returnBag);
    }

    @Test
    void should_get_bag_when_xiaoying_take_bag_given_valid_m_ticket_and_take_from_PrimaryLockerRobot() {
        Bag bag = new SBag();
        MLocker primaryLocker = new MLocker(1);
        PrimaryLockerRobot xiaoying = new PrimaryLockerRobot(singletonList(primaryLocker));
        MTicket ticket = (MTicket) xiaoying.saveBag(bag);

        Bag returnBag = primaryLocker.takeBag(ticket);

        assertEquals(bag, returnBag);
    }

    /*

Given: 一张L有效票在SuperLockerRobot取 When: 小樱取包，Then: SuperLockerRobot返回一个包
*/
    @Test
    void should_get_bag_when_xiaoying_take_bag_given_valid_L_ticket_and_take_from_SuperLockerRobot() {
        Bag bag = new SBag();
        LLocker primaryLocker = new LLocker(1);
        SuperLockerRobot xiaoying = new SuperLockerRobot(singletonList(primaryLocker));
        LTicket ticket = (LTicket) xiaoying.saveBag(bag);

        Bag returnBag = primaryLocker.takeBag(ticket);

        assertEquals(bag, returnBag);
    }
}
