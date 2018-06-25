<%--
  Created by IntelliJ IDEA.
  User: hxs
  Date: 18-6-9
  Time: 上午10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    td{
        border: 1px solid white;
    }
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

    var user = '<%=session.getAttribute("userid")%>';
    $(document).ready(function(){
        $.ajax({
            url : "${pageContext.request.contextPath }/showpage/"+user,
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
                    console.log(data);
                    putintotable(data);
                    // console.log(data[0].paperid);
                }
            },
            error: function(data) {
                // alert(data);
                console.log(data);
            }
        });
    });
    function  putintotable(data) {
        var table=$("table");
        for(var i=0;i<data.length;i++) {
            var tr=$("<tr></tr>");
            for(var j in data[i]){
                var td=$("<td>"+data[i][j]+"</td>");
                tr.append(td);
            }
            var dowurl='/dwnpaper/'+data[i]['paperid'];
            var td=$("<td></td>");
            var a=$("<a></a>");
            a.attr("href",dowurl);
            a.text("下载");
            td.append(a);
            tr.append(td);
            // var editurl='';
            // td.empty();
            // a.innerText="删除";
            // a.attr("href",editurl);
            // td.append(a);
            // tr.append(td);
            table.append(tr);

        }
    }
    function testJson(){
        // 获取输入的用户名和密码
        var username = $("#username").val();
        var password = $("#password").val();
        console.log("ssffs"+username);
        // $(function(){ alert("JQuery已引用成功!"); })
        $.ajax({
            url : "${pageContext.request.contextPath }/testJson",
            type : "post",
            // data表示发送的数据
            data :JSON.stringify({username:username,password:password}),
            // 定义发送请求的数据格式为JSON字符串
            contentType : "application/json;charset=UTF-8",
            //定义回调响应的数据格式为JSON字符串,该属性可以省略
            dataType : "json",
            //成功响应的结果
            success : function(data){
                if(data != null){
                    // alert("您输入的用户名为："+data.username+
                    //     "密码为："+data.password);
                    ttt();
                    // window.location.href＝"/index.jsp";
                }
            },
            error: function(data) {
                // alert(data);
                console.log("wtf? 会传到这里的？拦截其不应该直接跳掉了吗");
                console.log(data);
            }
        });
    }
    function ttt() {
        console.log("ttt");
        window.location.href="/index.jsp";
    }
</script>
<body>
<div>
    <%
        String welmsg=(String)session.getAttribute("username");
        if(welmsg!=null){
            session.removeAttribute("welmsg");
            out.println("你好"+welmsg);
        }
    %>
    <a href="/logout">退出登录</a>
</div>

<style>
    .test{
        display: flex;
    }
    .right{
        margin-left: 30px;
    }
    ul{
        /*color: blue;*/

        list-style: none;
        margin: 0px;
    }
    /*td{*/
        /*border: 5px solid white;*/
    /*}*/
</style>
<div class="test">

    <ul>
        <li><a href="/uploadpaper">上传论文</a></li>
        <li>查看论文状态</li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
    <div class="right">
        <table border="5">
            <tr>
                <td>论文ｉd</td>
                <td>论文作者ｉd</td>
                <td>审核状态</td>
                <td>标题</td>
                <td>编辑者id</td>
                <td>作者列表</td>
                <td>论文分类</td>
                <td>上传时间</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
