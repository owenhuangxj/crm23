<template>

    <div id="resumeAllot">
        <el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>

        <el-dialog
                title="简历分配"
                :visible.sync="dialogVisible"
                width="80%"
                :before-close="handleClose">

            <div id="dialog-main">
                <div id="msgTab" ref="msgTab">
                    <a  @click="tabSwitch(index)"
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
                    <el-select v-model="employeeSelect" multiple placeholder="请选择">
                        <el-option
                                v-for="item in employees"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>


                    <div>

                        <div class="block">
                            <span >分配时间：</span>
                            <el-radio v-model="radio" label="1" @change="hideTimePicker()">立刻发送</el-radio>
                            <el-radio v-model="radio" label="2" @change="showTimePicker()">选择时间</el-radio>
                            <br/>
                            <div id="timePicker">
                                <el-date-picker
                                        size=="mini"
                                        v-model="dateDefault"
                                        type="datetime"
                                        placeholder="选择任务时间"
                                        align="right"
                                        :picker-options="timePicker">
                                </el-date-picker>

                            </div>

                        </div>

                    </div>

                    <div >
                        <span>分配数量</span>
                        <el-input-number v-model="num" :step="3" :min="1" :max="100" label="描述文字"></el-input-number>
                    </div>

                </div>



            </div>

    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
        </el-dialog>

        </div>


</template>

<script>
    let employees=[{value:1,label:"赵老铁"},{value:2,label:"赵老铁"},{value:3,label:"赵老铁"}]

    module.exports = {
        data: function () {
            return {
                dialogVisible: false,
                /*时间选框*/
                timePicker: {
                    shortcuts: [{
                        text: '今天下午',
                        onClick(picker) {
                            const date = new Date();
                            date.setHours(14);
                            date.setMinutes(0);
                            date.setSeconds(0);
                            if(date>new Date()){
                                picker.$emit('pick',date);
                            }else{
                                picker.$emit('pick',new Date());
                            }

                        }
                    }, {
                        text: '明天早上',
                        onClick(picker) {
                            const date = new Date();
                            date.setHours(9);
                            date.setDate(date.getDate()+1);
                            date.setMinutes(0);
                            date.setSeconds(0);
                            date.setTime(date.getTime());
                            picker.$emit('pick', date);
                        }
                    }]
                },
                radio:'allotTime',
                dateDefault: '',
                curId: 0,
                // 标签名
                title:"简历分配",
                //数字框每次增量
                val:5,
                //初始量
                num:5,
                tabs: [
                    {name: '固定分配'},
                    {name: '随机分配'},

                ],
                employees: employees,
                employeeSelect: [],

            }
        },
        methods: {
        //选项卡变化
            tabSwitch(index) {
                this.curId = index;
                //等vue将active类赋值完成后再执行
                setTimeout(tabScrollMove, 10, this);
                console.log('执行Tab');
                console.log(this.contents);
            },
            showTimePicker(){
                document.getElementById("timePicker").style.display="block";
            },
            hideTimePicker(){
                document.getElementById("timePicker").style.display="none";
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {});
            }

        },
        beforeCreate: function(){


        },



    }

    function tabScrollMove(obj) {
        obj.$refs.smallScroll.style.left = document.getElementsByClassName("active")[0].offsetLeft + 'px';
    }



</script>

<style scoped>

#dialog-main{
    padding-left: 2em;
    padding-right: 2em;
}
    #resumeAllot {
        padding: 30px;
        background: #fff;
        border: 1px solid #ddd;
        border-radius: 5px;
        height: 500px;
    }

    #msgTab {
        height: 50px;

    }
    #timePicker{
        display: none;
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

/*分割线*/
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



</style>