import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/'], resolve),
            meta: { title: '自述文件' },
            children:[
                {
                    path: '/xueYuan',
                    component: resolve => require(['resources/components/Allot.vue'], resolve),
                    meta: { title: '学员跟踪' }
                },
                {
                    path: '/jianLi',
                    component: resolve => require(['../components/App.vue'], resolve),
                    meta: { title: '未分配简历' }
                },
                {
                    path: '/renWu',
                    component: resolve => require(['../components/page/BaseTable.vue'], resolve),
                    meta: { title: '定时任务' }
                },
                {
                    path: '/xinXi',
                    component: resolve => require(['../components/page/Tabs.vue'], resolve),
                    meta: { title: '个人信息' }
                },
                {
                    path: '/ziDian',
                    component: resolve => require(['../components/page/BaseForm.vue'], resolve),
                    meta: { title: '数据字典' }
                },
                {
                    path: '/riZhi',
                    component: resolve => require(['../components/page/VueEditor.vue'], resolve),
                    meta: { title: '操作日志' }
                },
                {

                    path: '/muBiao',
                    component: resolve => require(['../components/page/Markdown.vue'], resolve),
                    meta: { title: '绩效目标设置' }
                },
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
    ]
})
