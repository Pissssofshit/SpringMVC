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
    <div>��ӭʹ�����Ĺ���ϵͳ</div>
</center>
<form action="" method="post">
    <center>
        <select  name="usertype">
            <option value ="teacher">��ʦ�û�</option>
            <option value ="admin">����Ա�û�</option>
        </select></center><br>
  <center>�û���<input type="text" name="username"></input></center><br>
  <center>���룺<input type="password" name="password"></input></center><br>
  <center><input type="submit" value="��½" onclick="javascript:this.form.action='/login2';"></input></center><br>
    <center><input type="submit" value="ע��" onclick="javascript:this.form.action='/register';"></center>
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