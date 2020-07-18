package com.jarn.common.cache;

import com.jarn.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author WZY
 * session缓存
 **/
public class SessionCache {

    private static ConcurrentHashMap<String, User> CACHE_USER;

    public static ConcurrentHashMap<String, User> getCacheMap(){
        if (CACHE_USER == null) CACHE_USER = new ConcurrentHashMap<>();
        return CACHE_USER;
    }

    public static void setUser(HttpServletRequest request, User user){
        String sessionId = request.getSession().getId();
        getCacheMap().put(sessionId,user);
    }

    public static User getUser(HttpServletRequest request){
        return getCacheMap().get(request.getSession().getId());
    }

    public static void removeUser(HttpServletRequest request){
        getCacheMap().remove(request.getSession().getId());
    }
}
