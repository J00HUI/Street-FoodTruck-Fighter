<template>
  <header class="header">
    <div class="back">
      <button @click ="goBack">
        <img src="@/assets/Back-Navs.svg" alt />
      </button>
    </div>
    <div class="home">
      <router-link to="/">
        <button>
          <img src="@/assets/Home.svg" alt />
        </button>
      </router-link>
      <router-link to="/cart">
        <button>
          <img src="@/assets/Cart.svg" style="margin-left:1rem;" alt />
        </button>
      </router-link>
    </div>
  </header>
</template>

<script>
import { useStoreDetail } from "@/stores/customer/menu/storeDetail";

export default {
  setup() {
    const detailStore = useStoreDetail();
    function goBack() {
      detailStore.amount = 1
      window.history.back()
    }
    return {
      detailStore,
      goBack,
    }
  }
};
</script>

<style scoped>
button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: none;
  align-items: left;
}
.header {
  font-family: "SCoreDream";
  display: flex;
  width: 90%;
  margin: 5%;
  justify-content: space-between;
  align-items: center;
}
.back{
  align-self: left;
}
.home{
  align-self: right;
}

</style>