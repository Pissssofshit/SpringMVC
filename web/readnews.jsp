<%@ page language="java" pageEncoding="GB2312" %>
<%@ page import="cn.edu.zucc.news.model.*"%>
<html>
<head><title></title></head>
<body bgcolor="#FFFFFF">
<%
    News news=(News) request.getAttribute("obj");
    if(news==null) return;         
%>
<%
           	out.println(news.getNewsTitle());
           	out.println("<br>");
           	if("y".equals(news.getIsurl())){
           		response.sendRedirect(news.getNewsContent());
             	return;
             }
             out.println(news.getNewsContent());
      	
       	
%>
</body>
</html>