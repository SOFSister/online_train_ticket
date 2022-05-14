package cn.feedsheep.online_train_ticket.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Package : cn.feedsheep.online_train_ticket.model
 * @ClassName : User.java
 * @createTime : 2022/5/8 14:14
 * @Email : 874280179@qq.com
 * @Description :
 *CREATE TABLE `user` (
 *   `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
 *   `user_name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '用户名',
 *   `pwd` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '密码',
 *   `email` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '邮箱',
 *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 *   `id_card` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
 *   `phone` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `email` (`email`) USING BTREE COMMENT '邮箱必须唯一',
 *   UNIQUE KEY `id_card` (`id_card`) USING BTREE COMMENT '身份证必须唯一',
 *   UNIQUE KEY `phone` (`phone`) USING BTREE COMMENT '手机号必须唯一'
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
 */
@Data
@TableName("user")
public class User {

    /**
     * 自增id
     */
    private Integer id;

    /**
     * 用户名
     */
    //@JsonProperty("user_name")
    private String userName;

    /**
     * 密码
     */
    @JsonIgnore
    private String pwd;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证
     */
    //@JsonProperty("id_card")
    private String idCard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    //@JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
