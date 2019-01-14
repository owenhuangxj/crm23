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
            <el-table-column prop="remark" label="备注" width="350"></el-table-column>
        </el-table>
        <div style="text-align: center">
            <el-button type="primary" @click="dialogFormVisible = true" plain>新增记录</el-button>
        </div>
        <el-dialog title="新增跟踪记录" :visible.sync="dialogFormVisible" center>
            <el-form :model="form">
                <div class="newTrackCss">
                    <el-form-item label="跟踪方式">
                        <el-select v-model="form.newTrackMethod" >
                            <el-option label="QQ" value="qq"></el-option>
                            <el-option label="电话" value="phone"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="跟踪时间">
                        <el-date-picker v-model="form.newTrackTime" type="datetime" ></el-date-picker>
                    </el-form-item>
                </div>
                <div class="newTrackCss">
                    <el-form-item label="跟踪状态">
                        <el-select v-model="form.trackStatus" >
                            <el-option label="新增" value="shanghai"></el-option>
                            <el-option label="面试" value="beijing"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="下次跟踪时间">
                        <el-date-picker v-model="form.newNextTrackTime" type="datetime" ></el-date-picker>
                    </el-form-item>
                </div>
                <div class="newTrackCss">
                    <el-form-item label="跟踪时长">
                        <el-input v-model="form.newTrackDuration" placeholder="分钟"></el-input>
                    </el-form-item>
                    <el-form-item label="优先级别">
                        <el-select v-model="form.newPriority" >
                            <el-option label="高" value="high"></el-option>
                            <el-option label="低" value="low"></el-option>
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
                    remark:"神魔都没有",
                }, ],
                dialogFormVisible: false,
                form:{
                    newTrackMethod:"",
                    newTrackTime:"",
                    trackStatus:"",
                    newNextTrackTime:"",
                    newTrackDuration:"",
                    newPriority:"",
                    predictTime:"",
                    predictTrade:"",
                    trackDesc:"",
                }
            }
        },
        methods: {
            handleClick(row) {
                console.log(row.remark);
            },
            onSubmit() {
                console.log(this.form.trackDesc)
                this.dialogFormVisible = false;
            },
            newCancel(){
                alert("取消")
                this.dialogFormVisible = false;
            }
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