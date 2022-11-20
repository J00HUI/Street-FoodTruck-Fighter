<template>
  <router-link to="/" style="text-decoration: none">
    <img
      src="@/assets/xicon.svg"
      style="width: 5%; margin-top: 15%; margin-left: 5%"
      alt
    />
  </router-link>

  <div class="MenuSelect">
    <div
      style="
        font-family: SCoreDream;
        font-style: normal;
        font-weight: 600;
        font-size: 140%;

        text-align: center;

        padding-top: 10%;
      "
    >
      원하시는 푸드트럭을<br />선택해주세요!
    </div>
    <div
      style="
        font-family: SCoreDream;
        font-style: normal;
        font-weight: 500;
        font-size: 110%;

        text-align: center;

        padding-top: 7%;
      "
    >
      우리 동네로 찾아갑니다!
    </div>

    <label
      ><input type="radio" name="menu" value="햄버거" />
      <img
        src="@/assets/hamburger.svg"
        style="width: 20%; margin-left: 7.5%; margin-top: 13%"
        @click="menuSelect(1)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="핫도그" />
      <img
        src="@/assets/hotdog.svg"
        class="middleContent hotdog"
        @click="menuSelect(2)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="카페" /><img
        src="@/assets/coffee.svg"
        class="middleContent"
        @click="menuSelect(3)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="고구마" /><img
        src="@/assets/sweetpotato.svg"
        class="middleContent"
        @click="menuSelect(4)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="아이스크림" />
      <img
        src="@/assets/icecream.svg"
        style="width: 20%; margin-left: 7.5%"
        @click="menuSelect(5)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="와플" />
      <img
        src="@/assets/waffle.svg"
        class="middleContent"
        @click="menuSelect(6)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="스테이크" />
      <img
        src="@/assets/steak.svg"
        class="middleContent"
        @click="menuSelect(7)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="샌드위치" />
      <img
        src="@/assets/sandwich.svg"
        class="middleContent"
        @click="menuSelect(8)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="꼬치" />
      <img
        src="@/assets/skeweredfood.svg"
        style="width: 20%; margin-left: 7.5%"
        @click="menuSelect(9)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="라면" />
      <img
        src="@/assets/ramen.svg"
        class="middleContent"
        @click="menuSelect(10)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="피자" />
      <img
        src="@/assets/pizza.svg"
        class="middleContent"
        @click="menuSelect(11)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="음료수" />
      <img
        src="@/assets/drink.svg"
        class="middleContent"
        @click="menuSelect(12)"
        alt
    /></label>

    <div style="text-align: center">
      <input
        type="button"
        value="내 위치 확인하기"
        class="SubmitButton"
        @click="openMap()"
        style="
          background-color: orange;
          color: white;
          width: 70%;
          margin-top: 10%;
        "
      />
    </div>
  </div>

  <div style="text-align: center">
    <input
      type="button"
      value="제출"
      class="SubmitButton"
      @click="surveyStore.setCustomerSurvey"
    />
  </div>

  <MapModal class="modal" v-if="isModal" @close="closeMap" />
</template>
        
<script>
import { useCustomerSurveyStore } from "@/stores/customer/survey/survey";
import { useKakaoStore } from "@/stores/kakao";
import MapModal from "@/components/customer/MapModal.vue";

export default {
  components: {
    MapModal,
  },

  setup() {
    const surveyStore = useCustomerSurveyStore();

    const kakaoStore = useKakaoStore();
    kakaoStore.setHeaderAddress();

    function location() {
      navigator.geolocation.getCurrentPosition(function (position) {
        surveyStore.surveyData.latitude = position.coords.latitude;
        surveyStore.surveyData.longitude = position.coords.longitude;

        surveyStore.surveyData.address = kakaoStore.mapCenter.address;
      });
    }

    location();

    return {
      surveyStore,
      kakaoStore,
      location,
    };
  },

  data() {
    return {
      isModal: false,
    };
  },

  methods: {
    menuSelect(menu) {
      if (menu == 1) {
        this.surveyStore.surveyData.category = "햄버거";
      } else if (menu == 2) {
        this.surveyStore.surveyData.category = "핫도그";
      } else if (menu == 3) {
        this.surveyStore.surveyData.category = "카페";
      } else if (menu == 4) {
        this.surveyStore.surveyData.category = "고구마";
      } else if (menu == 5) {
        this.surveyStore.surveyData.category = "아이스크림";
      } else if (menu == 6) {
        this.surveyStore.surveyData.category = "와플";
      } else if (menu == 7) {
        this.surveyStore.surveyData.category = "스테이크";
      } else if (menu == 8) {
        this.surveyStore.surveyData.category = "샌드위치";
      } else if (menu == 9) {
        this.surveyStore.surveyData.category = "꼬치";
      } else if (menu == 10) {
        this.surveyStore.surveyData.category = "라면";
      } else if (menu == 11) {
        this.surveyStore.surveyData.category = "피자";
      } else if (menu == 12) {
        this.surveyStore.surveyData.category = "음료수";
      }
    },

    openMap() {
      this.isModal = true;
    },

    closeMap() {
      this.isModal = false;
    },
  },
};
</script>

<style scoped>
.MenuSelect {
  width: 80%;
  height: 60%;

  background: #fff6dd;
  box-shadow: 0rem 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  border-radius: 1rem;

  margin-top: 12%;
  margin-left: 10%;
  /* text-align: center; */
}
.middleContent {
  width: 20%;
  margin-left: 1.75%;
}
[type="radio"] {
  position: absolute;
  opacity: 0;
  width: 0;
  height: 0;
}
/* IMAGE STYLES */
[type="radio"] + img {
  cursor: pointer;
}

/* CHECKED STYLES */
[type="radio"]:checked + img {
  /* outline: 2px solid #f00; */
  border: 0.2rem solid var(--color-orange-1);
  border-radius: 3rem;
  opacity: 70%;
  box-sizing: border-box;
}
.SubmitButton {
  width: 15rem;
  height: 4rem;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 1rem;

  filter: drop-shadow(0rem 0.625rem 1.3rem rgba(150, 170, 250, 0.3));

  background: #ffe060;
  border-radius: 2rem;
  border: none;
  outline-style: none;

  margin-top: 15%;
}
.modal {
  position: fixed;
  top: 0;
  /* left: 30.7%; */
  width: 25.1rem;
  margin-left: 0rem;
}
</style>