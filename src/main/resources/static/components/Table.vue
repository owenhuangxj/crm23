
    <template>
        <div style="padding:20px;" id="app">
            <div class="panel panel-primary">
                <div class="panel-heading">用户管理</div>
                <div>


                    <select id="sendSybol"  style="width: 100px" v-model="typename">
                        <option  v-for="send  in sendList" :name="send.value" :key="send.value" v-bind:value="send.typename" >{{send.typename}}</option>
                    </select>
                    <input type="text"  id="search"  v-model="search.name"/> <img src="../img/search.png" style="width: 20px;height: 20px" @click="serch">
                    <span>选的：{{typename}}</span>
                </div>

                <table class="basetable table-bordered table-striped text-center">
                    <thead>
                    <tr >
                        <th v-for="item in user" >{{item.typename}}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for ="(user,index) in users" >
                        <td>{{index+1}}</td>
                        <td  v-for="value in users[index]" @dblclick="doubleclick(index)" style="">{{value}}</td>
                        <td><button v-on:click="remove(index)">remove</button></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="text"  id="name" v-model="user.name"/></td>
                        <td><input type="text" id="age"v-model="user.age"/></td>
                        <td><input type="text" id="school"v-model="user.school"/></td>
                        <td><button @click="insert">insert</button></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </template>
<script>
    module.exports= {

        name: 'basetable',
        mounted:function(){
            this.changeCollapse(5);
        },
        data () {
            return {

                search:{'typename': 'Id','name': 'name'},
                sendList:[{'typename': 'Id'},{'typename': '姓名'},{'typename': '年纪'},{'typename': '时间'}],
                typename:'Id',
                user: [{'typename': 'Id'},{'typename': 'name'},{'typename': 'age'},{'typename': 'school'},{'typename': 'option'}],
                users: [
                    {name: '李磊sdf', age: '25', school: '洛阳理工'},
                    {name: '张成sd', age: '23', school: '桂林电子科技'},
                    {name: '炼心sd', age: '22', school: '江西电子科技'},
                    {name: '炼心sd', age: '22', school: '江西电子科技'}
                ]
            }
        },
        methods: {
            insert: function () {
                this.users.push(this.user)
            },
            remove: function (index) {
                this.users.splice(index, 1)
            },
            changeCollapse:function (lenth) {
                var td=document.getElementsByTagName("td");                console.log("我执行了这个方法"+lenth);
                alert("我执行了这个方法"+lenth);

              var baifenbi=(100/lenth);
              alert("计算的东西"+baifenbi)
              // td.style.width='10%';
            },
            doubleclick:function (index) {
                alert("你好我是双击事件:"+index)
            },
            serch:function () {
                alert("这是我要搜索的东西："+this.typename+':'+this.search.name)
            }
        }
    }
</script>

<style scoped>
    h1, h2 {
        font-weight: normal;
    }

    ul {
        list-style-type: none;
        padding: 1px;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
    tr,th{
        text-align:center;
        border-top: 1px solid #0058a3;
        border-bottom: 1px solid #0058a3;
    }
    td{
        width: 25%;
    }


    .datalist caption {
        padding-bottom: 5px;
        text-align: left;
    }

    .datalist th {
        border: 1px solid #0058a3; /* 行名称边框 */
        background-color: #4bacff; /* #c4e4ff #4bacff %%%%%%%%%%%%%%%%% 非常好的背景颜色 %%%%%%%%%%%%%  行名称背景色 */
        color: #FFFFFF; /* 行名称颜色 */
        font-weight: bold;
        padding-top: 4px;
        padding-bottom: 4px;
        padding-left: 12px;
        padding-right: 12px;
        text-align: center;
    }

    .datalist td {
        border-top: 1px solid #0058a3;
        border-bottom: 1px solid #0058a3;
        text-align: left;
        padding-top: 4px;
        padding-bottom: 4px;
        padding-left: 10px;
        padding-right: 10px;
    }

    /*css控制隔行变色，注意：table的td标签不能有背景色，否则隔行变色失败*/
    .datalist tr:nth-child(even) {background:#e1f2f9}
    .datalist tr:nth-child(odd) {background: #cc9933}

    .datalist tr {
        background-color: #c4e4ff; /*非常好的背景颜色，动态变色*/
    }
</style>