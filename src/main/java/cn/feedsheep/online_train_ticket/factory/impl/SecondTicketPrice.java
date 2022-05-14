package cn.feedsheep.online_train_ticket.factory.impl;

import cn.feedsheep.online_train_ticket.factory.TicketPriceBasic;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.factory.impl
 * @ClassName : BusinessTicketPrice.java
 * @createTime : 2022/5/13 23:42
 * @Email : 874280179@qq.com
 * @Description :
 */
public class SecondTicketPrice implements TicketPriceBasic {
    @Override
    public Map<String, Object> getTicketPrice(Map<String, Object> map) {

        map.put("price",map.get("secondPrice"));
        map.remove("businessPrice");
        map.remove("firstPrice");
        map.remove("secondPrice");
        map.remove("standingPrice");

        return map;
    }
}
