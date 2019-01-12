<template>
    <div id="tab">
        <!--上部标题标签-->
        <div id="msgTab" ref="msgTab">
            <a href="javascript:;"
               @click="tabSwitch(index)"
               v-for="(item,index) in tabs"
               :class="{active : index===curId}"
            >{{item.name}}</a>
        </div>
        <!--横线-->
        <div id="tabScroll" class="split" >
            <!--滑动条-->
            <div ref="smallScroll" class="smallScroll"></div>
        </div>
        <!--中间消息列表-->
        <div class="tab-con">
            <span>人员：</span>

                <select id="empSelect">
                    <option>请选择</option>
                    <option v-for="(employee,i) in employees" value={{employee.id}} >{{employee.name}}</option>
                </select>
        <div>



        </div>
            <span>分配数量</span>
            <div id="number-box">
                <button class="number-button-left" v-on:click="numberBoxMinus()">-</button>
                <input id="number-val" type="text"  v-model="number" placeholder="请输入分配数量">
                <button class="number-button-right" v-on:click="numberBoxAdd()">+</button>
            </div>

        </div>
        <div class="split"/>


        <!--按钮-->
        <a class="allot-button" v-for="k in buttons"> {{k.text}}</a>
    </div>
</template>

<script>
    var employees = [
        {name: "胡 杰", id: 1},
        {name: "赵老铁", id: 1},
        {name: "郑  健", id: 1}
    ];
    module.exports = {
        data: function () {
            return {
                curId: 0,
                // 标签名
                title:"简历分配",
                employees:employees,
                //数字框每次增量

                val:5,
                //初始量
                number:5,
                tabs: [
                    {name: '固定分配'},
                    {name: '随机分配'},

                ],
                // 下方工具按钮

                buttons: [
                    {text: '确  定', color: '#3CB371'},
                    {text: '应  用', color: '#40E0D0'},
                    {text: '取  消', color: '#f56c6c'}
                ],

            }
        },
        methods: {
            numberBoxAdd: function () {
                this.number += this.val;
            },
            numberBoxMinus: function () {
                this.number-=this.val;

            },
            tabSwitch(index) {

                this.curId = index;

               let a=0;
                for (var button of this.buttons) {
                    document.getElementsByClassName("allot-button")[a++].style.background = button.color;
                }

                //等vue将active类赋值完成后再执行
                setTimeout(tabScrollMove, 10, this);
                setTimeout(initMsgList, 11, this);
                console.log('执行Tab');
                console.log(this.contents);
            },


        },
        /*数字变化*/

        beforeCreate: function(){
            // 中间表内容
            this.contents = [];
            // axios中this是window对象。所以要将现在的this赋值给中间变量_this
            // var _this = this;
            initMsgList(this);

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
    function initMsgList(obj){
        console.log('请求接口')
        axios.get('./msgData.json')
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
        obj.tabSwitch(1);
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


    .split{
        margin-top: 1rem;
        margin-bottom: 1rem;
        background-color: #f0f0f0;
        height: 0.2rem;
    }

    .smallScroll {
        /*左右位置过渡*/
        transition: left .4s;
        position: absolute;
        width: 4rem;
        height: 0.2rem;
        background-color: #409eff;
    }
#empSelect{
    width: 10em;
    height: 3em;
    font-size: 1em;
    padding-top: 0.1rem;
    padding-bottom: 0.1rem;

}
/*人物选择框框*/
#empSelect option{
    text-align: center;
    padding: auto;
}
    #msgTab a.active {
        color: #409eff;
    }

    .tab-con {
        margin-top: 20px;
    }

    .allot-button {
        display: inline-block;
        color: #fff;
        padding: 0.8rem 2rem;
        margin-top: 3rem;
        background: #409eff;
        font-size: 1rem;
        border-radius: 0.5rem;
        margin-left: 1rem ;
    }

    .msgItem>div{
        float: right;
    }
    /*数字变化框*/

    #number-box{
        display: -webkit-box;
        -webkit-box-orient: horizontal;
        margin:0 auto
    }
    #number-box input{
        text-align: center;
        height: 100%;
        border: none;
        width: 5rem;
    }

    #number-box button{
        background-color: rgba(128,128,128,0.8);
        color: white;
        height: 100%;
        width: 2em;
        text-align: center;
        font-weight: bolder;
    }
    .number-button-left{
        border-radius: 0.5em 0 0 0.5em ;
    }
    .number-button-right{
        border-radius: 0  0.5em 0.5em 0;
    }



</style>