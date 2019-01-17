<template>
    <div id="record">
        <div style="color: #66b1ff">| 跟踪记录</div>
        <hr/>
        <el-table :data="tableData" style="width: 100%">
            <el-table-column fixed label="操作" width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text">查看</el-button>
                </template>
            </el-table-column>
            <el-table-column prop="trackTime" label="跟踪时间" width="200"></el-table-column>
            <el-table-column prop="priority" label="优先级" width="100"></el-table-column>
            <el-table-column prop="trackMethod" label="跟踪方式" width="100"></el-table-column>
            <el-table-column prop="currentStatus" label="当前状态" width="120"></el-table-column>
            <el-table-column prop="trackDuration" label="跟踪时长" width="120"></el-table-column>
            <el-table-column prop="nextTrackTime" label="下次跟踪时间" width="200"></el-table-column>
            <el-table-column prop="trailsman" label="跟踪者" width="120"></el-table-column>
            <el-table-column prop="technicalInterviewer" label="技术面试老师" width="120"></el-table-column>
            <el-table-column prop="trackDesc" label="备注" width="350"></el-table-column>
        </el-table>
        <div style="text-align: center">
            <el-button type="primary" @click="dialogFormVisible = true" plain>新增记录</el-button>
            <!--<el-button type="primary" @click="xun">渲染</el-button>-->
        </div>
        <el-dialog title="新增跟踪记录" :visible.sync="dialogFormVisible" center>
            <el-form :model="form">
                <div class="newTrackCss">
                    <el-form-item label="跟踪方式">
                        <el-select v-model="form.trackMethod" >
                            <el-option label="QQ" value="QQ"></el-option>
                            <el-option label="电话" value="电话"></el-option>
                            <el-option label="微信" value="微信"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="跟踪时间">
                        <el-date-picker v-model="form.trackTime" type="datetime" ></el-date-picker>
                    </el-form-item>
                </div>
                <div class="newTrackCss">
                    <el-form-item label="跟踪状态">
                        <el-select v-model="form.currentStatus" >
                            <el-option label="新增" value="新增"></el-option>
                            <el-option label="跟踪中" value="跟踪中"></el-option>
                            <el-option label="待面试" value="待面试"></el-option>
                            <el-option label="面试未通过" value="面试未通过"></el-option>
                            <el-option label="面试通过" value="面试通过"></el-option>
                            <el-option label="已缴未清" value="已缴未清"></el-option>
                            <el-option label="已缴费" value="已缴费"></el-option>
                            <el-option label="入学" value="入学"></el-option>
                            <el-option label="放弃入学" value="放弃入学"></el-option>
                            <el-option label="退学" value="退学"></el-option>
                            <el-option label="放弃" value="放弃"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="下次跟踪时间">
                        <el-date-picker v-model="form.nextTrackTime" type="datetime" ></el-date-picker>
                    </el-form-item>
                </div>
                <div class="newTrackCss">
                    <el-form-item label="跟踪时长">
                        <el-input v-model="form.trackDuration" placeholder="分钟"></el-input>
                    </el-form-item>
                    <el-form-item label="优先级别">
                        <el-select v-model="form.priority" >
                            <el-option label="无" value="无"></el-option>
                            <el-option label="中" value="中"></el-option>
                            <el-option label="高" value="高"></el-option>
                            <el-option label="低" value="低"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div class="newTrackCss">
                    <el-form-item label="预计上门">
                        <el-date-picker v-model="form.predictTime" type="datetime" ></el-date-picker>
                    </el-form-item>
                    <el-form-item label="预计成交">
                        <el-date-picker v-model="form.predictTrade" type="datetime" ></el-date-picker>
                    </el-form-item>
                </div>
                <el-form-item label="跟踪内容">
                    <el-input type="textarea" v-model="form.trackDesc"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="newCancel">取 消</el-button>
                <el-button type="primary" @click="onSubmit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    module.exports ={
        data(){
            return {
                tableData:[{
                    trackTime: '2012-05-36 12:65:87',
                    priority: '低',
                    trackMethod: '不要脸',
                    currentStatus: '糊弄中',
                    trackDuration: '40小时',
                    nextTrackTime:"2012-05-36 12:65:87",
                    trailsman:"阿四",
                    technicalInterviewer:"阿三",
                    trackDesc:"神魔都没有",
                }, ],
                dialogFormVisible: false,
                form:{
                    trackMethod:"",
                    trackTime:"",
                    currentStatus:"",
                    nextTrackTime:"",
                    trackDuration:"",
                    priority:"",
                    predictTime:"",
                    predictTrade:"",
                    trackDesc:"",
                    trailsman: "1",
                    stuNumber:"20191121870",
                }
            }
        },
        mounted(){
            axios.get("http://localhost/track/getTrack?stuNumber=20191121870")
                .then(response => (this.tableData = response.data))
        },
        methods: {
            handleClick(row) {
                console.log(row.stuNumber);
            },
            onSubmit() {
                let trackInfo = this.form;
                console.log(JSON.stringify(trackInfo));
                axios.post("/track/addTrack",trackInfo,{
                    header: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(function () {
                    alert("成功")
                }).catch(function () {
                    alert("链接失败")
                })
                this.dialogFormVisible = false;
            },
            newCancel(){
                alert("取消")
                this.dialogFormVisible = false;
            },
        },
    }
</script>

<style scoped>
    .newTrackCss{
        display: flex;
        flex-wrap: wrap;
    }
    el-form-item{
        width: 50%;
        height: 3rem;
    }
</style>