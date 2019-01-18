<template>
    <div id="msgtip">
        <el-button :plain="true" @click="open">打开消息提示</el-button>
        <el-button :plain="true" @click="openVn">VNode</el-button>
        <el-button :plain="true" @click="passMsg">调试发送</el-button>
    </div>
</template>

<script>

    module.exports = {
        data:function() {
            return {
                // stompClient: '',
                // timer: '',
            }
        },
        methods: {
          open() {
            this.$message('这是一条消息提示');
          },

          openVn() {
            const h = this.$createElement;
            this.$message({
              message: h('p', null, [
                h('span', null, '内容可以是 '),
                h('i', {style: 'color: teal'}, 'VNode')
              ])
            });
          },



          // 传递消息。target: 消息json数组。将消息存入缓存，并提示接收者
          passMsg(target){
            var _this = this;
            // 模拟
            target = [
              {
                "id": 1,
                "msgFrom": 1,
                "msgTo": 1,
                "title": "标题标题1",
                "text": "内容内容1",
                "status": 0,
                "timeStap": "2019-01-16"
              },
              {
                "id": 2,
                "msgFrom": 1,
                "msgTo": 2,
                "title": "标题标题2",
                "text": "内容内容2",
                "status": 0,
                "timeStap": "2019-01-16"
              },
              {
                "id": 3,
                "msgFrom": 1,
                "msgTo": 3,
                "title": "标题标题3",
                "text": "内容内容3",
                "status": 0,
                "timeStap": "2019-01-16"
              },
              {
                "id": 4,
                "msgFrom": 1,
                "msgTo": 1,
                "title": "标题标题4",
                "text": "内容内容4",
                "status": 0,
                "timeStap": "2019-01-16"
              },
            ]
            console.log(target);
            if(target.length > 0){
              // 将消息传给后端接口
              axios({
                headers:{
                  'Content-Type': 'application/json;charset=UTF-8'
                },
                method: 'post',
                url: 'http://localhost:80/passMsg/',
                params: target,
                transformRequest: [
                    function(){
                        return JSON.stringify(target)
                    }
                ]
                // + item.id + '/' + item.status

              })  // 返回成功/失败
                .then(function (response) {
                alert('发送' + response.data)

                  if(response.data === true){
                    // 提示接收者
                    // 这里应该从session中获取当前userid
                    var userid = 1;
                    var msgcount = 0;
                    // 统计当前接收者接收的消息数量。> 0 就提示接收的消息条数。
                    for(var i = 0; i < target.length; i++){
                      if(target[i].msgTo === userid){
                        msgcount++;
                      }
                    }
                    if(msgcount > 0){
                      _this.$message('您有'+msgcount+'条新消息');
                    }
                  }

              }).catch(function (error) {
                console.log(error)
              })


              for(var i = 0; i < target.length; i++){

              }
            }
          }

        },



        mounted() {
        },
        beforeDestroy: function () {
        }
    }
</script>
