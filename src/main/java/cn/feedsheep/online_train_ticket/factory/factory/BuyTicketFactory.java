package cn.feedsheep.online_train_ticket.factory.factory;

import cn.feedsheep.online_train_ticket.factory.BuyTicketBasic;
import cn.feedsheep.online_train_ticket.factory.impl.BuyBusinessSeatTicket;
import cn.feedsheep.online_train_ticket.factory.impl.BuyFirstSeatTicket;
import cn.feedsheep.online_train_ticket.factory.impl.BuySecondSeatTicket;
import cn.feedsheep.online_train_ticket.factory.impl.BuyStandingSeatTicket;

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
 * @ClassName : BuyTicketFactory.java
 * @createTime : 2022/5/13 17:21
 * @Email : 874280179@qq.com
 * @Description :
 */
public class BuyTicketFactory {
    static Map<Integer, BuyTicketBasic> operationMap = new HashMap<>();
    static {
        operationMap.put(new Integer(0), new BuyBusinessSeatTicket());
        operationMap.put(new Integer(1), new BuyFirstSeatTicket());
        operationMap.put(new Integer(2), new BuySecondSeatTicket());
        operationMap.put(new Integer(3), new BuyStandingSeatTicket());
    }
    public static Optional<BuyTicketBasic> getOperation(Integer operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }
}
