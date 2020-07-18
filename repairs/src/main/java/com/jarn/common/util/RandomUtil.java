package com.jarn.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author WZY
 **/
public class RandomUtil {

    public static String getUuidStr(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static String getDateStr(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }
}
