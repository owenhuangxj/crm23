<template>
    <div id="tracktable">
        <div id="button_div">
            <span id="stuInfo">学员信息操作</span>&nbsp;
            <el-button class="el_bt" round @click="addResoume"><i class="el-icon-plus"></i>&nbsp;新增简历</el-button>
            <el-button class="el_bt" round @click="delResoume"><i class="el-icon-delete"></i>&nbsp;删除简历</el-button>
            <el-button class="el_bt" round @click="resoumeFormVisible = true"><i class="el-icon-upload"></i>&nbsp;导入简历</el-button>
            <el-button class="el_bt" round @click="recallResoume"><i class="el-icon-circle-close-outline"></i>&nbsp;撤回简历</el-button>
            <el-button class="el_bt" round v-if="this.multipleSelection.length !== 0" @click="dialogFormVisible = true"><i class="el-icon-refresh"></i>&nbsp;调换咨询电话</el-button>
            <el-button class="el_bt" round v-else-if="this.multipleSelection.length <= 0" @click="exchangePhone"><i class="el-icon-refresh"></i>&nbsp;调换咨询电话</el-button>
            <!--弹出“调换咨询电话”的对话框-->
            <el-dialog title="调换咨询电话" :visible.sync="dialogFormVisible" center :append-to-body='true' :lock-scroll="false" width="30%">
                <el-form :model="form">
                    <p>请选择将要调换的</p>
                    <el-radio-group v-model="ruleForm.resource">
                        <el-radio label="电话"></el-radio>
                        <el-radio label="咨询师"></el-radio>
                    </el-radio-group>
                    <el-form-item>
                        <el-input v-model="form.info" placeholder="请输入内容"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="doConfirm">确 定</el-button>
                </div>
            </el-dialog>

            <!--弹出“导入简历”的对话框-->
            <el-dialog title="导入简历" :visible.sync="resoumeFormVisible" center :append-to-body='true' :lock-scroll="false" width="45%">
                <span>选择导入模板</span>
                <el-select v-model="form.template">
                    <el-option label="智联招聘" value="智联招聘"></el-option>
                    <el-option label="58同城" value="58同城"></el-option>
                    <el-option label="赶集网" value="赶集网"></el-option>
                </el-select>&nbsp;&nbsp;&nbsp;&nbsp;
                <span>选择导入人员</span>
                <el-select v-model="form.name">
                    <el-option label="周梦" value="周梦"></el-option>
                    <el-option label="赵老铁" value="赵老铁"></el-option>
                </el-select>
                <br/><br/>
                <!--文件上传  ref="upload" -->
                <el-upload class="upload-demo" ref="upload" action="#" :on-preview="handlePreview" :on-remove="handleRemove"
                           :before-upload="beforeAvatarUpload" :file-list="fileList" :auto-upload="false">
                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                    <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">点击上传</el-button>
                    <div slot="tip" class="el-upload__text">只能上传文档或表格文件，且不超过10M</div>
                </el-upload>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="resoumeFormVisible = false">关 闭</el-button>
                    <!--<el-button type="primary" @click="resoumeFormVisible = false">确 定</el-button>-->
                </div>
            </el-dialog>
        </div>

        <div id="search">
            <!--下拉框-->
            <el-select style="width: 110px" v-model="value">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
            <!--输入框-->
            <el-input v-model="input" style="width: 200px" placeholder="请输入关键字"><i class="el-icon-search"></i></el-input>
            <!--搜索按键-->
            <el-button class="el_bt" type="primary" v-if="this.input !== ''" @click="doSearch">点击搜索</el-button>
            <el-button class="el_bt" type="primary" v-else-if="this.input === ''" @click="hints">点击搜索</el-button>
        </div>

        <!--表格 @row-click：行的单击事件，@cell-dblclick：行的双击事件，@select-all：全选，@clearSelection：取消全选-->
        <el-table ref="multipleTable" border :data="tableData" @row-dblclick="rowClick($event)" @selection-change="selectionChange"
                  @select-all="selectAll(tableData)" @clearSelection="clearSelect" reserve-selection>
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column type="index" label="编号" width="55"></el-table-column>
            <el-table-column prop="flag" label="状态标识" width="120"></el-table-column>
            <el-table-column prop="stuName" label="姓名" width="120"></el-table-column>
            <el-table-column prop="stuLevel" label="优先级" width="120"></el-table-column>
            <el-table-column prop="trackCount" label="跟踪次数" width="120"></el-table-column>
            <el-table-column prop="stuPhoneNum" label="手机号" width="120"></el-table-column>
            <el-table-column prop="stuSource" label="来源途径" width="120"></el-table-column>
            <el-table-column prop="stuStatus" label="进度情况" width="120"></el-table-column>
            <el-table-column prop="trackNextTime" label="下次跟踪时间" width="120"></el-table-column>
            <el-table-column prop="trackWays" label="咨询方式" width="120"></el-table-column>
            <el-table-column prop="userName" label="咨询师" width="120"></el-table-column>
            <el-table-column prop="updateTime" label="更新时间" width="120"></el-table-column>
            <el-table-column prop="trackPredictTime" label="预计上门" width="120"></el-table-column>
        </el-table>

        <!--
        :total：总共有多少条数据，把后台获取到的数据总数复制给total就可以了
        :page-size：每页显示的条数
        :page-sizes：下拉框选择每页显示多少条数据
        -->
        <div class="fenye">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                           :current-page="currentPage" :page="pageNum" :page-size="pageSize" :total="total"
                           :page-sizes="[10, 20, 30, 40]" layout="total, sizes, prev, pager, next, jumper">
            </el-pagination>
        </div>


    </div>
</template>

<script>
    // import ElTableHeader from "element-ui";
    // import ElTableFilterPanel from "element-ui/packages/table/src/filter-panel";

    module.exports = {
        // components: {ElTableFilterPanel, ElTableHeader},
        data() {
            return {
                tableData: [], // 后台传入的数据
                options: [{
                    value: '1',
                    label: '姓名'
                }, {
                    value: '2',
                    label: '咨询师'
                }, {
                    value: '3',
                    label: '咨询方式'
                }],
                pageSize: 10,    // pagesize：一页显示多少条，
                total: 0,        // total：一共多少条
                pageNum: 1,      // 当前页
                currentPage: 1,  // 默认显示第1页
                input: '',       // 输入框
                value: '1',      // 下拉框，默认让它选中“姓名”
                multipleSelection: [],     // 储存选中行的数据
                dialogFormVisible: false,  // “调换咨询电话”的对话框 false为关闭
                resoumeFormVisible: false, // “导入简历”的对话框
                fileList: [], // 上传文件的数组
                form: {
                    info: '',
                    template: '智联招聘',
                    name: '周梦'
                },
                ruleForm: {
                    resource: ''
                }
            }
        },
        // 钩子函数，页面加载时就会执行
        created() {
            this.getNewsList();
        },
        methods: {
            // 发送请求，获取相应的数据
            getNewsList() {
                axios({
                    url: "/getTrackList",
                    method: "post",
                    headers: {
                      'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    data: this.pagingData()
                }).then(res => {
                    this.tableData = res.data.rows;
                    this.total = res.data.total;
                    console.log("返回成功：" + res)
                }).catch(err => {
                    this.$message({ // 提示框
                        type: 'warning',
                        message: '系统内部错误！请联系管理员。错误代码：' + err
                    });
                    console.log("发生错误：" + err);
                })
            },
            // 一页显示多少条
            handleSizeChange(val) {
                this.pageSize = val;
                this.getNewsList();
            },
            // 当前页
            handleCurrentChange(val) {
                this.pageNum = val;
                this.getNewsList();
            },
            // 点击查询时执行的函数
            doSearch() {
                this.getNewsList();
            },
            // 如果没输入查询关键字会执行这个函数
            hints() {
                this.$message({ // 提示框
                    type: 'info',
                    message: '请输入查询关键字'
                })
            },
            // 双击行执行的函数
            rowClick(column) {
                console.log(column);
                alert("行的双击事件：" + column.stuName);
            },
            // 单独选择一行或多行（未点全选的情况）
            selectionChange(val){
                this.multipleSelection = val
            },
            // 取消全选
            clearSelect(rows) {
                if (rows) {
                    rows.forEach(row => {
                        this.$refs.multipleTable.toggleRowSelection(row)
                    });
                } else {
                    this.$refs.multipleTable.clearSelection();
                }
            },
            // 全选
            selectAll(val) {
                this.multipleSelection = val;
            },

            // 添加简历
            addResoume() {
                alert("添加简历操作");
            },
            // 删除简历
            delResoume() {
                alert("删除简历操作");
            },
            // 撤回简历
            recallResoume() {
                alert("撤回简历的操作");
            },
            // 调换咨询电话
            exchangePhone() {
                this.$message({ // 提示框
                    type: 'info',
                    message: '至少选择一个学员'
                })
            },
            // 提交咨询电话时执行
            doConfirm(){
                alert("选择：" + this.ruleForm.resource + "，输入了：" + this.form.info);
                if(this.ruleForm.resource === '电话'){
                    this.multipleSelection.phoneNum = this.form.info
                }else{
                    this.multipleSelection.counselor = this.form.info
                }
                this.dialogFormVisible = false
            },
            // 提交
            submitUpload(){
                this.$refs.upload.submit();
                /*this.$axios({
                  url: '/uploadFiles',
                  data: ''
                }).then(res => function () {
                  this.$message({
                    message: res,
                    type: 'info'
                  });
                })*/
            },
            // 上传文件的判断
            beforeAvatarUpload(file){
                alert("上传文件的判断：" + file.name);
                var fileName = new Array();
                fileName = file.name.split('.');
                const extension = fileName[fileName.length-1] === 'xls';
                const extension1 =  fileName[fileName.length-1]=== 'xlsx';
                const extension2 =  fileName[fileName.length-1]=== 'doc';
                const extension3 =  fileName[fileName.length-1]=== 'docx';
                const isLt2M = file.size / 1024 / 1024 < 10;
                // 验证文件类型
                if (!extension && !extension1 && !extension2 && !extension3) {
                    this.$message({
                        message: '上传失败！文件只能是文档或表格格式!',
                        type: 'warning'
                    });
                    console.log('上传失败！文件只能是文档或表格格式!')
                }
                // 验证文件大小
                if (!isLt2M) {
                    this.$message({
                        message: '上传失败！文件大小不能超过 10MB!',
                        type: 'warning'
                    });
                    console.log('上传模板大小不能超过 10MB!')
                }
                return extension || extension1 || extension2 || extension3 && isLt2M;
            },
            handleRemove(file, fileList) {
                alert("handleRemove："+file, fileList);
                console.log(file, fileList);
            },
            handlePreview(file) {
                alert("handlePreview："+file);
                console.log(file);
            },
            // 分页参数的写法
            pagingData() {
                const params = new URLSearchParams();
                params.append('pageSize', this.pageSize);
                params.append('pageNum', this.pageNum);
                params.append('input', this.input);
                params.append('value', this.value);
                console.log("传入后台的参数：" + params);
                return params;
            }
        },

    }
</script>

<style>
    #tracktable {
        border: 1px solid #e3e3e3;
        width: 100%;
        height: 100%;
    }

    #button_div {
        border-bottom: 1px solid #e3e3e3;
        margin-top: 10px;
        height: 50px;
    }

    #stuInfo, .el_bt {
        font-family: Microsoft YaHei;
    }

    #search {
        padding: 10px;
    }
</style>

