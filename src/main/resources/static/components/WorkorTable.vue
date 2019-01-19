<template>
  <div id="app">
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <el-container>
      <el-header height="230px">
          <p>本周完成量</p>
        <div class="handle-box" style="text-align: left">
        <P class="yewu">{{worktor}}:业务详情 今日完成量：{{numbers}}  未完成量：{{numbers2}} 完成率：</P>
      </div>
      </el-header>
      <el-main >
        <div @dblclick="doubleclick">
          <el-table
                  :data="tableData"
                  stripe
                  style="width: 100%"  @row-dbclick="doubleclick(scope.$index)">
            <el-table-column
                    prop="stuNumber"
                    label="编号"
                    width="90" >
            </el-table-column>
            <el-table-column
                    prop="stuNumber"
                    label="姓名"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="stuLevel"
                    label="优先级"
                    width="100" >
            </el-table-column>
            <el-table-column
                    prop="followSize"
                    label="跟踪次数"
                    width="90" >
            </el-table-column>
            <el-table-column
                    prop="stuPhoneNum"
                    label="手机号"
                    width="160" >
            </el-table-column>
            <el-table-column
                    prop="stuQq"
                    label="QQ"
                    width="160" >
            </el-table-column>
            <el-table-column
                    prop="stuSource"
                    label="来源途径"
                    width="160" >
            </el-table-column>
            <el-table-column
                    prop="stuStatus"
                    label="进度情况"
                    width="160" >
            </el-table-column>
            <el-table-column
                    prop="stuAddress"
                    label="地址">
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
      </el-main>
    </el-container>

  </div>

</template>

<script>
  module.exports={
    name:'WorkorTable',
    data() {
      return {
        // worktor:this.$route.query.worktor,
        worktor:'李小姐',
        numbers:'10',
        numbers2:'5',
        numbers3:33,
        select_cate:'',
        select_word:'',
        cur_page:1,
        cur_page_size:10,
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
          stuId:'1312',
          stuNumber:'23156451',
          stuName: '王小虎',
          stuSex:'男',
          stuLevel:'高',
          followSize:'2',
          stuSource:'网络招聘',
          stuPhoneNum:'12345678952',
          stuQq:'321498@163.com',
          date: '2016-05-02',
          stuStatus:'入学',
          stuAddress: '上海市普陀区金沙江路 153 弄'
        }, {
          stuId:'1312',
          stuNumber:'23156451',
          stuName: '王小虎',
          stuSex:'男',
          stuLevel:'高',
          followSize:'2',
          stuSource:'网络招聘',
          stuPhoneNum:'12345678952',
          stuQq:'321498@163.com',
          date: '2016-05-02',
          stuStatus:'入学',
          stuAddress: '上海市普陀区金沙江路 153 弄'
        }, {
          stuId:'1312',
          stuNumber:'23156451',
          stuName: '王小虎',
          stuSex:'男',
          stuLevel:'高',
          followSize:'2',
          stuSource:'介绍来的',
          stuPhoneNum:'12345678952',
          stuQq:'321498@163.com',
          date: '2016-05-02',
          stuStatus:'入学',
          stuAddress: '上海市普陀区金沙江路 15w弄'
        }, {
          stuId:'1312',
          stuNumber:'23156451',
          stuName: '王大虎',
          stuSex:'男',
          stuLevel:'高',
          followSize:'2',
          stuSource:'网络招聘',
          stuPhoneNum:'12345678952',
          stuQq:'321498@163.com',
          date: '2016-05-02',
          stuStatus:'入学',
          stuAddress: '上海市普陀区金沙江路 153 弄'
        }]
      }
    },
    //获得初始化数据
    created() {
      alert("我执行了careated的方法");
      this.getData();
    },
    computed: {
      data() {
        alert("wozhixing")
      }
    },
    methods:{

      //获得初始化数据
      getData(){
        alert("hahah");
        // 开发环境使用 easy-mock 数据，正式环境使用 json 文件
        this.url = '/selectWorktorOfStu/'+1+'/'+this.cur_page+'/'+this.cur_page_size;
        axios.get(this.url).then((res) => {
          alert("获得的数据："+JSON.stringify(res.data));
          this.tableData = res.data;
        })
      },
      handleClick(row) {
        //查看的内容
        alert("查看的内容"+JSON.stringify(row))
        console.log(row);
        // this.$router.push({path:'Genzongxiangqing',query:{student:row.name}})
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
        this.cur_page_size=val;
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.cur_page=val;

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
