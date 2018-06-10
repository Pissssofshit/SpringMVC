<%@ page language="java" pageEncoding="GB2312" %>
<%@ page import="cn.edu.zucc.news.model.*"%>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head><title>用户管理</title></head>
<body bgcolor="#FFFFFF">
<%@ include file="_banner.jsp"%>
<%
    WebUser user=(WebUser) request.getAttribute("obj");
    if(user==null) return;     
    
%>

<form action="user" method="post">  
  <input type="hidden" name="method" value='<%=user.getMethod()%>'>
  登录名： <input type="text" name="userid" value='<%=user.getUserid()%>' 
             <%if("modifyuserresult".equals(user.getMethod())) out.print("readonly=true");%> >
  <p>密 码：<input type="password" name="pwd" value='<%=user.getPwd()%>'></p>
  <p>姓 名：<input type="text" name="username" value='<%=user.getUsername()%>'></p>
  <p>类 别：    
    <select name="usertype" size="1">
      <option value="admin" <%if("admin".equals(user.getUsertype())) out.print("selected");%>>管理员</option>
      <option value="editor" <%if("editor".equals(user.getUsertype())) out.print("selected");%>>编辑</option>
    </select>    
  </p>
  <input type="submit" name="Submit" value="提交">
</form>
</body>
</html>