<template>
    <div class="header">
        <button @click ="goBack">
            <img src="@/assets/CloseButton.svg" alt />
        </button>
        <div>
            <h2>주문 취소</h2>
        </div>

    </div>
    <div class="container">
        <div class="pic">
            <img src="@/assets/PayCancel.svg" alt />
        </div>
    </div>
</template>

<script>
export default {
  setup() {
    function goBack() {
      window.history.back()
    }
    return {
      goBack,
    }
  }
}
</script>

<style>
.header{
    display: flex;
    flex-direction: row;
    height: 1.5rem;
    width: 100%;
}
button{
    margin-left: 1rem;
}
h2{
    margin-left: 1.5rem;
}
.container{
    display: flex;
    justify-content: center;
    align-content: center;
    height: 15rem;
    width: 100%;
}
.pic{
    width: 70%;
    height: 50%;
}

</style>