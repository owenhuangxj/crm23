<template>
    <div id="tab">
        <!--上部标题标签-->
        <div id="msgTab" ref="msgTab">
            <a href="javascript:;"
               @click="Tab(index)"
               v-for="(item,index) in items"
               :class="{active : index===curId}"
            >{{item.item}}</a>
            <!--<span class="count">({{item.msgCount}})</span>-->
        </div>
        <!--横线-->
        <div id="tabScroll">
            <!--滑动条-->
            <div ref="smallScroll" class="smallScroll"></div>
        </div>
        <!--中间消息列表-->
        <div class="tab-con">
            <div v-for="(content, index) in contents">
                <div class="msgItem"  v-for="item in content" v-show="item.status===curId">
                    <div class="msgTitle">{{item.title}}</div>
                    <div class="itemUtil">
                        <a href="javascript:;" class="itemBtn" v-for="(itemBtn,index) in itemBtns" v-show="index===curId"
                           @click="changeStatus(item,itemBtn.func)">{{itemBtn.btntext}}</a>
                    </div>
                    <div class="msgTime">{{item.time}}</div>

                </div>
            </div>
        </div>
        <!--下部工具按钮-->
        <a href="javascript:;" v-show="index===curId"
           v-for="(btntext, index) in utilBtns"
           class="utilBtn"
        >{{utilBtns[index].btntext}}</a>
    </div>
</template>

<script>

    // axios.get('./msgData.json')
    //     .then(function (response) {
    //         console.log(response);
    //     })
    //     .catch(function (error) {
    //         console.log(error);
    //     });

    module.exports = {
        data: function () {
            return {
                curId: 0,
                // 标签名
                items: [
                    {item: '未读消息', msgCount:0},
                    {item: '已读消息',msgCount:0},
                    {item: '回收站',msgCount:0},
                ],

                // // 中间消息列表
                // contents: [
                //     [],
                //     [],
                //     [],
                // ],

                // 下方工具按钮
                utilBtns: [
                    {btntext: '全部标为已读', color: '#409eff'},
                    {btntext: '删除全部', color: '#f56c6c'},
                    {btntext: '清空回收站', color: '#f56c6c'}
                ],

                // 每条消息后的工具按钮
                itemBtns: [
                    {btntext: '标为已读', color: '#fff', func:"read"},
                    {btntext: '删除', color: '#f56c6c', func:"delete"},
                    {btntext: '还原', color: '#f56c6c', func:"reduct"}
                ],

            }
        },
        methods: {
            Tab(index) {

                this.curId = index;
                document.getElementsByClassName("utilBtn")[index].style.background = this.utilBtns[index].color;
                //等vue将active类赋值完成后再执行
                setTimeout(tabScrollMove, 10, this);
                // setTimeout(initMsgList, 11, this, index);
                initMsgList(this, index);
            },
            changeStatus(item, func){
                console.log(item);
                console.log(item.id);
                console.log(func);
                axios({
                    method:'post',
                    url:'./changeMsgStatus/'+item.id+'/'+item.status,

                }).then(function(response){
                        alert("修改结果"+response.data)
                    }).catch(function (error) {
                        console.log(error);
                    });
            }


        },
        beforeCreate: function(){
            // 中间表内容
            this.contents = [[],[],[]];
            // axios中this是window对象。所以要将现在的this赋值给中间变量_this
            // var _this = this;
            console.log('before')
            initMsgList(this,0);

        },
        created: function () {
            // 50毫秒后加载消息列表
            // setTimeout(loadMsgList, 50, this);
            // loadMsgList(obj)
        },
        mounted: function(){

        }

    }

    function tabScrollMove(obj) {
        obj.$refs.smallScroll.style.left = document.getElementsByClassName("active")[0].offsetLeft + 'px';
    }
    // 初始化消息列表数据
    function initMsgList(obj, state){
        console.log('请求接口')
        console.log("init中的obj")
        console.log(obj)
        axios.get('./msgData',{
            params: {
                status: state,
            }
        })
            .then(function (response) {
                // 在重新获取消息数据之前清空对应选项卡的消息列表，和消息统计数量
                obj.contents[state] = [];
                console.log(obj.contents);
                response.data.forEach(function(v){
                    // 对应状态的消息统计加一
                    obj.items[v.status].msgCount++;
                    // 将消息填入消息列表
                    obj.contents[state].push(v);
                });
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    // 刷新后加载消息列表
    function loadMsgList(obj){
        obj.Tab(1);
        obj.$refs.msgTab.firstElementChild.click();
    }
    // // 发送请求获取消息并拼接dom
    // function getMsgData(obj,status) {
    //     alert(status);
    //     axios.get('./msgData.json')
    //         .then(function (response) {
    //             response.data.forEach(function(v){
    //                 obj.contents.push({content: v});
    //             })
    //         })
    //         .catch(function (error) {
    //             console.log(error);
    //         });
    // }


</script>

<style scoped>

    #tab {
        padding: 30px;
        background: #fff;
        border: 1px solid #ddd;
        border-radius: 5px;
        height: 500px;
    }

    #msgTab {
        height: 50px;

    }

    a{
        color: #000;
        text-decoration: none;
    }

    #msgTab > a {
        float: left;
        /*padding-right: 40px;*/
        height: 70px;
        line-height: 70px;
        font-size: 14px;
        text-align: left;
        display: inline-block;
        width: 100px;
    }

    #msgTab > a:hover {
        color: #409eff;
    }

    #tabScroll {
        /*position: relative;*/
        /*padding:0 30px;*/
        background-color: #f0f0f0;
        height: 2px;
    }

    .smallScroll {
        /*左右位置过渡*/
        transition: left .3s;
        position: absolute;
        width: 60px;
        height: 2px;
        background-color: #409eff;
    }

    #msgTab a.active {
        color: #409eff;
    }

    .tab-con {
        margin-top: 20px;
    }

    .utilBtn {
        display: inline-block;
        color: #fff;
        padding: 9px 15px;
        margin-top: 30px;
        background: #409eff;
        font-size: 12px;
        border-radius: 5px;
    }
    .msgItem{
        box-sizing: border-box;
        border-bottom: 1px solid #ccc;
        height: 50px;
    }
    .msgItem>div{
        float: right;
    }
    .msgItem .msgTitle{
        float: left;
        color: #409eff;
        font-size: 12px;
        height: 50px;
        line-height: 50px;
    }
    .msgItem .itemUtil{
        border: 1px solid #ddd;
        font-size: 12px;
        margin-top: 7px;
        margin-left: 60px;
        border-radius: 5px;
    }
    .itemBtn{
        display: inline-block;
        color: #fff;
        text-decoration: none;
        padding: 9px 15px;
        background: #409eff;
        font-size: 12px;
        border-radius: 5px;
    }
    .msgTime{
        font-size: 12px;
        height: 50px;
        line-height: 50px;
    }

</style>