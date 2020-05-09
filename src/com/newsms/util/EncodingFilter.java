//package com.newsms.util;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//
///**
// * @author lmz
// */
//@WebFilter("/*")
//public class EncodingFilter implements Filter{
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		request.setCharacterEncoding("UTF-8");
//		chain.doFilter(request, response);
//		response.setCharacterEncoding("UTF-8");
//
//	}
//
//}
