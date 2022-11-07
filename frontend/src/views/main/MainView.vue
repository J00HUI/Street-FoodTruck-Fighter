<template>
  <MainpageHeader></MainpageHeader>
  <MyOrder></MyOrder>
  <FoodtruckMenu></FoodtruckMenu>
  <WantFoodtruck></WantFoodtruck>
</template>

<script>
import MainpageHeader from "@/components/customer/MainpageHeader.vue";
import MyOrder from "@/components/customer/MyOrder.vue";
import FoodtruckMenu from "@/components/customer/FoodtruckMenu.vue";
import WantFoodtruck from "@/components/customer/WantFoodtruck.vue";
export default {
  components: {
    MainpageHeader,
    MyOrder,
    FoodtruckMenu,
    WantFoodtruck,
  },
};
</script>

<style>
</style>