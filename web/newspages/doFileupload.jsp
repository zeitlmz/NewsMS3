<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.FileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="java.util.List" %>
<%@ page import="com.newsms.entity.News" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../ppp.jsp" %>
<%
    request.setCharacterEncoding("utf-8");
    News news = new News();
    String uploadFileName = "";
    String uploadFilePath = request.getSession().getServletContext().getRealPath("upload/");
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    if (isMultipart) {
        FileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
        List<FileItem> filelist = null;
        try {
            filelist = upload.parseRequest(request);
            for (FileItem fileItem : filelist) {
                //判断当前元素是否是一个普通表单元素
                if (fileItem.isFormField()) {
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
                    }
                } else {
                    String fileName = fileItem.getName();
                    List<String> gs= Arrays.asList("jpg","png","jpeg","bmp","gif");
                    String ext=fileName.substring(fileName.indexOf(".")+1);
                    if (gs.contains(ext)){
                        File file = new File(fileName);
                        File saveFile = new File(uploadFilePath, file.getName());
                        fileItem.write(saveFile);
                        news.setPicture(uploadFilePath + fileName);
                    }else{
                        response.sendRedirect("fileError.jsp");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        String newsTitle = request.getParameter("newsTitle");
        String newsAuthor = request.getParameter("newsAuthor");
        String content = request.getParameter("content");
        Integer topicId = Integer.valueOf(request.getParameter("topicId"));

        news.setNewstitle(newsTitle);
        news.setNewsauthor(newsAuthor);
        news.setContent(content);
        news.setTopicId(topicId);
    }
    if (newsservice.addNews(news)) {
        out.print("<h2>添加成功！</h2>");
    } else {
        out.print("<h2>添加失败！</h2>");
    }
    out.print("<a href='admin.jsp?page=1&limit=5'>点击回到主页</a>");
%>