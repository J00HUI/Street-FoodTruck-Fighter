<template>
<Header></Header>
<div class="container">

    <div>
      <h1>상세 - 메뉴</h1>
      <div v-if="tapState === 1">tap1</div>
      <div v-if="tapState === 2">tap2</div>
      <div v-if="tapState === 3">tap3</div>
      <button @click="tapState = 1">Button1</button>
      <button @click="tapState = 2">Button2</button>
      <button @click="tapState = 3">npButton3</button>
    </div>
</div>
</template>

<script>
import Header from '@/components/customer/BackButtonHeader.vue'
export default {
    components: {
        Header,
    }

}
</script>

<style>

</style>