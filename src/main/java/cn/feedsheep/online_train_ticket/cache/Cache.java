package cn.feedsheep.online_train_ticket.cache;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.cache
 * @ClassName : Cache.java
 * @createTime : 2022/5/18 23:42
 * @Email : 874280179@qq.com
 * @Description :
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    /**
     * 过期时间，默认10s
     * @return
     */
    long expire() default 1 * 10 * 1000;

    /**
     * 缓存标识name
     * @return
     */
    String name() default "";
}
