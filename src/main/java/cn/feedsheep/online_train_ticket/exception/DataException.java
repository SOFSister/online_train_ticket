package cn.feedsheep.online_train_ticket.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.exception
 * @ClassName : DataException.java
 * @createTime : 2022/5/17 13:52
 * @Email : 874280179@qq.com
 * @Description :
 */
@Data
@AllArgsConstructor
public class DataException extends RuntimeException{

    private Integer code;

    private String msg;
}
