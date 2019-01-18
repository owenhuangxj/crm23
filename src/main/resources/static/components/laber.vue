<template>
    <el-row class="tac" style="width:200px">
        <el-col :span="12">
            <el-menu
                    default-active="2"
                    class="el-menu-vertical-demo"
                    @open="handleOpen"
                    @close="handleClose"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b">
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>学员管理</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="xueYuan">学员跟踪</el-menu-item>
                        <el-menu-item index="jianLi">未分配简历</el-menu-item>
                        <el-menu-item index="renWu">定时任务</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
                <el-submenu index="2">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>系统设置</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="xinXi">个人信息</el-menu-item>
                        <el-menu-item index="ziDian">数据字典</el-menu-item>
                        <el-menu-item index="riZhi">操作日志</el-menu-item>
                        <el-menu-item index="muBiao">绩效目标设置</el-menu-item>
                    </el-menu-item-group>
                    </el-submenu>
            </el-menu>
        </el-col>
    </el-row>
</template>
 <script>

         module.exports = {

            methods: {
                handleOpen(key, keyPath) {
                    console.log(key, keyPath);
                },
                handleClose(key, keyPath) {
                    console.log(key, keyPath);
                }
            }
        }
    </script>

<style scoped>
    .el-col-12{
    width:100%
    }
<style>
