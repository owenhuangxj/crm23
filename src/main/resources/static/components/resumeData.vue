<template>

    <!--主界面-->
    <div id="dataGrid">
        <div id="tool-bar">
            <el-button @click="toggleSelection()"><i class="el-icon-close"></i>取消选择</el-button>
            <el-button><i class="el-icon-plus"></i>新增简历</el-button>
            <el-button><i class="el-icon-upload"></i>导入简历</el-button>
            <el-button><i class="el-icon-message" @click="dialogVisible = true"></i>分配简历</el-button>
            <el-button><i class="el-icon-delete"></i> 删除简历</el-button>

        </div>
        <div id="table">
            <el-table
                    ref="multipleTable"
                    :data="resumeData"
                    tooltip-effect="dark"
                    style="width: 100%"
                    stripe="true"
                    :default-sort = "{prop: 'date', order: 'descending'}"
                    @selection-change="handleSelectionChange">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        label="序号"
                        type="index"
                        width="50">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="gender"
                        label="性别"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop=""
                        label="电话"
                        width="120">
                </el-table-column>
                <el-table-column
                        label="导入日期"
                        width="120">
                    <template slot-scope="scope">{{ scope.row.date }}</template>
                </el-table-column>

                <el-table-column
                        prop="address"
                        label="地址"
                        show-overflow-tooltip>
                </el-table-column>
            </el-table>
        </div>
        <div id="pagination">

            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage4"
                    :page-sizes="[100, 200, 300, 400]"
                    :page-size="100"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="400">
            </el-pagination>
        </div>

    </div>




</template>

<script>
    let resumeData=[{
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
    }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
    }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
    }]

    module.exports= {
        data() {
            return {
                resumeData: resumeData,
                multipleSelection: []
            }
        },

        methods: {
            toggleSelection(rows) {
                if (rows) {
                    rows.forEach(row => {
                        this.$refs.multipleTable.toggleRowSelection(row);
                    });
                } else {
                    this.$refs.multipleTable.clearSelection();
                }
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            }
        }
    }
</script>

<style scoped>
    /*数据框的主界面*/
#dataGrid{
    display: -webkit-box;
    -webkit-box-orient: vertical;
    text-align: center;
    padding-left: 2em;
    padding-right: 2em;
}
    #tool-bar{
        padding-bottom: 1em;
        padding-top: 2em;
    }
#pagination{
    padding-bottom: 1em;
    padding-top: 1em;
}
    /*分配简历窗口*/


</style>