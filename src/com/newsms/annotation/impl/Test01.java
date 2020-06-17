package com.newsms.annotation.impl;

import com.newsms.entity.News;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author lmz
 */
public class Test01 {
    public static void main(String[] args) {
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("newstitle", new String[]{"测试文章01", "测试文章02", "测试文章03"});
        parameterMap.put("newsauthor", new String[]{"小白01", "小白02", "小白03"});
        parameterMap.put("content", new String[]{"测试内容01", "测试内容02", "测试内容03"});
        parameterMap.put("publishdate", new String[]{"2020-01-02", "2020-01-05", "2020-01-09"});
        Class<?> tClass = News.class;
        List<Object> list = new ArrayList<>();
        int max = 0;
        //求出最大的数组长度
        for (String[] value : parameterMap.values()) {
            //如果数组长度大于max，就把值赋给max,直到遍历完毕
            if (value.length > max) {
                max = value.length;
            }
        }
        //mapSize做下面两个数组的数组长度
        int mapSize = parameterMap.size();
        //创建一个用来存放val的普通数组
        Object[] vals = new Object[mapSize];
        int a = 0;
        //遍历val并将val放进一个新的普通的数组
        for (String[] val : parameterMap.values()) {
            vals[a] = val;
            a++;
        }
        //创建一个用来存放key的普通数组
        String[] keys = new String[mapSize];
        int b = 0;
        //遍历key并将key放进一个新的普通的数组
        for (String key : parameterMap.keySet()) {
            keys[b] = key;
            b++;
        }
        try {
            for (int i = 0; i < max; i++) {
                //创建实例
                Object o = tClass.getConstructor().newInstance();
                for (int c = 0; c < mapSize; c++) {
                    String value = "";
                    //为的就是能在同一个循环同时根据一样的下标取出key和对应的val
                    String key = keys[c];
                    String[] val = (String[]) vals[c];
                    //用key来查找指定的字段，后面反射调用方法的时候需要指定参数类型
                    Field field = tClass.getDeclaredField(key);
                    //这个判断是因为可能接收的数据有些字段没有值，可能会造成数组下标越界
                    if (val.length > i) {
                        value = val[i];
                    }
                    //拼接set方法名称
                    String setMethod = "set" + key.toUpperCase().charAt(0) + key.substring(1);
                    //使用拼接好的set方法名称获取和字段类型，获取指定set方法
                    Method method = tClass.getDeclaredMethod(setMethod, field.getType());
                    //反射调用set方法,并且将值转换为当前字段的类型
                    method.invoke(o, field.getType().cast(value));
                }
                //将对象放入list集合
                list.add(o);
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }
}
