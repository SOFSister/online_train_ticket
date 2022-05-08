package cn.feedsheep.online_train_ticket.config;

import cn.feedsheep.online_train_ticket.interceptor.CorsInterceptor;
import cn.feedsheep.online_train_ticket.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.config
 * @ClassName : InterceptorConfig.java
 * @createTime : 2022/5/8 22:41
 * @Email : 874280179@qq.com
 * @Description :
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 添加全局跨域拦截器配置
         */
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");

        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v1/pri/*/**")
                .excludePathPatterns("/api/v1/pri/user/register","/api/v1/pri/user/login");

        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * 登录拦截
     * @return
     */
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    /**
     * 跨域配置
     * @return
     */
    @Bean
    public CorsInterceptor corsInterceptor(){return new CorsInterceptor();}

}

