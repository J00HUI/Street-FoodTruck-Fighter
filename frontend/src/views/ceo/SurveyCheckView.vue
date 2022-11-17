<template>
  <CeoHeader></CeoHeader>
  <SurveySearch></SurveySearch>
  <!-- <Icons></Icons> -->
  <KakaoMap></KakaoMap>
</template>

<script>
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import SurveySearch from "@/components/ceo/SurveySearchBar.vue";
import KakaoMap from "@/components/ceo/SurveyKakaoMap.vue";
// import Icons from '@/components/ceo/SurveyIcons.vue'
export default {
  components: {
    CeoHeader,
    SurveySearch,
    // Icons,
    KakaoMap,
  }
};
</script>

<style>
</style>