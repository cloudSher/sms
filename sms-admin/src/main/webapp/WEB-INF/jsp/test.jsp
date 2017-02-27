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
            var data = {"mobiles":$(".mobiles").val(),"content":$(".content").val(),"type":"1","scope":$("#scope").val()};
            console.info(data);
            if($("#scope").val() == 1){
                if(confirm("你确定要提交验证码信息吗？")){
                    $.ajax({
                        type:'get',
                        data: data,
                        async: false,
                        url:'http://localhost:8231/services/mps/test',
                        dataType:'jsonp',
                        jsonp:'jsoncallback',
                        jsonpCallback:"callback",
                        success: function(data){
                        }
                    })
                }
            }else{
                $.ajax({
                    type:'get',
                    data: data,
                    async: false,
                    url:'http://localhost:8231/services/mps/test',
                    dataType:'jsonp',
                    jsonp:'jsoncallback',
                    jsonpCallback:"callback",
                    success: function(data){
                    }
                })
            }

        })

        $("#pt").click(function(){
            var num = $("#num").val();
            if(parseInt(num) > 0){
                var phones = "";
                for(var k = 0; k < num; k++){
                    var pn = "1";
                    var ss = [3,5,8];
                    Math.floor(Math.random()*10)
                    pn += ss[ Math.floor(Math.random()*3)];
                    for(var i = 2 ; i< 11; i++){
                        pn += Math.floor(Math.random()*10);
                    }
                    phones += pn;
                    if(k < num -1){
                        phones += ",";
                    }

                }
                $(".phone").html(phones);
            }

        })

        callback = function(result){
            var s = "<div style='width:800px; word-wrap:break-word; word-break:break-all; overflow: hidden;'>"+"请求消息状态："+result.msg+"  "
                    +"请求的手机号："+result.result.body.sender.parameters.mobiles+"  "
                    +"请求的内容:"+result.result.body.content+"</p>";

            $(".result >p").append("<hr/>"+s);
            var id = result.result.msgId;
            p_timer = setTimeout("getData('"+id+"')",2000);
        }

        var time = 0;
        getData = function(msgId){
            $.ajax({
                type:'post',
                data:{"msgId":msgId},
                url:'/test/sms/data',
                dataType:'json',
                success: function(data){
                    if(data!=null){
                        if(data.length > 0){
                            for(var i in data){
                                var s = "<div style='width:800px; word-wrap:break-word; word-break:break-all; overflow: hidden;margin-top: 10px;'>"
                                        +"响应消息状态："+data[i].description+"  "
                                        +"<br/>响应的手机号："+data[i].mobiles+"  "
                                        +"<br/>手机号个数："+mobiles_num(data[i].mobiles)
                                        +"<br/>响应的渠道商："+data[i].channelsname
                                        +"<br/>运行商："+data[i].mobilesOperator
                                        +"</div>";
                                $(".result > p").append(s);
                                time++;
                            }
                        }
                    }

                }
            })
        }


        mobiles_num = function(mobiles){
            var num = "";
            if(mobiles){
               num = mobiles.split(",");
            }
            return num.length;
        }


    })


</script>
<body>
          短信测试页面！！O-O
        <div>
            <form name="form" method="post">
                <p><span>消息类型：</span><select id="scope"><option value="2">推送消息</option><option value="1">验证码</option></select></p>
                <p><span>手机号：</span><input type="text" name="mobiles" class="mobiles" size="20"></p>
                <p><span>消息体：</span><textarea name="content" class="content"></textarea></p>
                <p><span></span><input type="button" name="bt" class="bt" value="提交"></p>
                <hr/>
                <p><span>手机号码个数:</span><input type="text" id="num" size="2"/></p>
                <p><span>手机号码:</span><input type="button" id="pt" value="生成手机号码"/></p>
                <div class="phone" style="width:800px; word-wrap:break-word; word-break:break-all; overflow: hidden;">
                </div>
            </form>

            <div class="result">
                <p></p>
            </div>

        </div>

</body>
</html>
