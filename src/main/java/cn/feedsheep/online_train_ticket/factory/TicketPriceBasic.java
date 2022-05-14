package cn.feedsheep.online_train_ticket.factory;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.factory
 * @ClassName : TicketPriceBasic.java
 * @createTime : 2022/5/13 23:40
 * @Email : 874280179@qq.com
 * @Description :
 */
public interface TicketPriceBasic {
    Map<String,Object> getTicketPrice(Map<String,Object> map);
}
