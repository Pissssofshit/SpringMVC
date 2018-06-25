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
<script>

    function shengyue() {

    }
    function getcheckedPaper() {

            $.ajax({
                url : "${pageContext.request.contextPath }/getcheckedpaper",
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
                        console.log("checkpaper()！");
                        console.log(data);
                        // console.log(data[0].paperid);
                        $("div[name='maodian']").children().remove().removeClass();
                        var table=$("<table></table>");
                        var tr=$("<tr></tr>");
                        tr.append($("<td>上传者</td>"));
                        tr.append($("<td>审阅状态</td>"));
                        tr.append($("<td>标题</td>"));
                        tr.append($("<td>作者</td>"));
                        tr.append($("<td>论文分类</td>"));
                        table.append(tr);
                        for(var i=0;i<data.length;i++){
                            var tr=$("<tr></tr>")
                            tr.append($("<td>"+data[i]['uploaderid']+"</td>")); //后期来个多表查询　emmm contorller那边
                            tr.append($("<td>"+data[i]['state']+"</td>"));
                            tr.append($("<td>"+data[i]['title']+"</td>"));
                            tr.append($("<td>"+data[i]['authorlist']+"</td>"));
                            tr.append($("<td>"+data[i]['papertype']+"</td>"));
                            var dowurl='/dwnpaper/'+data[i]['paperid'];
                            var td=$("<td></td>");
                            var a=$("<a></a>");
                            a.attr("href",dowurl);
                            a.text("下载");
                            td.append(a);
                            tr.append(td);
                            // var tr1=$("<tr></tr>");
                            var td1=$("<td></td>");
                            var a1=$("<a></a>");
                            a1.attr("href","javascript:shengyue();");//无效？
                            a1.text("审阅");
                            td1.append(a1);
                            tr.append(td1);

                            // console.log("test");
                            var gg=$("<td></td>");
                            gg.html("<input type=\"button\" value=\"审核\" id=\"btntc\" " +
                                "onclick=\""+"showdia("+data[i]['paperid']+")"+"\" "+
                                "style=\"width:100px; height:30px; font-size:18px;\" />");
                            // gg.bind("click",);
                            // 使用jquery.onclick 会直接触发，为什么？
                            // 主要在Html的语句中，也不要用字符串＋函数名＋字符串的形式，这样一解析，也等于直接触发了．所以要把函数名也改成字符串的形式
                            tr.append(gg);
                            table.append(tr);
                        }
                        console.log(table);
                        $("div[name='maodian']").append(table);
                        console.log("maodian should be inserted!");
                    }
                },
                error: function(data) {
                    // alert(data);
                    console.log(data);
                }
            });

    }
    function  checkPaper() {
        $.ajax({
            url : "${pageContext.request.contextPath }/getUncheckpaper",
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
                    console.log("checkpaper()！");
                    console.log(data);
                    // console.log(data[0].paperid);
                    $("div[name='maodian']").children().remove().removeClass();
                    var table=$("<table></table>");
                    var tr=$("<tr></tr>");
                    tr.append($("<td>上传者</td>"));
                    tr.append($("<td>审阅状态</td>"));
                    tr.append($("<td>标题</td>"));
                    tr.append($("<td>作者</td>"));
                    tr.append($("<td>论文分类</td>"));
                    table.append(tr);
                    for(var i=0;i<data.length;i++){
                        var tr=$("<tr></tr>")
                        tr.append($("<td>"+data[i]['uploaderid']+"</td>")); //后期来个多表查询　emmm contorller那边
                        tr.append($("<td>"+data[i]['state']+"</td>"));
                        tr.append($("<td>"+data[i]['title']+"</td>"));
                        tr.append($("<td>"+data[i]['authorlist']+"</td>"));
                        tr.append($("<td>"+data[i]['papertype']+"</td>"));
                        var dowurl='/dwnpaper/'+data[i]['paperid'];
                        var td=$("<td></td>");
                        var a=$("<a></a>");
                        a.attr("href",dowurl);
                        a.text("下载");
                        td.append(a);
                        tr.append(td);
                        // var tr1=$("<tr></tr>");
                        var td1=$("<td></td>");
                        var a1=$("<a></a>");
                        a1.attr("href","javascript:shengyue();");//无效？
                        a1.text("审阅");
                        td1.append(a1);
                        tr.append(td1);

                        // console.log("test");
                        var gg=$("<td></td>");
                        gg.html("<input type=\"button\" value=\"审核\" id=\"btntc\" " +
                                "onclick=\""+"showdia("+data[i]['paperid']+")"+"\" "+
                            "style=\"width:100px; height:30px; font-size:18px;\" />");
                        // gg.bind("click",);
                        // 使用jquery.onclick 会直接触发，为什么？
                        // 主要在Html的语句中，也不要用字符串＋函数名＋字符串的形式，这样一解析，也等于直接触发了．所以要把函数名也改成字符串的形式
                        tr.append(gg);
                        table.append(tr);
                    }
                    console.log(table);
                    $("div[name='maodian']").append(table);
                    console.log("maodian should be inserted!");
                }
            },
            error: function(data) {
                // alert(data);
                console.log(data);
            }
        });
    }
</script>
<style>
    ul{
        list-style: none;
        margin: 0px;
    }
    .test{
        display: flex;
    }
    .sss{
        margin-left: 30px
        background: cadetblue;
        border:2px solid darkslategray;
        box-shadow: darkgrey 10px 10px 30px 5px ;//边框阴影
    }
</style>
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
<div class="test">
    <ul>
        <li onclick="loadpapertype()">论文类别管理</li>
        <li>论文管理
            <ul>
                <li><a onclick="checkPaper()">待审核论文</a></li>
                <li><a onclick="getcheckedPaper()">已审核论文</a></li>
                <li></li>
                <li></li>
            </ul>
        </li>
        <li onclick="loadSearch()">论文检索</li>
        <li></li>
        <li></li>
    </ul>
    <div name="maodian">

    </div>

</div>

<style>
    .contents{
        background-color: white;
        z-index: 1001;
        position: absolute;
        top: 51%;
        left: 50%;
    }

    .sssf{
        top: 0px;
        left: 0px;
        opacity: 0.5;
        width: 100%;
        height: 100%;
        background-color: grey;
        z-index: 1000;
        position: absolute;
    }
</style>
<script>
    function searchbytime() {
        console.log("searchbytime");
        var formdata=new FormData();
        var time=$("select[name='time']").val();
        var keyword=$("input[name='keyword']").val();
        formdata.append("time",time);
        formdata.append("keyword",keyword);
        $.ajax({
            url : "${pageContext.request.contextPath }/timesearch",
            type : "POST",
            // data表示发送的数据
            data :formdata,
            // 定义发送请求的数据格式为JSON字符串
            processData: false,
            contentType: false,
            // contentType : "application/json;charset=UTF-8",
            //定义回调响应的数据格式为JSON字符串,该属性可以省略
            dataType : "json", //无奈　controller那边设置不好,,,
            //成功响应的结果
            success : function(data){
                if(data != null){
                    // console.log(data);
                    var table = $("<table name=\"wunai\"></table>");//...无奈
                    var tr=$("<tr><td>论文id</td><td>上传者id</td><td>论文状态</td><td>标题</td><td>编辑者id</td><td>作者列表</td><td>论文种类</td><td>日期</td></tr>");
                    table.append(tr);
                    for(var i=0;i<data.length;i++){
                        var tr=$("<tr></tr>");
                        for(var j in data[i]){
                            var td=$("<td>"+data[i][j]+"</td>");
                            // console.log(data[i][j]);
                            // td.innerText=""+data[i][j];
                            tr.append(td);//可是这样会导致后面的变量覆盖了之前的东西
                        }
                        table.append(tr);
                    }
                    $("div[name='maodian2']").children().remove().removeClass();
                    $("div[name='maodian2']").append(table);
                }
            },
            error: function(data) {
                // alert(data);
                console.log("error")
                console.log(data);
            }
        });
    }
    function loadSearch() {
        console.log("loadsearch");
        $("div[name='maodian']").children().remove().removeClass();
        var div=$("<div></div>");//error!不是这样用的，不过反正起作用了也就不改了
        div.html("<form><input name=\"keyword\"><input type=\"button\" value=\"搜索\" onclick='searchbytime()' >\n" +
            "        <select name=\"time\">\n" +
            "            <option value=\"none\">\n" +
            "                无\n" +
            "            </option>\n" +
            "            <option value=\"today\">\n" +
            "                今日\n" +
            "            </option>\n" +
            "            <option value=\"week\">\n" +
            "                一周内\n" +
            "            </option>\n" +
            "            <option value=\"month\">\n" +
            "                一月内\n" +
            "            </option>\n" +
            "        </select></from><div name=\"maodian2\"></div>");
        $("div[name='maodian']").append(div);
    }
    function loadpapertype() {
        // console.log("logsss");
        $("div[name='maodian']").children().remove().removeClass();
        var div=$("<div></div>");//error!不是这样用的，不过反正起作用了也就不改了
        div.html("<form>\n" +
            "            <p>新增论文类型</p><input name=\"addtype\">\n" +
            "            <input type=\"button\" value=\"提交\" onclick=\"addpapertype()\">\n" +
            "        </form>\n" +
            "        <table name=\"papertype\" >\n" +
            "        <tr>\n" +
            "            <td>类型名称</td>\n" +
            "            <td>修改</td>\n" +
            "        </tr>\n" +
            "        </table>");
        $("div[name='maodian']").append(div).addClass("sss");
        $.ajax({
            url : "${pageContext.request.contextPath }/getpapertype",
            type : "get",
            contentType : "application/json;charset=UTF-8",
            //定义回调响应的数据格式为JSON字符串,该属性可以省略
            dataType : "json",
            //成功响应的结果
            success : function(data){
                if(data != null){
                    putintotable(data);
                }
            },
            error: function(data) {
                // alert(data);
                console.log(data);
            }
        });
    }
    function  putintotable(data) {
        var table = $("table[name='papertype']");
        for (var i = 0; i < data.length; i++) {
            var tr = $("<tr></tr>");
            // for(var j in data[i]){
            var td = $("<td>" + data[i]['type'] + "</td>");
            tr.append(td);
            // var td=  $("<td onclick=\"createjquery("+data+")\""+">修改类型名称</td>");
            var td=$("<td>修改类型名称</td>");
            td.attr("onclick","createjquery("+JSON.stringify(data[i])+")"); //finally...
            tr.append(td);
            console.log("putintotable");
            table.append(tr);

        }
    }
    function createjquery(data){
        console.log("createjquery");
        console.log(data);
        var tanchuang=$("<div name='tanchuang'></div>");
        tanchuang.html("<div class=\"sssf\">\n" +
            "        </div>\n" +
            "    <div class=\"contents\"><form><p>"+data['type']+"</p> <p>修改为</p>\n" +
            "        <input name=\"newtypename\">\n" +
            "        <input type=\"button\" value=\"提交\" onclick=\"submitchangetype("+data['typeid']+")\">\n" +
            // "        <input type=\"button\" onclick=\"javascript:$(\"div[name='tanchuang']\").remove()\" value=\"取消\">\n" +
            "<input type=\"button\" onclick=\"javascript:$(\"div[name='tanchuang']\").remove()\" value=\"取消\">"+ //todo 这个迷一样的格式错误？
            "    </form>\n" +
            "    </div>");
        $("body").append(tanchuang);

        $(".sssf").attr("onclick","javascript:$(\"div[name='tanchuang']\").remove()");
        // console.log(i);
    }

    function  submitchangetype(typeid) {
        console.log("typeid"+typeid);
        var newtypename = $("input[name='newtypename']").val();
        var formdata=new FormData();
        // var ss=$("input[name='addtype']").val();
        // console.log("ss"+ss);
        formdata.append("typeid",typeid);
        formdata.append("typename",newtypename);

        $.ajax({
            url : "${pageContext.request.contextPath }/changetypename",
            type : "POST",
            // data表示发送的数据
            data :formdata,
            // 定义发送请求的数据格式为JSON字符串
            processData: false,
            contentType: false,
            // contentType : "application/json;charset=UTF-8",
            //定义回调响应的数据格式为JSON字符串,该属性可以省略
            dataType : "text",
            //成功响应的结果
            success : function(data){
                if(data != null){
                    // alert("您输入的用户名为："+data.username+
                    //     "密码为："+data.password);
                    // ttt();
                    // window.location.href＝"/index.jsp";

                    $(".zhuti").remove();//闭包闭包．．．恨你
                    $("table[name='nice']").children().remove();
                    checkPaper();//更新未审阅的论文
                    console.log("审阅完成");
                }
            },
            error: function(data) {
                // alert(data);
                $("div[name='tanchuang']").remove();
                loadpapertype();
            }
        });
    }
    function addpapertype() {
        var formdata=new FormData();
        var ss=$("input[name='addtype']").val();
        console.log("ss"+ss);
        formdata.append("type",ss);

        $.ajax({
            url : "${pageContext.request.contextPath }/addpapertype",
            type : "post",
            contentType : "application/json;charset=UTF-8",
            //定义回调响应的数据格式为JSON字符串,该属性可以省略
            dataType : "json",
            data: formdata,
            processData: false,
            contentType: false,
            //成功响应的结果
            success : function(data){
                if(data != null){
                    loadpapertype();
                   //  console.log("status"+data.status);
                   // if(data.status=="sucess"){
                   //     console.log("添加成功");
                   // }else {
                   //     console.log("添加失败"+data.msg);
                   // }
                }
            },
            error: function(data) {
                // alert(data);
                loadpapertype();//我这也是无奈啊
                console.log(data);
            }
        });
    }
</script>
</body>

<style>
    .zhuti{
        position: absolute;
    }
</style>
<script>
    function submitcheckform(index ) {
        console.log('index'+index);
        var advice=$("textarea[name='advice']").val();//无效？为何？textarea tag未闭合？
        // var advice=$("#ss").val();
        var decide=$("select[name='decide']").val();
        //为何只能获取到初始值？　emm...
        console.log("advice"+advice+"decide"+decide);
        // var username = $("#username").val();
        // var password = $("#password").val();
        // var usertype = $("#usertype").val();
        var formData = new FormData();

        formData.append("advice",advice.toString());
        formData.append("decide",decide.toString());
        formData.append("paperid",index);
        <%
   HttpSession s = request.getSession();
   %>
        var editorid='<%=s.getAttribute("userid") %>';
        formData.append("editorid",editorid);
        // $(function(){ alert("JQuery已引用成功!"); })
        $.ajax({
            url : "${pageContext.request.contextPath }/checkPaper",
            type : "POST",
            // data表示发送的数据
            data :formData,
            // 定义发送请求的数据格式为JSON字符串
            processData: false,
            contentType: false,
            // contentType : "application/json;charset=UTF-8",
            //定义回调响应的数据格式为JSON字符串,该属性可以省略
            dataType : "text", //无奈　controller那边设置不好,,,
            //成功响应的结果
            success : function(data){
                if(data != null){
                    // alert("您输入的用户名为："+data.username+
                    //     "密码为："+data.password);
                    // ttt();
                    // window.location.href＝"/index.jsp";

                    $(".zhuti").remove();//闭包闭包．．．恨你
                    $("table[name='nice']").children().remove();
                    checkPaper();//更新未审阅的论文
                    console.log("审阅完成");
                }
            },
            error: function(data) {
                // alert(data);
                console.log("error")
                console.log(data);
            }
        });
    }
</script>
<script type="text/javascript">
    function showdia(index){

            var html = "<form name=\"check\">\n" +
                "            <div>审核意见</div>\n" +
                "            <textarea name=\"advice\"></textarea>\n" +
                "            <select name=\"decide\">\n" +
                "                <option value=\"pass\">pass</option>\n" +
                "                <option value=\"refuse\">refuse</option>\n" +
                "            </select>\n" +
                "            <input type=\"button\" onclick=\"submitcheckform(" +index
                +")\"" +
                " value=\"提交\">\n" +
                "        </form>";

            var button ="<input type='button' value='确定' /><input type='button' value='取消' />";

            var win = new Window({

                width : 400, //宽度
                height : 300, //高度
                title : '测试弹窗', //标题
                content : html, //内容
                isMask : false, //是否遮罩
                buttons : button, //按钮
                isDrag:true, //是否移动

            });

        }
    // $(document).ready(function(e) {

        // $("table[name='nice']").on("click","#btntc",function(){
        //
        //     var html = "<form name=\"check\">\n" +
        //         "            <div>审核意见</div>\n" +
        //         "            <textarea name=\"advice\"></textarea>\n" +
        //         "            <select name=\"decide\">\n" +
        //         "                <option value=\"pass\">pass</option>\n" +
        //         "                <option value=\"refuse\">refuse</option>\n" +
        //         "            </select>\n" +
        //         "            <input type=\"button\" onclick=\"submitcheckform()\" value=\"提交\">\n" +
        //         "        </form>";
        //     var button ="<input type='button' value='确定' /><input type='button' value='取消' />";
        //
        //     var win = new Window({
        //
        //         width : 400, //宽度
        //         height : 300, //高度
        //         title : '测试弹窗', //标题
        //         content : html, //内容
        //         isMask : false, //是否遮罩
        //         buttons : button, //按钮
        //         isDrag:true, //是否移动
        //
        //     });
        //
        // });
    // });
</script>

<script>
    var x =0;

    var idzt = new Array();

    var Window = function(config){

        //ID不重复
        idzt[x] = "zhuti"+x;  //弹窗ID

        //初始化，接收参数
        this.config = {
            width : config.width || 300, //宽度
            height : config.height || 200, //高度
            buttons : config.buttons || '', //默认无按钮
            title : config.title || '标题', //标题
            content : config.content || '内容', //内容
            isMask : config.isMask == false?false:config.isMask || true, //是否遮罩
            isDrag : config.isDrag == false?false:config.isDrag || true, //是否移动
        };

        //加载弹出窗口
        var w = ($(window).width()-this.config.width)/2;
        var h = ($(window).height()-this.config.height)/2;

        var nr = "<div class='zhuti' id='"+idzt[x]+"' bs='"+x+"' style='width:"+this.config.width+"px; height:"+this.config.height+"px; background-color:white; left:"+w+"px; top:"+30+h+"px;'></div>";
        $("body").append(nr);

        //加载弹窗标题
        var content ="<div id='title"+x+"' style='background-color: blue' class='title' bs='"+x+"'>"+this.config.title+"<div id='close"+x+"' class='close' bs='"+x+"'>×</div></div>";
        //加载弹窗内容
        var nrh = this.config.height - 75;
        content = content+"<div id='content"+x+"' style='background-color: yellow' bs='"+x+"' class='content' style='width:100%; height:"+nrh+"px;'>"+this.config.content+"</div>";
        //加载按钮
        // content = content+"<div id='btnx"+x+"' bs='"+x+"' class='btnx'>"+this.config.buttons+"</div>";

        //将标题、内容及按钮添加进窗口
        $('#'+idzt[x]).html(content);


        //创建遮罩层
        if(this.config.isMask)
        {
            var zz = "<div id='zz'></div>";
            $("body").append(zz);
            $("#zz").css('display','block');
        }

        //最大最小限制，以免移动到页面外
        var maxX = $(window).width()-this.config.width;
        var maxY = $(window).height()-this.config.height;
        var minX = 0,
            minY = 0;

        //窗口移动
        if(this.config.isDrag)
        {
            //鼠标移动弹出窗
            $(".title").bind("mousedown",function(e){

                var n = $(this).attr("bs"); //取标识

                //使选中的到最上层
                $(".zhuti").css("z-index",3);
                $('#'+idzt[n]).css("z-index",4);

                //取初始坐标
                var endX = 0, //移动后X坐标
                    endY = 0, //移动后Y坐标
                    startX = parseInt($('#'+idzt[n]).css("left")), //弹出层的初始X坐标
                    startY = parseInt($('#'+idzt[n]).css("top")), //弹出层的初始Y坐标
                    downX = e.clientX, //鼠标按下时，鼠标的X坐标
                    downY = e.clientY; //鼠标按下时，鼠标的Y坐标

                //绑定鼠标移动事件
                $("body").bind("mousemove",function(es){

                    endX = es.clientX - downX + startX; //X坐标移动
                    endY = es.clientY - downY + startY; //Y坐标移动

                    //最大最小限制
                    if(endX > maxX)
                    {
                        endX = maxX;
                    } else if(endX < 0)
                    {
                        endX = 0;
                    }
                    if(endY > maxY)
                    {
                        endY = maxY;
                    } else if(endY < 0)
                    {
                        endY = 0;
                    }

                    $('#'+idzt[n]).css("top",endY+"px");
                    $('#'+idzt[n]).css("left",endX+"px");

                    window.getSelection ? window.getSelection().removeAllRanges():document.selection.empty(); //取消选中文本

                });
            });
            //鼠标按键抬起，释放移动事件
            $("body").bind("mouseup",function(){

                $("body").unbind("mousemove");

            });
        }
        function  test() {

            var m = this.getAttribute("bs"); //找标识
            $('#'+idzt[m]).remove(); //移除弹窗
            $('#zz').remove(); //移除遮罩

        }
        //关闭窗口
        $(".close").click(test);

        x++;  //标识增加

    }
</script>
</html>
