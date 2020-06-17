package com.newsms.annotation.impl;

import com.newsms.annotation.*;
import com.newsms.util.ObjectEmpty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 给controller层映射一二级访问目录
 *
 * @author lmz
 */
public class AccessUrlMapper {
    public static void Go(HttpServletRequest req, HttpServletResponse resp, String oneAction, String twoAction, String controller) throws ServletException, IOException {
        //从查询到的路由信息里面判断取到的Controller是否为空
        if (controller != null) {
            try {
                //动态的加载类
                Class<?> cls = Class.forName(controller);
                //获取类上的注解
                RequestMapping annotation = cls.getAnnotation(RequestMapping.class);
                //如果类上的注解不为空
                if (annotation != null) {
                    //取出注解上的value值，这个value代表映射的一级访问路径
                    String oneUrl = annotation.value().replace("/", "");
                    if (oneUrl.equals(oneAction)) {
                        //创建类的实例
                        Object obj = cls.getDeclaredConstructor().newInstance();
                        //遍历类中的所有方法
                        Method[] declaredMethods = cls.getDeclaredMethods();
                        for (Method met : declaredMethods) {
                            RequestMapping requestMapping = met.getAnnotation(RequestMapping.class);
                            PostMapping postMapping = met.getAnnotation(PostMapping.class);
                            GetMapping getMapping = met.getAnnotation(GetMapping.class);
                            String reqMethod = req.getMethod();
                            //代表二级访问路径
                            String twoUrl = "";
                            if (requestMapping != null) {
                                twoUrl = requestMapping.value().replace("/", "");
                            } else if (postMapping != null) {
                                twoUrl = postMapping.value().replace("/", "");

                            } else if ((getMapping != null)) {
                                twoUrl = getMapping.value().replace("/", "");
                            }
                            if (twoUrl.equals(twoAction)) {
                                if (requestMapping != null) {
                                    //获取requestMapping注解上的规定的请求方法
                                    String annMethod = requestMapping.method();
                                    if (!"".equals(annMethod)) {
                                        //如果规定的是POST，但是请求对象中的请求方法不是POST,就提示错误信息
                                        if ("POST".equals(annMethod) && !reqMethod.equals(annMethod)) {
                                            System.err.println(met.getName() + "方法请使用POST方式提交！");
                                            return;
                                            //如果规定的是GET，但是请求对象中的请求方法不是GET,就提示错误信息
                                        } else if ("GET".equals(annMethod) && !reqMethod.equals(annMethod)) {
                                            System.err.println(met.getName() + "方法请使用GET方式提交！");
                                            return;
                                        } else if (("GET".equals(annMethod) & reqMethod.equals(annMethod))
                                                | "POST".equals(annMethod) & reqMethod.equals(annMethod)) {
                                            twoUrl = requestMapping.value().replace("/", "");
                                        } else {
                                            System.err.println(met.getName() + "方法暂时不支持其他提交方式！请使用POST或者GET！");
                                        }
                                    }
                                } else if (postMapping != null) {
                                    if (!"POST".equals(reqMethod)) {
                                        System.err.println(met.getName() + "方法请使用POST方式提交！");
                                        return;
                                    }
                                } else if (getMapping != null) {
                                    if (!"GET".equals(reqMethod)) {
                                        System.err.println(met.getName() + "方法请使用GET方式提交！");
                                        return;
                                    }
                                }
                                Object result = ParamsMapper.Go(met, obj, req, resp);
                                ResponseType responseType = met.getAnnotation(ResponseType.class);
                                if (ObjectEmpty.isEmpty(responseType)) {
                                    Result.respJson(resp, result);
                                } else {
                                    String value = responseType.value();
                                    String respType = value.toUpperCase();
                                    if ("TEXT/HTML".equals(respType)) {
                                        Result.respText_Html(resp, result);
                                    } else if ("JSON".equals(respType)) {
                                        Result.respJson(resp, result);
                                    } else if ("PAGE".equals(respType)) {
                                        Result.respPage(resp, req, result);
                                    } else {
                                        System.err.println(STUTIC.RESPONSE_TYPE_NOTFOUND);
                                        System.err.println("温馨提示：可用类型有以下几种：");
                                        System.err.println(STUTIC.RESPONSE_TYPE_TEXT_HTML);
                                        System.err.println(STUTIC.RESPONSE_TYPE_JSON);
                                        System.err.println(STUTIC.RESPONSE_TYPE_PAGE);
                                    }
                                }
                                break;
                            }
                        }
                    } else {
                        req.getRequestDispatcher("/404.html").forward(req, resp);
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e) {
                req.getRequestDispatcher("/404.html").forward(req, resp);
                System.err.println("==========调用方法出错!============");
                e.printStackTrace();
            }
        } else {
            req.getRequestDispatcher("/404.html").forward(req, resp);
        }
    }
}
