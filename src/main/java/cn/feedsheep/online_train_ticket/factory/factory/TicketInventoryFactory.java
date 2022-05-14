package cn.feedsheep.online_train_ticket.factory.factory;

import cn.feedsheep.online_train_ticket.factory.TicketInventoryBasic;
import cn.feedsheep.online_train_ticket.factory.impl.BusinessTicketInventory;
import cn.feedsheep.online_train_ticket.factory.impl.FirstTicketInventory;
import cn.feedsheep.online_train_ticket.factory.impl.SecondTicketInventory;
import cn.feedsheep.online_train_ticket.factory.impl.StandingTicketInventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.factory
 * @ClassName : OperateTicketInventoryBasic.java
 * @createTime : 2022/5/13 15:26
 * @Email : 874280179@qq.com
 * @Description :
 */
public class TicketInventoryFactory {
    static Map<Integer, TicketInventoryBasic> operationMap = new HashMap<>();
    static {
        operationMap.put(new Integer(0), new BusinessTicketInventory());
        operationMap.put(new Integer(1), new FirstTicketInventory());
        operationMap.put(new Integer(2), new SecondTicketInventory());
        operationMap.put(new Integer(3), new StandingTicketInventory());
    }
    public static Optional<TicketInventoryBasic> getOperation(Integer operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }
}
