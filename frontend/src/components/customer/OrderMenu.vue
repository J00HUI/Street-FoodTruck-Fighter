<template>
  <div class="card" >
      <div>
          <img class="picture" src="@/assets/hamburger.svg" alt />
      </div>
      <div class="detail">
          <div class="storeName">
              <h3 @click="showDetail">맛있는 햄버거</h3>
          </div>
          <div class="orders">
              <h4>7500원</h4>
          </div>
      </div>
      <hr>
  </div>
</template>

<script>
import router from "@/router";
export default {
  components: {
    
  },
  setup() {
    function showDetail(){
      router.push('/menudetail')
    }
    return{
      showDetail
    }
  }
}
</script>

<style>

</style>