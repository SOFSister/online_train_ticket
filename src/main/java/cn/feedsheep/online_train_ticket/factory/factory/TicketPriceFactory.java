package cn.feedsheep.online_train_ticket.factory.factory;

import cn.feedsheep.online_train_ticket.factory.TicketInventoryBasic;
import cn.feedsheep.online_train_ticket.factory.TicketPriceBasic;
import cn.feedsheep.online_train_ticket.factory.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.factory.factory
 * @ClassName : TicketPriceFactory.java
 * @createTime : 2022/5/13 23:47
 * @Email : 874280179@qq.com
 * @Description :
 */
public class TicketPriceFactory {
    static Map<Integer, TicketPriceBasic> operationMap = new HashMap<>();
    static {
        operationMap.put(new Integer(0), new BusinessTicketPrice());
        operationMap.put(new Integer(1), new FirstTicketPrice());
        operationMap.put(new Integer(2), new SecondTicketPrice());
        operationMap.put(new Integer(3), new StandingTicketPrice());
    }
    public static Optional<TicketPriceBasic> getOperation(Integer operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }
}
