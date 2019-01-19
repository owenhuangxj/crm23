<!--左侧导航栏-->
<template>
    <div>
        <div class="header">
            <div class="logo">后台管理系统</div>
            <div class="header-right">
                <div class="header-user-con">
                    <!-- 用户头像 -->
                    <div class="user-avator"><img src="resources/img/person.jpg"></div>
                    <!-- 用户名下拉菜单 -->
                    <el-dropdown class="user-name" trigger="click" @command="handleCommand">
                    <span class="el-dropdown-link">
                        {{username}} <i class="el-icon-caret-bottom"></i>
                    </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item divided  command="loginout">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </div>
        </div>
    <div class="sidebar">

        <div class="from" >
            <div class="signIn1">
                <div class="left" id="left" style="width: 95px ;height: 200px"></div>
            </div>
            <div class="signIn2">
                <div class="right" id="right" style="width: 95px ;height: 200px"></div>
            </div>
        </div>
        <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#324157"
            text-color="#bfcbd9" active-text-color="#20a0ff" unique-opened router>
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index" :key="item.index">
                        <template slot="title">
                            <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                        </template>
                        <template v-for="subItem in item.subs">
                            <el-submenu v-if="subItem.subs" :index="subItem.index" :key="subItem.index">
                                <template slot="title">{{ subItem.title }}</template>
                                <el-menu-item v-for="(threeItem,i) in subItem.subs" :key="i" :index="threeItem.index">
                                    {{ threeItem.title }}
                                </el-menu-item>
                            </el-submenu>
                            <el-menu-item v-else :index="subItem.index" :key="subItem.index">
                                {{ subItem.title }}
                            </el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index" :key="item.index">
                        <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
    </div>
</template>

<script>
    module.exports = {
        data() {
            return {
                name: '未登录',
                doorOf:null,
                rightDoor:null,
                chartData:'',
                targetData:'',
                collapse: false,
                items: [
                    {
                        index: '2',
                        title: '学员管理',
                        subs: [
                            {
                                index: 'xueYuan',
                                title: '学员跟踪'
                            },
                            {
                                index: 'jianLi',
                                title: '未分配简历'
                            },
                            {
                                index: 'renWu',
                                title: '定时任务'
                            },
                        ]
                    },
                    {
                        index: '3',
                        title: '系统设置',
                        subs: [
                            {
                                index: 'xinXi',
                                title: '个人信息',
                            },
                            {
                                index: 'ziDian',
                                title: '数据字典',
                            },
                            {
                                index: 'riZhi',
                                title: '操作日志',
                            },
                            {
                                index: 'muBiao',
                                title: '绩效目标设置',
                            }
                        ]
                    },
                ]
            }
        },
        //实例创建完立即调用
        created(){
            this.getData()
            this.findData()
        },
        //监控
        mounted(){
            this.inCreate();
            this.rightIcr();
        },
        //销毁之前
        beforeDestroy(){
            if(!this.doorOf){
                this.doorOf.dispose()
                this.doorOf=null
            }
            if(!this.rightDoor){
                this.rightDoor.dispose()
                this.rightDoor=null
            }

        },
        //在添加之前
        beforeCreate(){

        },
        /*计算属性将被混入到 Vue 实例中。所有 getter 和 setter 的 this 上下文自动地绑定为 Vue 实例。*/
        computed:{
            onRoutes(){
                // return this.$route.path.replace('/','');
            },
            username(){
                let username = localStorage.getItem('ms_username');
                console.log("name:"+username)
                return username ? username : this.name;
            }
        },
        /*computed是在HTML DOM加载后马上执行的，
        如赋值；而methods则必须要有一定的触发条件才能执行，如点击事件；*/
        //方法
        /*methods 将被混入到 Vue 实例中。可以直接通过 VM 实例访问这些方法，
        或者在指令表达式中使用。方法中的 this 自动绑定为 Vue 实例。*/
        methods:{
            handleCommand(command) {
                if(command == 'loginout'){
                    localStorage.removeItem('ms_username')
                    //this.$router.push('/login');
                    window.location.href="/"
                }
            },
            getData(){
                axios.get('/getCount').then((res) =>{
                    //alert(JSON.stringify(res.data))
                    this.chartData=res.data
                    this.setChart()
                })
            },
            findData(){
                axios.get('/findCount').then((res) =>{
                    //alert(JSON.stringify(res.data))
                    this.targetData=res.data
                    this.setTargetChart()
                })
            },
            inCreate(){
                this.doorOf=echarts.init(document.getElementById('left'));
                this.doorOf.setOption({
                    title: {
                        text: '上门量'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['本周', '本月']
                    },
                    toolbox: {
                        show: true,
                    },
                    calculable: true,
                    xAxis: {
                        type: 'category',
                        data: ['实际','目标']
                    },
                    yAxis: {
                        type: 'value',

                        "show" : true,
                        "axisLine":{       //y轴
                            "show":false

                        },
                        "axisTick":{       //y轴刻度线
                            "show":false
                        },
                        "splitLine": {     //网格线
                            "show": false
                        }
                    },
                    series: [
                        {
                            name: '实际',
                            type: 'bar',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top'
                                }
                            },
                            data: []
                        },
                        {
                            name: '目标',
                            type: 'bar',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top'
                                }
                            },
                            data: []
                        },
                    ]

                })
            },
            setChart(){
                // alert("我是setchart"+JSON.stringify(this.chartData))
                this.doorOf.setOption({
                    xAxis: {
                        data: this.chartData.category
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '实际',
                        data: this.chartData.AlreadData

                    }, {
                        // 根据名字对应到相应的系列
                        name: '目标',
                        data: this.chartData.NoData
                    }]
                });
            },
            rightIcr(){
                // alert("我是increate")
                this.rightDoor=echarts.init(document.getElementById('right'));
                this.rightDoor.setOption({
                    title: {
                        text: '关单量'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['本周', '本月']
                    },
                    toolbox: {
                        show: true,
                    },
                    calculable: true,
                    xAxis: {
                        type: 'category',
                        data: ['实际','目标']
                    },
                    yAxis: {
                        type: 'value',

                        "show" : true,
                        "axisLine":{       //y轴
                            "show":false

                        },
                        "axisTick":{       //y轴刻度线
                            "show":false
                        },
                        "splitLine": {     //网格线
                            "show": false
                        }
                    },
                    series: [
                        {
                            name: '实际',
                            type: 'bar',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top'
                                }
                            },
                            data: []
                        },
                        {
                            name: '目标',
                            type: 'bar',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top'
                                }
                            },
                            data: []
                        },

                    ]

                })
            },
            setTargetChart(){
                // alert("我是setchart"+JSON.stringify(this.targetData))
                this.rightDoor.setOption({
                    xAxis: {
                        data: this.targetData.category
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '实际',
                        data: this.targetData.AlreadData

                    }, {
                        // 根据名字对应到相应的系列
                        name: '目标',
                        data: this.targetData.NoData
                    }]
                });
            },
        },

    };
</script>

<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        left: 0;
        top: 0px;
        bottom:0;
        overflow-y: scroll;
    }
    .sidebar::-webkit-scrollbar{
        width: 0;
    }
    .sidebar-el-menu:not(.el-menu--collapse){
        width: 200px;
    }
    .sidebar > ul {
        height:100%;
    }

    .from{
    //左右布局
    display: flex;
        border: 1px solid #D6EAF6;
        width: 200px;
        height: 200px;
    }
    .signIn1{
        float: left;
        margin-right: 5px;
    }
    .signIn2{
        float: right;
    }
    .header {
        position: relative;
        box-sizing: border-box;
        width: 100%;
        height: 70px;
        font-size: 22px;
        color: #fff;
    }

    .collapse-btn{
        float: left;
        padding: 0 21px;
        cursor: pointer;
        line-height: 70px;
    }
    .header .logo{
        float: left;
        width:250px;
        line-height: 70px;
    }
    .header-right{
        float: right;
        padding-right: 50px;
    }
    .header-user-con{
        display: flex;
        height: 70px;
        align-items: center;
    }
    .btn-fullscreen{
        transform: rotate(45deg);
        margin-right: 5px;
        font-size: 24px;
    }
    .btn-bell, .btn-fullscreen{
        position: relative;
        width: 30px;
        height: 30px;
        text-align: center;
        border-radius: 15px;
        cursor: pointer;
    }
    .btn-bell-badge{
        position: absolute;
        right: 0;
        top: -2px;
        width: 8px;
        height: 8px;
        border-radius: 4px;
        background: #f56c6c;
        color: #fff;
    }
    .btn-bell .el-icon-bell{
        color: #fff;
    }
    .user-name{
        margin-left: 10px;
    }
    .user-avator{
        margin-left: 20px;
    }
    .user-avator img{
        display: block;
        width:40px;
        height:40px;
        border-radius: 50%;
    }
    .el-dropdown-link{
        color: red;
        cursor: pointer;
    }
    .el-dropdown-menu__item{
        text-align: center;
    }
</style>
