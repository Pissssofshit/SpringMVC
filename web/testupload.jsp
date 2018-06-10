<%--
  Created by IntelliJ IDEA.
  User: hxs
  Date: 18-6-10
  Time: 上午6:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/uploadfile" method="post" enctype="multipart/form-data">
    <h2>论文上传</h2>
    文件:<input type="file" name="uploadFile"/><br/><br/>
    <input type="submit" value="上传"/>
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</body>
<script>
    var url = "http://192.168.100.151:7001/GDPaymentCenterServer_FromService/UploadFileServlet.do";

    $('#uploadBtn').on('tap', function(){

        fd.append("paragram", 12345); //上传的参数名 参数值 k-v键值对
        fd.append("upfile", $("#upfile").get(0).files[0]);//上传的文件file
        $.ajax({
            url: url,
            type: "POST",
            processData: false,
            contentType: false,
            data: fd,
            success: function(data) {
                console.log(data);
            }
        })
    })
</script>
</html>
