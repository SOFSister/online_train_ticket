package cn.feedsheep.online_train_ticket.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.model.entity
 * @ClassName : TicketOrder.java
 * @createTime : 2022/5/8 14:36
 * @Email : 874280179@qq.com
 * @Description :
 * CREATE TABLE `ticket_order` (
 *   `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
 *   `out_trade_no` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '订单号',
 *   `state` int unsigned NOT NULL DEFAULT '0' COMMENT '支付状态：0未支付 1已支付',
 *   `user_id` int unsigned NOT NULL COMMENT '用户编号',
 *   `ticket_id` int unsigned NOT NULL COMMENT '火车票号',
 *   `seat_level` int NOT NULL COMMENT '座位等级：0商务座 1一等座 2二等座 3站票',
 *   `seat_no` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '座位号',
 *   `create_time` datetime NOT NULL COMMENT '订单生成时间',
 *   PRIMARY KEY (`id`),
 *   KEY `user_id` (`user_id`),
 *   KEY `ticket_id` (`ticket_id`),
 *   CONSTRAINT `ticket_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
 *   CONSTRAINT `ticket_order_ibfk_2` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
 */
@Data
public class TicketOrder {

    /**
     * 自增id
     */
    private Integer id;

    /**
     * 订单号
     */
    //@JsonProperty("out_trade_no")
    private String outTradeNo;

    /**
     * 订单状态
     * 0未支付 1已支付
     */
    private Integer state;

    /**
     * 用户编号
     */
    //@JsonProperty("user_id")
    private Integer userId;

    /**
     * 车票编号
     */
    //@JsonProperty("ticket_id")
    private Integer ticketId;

    /**
     * 座位等级
     * 0商务座 1一等座 2二等座 3站票
     */
    //@JsonProperty("seat_level")
    private Integer seatLevel;

    /**
     * 座位位置
     */
    private String seatNo;

    /**
     * 订单创建时间
     */
    //@JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

}
