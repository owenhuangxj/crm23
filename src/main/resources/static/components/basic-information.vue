<template>
    <div >
        <link rel="stylesheet" href="https://unpkg.com/element-ui@2.4.11/lib/theme-chalk/index.css"/>
        <div style="color: #66b1ff">| 基础信息</div>
        <hr/>
        <div id="information">
            <el-form ref="form" :model="basicInfo" label-width="250px" >
                <div class="formCss">
                    <el-form-item class="basicInfoM" label="姓名：">
                        <el-input v-model="basicInfo.stuName" :disabled="isEdit"></el-input>
                    </el-form-item>
                    <el-form-item class="basicInfoM" label="学员编号:">
                        <el-input v-model="basicInfo.stuNumber" :disabled="isEdit"></el-input>
                    </el-form-item>
                    <el-form-item class="basicInfoM" label="性别:">
                        <el-input v-model="basicInfo.stuSex" :disabled="isEdit"></el-input>
                    </el-form-item>
                </div>
                <div class="formCss">
                    <el-form-item class="basicInfoM" label="重要客户：">
                        <el-input v-model="basicInfo.stuImportance" :disabled="isEdit"></el-input>
                    </el-form-item>
                    <el-form-item class="basicInfoM" label="手机号:">
                        <el-input v-model="basicInfo.stuPhoneNum" :disabled="isEdit"></el-input>
                    </el-form-item>
                    <el-form-item class="basicInfoM" label="QQ:">
                        <el-input v-model="basicInfo.stuQq" :disabled="isEdit"></el-input>
                    </el-form-item>
                </div>
                <div class="formCss">
                    <el-form-item class="basicInfoM" label="优先级：">
                        <el-input v-model="basicInfo.stuLevel" :disabled="isEdit"></el-input>
                    </el-form-item>
                    <el-form-item class="basicInfoM" label="渠道:">
                        <el-input v-model="basicInfo.stuChannel" :disabled="isEdit"></el-input>
                    </el-form-item>
                    <el-form-item class="basicInfoM" label="来源途径:">
                        <el-input v-model="basicInfo.stuSource" :disabled="isEdit"></el-input>
                    </el-form-item>
                </div>
                <div class="formCss">
                    <el-form-item class="basicInfoM" label="暂住地：">
                        <el-input v-model="basicInfo.stuAddress" :disabled="isEdit"></el-input>
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
            <el-button type="info" @click="edit" plain>编辑</el-button>
            <el-button v-if="!isEdit" type="primary" @click="saveInfo" plain>保存</el-button>
        </div>
    </div>
</template>

<script>
    let info = {
        stuName:"张三",
        stuNumber:"123",
        stuSex:"男",
        stuImportance:"是",
        stuPhoneNum:"123456789",
        stuQq:"123456",
        stuLevel:"高",
        stuChannel:"ee",
        stuSource:"ff",
        stuAddress:"aa",
        stuEvaluation:"dasd",
        stuWork:"fgvasd",
        stuProject:"hfh",
        stuEducation:"jh",
        stuTrained:"lul",
        stuCredentials:"etw",
        stuPerformance:"mnf",
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