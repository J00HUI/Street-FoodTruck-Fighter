<template>
  <div class="myView">
  <CeoHeader></CeoHeader>
	<UpdateInput></UpdateInput>
</div>

</template>

<script>
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import UpdateInput from "@/components/ceo/MyUpdate.vue"
export default {
  components: {
    CeoHeader,
		UpdateInput,
  }
};
</script>

<style>
.myView{
  position:relative;
  width: 100%;
  height: 100%;
  top: 0px;
  margin: 0px;

}
</style>