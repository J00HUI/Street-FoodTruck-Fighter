<template>
	<CeoHeader></CeoHeader>
  <Update></Update>
</template>

<script>
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import Update from "@/components/ceo/ScheduleUpdate.vue";
export default {
  components: {
    Update,
		CeoHeader,
  }
};
</script>

<style>
</style>