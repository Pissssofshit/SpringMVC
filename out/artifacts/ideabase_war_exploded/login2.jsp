<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>测试JSON交互</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<script type="text/javascript"--%>
    <%--src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js">--%>
    <%--</script>--%>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        function testJson(){
            // 获取输入的用户名和密码
            var username = $("#username").val();
            var password = $("#password").val();
            var usertype = $("#usertype").val();
            var formData = new FormData();

            formData.append("username",username.toString());
            formData.append("password",password.toString());
            formData.append("usertype",usertype.toString());
            // $(function(){ alert("JQuery已引用成功!"); })
            $.ajax({
                url : "${pageContext.request.contextPath }/login2",
                type : "POST",
                // data表示发送的数据
                data :formData,
                // 定义发送请求的数据格式为JSON字符串
                processData: false,
                contentType: false,
                // contentType : "application/json;charset=UTF-8",
                //定义回调响应的数据格式为JSON字符串,该属性可以省略
                dataType : "json",
                //成功响应的结果
                success : function(data){
                    if(data != null){
                        // alert("您输入的用户名为："+data.username+
                        //     "密码为："+data.password);
                        // ttt();
                        // window.location.href＝"/index.jsp";
                        console.log("success");
                        console.log(data.responseText);
                        $("html").innerHTML=data.responseText;
                    }
                },
                error: function(data) {
                    // alert(data);
                    console.log("error")
                    console.log(data);
                }
            });
        }
        function ttt() {
            console.log("ttt");
            window.location.href="/index.jsp";
        }
    </script>
</head>
<body>
<form>
    <center>
        <select  id="usertype">
            <option value ="teacher">教师用户</option>
            <option value ="admin">管理员用户</option>
        </select></center><br>
    <center>用户：<input type="text" id="username"></input></center><br>
    <center>密码：<input type="password" id="password"></input></center><br>
    <center><input type="button" value="登陆" onclick="testJson()"></input></center><br>
</form>
</body>
</html>