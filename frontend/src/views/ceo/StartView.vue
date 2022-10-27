<template>
    <CeoHeader></CeoHeader>
    <NewOrder></NewOrder>

    <h1>영업시작 페이지</h1>
</template>

<script>
import CeoHeader from '@/components/ceo/CeoHeader.vue'
import NewOrder from '@/components/ceo/StartNewOrder.vue'
export default {
    components: {
        CeoHeader,
        NewOrder,
    }

}
</script>

<style>

</style>