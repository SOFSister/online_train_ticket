package cn.feedsheep.online_train_ticket.mapper;

import cn.feedsheep.online_train_ticket.model.entity.Ticket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.mapper
 * @ClassName : TicketMapper.java
 * @createTime : 2022/5/11 15:28
 * @Email : 874280179@qq.com
 * @Description :
 */
@Repository
public interface TicketMapper {

    int save(Ticket ticket);

    List<Ticket> findByDateAndStation(@Param("dateStart") Date dateStart,@Param("dateEnd") Date dateEnd,
                                      @Param("startStation") String startStation,@Param("endStation") String endStation);
}
