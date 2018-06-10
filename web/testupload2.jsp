<%--
  Created by IntelliJ IDEA.
  User: hxs
  Date: 18-6-10
  Time: 上午8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div  >
    <input type="file" name="FileUpload" id="FileUpload">
    <a class="layui-btn layui-btn-mini" id="btn_uploadimg">上传</a>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</body>
<script >

       $(function () {
           $("#btn_uploadimg").click(function () {
               console.log("click");
               var fileObj = document.getElementById("FileUpload").files[0]; // js 获取文件对象
               if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
                   alert("请选择图片");
                   return;
               }
               var formFile = new FormData();
               formFile.append("action", "UploadVMKImagePath");
               formFile.append("file", fileObj); //加入文件对象


               var data = formFile;
               $.ajax({
                   url: "${pageContext.request.contextPath }/uploadfile",
                   data: data,
                   type: "Post",
                   dataType: "json",
                   cache: false,//上传文件无需缓存
                   processData: false,//用于对data参数进行序列化处理 这里必须false
                   contentType: false, //必须
                   success: function (result) {
                       alert("上传完成!");
                   },
                   error: function (data) {
                       console.log(data);
                   }
               })
           })
       })

   </script>
</html>
