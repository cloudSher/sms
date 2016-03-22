<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/18
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<script src="/js/jquery-2.1.1.js"></script>
<script>
    $(function(){
        $(".bt").click(function(){
            var data = {
                "message":
                        '{"mobiles":"'+$(".mobiles").val()+'","content":"'+$(".content").val()+'","type":"1","scope":"1"}'};
            console.info(data);
            $.ajax({
                type:'post',
                data:data,
                contentType:'application/json',
                url:'/services/mps/push',
                dataType:'json',
                success: function(data){
                    alert(data.msg);
                }
            })
        })
    })


</script>
<body>
          短信测试页面！！O-O
        <div>
            <form name="form" method="post">
                <p><span>手机号：</span><input type="text" name="mobiles" class="mobiles"></p>
                <p><span>消息体：</span><textarea name="content" class="content"></textarea></p>
                <p><span></span><input type="button" name="bt" class="bt" value="提交"></p>
            </form>
        </div>

</body>
</html>
