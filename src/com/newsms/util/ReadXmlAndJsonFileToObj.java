package com.newsms.util;

import com.alibaba.fastjson.JSON;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

/**
 * @author lmz
 * 读取json和xml文件，并且转换成指定的类型的对象或者List<T>
 * 依赖jar包：读取文档 dom4j-1.6.1.jar、Json和对象之间的转换 fastjson-1.2.68.jar
 */
public class ReadXmlAndJsonFileToObj {
    /**
     * 选择xml文件中的某个节点，将其转换成指定类型对象
     *
     * @param path      xml文件位置
     * @param action    url请求方法参数，比如login、index，等等
     * @param classType 指定要转换的对象的类型
     * @param <T>       代表传入的classType类型对象
     * @return 返回指定类型的对象
     */
    public static <T> T selectXMLNode(String path, String action, Class<T> classType) {
        SAXReader saxReader = new SAXReader();
        StringBuffer sb = null;
        Document doc = null;
        try {
            sb = new StringBuffer();
            doc = saxReader.read(path);
            Element root = doc.getRootElement();
            Iterator<Element> routers = root.elementIterator();
            while (routers.hasNext()) {
                Element router = routers.next();
                Iterator<Element> val = router.elementIterator();
                Element vals = val.next();
                String text = vals.getText();
                if (text.equals(action)) {
                    sb.append("{");
                    sb.append("\"").append(vals.getName()).append("\":\"").append(vals.getText()).append("\",");
                    while (val.hasNext()) {
                        Element next = val.next();
                        sb.append("\"").append(next.getName()).append("\":\"").append(next.getText()).append("\",");
                    }
                    sb.deleteCharAt(sb.lastIndexOf(","));
                    sb.append("}");
                    break;
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(sb.toString(), classType);
    }

    /**
     * 选择xml文件中的某个节点，将其转换成指定类型的对象List集合
     *
     * @param path      xml文件位置
     * @param classType 指定要转换的对象的类型
     * @param <T>       代表传入的classType类型对象
     * @return 返回指定类型的对象List集合
     */
    public static <T> List<T> readJSONFile(String path, Class<T> classType) {
        File file = new File("resource/Router.json");
        Reader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new FileReader(file);
            char[] chars = new char[8];
            int len = 0;
            while ((len = reader.read(chars)) != -1) {
                sb.append(new String(chars, 0, len));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseArray(sb.toString(), classType);
    }

    /**
     * 读取xml文件中的所有节点，将其转换成指定类型的对象List集合
     *
     * @param path      xml文件位置
     * @param classType 指定要转换的对象的类型
     * @param <T>       代表传入的classType类型对象
     * @return 返回指定类型的对象List集合
     */
    public static <T> List<T> readXMLFile(String path, Class<T> classType) {
        SAXReader saxReader = new SAXReader();
        StringBuffer sb = null;
        Document doc = null;
        try {
            sb = new StringBuffer();
            doc = saxReader.read(path);
            Element root = doc.getRootElement();
            Iterator<Element> routers = root.elementIterator();
            sb.append("[");
            while (routers.hasNext()) {
                Element router = routers.next();
                Iterator<Element> val = router.elementIterator();
                sb.append("{");
                while (val.hasNext()) {
                    Element vals = val.next();
                    sb.append("\"").append(vals.getName()).append("\":\"").append(vals.getText()).append("\",");
                }
                sb.deleteCharAt(sb.lastIndexOf(","));
                sb.append("},");
            }
            //删除最后一个逗号
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append("]");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return JSON.parseArray(sb.toString(), classType);
    }
}
