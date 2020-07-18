package com.jarn.common.util;

import com.jarn.entity.User;
import org.springframework.util.StringUtils;

import javax.sql.rowset.serial.SerialStruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * @Author WZY
 **/
public class CommonUtils {

    /**
     * 对象是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        return (obj == null);
    }

    /**
     * String是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(String obj){
        return (obj == null || "".equals(obj));
    }

    /**
     * 数组是否为空
     * @param arrays
     * @return
     */
    public static boolean isEmpty(Object[] arrays){
        return ((arrays == null) || (arrays.length == 0));
    }

    /**
     * 判断集合是否为空
     * @param c
     * @param <E>
     * @return
     */
    public static <E> boolean isEmpty(Collection<E> c){
        return ((c == null) || (c.size() == 0));
    }

}
