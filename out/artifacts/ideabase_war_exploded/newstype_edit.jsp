<%@ page language="java" pageEncoding="GB2312" %>
<%@ page import="cn.edu.zucc.news.model.*"%>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head><title>Lomboz JSP</title></head>
<body bgcolor="#FFFFFF">
<%@ include file="_banner.jsp"%>
<%
    NewsType newstype=(NewsType) request.getAttribute("obj");
    if(newstype==null) return;     
%>
<form action="news" method="post">  
  序号：<input type="text" name="listord" 
              value='<%=newstype.getListOrd()%>'>
  <p>标题：
    <input type="text" name="newstypename" 
              value='<%=newstype.getNewsTypeName()%>'>
  </p>
  <input type="hidden" name="newstypeid" 
              value='<%=newstype.getNewsTypeId()%>'>
  <input type="hidden" name="method" 
              value='<%=newstype.getMethod()%>'>
  <input type="submit" name="Submit" value="提交">
</form>

</body>
</html>