package cn.feedsheep.online_train_ticket.controller;

import cn.feedsheep.online_train_ticket.cache.Cache;
import cn.feedsheep.online_train_ticket.model.entity.Ticket;
import cn.feedsheep.online_train_ticket.service.TicketService;
import cn.feedsheep.online_train_ticket.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.controller
 * @ClassName : TicketController.java
 * @createTime : 2022/5/11 15:26
 * @Email : 874280179@qq.com
 * @Description :
 */
@RestController
@RequestMapping("/api/v1/pub/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/list_ticket")
    @Cache(name = "getListTicket")
    public JsonData getListTicket(@RequestParam("dateTick") Long dateTick,@RequestParam("startStation") String startStation,
                                  @RequestParam("endStation") String endStation){
        List<Ticket> ticketList = ticketService.findByDateAndStation(dateTick,startStation,endStation);
        return JsonData.buildSuccess(ticketList);
    }
}
