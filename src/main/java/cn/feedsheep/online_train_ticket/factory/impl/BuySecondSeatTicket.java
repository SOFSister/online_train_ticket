package cn.feedsheep.online_train_ticket.factory.impl;

import cn.feedsheep.online_train_ticket.factory.BuyTicketBasic;
import cn.feedsheep.online_train_ticket.model.entity.Ticket;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.factory.buy_ticket_impl
 * @ClassName : BuyFirstSeatTicket.java
 * @createTime : 2022/5/13 17:13
 * @Email : 874280179@qq.com
 * @Description :
 */
public class BuySecondSeatTicket implements BuyTicketBasic {
    @Override
    public Ticket buyTicket(Ticket ticket) {
        ticket.setSecondInventory(ticket.getSecondInventory() - 1);
        return ticket;
    }
}
