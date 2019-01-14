<template>
    <div >
        <link rel="stylesheet" href="https://unpkg.com/element-ui@2.4.11/lib/theme-chalk/index.css"/>
        <div style="color: #66b1ff">| 基础信息</div>
        <hr/>
        <div id="information">
            <el-form ref="form" :model="basicInfo" label-width="250px" >
                <div class="formCss">
                    <el-form-item class="basicInfoM" label="姓名：">
                        <el-input v-model="basicInfo.name" :disabled="isEdit"></el-input>
                    </el-form-item>
                    <el-form-item class="basicInfoM" label="学员编号:">
                        <el-input v-model="basicInfo.serialNum" :disabled="isEdit"></el-input>
                    </el-form-item>
                    <el-form-item class="basicInfoM" label="性别:">
                        <el-input v-model="basicInfo.gender" :disabled="isEdit"></el-input>
                    </el-form-item>
                </div>
                <div class="formCss">
                    <el-form-item class="basicInfoM" label="重要客户：">
                        <el-input v-model="basicInfo.isImportant" :disabled="isEdit"></el-input>
                    </el-form-item>
                    <el-form-item class="basicInfoM" label="手机号:">
                        <el-input v-model="basicInfo.phoneNum" :disabled="isEdit"></el-input>
                    </el-form-item>
                    <el-form-item class="basicInfoM" label="QQ:">
                        <el-input v-model="basicInfo.QQNum" :disabled="isEdit"></el-input>
                    </el-form-item>
                </div>
                <div class="formCss">
                    <el-form-item class="basicInfoM" label="优先级：">
                        <el-input v-model="basicInfo.priority" :disabled="isEdit"></el-input>
                    </el-form-item>
                    <el-form-item class="basicInfoM" label="渠道:">
                        <el-input v-model="basicInfo.channel" :disabled="isEdit"></el-input>
                    </el-form-item>
                    <el-form-item class="basicInfoM" label="来源途径:">
                        <el-input v-model="basicInfo.approach" :disabled="isEdit"></el-input>
                    </el-form-item>
                </div>
                <div class="formCss">
                    <el-form-item class="basicInfoM" label="暂住地：">
                        <el-input v-model="basicInfo.outFlown" :disabled="isEdit"></el-input>
                    </el-form-item>
                </div>
            </el-form>
        </div>
        <div style="height: 2rem"></div>
        <div style="color: #3a8ee6" >| 详细信息
            <i :class="calContro ? spread:pack" @click="unwind"></i>
        </div>
        <hr/>
        <div id="detailInfo" v-if="!calContro">
            <el-form :label-position="labelPosition" ref="form" :model="basicInfo" label-width="250px">
                <div class="detailCss">
                    <el-form-item class="detailInputForm" label="自我评价：">
                        <el-input type="textarea" class="inputSize" v-model="basicInfo.selfEvaluation" :disabled="isEdit" autosize></el-input>
                    </el-form-item>
                    <el-form-item class="detailInputForm" label="工作经历:">
                        <el-input type="textarea" class="inputSize" v-model="basicInfo.workExperience" :disabled="isEdit" autosize></el-input>
                    </el-form-item>
                </div>
                <div class="detailCss">
                    <el-form-item class="detailInputForm" label="项目经验：">
                        <el-input type="textarea" class="inputSize" v-model="basicInfo.projectExperience" :disabled="isEdit" autosize></el-input>
                    </el-form-item>
                    <el-form-item class="detailInputForm" label="教育经历:">
                        <el-input type="textarea" class="inputSize" v-model="basicInfo.educationExperience" :disabled="isEdit" autosize></el-input>
                    </el-form-item>
                </div>
                <div class="detailCss">
                    <el-form-item class="detailInputForm" label="培训经历：">
                        <el-input type="textarea" class="inputSize" v-model="basicInfo.trainingRecord" :disabled="isEdit" autosize></el-input>
                    </el-form-item>
                    <el-form-item class="detailInputForm" label="证书:">
                        <el-input type="textarea" class="inputSize" v-model="basicInfo.credential" :disabled="isEdit" autosize></el-input>
                    </el-form-item>
                </div>
                <div class="detailCss">
                    <el-form-item class="detailInputForm" label="在校情况：">
                        <el-input type="textarea" class="inputSize" v-model="basicInfo.schoolPerformance" :disabled="isEdit" autosize></el-input>
                    </el-form-item>
                </div>
            </el-form>
        </div>
        <div style="text-align: center">
            <el-button type="info" @click="edit" plain>编辑</el-button>
            <el-button v-if="!isEdit" type="primary" @click="saveInfo" plain>保存</el-button>
        </div>
    </div>
</template>

<script>
    let info = {
        name:"张三",
        serialNum:"123",
        gender:"男",
        isImportant:"是",
        phoneNum:"123456789",
        QQNum:"123456",
        priority:"高",
        channel:"ee",
        approach:"ff",
        outFlown:"aa",
        selfEvaluation:"dasd",
        workExperience:"fgvasd",
        projectExperience:"hfh",
        educationExperience:"jh",
        trainingRecord:"lul",
        credential:"etw",
        schoolPerformance:"mnf",
    }
    module.exports = {
        data(){
            return {
                labelPosition: 'top',
                calContro:true,
                spread:'el-icon-arrow-right',
                pack:'el-icon-arrow-down',
                isEdit:true,
                basicInfo: info
            }
        },
        methods : {
            unwind : function(){
                this.calContro = !this.calContro
            },
            edit:function () {
                this.isEdit = false
            },
            saveInfo:function () {
                this.isEdit = true
                alert("保存操作")
            }
        }
    }
</script>

<style scoped>
    .formCss,.detailCss{
        display: flex;
        flex-wrap: wrap;
    }
    #information > el-form-item{
        width: 33%;
        height: 3rem;
    }
    .detailInputForm{
        width: 45%;
        height: 150px;
    }
    .inputSize{
        width: 90%;
    }
</style>