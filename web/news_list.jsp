<%@ page language="java" pageEncoding="GB2312" %>
<%@ page import="java.util.*,cn.edu.zucc.news.model.*"%>
<html>
<head>
<title>Lomboz JSP</title>
</head>
<body bgcolor="#FFFFFF">
<%@ include file="_banner.jsp"%>

<%
   List objlist=(List) request.getAttribute("objlist");
	
	if(objlist==null) return;
    for(int i=0;i<objlist.size();i++){
         NewsType newstype=(NewsType) objlist.get(i);
         out.print(newstype.getNewsTypeName());
%>
				(<a href="news?method=addNews&newstypeid=<%=newstype.getNewsTypeId()%>">�������</a>)<br>
				<table width="100%" border="1" cellpadding="1">
				  <tr>
				    <td width="20%" nowrap="nowrap">���ű���</td>
				    <td width="10%" nowrap="nowrap">�༭��</td>
				    <td width="15%" nowrap="nowrap">�༭ʱ��</td>
				    <td width="20%" nowrap="nowrap">���õ�ַ</td>
				    <td width="5%" nowrap="nowrap">�Ķ�����</td>
				    <td width="15%" nowrap="nowrap">����Ķ�ʱ��</td>        
				    <td width="7%" nowrap="nowrap">�޸�</td>
				    <td width="7%" nowrap="nowrap">ɾ��</td>addNews&newstypeid
				  </tr>
	<%
	     List newslist=newstype.getNewsList();        
    	 for(int j=0;j<newslist.size();j++){
    	    News news=(News) newslist.get(j);
	%>  
				  <tr>
				    <td><%=news.getNewsTitle()%></td>
				    <td><%=news.getNewsAutor()%></td>
				    <td><%=news.getCreateDate()%></td>
				    <td><%if(news.getIsurl().equals("y")) out.print(news.getNewsContent());%></td>
				    <td><%=news.getReadcount()%></td>
				    <td><%=news.getRecentReadDate()%></td>
				    <td><a href="news?method=modifyNews&newsid=<%=news.getNewsid()%>">�޸�</a></td>
				    <td><a href="news?method=deleteNews&newsid=<%=news.getNewsid()%>">ɾ��</a></td>
    
			  	  </tr>
<% 
		 }
		 out.println("</table>");
	}
%>




</body>
</html>