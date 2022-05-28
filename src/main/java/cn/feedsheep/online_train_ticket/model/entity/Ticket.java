package cn.feedsheep.online_train_ticket.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @ClassName : Ticket.java
 * @createTime : 2022/5/8 14:22
 * @Email : 874280179@qq.com
 * @Description :
 * CREATE TABLE `ticket` (
 *   `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
 *   `departure_station` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '始发站名',
 *   `next_station` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '下一站名',
 *   `departure_time` datetime NOT NULL COMMENT '发车时间',
 *   `arrival_time` datetime NOT NULL COMMENT '到达时间',
 *   `train_number` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '车次号',
 *   `business_inventory` int DEFAULT '0' COMMENT '商务座余票',
 *   `first_inventory` int DEFAULT '0' COMMENT '一等座余票',
 *   `second_inventory` int DEFAULT '0' COMMENT '二等座余票',
 *   `standing_inventory` int DEFAULT '0' COMMENT '站票余票',
 *   `business_price` int DEFAULT NULL COMMENT '商务座价格（单位分）',
 *   `first_price` int DEFAULT NULL COMMENT '一等座价格（单位分）',
 *   `second_price` int DEFAULT NULL COMMENT '二等座价格（单位分）',
 *   `standing_price` int DEFAULT NULL COMMENT '站票价格（单位分）',
 *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 *   `version` int NOT NULL DEFAULT '0' COMMENT '乐观锁版本号',
 *   PRIMARY KEY (`id`),
 *   KEY `departure_station` (`departure_station`(10),`next_station`(10)) USING BTREE COMMENT '始发站和下一站的联合索引'
 * ) ENGINE=InnoDB AUTO_INCREMENT=7204 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
 */
@Data
@TableName("ticket")
public class Ticket {

    /**
     * 自增id
     */
    private Integer id;

    /**
     * 始发站名
     */
    //@JsonProperty("departure_station")
    private String departureStation;

    /**
     * 下一站名
     */
    //@JsonProperty("next_station")
    private String nextStation;

    /**
     * 发车时间
     */
    //@JsonProperty("departure_time")
    //@JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    private Date departureTime;

    /**
     * 到达时间
     */
    //@JsonProperty("arrival_time")
    //@JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    private Date arrivalTime;

    /**
     * 列车号
     */
    //@JsonProperty("train_number")
    private String trainNumber;

    /**
     * 商务座余票
     */
    //@JsonProperty("business_inventory")
    private Integer businessInventory;

    /**
     * 一等座余票
     */
    //@JsonProperty("first_inventory")
    private Integer firstInventory;

    /**
     * 二等座余票
     */
    //@JsonProperty("second_inventory")
    private Integer secondInventory;

    /**
     * 站票余票
     */
    //@JsonProperty("standing_inventory")
    private Integer standingInventory;

    /**
     * 商务座价格
     */
    //@JsonProperty("business_price")
    private Integer businessPrice;

    /**
     * 一等座价格
     */
    //@JsonProperty("first_price")
    private Integer firstPrice;

    /**
     * 二等座价格
     */
    //@JsonProperty("second_price")
    private Integer secondPrice;

    /**
     * 站票价格
     */
    //@JsonProperty("standing_price")
    private Integer standingPrice;

    /**
     * 创建时间
     */
    //@JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 乐观锁版本号
     */
    @Version
    private Integer version;
}
