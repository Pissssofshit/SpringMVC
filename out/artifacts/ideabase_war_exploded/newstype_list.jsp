<%@ page language="java" pageEncoding="GB2312" %>
<%@ page import="java.util.*,cn.edu.zucc.news.model.*"%>
<html>
<head>
<title></title>
</head>
<body bgcolor="#FFFFFF">
<%@ include file="_banner.jsp"%>
<div align="right"><a href="user">�û�����</a>    |    ����������</div>
<hr>
<a href="news?method=addNewsType">������</a><br>
<table width="100%" border="1" cellpadding="1">
  <tr>
    <td width="20%">���</td><td width="40%">�����������</td>
    <td width="20%">�޸�</td><td width="20%">ɾ��</td>
  </tr>
<%
	List objlist=(List) request.getAttribute("objlist");
	if(objlist!=null){
         for(int i=0;i<objlist.size();i++){
         	NewsType news=(NewsType) objlist.get(i);
%>  
  <tr>
    <td><%=news.getListOrd()%></td>
    <td><%=news.getNewsTypeName()%></td>
    <td><a href="news?method=modifyNewsType&newstypeid=<%=news.getNewsTypeId()%>">�޸�</a></td>
    <td><a href="news?method=deleteNewsType&newstypeid=<%=news.getNewsTypeId()%>">ɾ��</a></td>    
  </tr>
<% 
			}
      }
%>
</table>

</body>
</html>