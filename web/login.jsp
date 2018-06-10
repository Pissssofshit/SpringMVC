<%@ page language="java" pageEncoding="GB2312" %>

<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>Lomboz JSP</title>
</head>
<body bgcolor="#FFFFFF">
<%@ include file="_banner.jsp"%>
<form action="user?method=logincheck" method="post">
  <center>ำรปงฃบ<input type="text" name="userid"></input></center><br>  
  <center>รÜย๋ฃบ<input type="password" name="pwd"></input></center><br>  
  <center><input type="submit" value="ตวยฝ"></input></center><br>  
</form>
<%
   String errmsg=(String)session.getAttribute("errormsg");
   if(errmsg!=null){
     session.removeAttribute("errormsg");
     out.println(errmsg);
   }
%>
</body>
</html>