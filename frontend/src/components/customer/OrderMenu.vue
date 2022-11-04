<template>
  <div class="cardContainer">
    <div class="card" @click="showDetail">
        <div class="picture">
            <img  src="@/assets/hamburger.svg" alt />
        </div>
        <div class="detail">
            <div class="storeName">
                <h3 >맛있는 햄버거</h3>
            </div>
            <div class="orders">
                <h4>7500원</h4>
            </div>
        </div>
        
    </div>
  </div>
  <hr>
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

<style scoped>
/***************** card style start ******************/
.cardContainer{
    display: flex;
    justify-content: center;
    margin-top: 1rem;
    margin-bottom: 1.5rem;
}
.card{
    display: flex;
    justify-content: left;
    flex-direction: row;
    align-items: center;
    width: 21.5rem;
    height: 6rem;
    border: 0;
}
hr{
  width: 100%;
  color: lightgray;
}
.picture{
  margin-right: 1rem;
}

/***************** card style end ******************/
</style>