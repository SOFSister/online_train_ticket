package cn.feedsheep.online_train_ticket.interceptor;

import cn.feedsheep.online_train_ticket.utils.CommonUtils;
import cn.feedsheep.online_train_ticket.utils.JWTUtils;
import cn.feedsheep.online_train_ticket.utils.JsonData;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.interceptor
 * @ClassName : LoginInterceptor.java
 * @createTime : 2022/5/8 22:34
 * @Email : 874280179@qq.com
 * @Description :
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 进入到controller之前的方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            String accessToken = request.getHeader("token");
            if(accessToken == null){
                accessToken = request.getParameter("token");
            }

            if(StringUtils.isNotBlank(accessToken)){
                Claims claims = JWTUtils.checkJWT(accessToken);

                if(claims == null){
                    //告诉登录过期，重新登录
                    CommonUtils.sendJsonMessage(response, JsonData.buildError(-3,"登录过期，请重新登录"));
                    return false;
                }
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");

                request.setAttribute("user_id",id);
                request.setAttribute("name",name);

                return true;
            }
        }catch (Exception e){
            //登陆失败
            e.printStackTrace();

        }
        CommonUtils.sendJsonMessage(response, JsonData.buildError(-3,"登录过期，请重新登录"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

