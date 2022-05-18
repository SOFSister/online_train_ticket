package cn.feedsheep.online_train_ticket.controller;

import cn.feedsheep.online_train_ticket.exception.DataException;
import cn.feedsheep.online_train_ticket.model.entity.Ticket;
import cn.feedsheep.online_train_ticket.model.entity.TicketOrder;
import cn.feedsheep.online_train_ticket.service.TicketOrderService;
import cn.feedsheep.online_train_ticket.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.controller
 * @ClassName : TicketOrderController.java
 * @createTime : 2022/5/12 21:11
 * @Email : 874280179@qq.com
 * @Description :
 */
@RestController
@RequestMapping("/api/v1/pri/ticket_order")
public class TicketOrderController {

    @Autowired
    private TicketOrderService ticketOrderService;

    @PostMapping("/save")
    public JsonData saveTicketOrder(@RequestBody Map<String,String> ticketOrderMap, HttpServletRequest request) throws Exception {
        Integer ticketId = Integer.valueOf(ticketOrderMap.getOrDefault("ticketId",null));
        Integer seatNo = Integer.valueOf(ticketOrderMap.getOrDefault("seatNo",null));

        Integer userId = (Integer) request.getAttribute("user_id");
        if(ticketId == null || seatNo == null || userId == null){
            throw new DataException(-2,"存在数据为null");
        }else{

            Map<String,Object> map = ticketOrderService.saveTicketOrder(userId,ticketId,seatNo);

            return map != null ? JsonData.buildSuccess(map) : JsonData.buildError(7,"下单失败，请重试");

        }
    }

    @PostMapping("/pay")
    public JsonData payForOrder(@RequestBody Map<String,String> orderPayMap, HttpServletRequest request){
        String outTradeNo = orderPayMap.get("outTradeNo");
        Integer userId = (Integer) request.getAttribute("user_id");

        if(outTradeNo == null || userId == null){
            throw new DataException(-2,"存在数据为null");
        }else{

            return ticketOrderService.payForOrder(outTradeNo,userId) ? JsonData.buildSuccess() : JsonData.buildError(9,"支付失败，请重试");

        }
    }

    @PostMapping("/cancel")
    public JsonData cancelOrder(@RequestBody Map<String,String> orderCancelMap, HttpServletRequest request){
        String outTradeNo = orderCancelMap.get("outTradeNo");
        Integer userId = (Integer) request.getAttribute("user_id");

        if(outTradeNo == null || userId == null){
            throw new DataException(-2,"存在数据为null");
        }else{

            return ticketOrderService.cancelOrder(outTradeNo,userId) ? JsonData.buildSuccess() : JsonData.buildError(10,"取消订单失败，请重试");

        }
    }

    @GetMapping("/order_list")
    public JsonData getOrderList(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        if(userId == null){
            throw new DataException(-2,"存在数据为null");
        }else{

            List<Map<String,Object>> mapList = ticketOrderService.getOrderList(userId);

            return JsonData.buildSuccess(mapList);
        }
    }

}
