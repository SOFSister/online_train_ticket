package cn.feedsheep.online_train_ticket.service.impl;

import cn.feedsheep.online_train_ticket.exception.TicketOrderException;
import cn.feedsheep.online_train_ticket.factory.BuyTicketBasic;
import cn.feedsheep.online_train_ticket.factory.TicketPriceBasic;
import cn.feedsheep.online_train_ticket.factory.factory.BuyTicketFactory;
import cn.feedsheep.online_train_ticket.factory.factory.TicketInventoryFactory;
import cn.feedsheep.online_train_ticket.factory.TicketInventoryBasic;
import cn.feedsheep.online_train_ticket.factory.factory.TicketPriceFactory;
import cn.feedsheep.online_train_ticket.mapper.TicketMapper;
import cn.feedsheep.online_train_ticket.mapper.TicketOrderMapper;
import cn.feedsheep.online_train_ticket.model.entity.Ticket;
import cn.feedsheep.online_train_ticket.model.entity.TicketOrder;
import cn.feedsheep.online_train_ticket.service.TicketOrderService;
import cn.feedsheep.online_train_ticket.utils.DelayQueueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.service.impl
 * @ClassName : TicketOrderServiceImpl.java
 * @createTime : 2022/5/12 21:18
 * @Email : 874280179@qq.com
 * @Description :
 */
@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketOrderMapper ticketOrderMapper;

    @Autowired
    private DelayQueueUtils delayQueueUtils;

    private final Integer DELAY_TIME = 30000;

    @Override
    @Transactional
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 1, maxDelay = 10000))
    public Map<String,Object> saveTicketOrder(Integer userId, Integer ticketId, Integer seatNo) throws Exception {
        //判断是否买过这张票
        TicketOrder hasTicketOrder = ticketOrderMapper.selectByTickedIdAndUserId(ticketId,userId);
        if(hasTicketOrder != null){
            throw new TicketOrderException(8,"请不要重复下单");
        }

        //判断是否还有余票

        Ticket ticket = ticketMapper.selectById(ticketId);

        Integer ticketInventory = getTicketInventoryFactory(ticket,seatNo);

        //若有 可以下单 并随机产生座位

        if(ticketInventory != null && ticketInventory > 0){
            //下单
            //库存票数-1
            ticket = getBuyTicketFactory(ticket,seatNo);

            int i = ticketMapper.updateById(ticket);
            if(i == 0){
                throw new Exception();
            }
            //添加订单
            TicketOrder ticketOrder = new TicketOrder();

            String outTradeNo = UUID.randomUUID().toString().replace("-","");
            ticketOrder.setOutTradeNo(outTradeNo);
            ticketOrder.setState(0);
            ticketOrder.setUserId(userId);
            ticketOrder.setTicketId(ticketId);
            ticketOrder.setSeatLevel(seatNo);
            ticketOrder.setSeatNo(getRandomSeatNo());
            ticketOrder.setCreateTime(new Date());

            ticketOrderMapper.save(ticketOrder);

            //下单成功 放入延时消息队列
            delayQueueUtils.sendDelayMsg(String.valueOf(ticketOrder.getId()),DELAY_TIME);

            //查询所有需要的信息放入map并返回
            Map<String,Object> responseMap = ticketOrderMapper.selectOrderInfoById(ticketOrder.getId());

            responseMap = getTicketPriceFactory(responseMap,seatNo);

            return responseMap;

        }else{//若没有 下单失败 返回失败
            return null;
        }


    }

    @Override
    public boolean payForOrder(String outTradeNo, Integer userId) {

        int i = ticketOrderMapper.updateStateByOutTradeNoAndUserId(1,outTradeNo,userId);

        return i > 0 ? true : false;
    }

    @Override
    public List<Map<String, Object>> getOrderList(Integer userId) {

        List<Map<String,Object>> mapList = ticketOrderMapper.findByUserId(userId);

        return mapList;
    }


    /**
     * 得到剩余票数
     * @param ticket
     * @param seatNo 座位号（0商务票，1一等座，2二等座，3站票）
     * @return
     */
    private Integer getTicketInventoryFactory(Ticket ticket, Integer seatNo) {

        TicketInventoryBasic ticketInventoryBasic = TicketInventoryFactory
                .getOperation(seatNo)
                .orElseThrow(() -> new IllegalArgumentException("无效的座位号"));
        return ticketInventoryBasic.getTicketInventory(ticket);
    }

    /**
     * 操作ticket，让对应座位的票数减一
     * @param ticket
     * @param seatNo 座位号（0商务票，1一等座，2二等座，3站票）
     * @return
     */
    private Ticket getBuyTicketFactory(Ticket ticket, Integer seatNo) {

        BuyTicketBasic buyTicketBasic = BuyTicketFactory
                .getOperation(seatNo)
                .orElseThrow(() -> new IllegalArgumentException("无效的座位号"));
        return buyTicketBasic.buyTicket(ticket);
    }

    /**
     * 过滤map中的价格
     * @param map
     * @param seatNo 座位号（0商务票，1一等座，2二等座，3站票）
     * @return
     */
    private Map<String,Object> getTicketPriceFactory(Map<String,Object> map, Integer seatNo) {

        TicketPriceBasic ticketPriceBasic = TicketPriceFactory
                .getOperation(seatNo)
                .orElseThrow(() -> new IllegalArgumentException("无效的座位号"));
        return ticketPriceBasic.getTicketPrice(map);
    }

    /**
     * 分配随机座位
     * @return
     */
    private String getRandomSeatNo(){
        Random random = new Random();
        String seatNoStr = (random.nextInt(7) + 2) + "号车厢";

        int seatId = random.nextInt(13);
        if(seatId < 10){
            seatNoStr = seatNoStr + "0" + seatId;
        }else{
            seatNoStr = seatNoStr + seatId;
        }

        char seatChar = (char)('A' + random.nextInt(5));
        if(seatChar == 'E'){
            seatChar = 'F';
        }
        seatNoStr = seatNoStr + seatChar;

        return seatNoStr;
    }
}
