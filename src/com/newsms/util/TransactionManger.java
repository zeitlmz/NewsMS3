package com.newsms.util;

import com.newsms.annotation.Transactional;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用动态代理实现aop切入事务
 *
 * @author lmz
 */
public class TransactionManger {
    public static Object getInstance(final Object o) {
        //方法执行器，帮我们执行目标方法
        InvocationHandler invocationHandler = new InvocationHandler() {
            /**
             *
             * @param proxy 代理对象，不用使用这个
             * @param method 当前将要执行的目标对象的方法
             * @param args 当前将要执行的目标对象的参数
             * @return 处理后的代理对象
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //如果方法上没有带@Transactional注解，就不用开启事务
                if (!method.isAnnotationPresent(Transactional.class)) {
                    return method.invoke(o, args);
                }
                Object result = null;
                try {
                    //前置通知开启事务
                    TM.begin();
                    //利用反射调用方法，拿到返回值
                    result = method.invoke(o, args);
                    //后置通知提交
                    TM.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("出现异常，已回滚！");
                    //异常通知回滚
                    TM.rollback();
                }
                return result;
            }
        };
        //获取objectImpl中
        Class<?>[] interfaces = o.getClass().getInterfaces();
        //获取objectImpl类型的
        ClassLoader classLoaders = o.getClass().getClassLoader();
        return Proxy.newProxyInstance(classLoaders, interfaces, invocationHandler);
    }
}
