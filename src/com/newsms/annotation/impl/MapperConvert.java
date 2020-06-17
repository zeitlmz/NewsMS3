package com.newsms.annotation.impl;

import com.newsms.annotation.NotNull;
import com.newsms.annotation.Params;
import com.newsms.util.ObjectEmpty;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 请求参数数据自动封装适配器
 *
 * @author lmz
 */
public class MapperConvert {
    //传入参数对象parameter：这个对象的控制层方法的形参的参数对象，HttpServletRequest对象，clasz是形参的类型
    public <T> T paramsConvert(Parameter parameter, HttpServletRequest hsr, Class<T> clasz) {
        //获取自定义注解对象
        Params params = parameter.getAnnotation(Params.class);
        if (params == null) {
            return null;
        }
        //获取参数命名
        String paramsName = params.value();
        //部分泛型如List<T>需要指定类型
        Class<?> tClass = params.cls();
        //获取参数的全类名
        String className = clasz.getName();
        //获取注解上的的classType值
        String annClassType = params.classType();
        //通过.把全类名截取成数组
        String[] strArr = className.split("\\.");
        //下标最后一位就是类型名称了
        String classType = strArr[strArr.length - 1];
        boolean required = params.required();
        if ("Integer".equals(classType)
                | "String".equals(classType)
                | "Double".equals(classType)
                | "Long".equals(classType)
                | "Float".equals(classType)
                | "int".equals(classType)
                | "double".equals(classType)
                | "long".equals(classType)
                | "float".equals(classType)) {
            return ConvertBaseType(paramsName, hsr, clasz);
        } else if (!tClass.getName().equals(Object.class.getName())) {
            return ConvertGenericList(hsr, tClass);
        } else if ("List".equals(classType)) {
            return ConvertList(hsr);
        } else if ("Map".equals(classType)) {
            return ConvertMap(hsr);
        } else if ("Entity".equals(annClassType)) {
            return ConvertEntity2(hsr, clasz);
        } else {
            System.err.println(paramsName + ":" + STUTIC.PARAM_ILLEGAL_NOT_SUPPORT);
        }
        return null;
    }

    //转换基本数据类型，比如：int，String，double之类的
    private <T> T ConvertBaseType(String paramsName, HttpServletRequest hsr, Class<T> cls) {
        String parameter = hsr.getParameter(paramsName);
        T t = null;
        try {
            if (!ObjectEmpty.isEmpty(parameter)) {
                if (cls.getName().equals(java.util.Date.class.getName())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = sdf.parse(parameter);
                        t = (T) date;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    t = cls.getConstructor(String.class).newInstance(parameter);
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return t;
    }

    private <T> T CreateBaseType(String value, Class<T> cls) {
        T t = null;
        try {
            if (cls.getName().equals(java.util.Date.class.getName())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse(value);
                    t = (T) date;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                t = cls.getConstructor(String.class).newInstance(value);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将request对象的数据封装成指定实体类
     *
     * @param hsr       请求对象
     * @param classType 参数类型
     */
    private <T> T ConvertEntity2(HttpServletRequest hsr, Class<T> classType) {
        try {
            //获取类中所有字段
            Field[] fields = classType.getDeclaredFields();
            //创建类的实例
            T t = classType.getConstructor().newInstance();
            //遍历字段
            for (Field field : fields) {
                //获取该字段上的注解
                NotNull notNull = field.getAnnotation(NotNull.class);
                //获取字段的名称
                String fieldName = field.getName();
                //获取请求中参数为此字段的值
                String parameter = hsr.getParameter(fieldName);
                //如果注解对象不为空
                if (notNull != null) {
                    //获取isNotNull的值，默认为false，true代表不能为空，false代表可以为空
                    boolean isNotNull = notNull.value();
                    if (isNotNull & parameter == null) {
                        System.err.println("【" + fieldName + "】" + STUTIC.PARAM_ILLEGAL_NOTNULL);
                        return null;
                    }
                }
                if (!ObjectEmpty.isEmpty(parameter)) {
                    //先把全部变成大写，然后截取第一个字符
                    char before = fieldName.toUpperCase().charAt(0);
                    //截取除了第一个字符后的所有字符
                    String after = fieldName.substring(1);
                    //使用拼接好的set方法名称获取和字段类型，获取指定set方法
                    String metName = "set" + before + after;
                    String[] split = field.getType().getName().split("\\.");
                    String clsType = split[split.length - 1];
                    Method method = classType.getDeclaredMethod(metName, field.getType());
                    //反射调用set方法,并且将值转换为当前字段的类型
                    Object cast = CreateBaseType(parameter, field.getType());
                    method.invoke(t, cast);
                }
            }
            return t;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将req.getParameterMap数据封装成map集合
     *
     * @param hsr 请求对象
     */
    private <T> T ConvertMap(HttpServletRequest hsr) {
        Map<String, Object> map = new HashMap<>();
        Map<String, String[]> parameterMap = hsr.getParameterMap();
        for (String key : parameterMap.keySet()) {
            for (String[] value : parameterMap.values()) {
                map.put(key, value);
            }
        }
        return (T) map;
    }

    /**
     * 将req.getParameterMap数据封装成指定类型的泛型List集合，这个还没做好，不知道怎么搞
     *
     * @param hsr    请求对象
     * @param tClass 泛型的类型
     */
    private <T> T ConvertGenericList(HttpServletRequest hsr, Class<?> tClass) {
        Map<String, String[]> parameterMap = hsr.getParameterMap();
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
        Object[] vals = new Object[mapSize];
        int a = 0;
        //遍历val并将val放进一个新的普通的数组
        for (String[] val : parameterMap.values()) {
            vals[a] = val;
            a++;
        }
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
                    //为的就是能在同一个循环同时根据一样的下标取出key和对应的val
                    String key = keys[c];
                    //将普通数组存放的value数组取出来，并转换成String数组
                    String[] val = (String[]) vals[c];
                    String value = "";
                    //这个判断是因为可能接收的数据有些字段没有值，可能会造成数组下标越界
                    if (val.length > i) {
                        value = val[i];
                    }
                    if (value.length() != 0) {
                        //用key来查找指定的字段，用来判断实体类是否有这个字段，后面反射调用方法的时候需要指定参数类型
                        Field field = tClass.getDeclaredField(key);
                        //拼接set方法名称
                        String setMethod = "set" + key.toUpperCase().charAt(0) + key.substring(1);
                        //使用拼接好的set方法名称获取和字段类型，获取指定set方法
                        Method method = tClass.getDeclaredMethod(setMethod, field.getType());
                        //反射调用set方法,并且将值转换为当前字段的类型
                        Object values = CreateBaseType(value, field.getType());
                        //反射调用方法
                        method.invoke(o, values);
                    }
                }
                //将对象放入list集合
                list.add(o);
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | NoSuchFieldException ex) {
            ex.printStackTrace();
        }
        return (T) list;
    }

    /**
     * 将req.getParameterMap数据封装成List集合
     *
     * @param hsr 请求对象
     */
    private <T> T ConvertList(HttpServletRequest hsr) {
        List list = new ArrayList();
        Map<String, String[]> parameterMap = hsr.getParameterMap();
        for (String[] values : parameterMap.values()) {
            for (String value : values) {
                list.add(value);
            }
        }
        return (T) list;
    }
}
