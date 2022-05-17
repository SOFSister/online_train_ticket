package cn.feedsheep.online_train_ticket.exception;

import cn.feedsheep.online_train_ticket.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.exception
 * @ClassName : CustomExceptionHandler.java
 * @createTime : 2022/5/8 18:17
 * @Email : 874280179@qq.com
 * @Description :
 */
@ControllerAdvice
public class CustomExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handle(Exception e){

        if(e instanceof UserException){

            UserException loginException = (UserException) e;

            return JsonData.buildError(loginException.getCode(),loginException.getMsg());

        }else if(e instanceof DataException){

            DataException dataException = (DataException) e;

            return JsonData.buildError(dataException.getCode(),dataException.getMsg());

        }else if(e instanceof TicketOrderException){

            TicketOrderException ticketOrderException = (TicketOrderException) e;

            return JsonData.buildError(ticketOrderException.getCode(),ticketOrderException.getMsg());

        } else{
            logger.error("[ 系统异常 ]{}",e);

            return JsonData.buildError("全局异常，未知错误");
        }
    }

}
