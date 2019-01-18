<template>

<div>
           <span>选择模板：</span> <el-select v-model="value" placeholder="请选择">

         <el-option
                    v-for="item in fileModel"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
            </el-option>

        </el-select>

        <el-upload
                class="upload-demo"
                ref="upload"
                :limit=1
                action="http://localhost/upload"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传数据</el-button>
            <div slot="tip" class="el-upload__tip">只能上传xls、xlsx文件</div>
        </el-upload>
    </div>

</template>
<script>
    let fileModel=[{label:'58同城',value:1},{label:'boss直聘',value:2},{label:'赶集网',value:3}]
    module.exports ={
        data() {
            return {
                fileModel:fileModel,
                value: '',
                fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}]
            };
        },
        methods: {
            submitUpload() {
                this.$refs.upload.submit();
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            }
        }
    }
</script>
<style scoped>

</style>