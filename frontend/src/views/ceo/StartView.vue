<template>
  <div style="display: flex; height: 844px; flex-direction: column">
    <CeoHeader></CeoHeader>
    <NewOrder></NewOrder>
    <Order class="orderScroll"></Order>
  </div>
</template>

<script>
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import NewOrder from "@/components/ceo/StartNewOrder.vue";
import Order from "@/components/ceo/StartOrder.vue";
import { onMounted } from "vue";
export default {
  components: {
    CeoHeader,
    NewOrder,
    Order,
  },
  setup() {
    onMounted(() => {});

    return {};
  },
};
</script>

<style scoped>
.orderScroll {
  overflow-y: scroll;
  height: auto;
  min-height: 10px;

  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.orderScroll::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
</style>