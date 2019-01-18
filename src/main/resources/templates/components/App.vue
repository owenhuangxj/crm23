<template>
    <div id="app">
        <Tab></Tab>
        aasqq
    </div>
</template>

<script>
    module.exports =  {
        data:function ()
        {
            return{
                who:"!"
            }
        },
        components:{
            'Tab': httpVueLoader('Tab.vue')
        }
    }
</script>

<style scoped>

</style>