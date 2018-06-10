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
				(<a href="news?method=addNews&newstypeid=<%=newstype.getNewsTypeId()%>">添加新闻</a>)<br>
				<table width="100%" border="1" cellpadding="1">
				  <tr>
				    <td width="20%" nowrap="nowrap">新闻标题</td>
				    <td width="10%" nowrap="nowrap">编辑人</td>
				    <td width="15%" nowrap="nowrap">编辑时间</td>
				    <td width="20%" nowrap="nowrap">引用地址</td>
				    <td width="5%" nowrap="nowrap">阅读次数</td>
				    <td width="15%" nowrap="nowrap">最近阅读时间</td>        
				    <td width="7%" nowrap="nowrap">修改</td>
				    <td width="7%" nowrap="nowrap">删除</td>addNews&newstypeid
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
				    <td><a href="news?method=modifyNews&newsid=<%=news.getNewsid()%>">修改</a></td>
				    <td><a href="news?method=deleteNews&newsid=<%=news.getNewsid()%>">删除</a></td>
    
			  	  </tr>
<% 
		 }
		 out.println("</table>");
	}
%>




</body>
</html>