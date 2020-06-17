package com.newsms.annotation.impl;

import com.newsms.annotation.*;
import com.newsms.util.ObjectEmpty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 给controller动作方法参数数据
 *
 * @author lmz
 */
public class ParamsMapper {
    public static Object Go(Method met, Object obj, HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, ServletException, IOException {
        //实例化自己定义的注解实现类
        MapperConvert mapperConvert = new MapperConvert();
        Object result = null;
        //如果找到相同方法名的就执行以下代码
        //获取该方法中的所有参数
        Parameter[] parameters = met.getParameters();
        //创建一个数组用来存放获取到的mapperConvert的返回值，将Parameter[]的长度作为用来存返回值数组的长度，即动态数组长度
        Object[] paramsArr = new Object[parameters.length];
        int i = 0;
        //遍历参数
        for (Parameter params : parameters) {
            //往paramsConvert中传入参数对象，请求对象，参数类型，将返回值添加到数组
            String pname = params.getType().getName();
            if (pname.equals(HttpServletRequest.class.getName())) {
                paramsArr[i] = req;
                i++;
            } else if (pname.equals(HttpServletResponse.class.getName())) {
                paramsArr[i] = resp;
                i++;
            } else if (params.getAnnotation(Params.class) != null) {
                paramsArr[i] = mapperConvert.paramsConvert(params, req, params.getType());
                i++;
            }
        }
        for (Object o : paramsArr) {
            System.out.println("参数："+o);
        }
        //反射调用方法，并传入参数数组
        result = met.invoke(obj, paramsArr);
        return result;
    }
}
