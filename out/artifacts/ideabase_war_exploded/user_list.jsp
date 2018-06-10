<%@ page language="java" pageEncoding="GB2312" %>
<%@ page import="java.util.*,cn.edu.zucc.news.model.*"%>
<html>
<head><title>用户管理</title></head>
<body bgcolor="#FFFFFF">
<%@ include file="_banner.jsp"%>
<div align="right">用户管理   |    <a href="news?method=listNewsType">新闻类别管理</a></div>
<hr>
<a href="user?method=adduser">添加用户</a><br>
<table width="100%" border="1" cellpadding="1">
  <tr>
    <td width="20%">用户登录号</td>
    <td width="20%">用户姓名</td>
    <td width="20%">用户类别</td>
    <td width="20%">修改</td>
    <td width="20%">删除</td>
  </tr>
<%
	List objlist=(List) request.getAttribute("objlist");
	
	if(objlist!=null){
         for(int i=0;i<objlist.size();i++){
         	WebUser user=(WebUser) objlist.get(i);
%>  
  <tr>
    <td><%=user.getUserid()%></td>
    <td><%=user.getUsername()%></td>
    <td><%=user.getUsertype()%></td>
    <td><a href="user?method=modifyuser&userid=<%=user.getUserid()%>">修改</a></td>
    <td><a href="user?method=deleteuser&userid=<%=user.getUserid()%>">删除</a></td>    
  </tr>
<% 
			}
    }   
%>
</table>
</body>
</html>