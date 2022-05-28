package cn.feedsheep.online_train_ticket.cache;

import cn.feedsheep.online_train_ticket.utils.CacheService;
import cn.feedsheep.online_train_ticket.utils.CommonUtils;
import cn.feedsheep.online_train_ticket.utils.JsonData;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.cache
 * @ClassName : CacheAspect.java
 * @createTime : 2022/5/18 23:47
 * @Email : 874280179@qq.com
 * @Description :
 */
@Component
@Aspect
@Slf4j
public class CacheAspect {
    @Autowired
    private CacheService redisUtils;
    /**
     * aop切点
     * 拦截被指定注解修饰的方法
     */
    @Pointcut("@annotation(cn.feedsheep.online_train_ticket.cache.Cache)")
    public void cache() {
    }

    /**
     * 缓存操作
     *
     * @param pjp
     * @return
     */
    @Around("cache()")
    public Object toCache(ProceedingJoinPoint pjp) {

        try {
            // 思路： 设置存储的格式，获取即可

            Signature signature = pjp.getSignature();
            // 类名
            String className = pjp.getTarget().getClass().getSimpleName();
            // 方法名
            String methodName = signature.getName();

            // 参数处理
            Object[] args = pjp.getArgs();
            Class[] parameterTypes = new Class[args.length];
            String params = "";
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    parameterTypes[i] = args[i].getClass();
                    params += JSON.toJSONString(args[i]);
                }
            }
            if (StringUtils.isNotEmpty(params)) {
                //加密 以防出现key过长以及字符转义获取不到的情况
                params = CommonUtils.MD5(params);
            }

            // 获取controller中对应的方法
            Method method = signature.getDeclaringType().getMethod(methodName, parameterTypes);

            // 获取Cache注解
            Cache annotation = method.getAnnotation(Cache.class);
            long expire = annotation.expire();
            String name = annotation.name();

            // 访问redis（先尝试获取，没有则访问数据库）
            String redisKey = name + "::" + className + "::" + methodName + "::" + params;
            String redisValue = redisUtils.get(redisKey);
            if (StringUtils.isNotEmpty(redisValue)) {
                // 不为空返回数据
                JsonData result = JSON.parseObject(redisValue, JsonData.class);
                log.info("数据从redis缓存中获取,key: {}", redisKey);
                return result; // 跳出方法
            }
            Object proceed = pjp.proceed();
            redisUtils.add(redisKey, JSON.toJSONString(proceed), expire, TimeUnit.MILLISECONDS);
            log.info("数据存入redis缓存,key: {}", redisKey);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return JsonData.buildError("系统错误");
    }
}
