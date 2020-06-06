package com.newsms.controller;

import com.google.gson.Gson;
import com.newsms.entity.News;
import com.newsms.entity.Page;
import com.newsms.entity.Topic;
import com.newsms.service.NewsService;
import com.newsms.service.TopicService;
import com.newsms.service.impl.NewsServiceImpl;
import com.newsms.service.impl.TopicServiceImpl;
import com.newsms.util.ObjectEmpty;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/account")
public class AdminController extends HttpServlet {
    private NewsService newsService = new NewsServiceImpl();
    private TopicService topicService = new TopicServiceImpl();
    private Integer currPage = 1;
    private Integer limit = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("actioon");
        if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }
    }

    public void adminSearchNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("searchInfo");
        request.getSession().setAttribute("topics", topicList());
        adminSearchNewsPage(request, response);
    }

    public void adminSearchNewsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newsTitle = request.getParameter("newsTitle");
        String newsAuthor = request.getParameter("newsAuthor");
        String content = request.getParameter("content");
        String publishDate = request.getParameter("publishDate");
        String topicId = request.getParameter("topicId");

        String currPages = request.getParameter("pageIndex");
        String limit = request.getParameter("limit");

        if (currPages != null) {
            this.currPage = Integer.parseInt(currPages);
        }
        if (limit != null) {
            this.limit = Integer.parseInt(limit);
        } else {
            this.limit = 10;
        }

        System.out.println(newsTitle + newsAuthor + content + publishDate + topicId);
        Map<String, Object> map = null;
        if (request.getSession().getAttribute("searchInfo") == null) {
            map = new HashMap<>();
        } else {
            map = (Map<String, Object>) request.getSession().getAttribute("searchInfo");
        }

        if (ObjectEmpty.isEmpty(newsTitle)) {
            map.put("newsTitle", newsTitle);
        }
        if (ObjectEmpty.isEmpty(newsAuthor)) {
            map.put("newsAuthor", newsAuthor);
        }
        if (ObjectEmpty.isEmpty(content)) {
            map.put("content", content);
        }
        if (ObjectEmpty.isEmpty(publishDate)) {
            map.put("publishDate", publishDate);
        }
        if (ObjectEmpty.isEmpty(topicId)) {
            map.put("topicId", topicId);
        }

        map.put("limit", this.limit);
        map.put("page", this.currPage);
        request.getSession().setAttribute("searchInfo", map);
        Page pages = newsService.searchNews(map);
        Writer out = response.getWriter();
        Gson gson = new Gson();
        String a = gson.toJson(pages);
        out.write(a);
        request.getSession().setAttribute("adminNews", pages);
        request.getRequestDispatcher("newspages/admin.html").forward(request, response);
    }

    public void updateNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long newsId = Long.parseLong(request.getParameter("newsId"));
        String newsTitle = request.getParameter("newsTitle");
        String newsAuthor = request.getParameter("newsAuthor");
        String content = request.getParameter("content");
        Integer topicId = Integer.valueOf(request.getParameter("topicId"));

        News news = new News();
        news.setNewstitle(newsTitle);
        news.setNewsauthor(newsAuthor);
        news.setContent(content);
        news.setTopicId(topicId);
        news.setNewsid(newsId);

        Writer out = response.getWriter();
        if (newsService.updateNews(news)) {
            out.write("<h2>修改成功！</h2>");
            out.write("<a href='admin.html?page=1&limit=5'>点击回到主页</a>");
        } else {
            out.write("<h2>修改失败！</h2>");
            out.write("<a href='admin.html?page=1&limit=5'>点击回到主页</a>");
        }
    }

    public void addNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        News news = new News();
        String uploadFileName = "";
        String uploadFilePath = request.getSession().getServletContext().getRealPath("upload/");
        System.out.println("上传路径：" + uploadFilePath);
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            //创建文件工厂
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建文件上传服务对象
            ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
            //设置文件格式编码
            upload.setHeaderEncoding("utf-8");
            List<FileItem> filelist = null;
            try {
                //前端上传的文件对象信息进行处理
                filelist = upload.parseRequest(request);
                for (FileItem fileItem : filelist) {
                    //判断当前元素是否是一个普通表单元素
                    if (fileItem.isFormField()) {
                        request.setCharacterEncoding("utf-8");
                        String fieldName = fileItem.getFieldName();
                        switch (fieldName) {
                            case "newsTitle":
                                news.setNewstitle(fileItem.getString());
                                break;
                            case "newsAuthor":
                                news.setNewsauthor(fileItem.getString());
                                break;
                            case "content":
                                news.setContent(fileItem.getString());
                                break;
                            case "topicId":
                                news.setTopicId(Integer.valueOf(fileItem.getString()));
                                break;
                            default:
                                break;
                        }
                    } else {
                        //获取文件名称
                        String fileName = fileItem.getName();
                        //定义用来规定上传图片格式的数组
                        List<String> gs = Arrays.asList("jpg", "png", "jpeg", "bmp", "gif");
                        //截取文件名的后缀名
                        String ext = fileName.substring(fileName.indexOf(".") + 1);
                        //规定数组内是否包含文件名的后缀名，如果不包含直接重定向到提示用户上传文件格式错误页面
                        if (gs.contains(ext)) {
                            //创建文件
                            File file = new File(fileName);
                            File saveFile = new File(uploadFilePath, file.getName());
                            //将上传的文件写入服务器新建的文件
                            fileItem.write(saveFile);
                            news.setPicture(uploadFilePath + fileName);
                        } else {
                            response.sendRedirect("fileError.jsp");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.setCharacterEncoding("utf-8");
            String newsTitle = request.getParameter("newsTitle");
            String newsAuthor = request.getParameter("newsAuthor");
            String content = request.getParameter("content");
            Integer topicId = Integer.valueOf(request.getParameter("topicId"));

            news.setNewstitle(newsTitle);
            news.setNewsauthor(newsAuthor);
            news.setContent(content);
            news.setTopicId(topicId);
        }
        Writer out = response.getWriter();
        if (newsService.addNews(news)) {
            out.write("<h2>添加成功！</h2>");
        } else {
            out.write("<h2>添加失败！</h2>");
        }
        out.write("<a href='admin.html?page=1&limit=5'>点击回到主页</a>");
    }

    public void delNewsByNewsId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newsId = Integer.parseInt(request.getParameter("newsId"));
        boolean flag = newsService.delNewsByNewsId(newsId);
        Writer out = response.getWriter();
        out.write(String.valueOf(flag));
    }

    public List<Topic> topicList() {
        return topicService.selectTopicList();
    }

}
