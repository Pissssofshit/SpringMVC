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
<form id="uploadPic" action="#" enctype="multipart/form-data">
    <input type="file" name="file">
    <a href="javascript:savePic();" class="btn green"> 提交 </a>
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</body>
<script type="text/javascript">
    function savePic(){
        var formData = new FormData($( "#uploadPic" )[0]);
        formData.append("testusername","sfa");
        var ajaxUrl = "${pageContext.request.contextPath }/uploadpaper1";
        //alert(ajaxUrl);
        //$('#uploadPic').serialize() 无法序列化二进制文件，这里采用formData上传
        //需要浏览器支持：Chrome 7+、Firefox 4+、IE 10+、Opera 12+、Safari 5+。
        $.ajax({
            type: "POST",
            //dataType: "text",
            url: ajaxUrl,
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                alert(data);
            },
            error: function(data) {
                console.log("error:"+data.responseText);

            }
        });
    }
</script>
</html>
