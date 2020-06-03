package com.newsms.util;

import com.newsms.entity.Router;

import java.util.List;

/**
 * 读取.xml文件并返回指定类型对象List集合
 */
class Test01 {
    public static void main(String[] args) {
        List<Router> routers = ReadXmlAndJsonFileToObj.readXMLFile("resource/Router.xml",Router.class);
        for (Router router : routers) {
            System.out.println(router);
        }
    }
}

/**
 * 读取.json文件并返回指定类型对象List集合
 */
class Test02{
    public static void main(String[] args) {
        List<Router> routers = ReadXmlAndJsonFileToObj.readJSONFile("resource/Router.json", Router.class);
        for (Router router : routers) {
            System.out.println(router);
        }
    }
}
/**
 * 选择单个.xml文件的节点并返回指定类型对象
 */
class Test03{
    public static void main(String[] args) {
        Router router = ReadXmlAndJsonFileToObj.selectXMLNode("resource/Router.xml", "login",Router.class);
        System.out.println(router);
    }
}
