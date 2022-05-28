package cn.feedsheep.online_train_ticket.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *@Title JSONUtils.java
 *@description:  JSON工具类
 **/
public class JSONUtils {


    /**
     * @Title: strToObject
     * @Description: json字符串转Object对象
     * @param @param str
     * @param @param clazz
     * @return T
     */
    public static<T> T strToObject(String str,Class<T> clazz){
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(str, clazz);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * @Title: objectToStr
     * @Description: Object对象转json字符串
     * @param @param t
     * @return String
     */
    public static<T> String objectToStr(T t){
        ObjectMapper mapper = new ObjectMapper();
        String str = null;
        try {
            str = mapper.writeValueAsString(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }


}

