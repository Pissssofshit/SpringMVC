<%@ page language="java" pageEncoding="GB2312" %>
<%@ page import="cn.edu.zucc.news.model.*"%>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>Lomboz JSP</title>
</head>
<body bgcolor="#FFFFFF">
<%@ include file="_banner.jsp"%>
<%
    News news=(News) request.getAttribute("obj");
    if(news==null) return;         
%>
<form action="news" method="post">  
  <p>标题：
    <input type="text" name="newstitle" value='<%=news.getNewsTitle()%>' size="80">
  </p>
  <p>
    <input name="isurl" type="checkbox" value="y" <%if("y".equals(news.getIsurl())) out.print("checked");%>>是否引用其他地址  
  </p>
  <p>内容：
    <textarea name="newscontent" cols="80" rows="10"><%=news.getNewsContent()%></textarea>
  </p>
  <input type="hidden" name="newstypeid" value='<%=news.getNewsTypeId()%>'>
  <input type="hidden" name="newsid" value='<%=news.getNewsid()%>'>
  <input type="hidden" name="method" value='<%=news.getMethod()%>'>
  <input type="submit" name="Submit" value="提交">
</form>

</body>
</html>