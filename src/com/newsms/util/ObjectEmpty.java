package com.newsms.util;

/**
 * @author lmz
 */
public class ObjectEmpty {
    /**
          * 判断Object对象为空或空字符串
          * @param obj
          * @return
          */
    public static Boolean isNotEmpty(Object obj) {
        return !"".equals(obj) && obj != null;
    }
}
