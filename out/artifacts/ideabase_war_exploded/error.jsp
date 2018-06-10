<!-- Copyright (c) 2002 by ObjectLearn. All Rights Reserved. -->

<%@page isErrorPage="true"%>
<html>
	<head>
		<title>Error Page</title>
	</head>
	<body>
		<font face="Comic Sans MS" size=4>
		<blockquote>
		<center>
		<h1><font color=red>
			<%
   String errmsg=(String)session.getAttribute("errormsg");
   if(errmsg!=null){
     session.removeAttribute("errormsg");
     out.println(errmsg);
   }
%>

       </font></h1>
		</center>
		
		</blockquote>
		</font>
	</body>
</html>
