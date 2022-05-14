package cn.feedsheep.online_train_ticket.mapper;

import cn.feedsheep.online_train_ticket.model.entity.TicketOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.mapper
 * @ClassName : TicketOrderMapper.java
 * @createTime : 2022/5/13 12:08
 * @Email : 874280179@qq.com
 * @Description :
 */
@Repository
public interface TicketOrderMapper extends BaseMapper<TicketOrder> {

    int save(TicketOrder ticketOrder);

    TicketOrder selectByTickedIdAndUserId(@Param("ticketId") Integer ticketId,@Param("userId") Integer userId);

    Map<String, Object> selectOrderInfoById(@Param("orderId") Integer id);

    int updateToTimeOutIfStateZero(@Param("orderId") Integer orderId);

    int updateStateByOutTradeNoAndUserId(@Param("state") int state,@Param("outTradeNo") String outTradeNo,@Param("userId") Integer userId);

    List<Map<String, Object>> findByUserId(@Param("userId") Integer userId);

}
