<template>
    <CeoHeader></CeoHeader>
    <NewOrder></NewOrder>
    <Order></Order>

</template>

<script>
import CeoHeader from '@/components/ceo/CeoHeader.vue'
import NewOrder from '@/components/ceo/StartNewOrder.vue'
import Order from '@/components/ceo/StartOrder.vue'
export default {
    components: {
        CeoHeader,
        NewOrder,
        Order,
    }

}
</script>

<style>

</style>