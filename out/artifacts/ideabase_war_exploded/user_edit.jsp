<%@ page language="java" pageEncoding="GB2312" %>
<%@ page import="cn.edu.zucc.news.model.*"%>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head><title>�û�����</title></head>
<body bgcolor="#FFFFFF">
<%@ include file="_banner.jsp"%>
<%
    WebUser user=(WebUser) request.getAttribute("obj");
    if(user==null) return;     
    
%>

<form action="user" method="post">  
  <input type="hidden" name="method" value='<%=user.getMethod()%>'>
  ��¼���� <input type="text" name="userid" value='<%=user.getUserid()%>' 
             <%if("modifyuserresult".equals(user.getMethod())) out.print("readonly=true");%> >
  <p>�� �룺<input type="password" name="pwd" value='<%=user.getPwd()%>'></p>
  <p>�� ����<input type="text" name="username" value='<%=user.getUsername()%>'></p>
  <p>�� ��    
    <select name="usertype" size="1">
      <option value="admin" <%if("admin".equals(user.getUsertype())) out.print("selected");%>>����Ա</option>
      <option value="editor" <%if("editor".equals(user.getUsertype())) out.print("selected");%>>�༭</option>
    </select>    
  </p>
  <input type="submit" name="Submit" value="�ύ">
</form>
</body>
</html>