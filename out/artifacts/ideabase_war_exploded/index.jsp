<%@ page language="java" pageEncoding="GB2312" %>
<%@ page import="java.util.*,cn.edu.zucc.news.model.*"%>
<html>
	<head><title>Duke's News Center</title></head>
	<body>
	<%--<%@ include file="_banner.jsp"%>--%>

	
    <table width="80%" border="0" cellpadding="1" align="center">
<%
   List objlist=(List) request.getAttribute("objlist");
	
	if(objlist==null) return;
    for(int i=0;i<objlist.size();i++){
         NewsType newstype=(NewsType) objlist.get(i);
         
%>
	<%
	     out.print("<tr><td><h2>"+newstype.getNewsTypeName()+"<h2></td></tr>");
	     List newslist=newstype.getNewsList();        
    	 for(int j=0;j<newslist.size();j++){
    	    News news=(News) newslist.get(j);
    	   
	%>
				  <tr>
				    <td><a target="_black" href="news?method=read&newsid=<%=news.getNewsid()%>"> 
                          <%=news.getNewsTitle()%>
                        </a></td>
			  	  </tr>
	<% 
				}				
			}
        out.println("</table>");
	%>
	</body>
</html>
