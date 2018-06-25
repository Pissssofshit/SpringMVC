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
    <p>作者</p><input type="text" name="author"><br>
    <p>标题</p><input type="text" name="title"><br>
    <p>论文类别</p>

        <select  name="papertype">
        </select><br>

    <input type="file" name="file">
    <a href="javascript:savePic();" class="btn green"> 提交 </a>
    <%--<a href="/dwnpaper/888" >下载</a>--%>
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</body>
<script type="text/javascript">
    function savePic(){
        var title=$("input[name='title']").val();
        var authorlist=$("input[name='author']").val();
        var papertype=$("select[name='papertype'").val();
        var formData = new FormData($( "#uploadPic" )[0]);
        formData.append("title",title);
        formData.append("authorlist",authorlist);
        formData.append("papertype",papertype);
        // formData.append("testusername","sfa");
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
                alert("上传成功");
                window.location.href="/mainpage";
            },
            error: function(data) {
                console.log("error:"+data);

            }
        });
    }
</script>
<script>
    $(document).ready(function(){
        $.ajax({
            url : "${pageContext.request.contextPath }/getpapertype",
            type : "get",
            // data表示发送的数据
            // data :JSON.stringify({username:username,password:password}),
            // 定义发送请求的数据格式为JSON字符串
            contentType : "application/json;charset=UTF-8",
            //定义回调响应的数据格式为JSON字符串,该属性可以省略
            dataType : "json",
            //成功响应的结果
            success : function(data){
                if(data != null){
                    // alert("您输入的用户名为："+data.username+
                    //     "密码为："+data.password);
                    // ttt();
                    // window.location.href＝"/index.jsp";
                    console.log("下标下标！");
                    console.log(data);
                    // console.log(data[0].paperid);
                    for(var i=0;i<data.length;i++){
                        var $option=$("<option></option>");
                        $option.text(data[i]['type']);
                        $option.attr("value",data[i]['type']);
                        $("select").append($option);
                    }
                }
            },
            error: function(data) {
                // alert(data);
                console.log(data);
            }
        });
    });
</script>
</html>
