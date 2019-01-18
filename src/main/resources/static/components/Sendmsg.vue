<template>
    <div id="sendmsg">
        <input ref="myid" type="text" placeholder="你的用户编号">
        <button ref="myidbtn" @click="sendid">提交编号</button>
        <br>
        <input ref="targets" type="text" placeholder="发送给哪些人">
        <br>
        <input ref="msgtext" type="text" placeholder="发送内容">
        <el-button :plain="true" @click="send">发送</el-button>
        <div id="msgtipdiv" style="display: none;" @click="msgtip"></div>
    </div>
</template>

<script>

    module.exports = {
        data:function() {
            return {
            }
        },
        methods: {
            // 发送消息
            send(){
                var message = this.$refs.msgtext.value;
                var targets = this.$refs.targets.value.split(',');
                console.log("targets",targets)
                for(var ind = 0; ind < targets.length; ind++){
                    var msg = {};
                    msg.To = targets[ind]
                    msg.text = message;
                    console.log(JSON.stringify(msg))
                    websocket.send(JSON.stringify(msg));
                }

            },

            // 接收消息
            msgtip(){
                // 请求后台，将消息存入缓存

                // 提示
                this.$message('您有新的未读消息，请到消息中心查看');
            },

            // 将user保存到session中
            sendid(){
                var id = this.$refs.myid.value;
                console.log("id :",id)
                axios({
                    method:'post',
                    url: './savemyid',
                    params:{
                        id: id,
                    }
                })
            }
        },

        mounted() {
            msgV = this.$me
        },
        beforeDestroy: function () {
        }
    }

    // 域名、端口号
    var hostname = location.hostname;
    var port = location.port;

    var websocket = null;
    var msgV;

    //判断当前浏览器是否支持WebSocket  
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://"+hostname+"/websocket");
    } else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法  
    websocket.onerror = function() {
        console.log("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法  
    websocket.onopen = function() {
        console.log("WebSocket连接成功");
    }
    // //接收到消息的回调方法  
    websocket.onmessage = function(event) {
        var info=event.data.split(",");
        document.getElementById("msgtipdiv").click();
        console.log(event.data)
        console.log(info)
    }
    //连接关闭的回调方法  
    websocket.onclose = function() {
        console.log("WebSocket连接关闭");
    }
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。  
    window.onbeforeunload = function() {
        //closeWebSocket();  
    }

    //关闭WebSocket连接  
    function closeWebSocket() {
        console.log("close");
        websocket.close();

    }




</script>
