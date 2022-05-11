package cn.feedsheep.online_train_ticket.service.impl;

import cn.feedsheep.online_train_ticket.mapper.TicketMapper;
import cn.feedsheep.online_train_ticket.model.entity.Ticket;
import cn.feedsheep.online_train_ticket.service.TicketService;
import cn.feedsheep.online_train_ticket.utils.MatcherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.service.impl
 * @ClassName : TicketServiceImpl.java
 * @createTime : 2022/5/11 15:28
 * @Email : 874280179@qq.com
 * @Description :
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public List<Ticket> findByDateAndStation(Long dateTick, String startStation, String endStation) {
        //判断是否为空
        if(dateTick == null || startStation == null || endStation == null){
            return null;
        }else{

            Long aDay = 24L * 3600L * 1000L;

            //一天的开始
            Date dateStart = new Date(dateTick);
            //一天的结束
            Date dateEnd = new Date(dateTick + aDay - 1);

            List<Ticket> ticketList = ticketMapper.findByDateAndStation(dateStart,dateEnd,startStation,endStation);

            return ticketList;
        }
    }
}
