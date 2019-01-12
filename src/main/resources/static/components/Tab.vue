<template>
    <div id="tab">
        <!--上部标题标签-->
        <div id="msgTab" ref="msgTab">
            <a href="javascript:;"
               @click="Tab(index)"
               v-for="(item,index) in items"
               :class="{active : index===curId}"
            >{{item.item}} <span class="count">(0)</span></a>
        </div>
        <!--横线-->
        <div id="tabScroll">
            <!--滑动条-->
            <div ref="smallScroll" class="smallScroll"></div>
        </div>
        <!--中间消息列表-->
        <div class="tab-con">
            <div v-for="(content, index) in contents">
                <div class="msgItem" v-for="item in content">
                    <div class="msgTitle">{{item.title}}</div>
                    <div class="itemUtil">
                        <a href="javascript:;" class="itemBtn" v-show="index===curId"
                           v-for="(btntext,index) in itemBtns">{{itemBtns[index].btntext}}</a>
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
                    {item: '未读消息'},
                    {item: '已读消息'},
                    {item: '回收站'},
                ],
                // 下方工具按钮
                utilBtns: [
                    {btntext: '全部标为已读', color: '#409eff', func: ''},
                    {btntext: '删除全部', color: '#f56c6c'},
                    {btntext: '清空回收站', color: '#f56c6c'}
                ],
                // 每条消息后的工具按钮
                itemBtns: [
                    {btntext: '标为已读', color: '#fff', func: ''},
                    {btntext: '删除', color: '#f56c6c', func: ''},
                    {btntext: '还原', color: '#f56c6c', func: ''}
                ]
            }
        },
        methods: {
            Tab(index) {

                this.curId = index;
                document.getElementsByClassName("utilBtn")[index].style.background = this.utilBtns[index].color;
                //等vue将active类赋值完成后再执行
                setTimeout(tabScrollMove, 10, this);
                setTimeout(initMsgList, 11, this, index);
                console.log('执行Tab')
                console.log(this.contents);
            },


        },
        beforeCreate: function(){
            // 中间表内容
            this.contents = [];
            // axios中this是window对象。所以要将现在的this赋值给中间变量_this
            // var _this = this;
            initMsgList(this,0);

        },
        created: function () {

        },
        mounted: function(){
            // 10毫秒后加载消息列表
            setTimeout(loadMsgList, 10, this);
        }

    }

    function tabScrollMove(obj) {
        obj.$refs.smallScroll.style.left = document.getElementsByClassName("active")[0].offsetLeft + 'px';
    }
    // 初始化消息列表数据
    function initMsgList(obj, state){
        console.log('请求接口')
        axios.get('./msgData',{
            params: {
                status: state,
            }
        })
            .then(function (response) {
                obj.contents = [];
                console.log(obj.contents);
                response.data.forEach(function(v){
                    obj.contents.push({content: v});
                })
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
    // 发送请求获取消息并拼接dom
    function getMsgData(obj,status) {
        alert(status);
        axios.get('./msgData.json')
            .then(function (response) {
                response.data.forEach(function(v){
                    obj.contents.push({content: v});
                })
            })
            .catch(function (error) {
                console.log(error);
            });
    }


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
        padding-right: 40px;
        height: 70px;
        line-height: 70px;
        font-size: 14px;
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
        width: 75px;
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