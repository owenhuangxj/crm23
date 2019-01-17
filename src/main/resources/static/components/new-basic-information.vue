<template>
    <div>
        <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>
        <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
            <div id="information">
                <div style="color: #66b1ff">| 基础信息</div>
                <hr/>
                <el-form ref="form" :model="basicInfo" label-width="250px" >
                    <div class="formCss">
                        <el-form-item label="姓名：">
                            <el-input v-model="basicInfo.stuName" :disabled="isEdit"></el-input>
                        </el-form-item>
                        <el-form-item label="性别:">
                            <el-select v-model="basicInfo.stuSex" :disabled="isEdit">
                                <el-option label="男" value="男"></el-option>
                                <el-option label="女" value="女"></el-option>
                            </el-select>
                        </el-form-item>
                    </div>
                    <div class="formCss">
                        <el-form-item label="重要客户：">
                            <el-select v-model="basicInfo.stuImportance" :disabled="isEdit">
                                <el-option label="重要" value="重要"></el-option>
                                <el-option label="不重要" value="不重要"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="手机号:">
                            <el-input v-model="basicInfo.stuPhoneNum" :disabled="isEdit"></el-input>
                        </el-form-item>
                        <el-form-item label="QQ:">
                            <el-input v-model="basicInfo.stuQq" :disabled="isEdit"></el-input>
                        </el-form-item>
                    </div>
                    <div class="formCss">
                        <el-form-item label="优先级：">
                            <el-select v-model="basicInfo.stuLevel"  :disabled="isEdit">
                                <el-option label="无" value="无"></el-option>
                                <el-option label="中" value="中"></el-option>
                                <el-option label="高" value="高"></el-option>
                                <el-option label="低" value="低"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="渠道:">
                            <el-input v-model="basicInfo.stuChannel" :disabled="isEdit"></el-input>
                        </el-form-item>
                        <el-form-item label="来源途径:">
                            <el-input v-model="basicInfo.stuSource" :disabled="isEdit"></el-input>
                        </el-form-item>
                    </div>
                    <div class="formCss">
                        <el-form-item label="暂住地：">
                            <el-input v-model="basicInfo.stuAddress" :disabled="isEdit"></el-input>
                        </el-form-item>
                    </div>
                </el-form>
                <div></div>
                <div style="color: #3a8ee6" >| 详细信息
                    <i :class="calContro ? spread:pack" @click="unwind"></i>
                </div>
                <hr/>
                <div v-if="!calContro">
                    <el-form :label-position="labelPosition" ref="form" :model="basicInfo" label-width="250px">
                        <div class="detailCss">
                            <el-form-item class="detailInputForm" label="自我评价：">
                                <el-input type="textarea" class="inputSize" v-model="basicInfo.stuEvaluation" :disabled="isEdit" autosize></el-input>
                            </el-form-item>
                            <el-form-item class="detailInputForm" label="工作经历:">
                                <el-input type="textarea" class="inputSize" v-model="basicInfo.stuWork" :disabled="isEdit" autosize></el-input>
                            </el-form-item>
                        </div>
                        <div class="detailCss">
                            <el-form-item class="detailInputForm" label="项目经验：">
                                <el-input type="textarea" class="inputSize" v-model="basicInfo.stuProject" :disabled="isEdit" autosize></el-input>
                            </el-form-item>
                            <el-form-item class="detailInputForm" label="教育经历:">
                                <el-input type="textarea" class="inputSize" v-model="basicInfo.stuEducation" :disabled="isEdit" autosize></el-input>
                            </el-form-item>
                        </div>
                        <div class="detailCss">
                            <el-form-item class="detailInputForm" label="培训经历：">
                                <el-input type="textarea" class="inputSize" v-model="basicInfo.stuTrained" :disabled="isEdit" autosize></el-input>
                            </el-form-item>
                            <el-form-item class="detailInputForm" label="证书:">
                                <el-input type="textarea" class="inputSize" v-model="basicInfo.stuCredentials" :disabled="isEdit" autosize></el-input>
                            </el-form-item>
                        </div>
                        <div class="detailCss">
                            <el-form-item class="detailInputForm" label="在校情况：">
                                <el-input type="textarea" class="inputSize" v-model="basicInfo.stuPerformance" :disabled="isEdit" autosize></el-input>
                            </el-form-item>
                        </div>
                    </el-form>
                </div>
                <div style="text-align: center">
                    <el-button type="primary" @click="present" plain>提交</el-button>
                    <el-button type="warning" @click="abolish" plain>取消</el-button>
                </div>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    module.exports = {
        data(){
            return {
                dialogFormVisible: false,
                labelPosition: 'top',
                calContro:true,
                spread:'el-icon-arrow-right',
                pack:'el-icon-arrow-down',
                isEdit:false,
                basicInfo: {
                    stuName:"",
                    stuSex:"",
                    stuImportance:"",
                    stuPhoneNum:"",
                    stuQq:"",
                    stuLevel:"",
                    stuChannel:"",
                    stuSource:"",
                    stuAddress:"",
                    stuEvaluation:"",
                    stuWork:"",
                    stuProject:"",
                    stuEducation:"",
                    stuTrained:"",
                    stuCredentials:"",
                    stuPerformance:"",
                }
            }
        },
        methods : {
            unwind : function(){
                this.calContro = !this.calContro
            },
            present:function () {
                // alert("submit")
                let stuInfo = JSON.stringify(this.basicInfo);
                console.log("shuju："+stuInfo);
                axios.post("http://192.168.10.181/stu/addStu",JSON.stringify(this.basicInfo),{
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                }).then(function () {
                    alert("成功")
                }).catch(function () {
                    alert("链接失败")
                })
            },
            abolish:function () {
                alert("cancel")
            }
        }
    }
</script>

<style scoped>
    .formCss,.detailCss{
        display: flex;
        flex-wrap: wrap;
    }
    el-form-item{
        width: 33%;
        height: 3rem;
    }
    .detailInputForm{
        width: 45%;
        height: 5rem;
    }
    .inputSize{
        width: 90%;
    }
</style>