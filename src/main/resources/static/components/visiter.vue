<template>
    <form class="signIn">
        <div class="left" id="left" style="width: 88px ;height: 200px"></div>
        <div class="right" id="right" style="width: 88px ;height: 200px"></div>
    </form>
</template>

<script>
    //模板
    module.exports= {
        data() {
            return{
                doorOf:null,
                rightDoor:null
            }
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
        //实例创建完立即调用
        created(){

        },
        //方法
        methods:{
            inCreate(){
                this.doorOf=echarts.init(document.getElementById('left'));
                this.doorOf.setOption({
                    title: {
                        text: '本月上门量'
                    },
                    tooltip: {},
                    xAxis: {
                        data: ["实际","目标"]
                    },
                    yAxis: {},
                    series: [{
                        name: '销量',
                        type: 'bar',
                        data: [18,15]
                    }]
                });
            },
            rightIcr(){
                this.rightDoor=echarts.init(document.getElementById('right'));
                this.rightDoor.setOption({
                    title: {
                        text: '本月关单量'
                    },
                    tooltip: {},
                    xAxis: {
                        data: ["实际","目标"]
                    },
                    yAxis: {},
                    series: [{
                        name: '销量',
                        type: 'bar',
                        data: [18,15]
                    }]
                });
            }
        },

    };
</script>

<style scoped>
    .signIn{
        //可以左右布局
        display: flex;
        width: 200px;
        height: 200px;
    }
    .left{
        //布局向左
        float: left;
        font-size: 3px;
        //左边距
        margin-left: 2px;
    }
    .right{
        float: right;
        font-size: 3px;
        margin-right: 2px;
    }
</style>