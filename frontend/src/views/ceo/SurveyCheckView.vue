<template>
  <CeoHeader></CeoHeader>
  <SurveySearch></SurveySearch>
  <!-- <Icons></Icons> -->
  <KakaoMap v-if="kakaoStore.searchTypeData.is_map"></KakaoMap>
</template>

<script>
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import SurveySearch from "@/components/ceo/SurveySearchBar.vue";
import KakaoMap from "@/components/ceo/SurveyKakaoMap.vue";
// import Icons from '@/components/ceo/SurveyIcons.vue'
import { useKakaoStore } from "@/stores/kakao";
export default {
  components: {
    CeoHeader,
    SurveySearch,
    // Icons,
    KakaoMap,
  },
  setup() {
    const kakaoStore = useKakaoStore()
    kakaoStore.is_map = false
    kakaoStore.getSurvey()
    return {
      kakaoStore
    }
  }
};
</script>

<style>
</style>