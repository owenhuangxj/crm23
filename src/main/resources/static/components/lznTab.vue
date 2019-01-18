<template>
    <div class="app" align="center">
        <h3>日志操作</h3>
        <div>
        <!--时间-->
        <span class="demonstration">开始</span>
        <el-date-picker
                v-model="value1"
                type="date"
                placeholder="选择日期">
        </el-date-picker>
        <span class="demonstration">结束</span>
        <el-date-picker
                v-model="value2"
                type="date"
                placeholder="选择日期">
        </el-date-picker>
            <!--选择器-->
            <el-select v-model="value" placeholder="请选择">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
            <!--输入框-->
            <el-input v-model="input" placeholder="请输入要查询的用户名或账号" style="width: 20%"></el-input>
            <!--按钮-->
            <el-button type="primary" icon="el-icon-search" @click="seek">搜索</el-button>
        </div>
        <br/>
        <el-table
                :data="tableData"
                border
                style="width: 100%">
            <el-table-column
                    prop="log_id"
                    label="序号"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="log_type"
                    label="日志类型"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="log_content"
                    label="详细操作">
            </el-table-column>
            <el-table-column
                    prop="log_error_code"
                    label="错误码">
            </el-table-column>
            <el-table-column
                    prop="user_id"
                    label="用户账号">
            </el-table-column>
            <el-table-column
                    prop="user_name"
                    label="用户名">
            </el-table-column>
            <el-table-column
                    prop="log_date"
                    label="操作日期">
            </el-table-column>
        </el-table>
        <br/>
        <!--分页-->
        <div>

            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage4"
                    :page-sizes="[10, 20, 30, 40]"
                    :page-size="10"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="40"
                    @click="skip">
            </el-pagination>

        </div>
    </div>
</template>

<script>
    module.exports =  {
        data:function () {
            return {
                pickerOptions1: {
                    disabledDate(time) {
                        return time.getTime() > Date.now();
                    }
                },
                options: [{
                    value: '用户操作',
                    label: '用户操作'
                }, {
                    value: '错误',
                    label: '错误'
                }, {
                    value: '查询',
                    label: '查询'
                }, {
                    value: '删除',
                    label: '删除'
                }, {
                    value: '添加',
                    label: '添加'
                },{
                    value: '修改',
                    label: '修改'
                }],
                tableData: [{
                    log_id: '',
                    log_type:'',
                    log_error_code: '',
                    log_content:'',
                    user_id:'',
                    user_name: '',
                    log_date: '',
                }],
                currentPage4: 1,
                value: '',
                input: '',
                value1: '',
                value2: '',
            };
        },
        methods: {
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
            },
            seek:function () {
                alert("是什么:" + this.input + this.value1+this.value2+this.value)
            }
        },
        mounted() {
            axios.get("/selectLog")
                .then((res) => {
                    alert(JSON.stringify(res.data))
                })
        },
       /* created() {
            alert("我执行了careated的方法");
            this.getData();
        },*/
    };
</script>