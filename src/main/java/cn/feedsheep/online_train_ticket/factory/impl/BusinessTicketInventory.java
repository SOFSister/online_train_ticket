package cn.feedsheep.online_train_ticket.factory.impl;

import cn.feedsheep.online_train_ticket.factory.TicketInventoryBasic;
import cn.feedsheep.online_train_ticket.model.entity.Ticket;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.factory.ticket_inventory_impl
 * @ClassName : BussinessTicketInventory.java
 * @createTime : 2022/5/13 15:20
 * @Email : 874280179@qq.com
 * @Description :
 */
public class BusinessTicketInventory implements TicketInventoryBasic {
    @Override
    public Integer getTicketInventory(Ticket ticket) {
        return ticket.getBusinessInventory();
    }
}
