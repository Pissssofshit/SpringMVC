<%@ page language="java" pageEncoding="GB2312" %>
<%@ page import="java.util.*,cn.edu.zucc.news.model.*"%>
<html>
<head><title>�û�����</title></head>
<body bgcolor="#FFFFFF">
<%@ include file="_banner.jsp"%>
<div align="right">�û�����   |    <a href="news?method=listNewsType">����������</a></div>
<hr>
<a href="user?method=adduser">����û�</a><br>
<table width="100%" border="1" cellpadding="1">
  <tr>
    <td width="20%">�û���¼��</td>
    <td width="20%">�û�����</td>
    <td width="20%">�û����</td>
    <td width="20%">�޸�</td>
    <td width="20%">ɾ��</td>
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
    <td><a href="user?method=modifyuser&userid=<%=user.getUserid()%>">�޸�</a></td>
    <td><a href="user?method=deleteuser&userid=<%=user.getUserid()%>">ɾ��</a></td>    
  </tr>
<% 
			}
    }   
%>
</table>
</body>
</html>