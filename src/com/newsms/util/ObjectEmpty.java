package com.newsms.util;

/**
 * @author lmz
 */
public class ObjectEmpty {
    /**
     * 判断Object对象为空或空字符串
     *
     * @param obj 检验对象
     * @return  检验结果
     */
    public static Boolean isEmpty(Object obj) {
        return "".equals(obj) || obj == null;
    }
}
