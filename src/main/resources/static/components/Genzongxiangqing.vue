<template>
  <div id="app">
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <div class="handle-box" style="text-align: left">
      <P class="yewu">{{student}}的跟踪记录</P>
    </div>
    <div @dblclick="doubleclick">
      <el-table
        :data="tableData"
        stripe
        style="width: 100%"  @row-dbclick="doubleclick(scope.$index)">
        <el-table-column
          prop="id"
          label="编号"
          width="90" >
        </el-table-column>
        <el-table-column
          prop="followDate"
          label="跟踪时间"
          width="100">
        </el-table-column>
        <el-table-column
          prop="level"
          label="优先级"
          width="100" >
        </el-table-column>
        <el-table-column
          prop="followSize"
          label="跟踪次数"
          width="90" >
        </el-table-column>
        <el-table-column
          prop="followWay"
          label="跟踪方式"
          width="160" >
        </el-table-column>
        <el-table-column
          prop="followLongDate"
          label="跟踪时长"
          width="160" >
        </el-table-column>
        <el-table-column
          prop="followPerson"
          label="跟踪人员"
          width="160" >
        </el-table-column>
        <el-table-column
          prop="seeTeacher"
          label="面试老师"
          width="160" >
        </el-table-column>
        <el-table-column
          prop="nextDate"
          label="近期跟踪时间"
          width="180" >
        </el-table-column>
        <el-table-column
          prop="remarks"
          label="备注">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
            <el-button type="text" size="small">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        @prev-click="handlePrevPage"
        :current-page="currentPage4"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="400">
      </el-pagination>
    </div>
  </div>

</template>

<script>
  module.exports={
    name:'WorkorTable',
    data() {
      return {
        student:this.$route.query.student,
        numbers:'10',
        numbers2:'5',
        numbers3:'33',
        select_cate:'',
        select_word:'',
        sendList:[{'typename': 'Id'},{'typename': '姓名'},{'typename': '年纪'},{'typename': '时间'}],
        pickerOptions1: {
          disabledDate(time) {
            return time.getTime() > Date.now();
          },
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
        value1: '',
        value2: '',
        currentPage4: 4,
        msg: 'Welcome to Your Vue.js App',
        tableData: [{
          id:'1312',
          followDate: '王小虎',
          followSize:'男',
          level:'高',
          followSize:'2',
          followLongDate:'网络招聘',
          followPerson:'12345678952',
          seeTeacher:'321498@163.com',
          nextDate: '2016-05-02',
          remarks:'入学'
        }, {
          id:'1312',
          followDate: '王小虎',
          followSize:'男',
          level:'高',
          followSize:'2',
          followLongDate:'网络招聘',
          followPerson:'12345678952',
          seeTeacher:'321498@163.com',
          nextDate: '2016-05-02',
          remarks:'入学'
        }, {
          id:'1312',
          followDate: '王小虎',
          followSize:'男',
          level:'高',
          followSize:'2',
          followLongDate:'网络招聘',
          followPerson:'12345678952',
          seeTeacher:'321498@163.com',
          nextDate: '2016-05-02',
          remarks:'入学'
        }, {
          id:'1312',
          followDate: '王小虎',
          followSize:'男',
          level:'高',
          followSize:'2',
          followLongDate:'网络招聘',
          followPerson:'12345678952',
          seeTeacher:'321498@163.com',
          nextDate: '2016-05-02',
          remarks:'入学'
        }]
      }
    },
    methods:{
      //获得初始化数据
      getData(){
        // 开发环境使用 easy-mock 数据，正式环境使用 json 文件
        this.url = '/selectStudentOfRecrod/'+this.student.id;
        axios.get(this.url).then((res) => {
          alert("获得的数据："+JSON.stringify(res.data));
          // this.tableData = res.data;
        })
      },
      handleClick(row) {
        //查看的内容
        alert("查看的内容"+JSON.stringify(row))
        console.log(row);
      },
      doubleclick(index){
        alert("你好我是双击事件"+JSON.stringify(index) )
      },
      handlePrevPage(val){
        console.log(`上页 ${val} 条`);
      },
      handleNextPage(val){
        console(`下页 ${val} 条`);
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      }
    }
  }
</script>

<style scoped>
  .handle-box {
    margin-bottom: 20px;
  }

  .yewu{
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  }
</style>
