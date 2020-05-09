<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.FileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="java.util.List" %>
<%@ page import="com.newsms.entity.News" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../ppp.jsp" %>
<%
    News news = new News();
    String uploadFileName = "";
//    String uploadFilePath = request.getSession().getServletContext().getRealPath("upload/");
        String uploadFilePath = request.getSession().getServletContext().getRealPath("upload/");
    System.out.println("上传路径："+uploadFilePath);
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
    if (newsservice.addNews(news)) {
        out.print("<h2>添加成功！</h2>");
    } else {
        out.print("<h2>添加失败！</h2>");
    }
    out.print("<a href='admin.jsp?page=1&limit=5'>点击回到主页</a>");
%>