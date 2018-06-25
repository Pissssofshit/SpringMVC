<%@ page language="java" pageEncoding="GB2312" %>

<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>Lomboz JSP</title>
</head>
<style>
    *{
        margin: 0 auto;
    }
</style>
<body bgcolor="#FFFFFF">
<%--<%@ include file="_banner.jsp"%>--%>
<center>
    <div>欢迎使用论文管理系统</div>
</center>
<form action="" method="post">
    <center>
        <select  name="usertype">
            <option value ="teacher">教师用户</option>
            <option value ="admin">管理员用户</option>
        </select></center><br>
  <center>用户：<input type="text" name="username"></input></center><br>
  <center>密码：<input type="password" name="password"></input></center><br>
  <center><input type="submit" value="登陆" onclick="javascript:this.form.action='/login2';"></input></center><br>
    <center><input type="submit" value="注册" onclick="javascript:this.form.action='/register';"></center>
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