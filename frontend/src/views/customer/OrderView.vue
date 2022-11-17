<template>
  <Header></Header>
  <div class="container">
    <div class="truck">
      <!-- <h1>{{store.aboutStore.name}}</h1> -->
      <h1>{{ detailStore.aboutStore.data.name }}</h1>
      <form name="starForm" id="starForm">
        <fieldset>
          <input type="radio" name="rating" value="1" id="rate1" /><label
            for="lrate1"
            >⭐</label
          >
          <input type="radio" name="rating" value="2" id="rate2" /><label
            for="lrate2"
            >⭐</label
          >
          <input type="radio" name="rating" value="3" id="rate3" /><label
            for="lrate3"
            >⭐</label
          >
          <input type="radio" name="rating" value="4" id="rate4" /><label
            for="lrate4"
            >⭐</label
          >
          <input type="radio" name="rating" value="5" id="rate5" /><label
            for="lrate5"
            >⭐</label
          >
        </fieldset>
      </form>
      <h4 class="grade">{{ detailStore.aboutStore.data.grade }}</h4>
    </div>
    <div class="cardContainer">
      <div class="card">
        <h1>대기인원 : {{ detailStore.aboutStore.data.numberOfPeople }}명</h1>
        <h3>예상 시간 : {{ detailStore.aboutStore.data.time }}분</h3>
      </div>
    </div>

    <div class="tabmenu out-tabmenu">
      <ul>
        <li id="tab1" class="btnCon">
          <input
            type="radio"
            checked
            name="tabmenu"
            id="tabmenu1"
            @click="menuClick"
          />
          <label for="tabmenu1">메뉴</label>
          <div class="tabCon"></div>
        </li>
        <li id="tab2" class="btnCon">
          <input type="radio" name="tabmenu" id="tabmenu2" @click="infoClick" />
          <label for="tabmenu2">정보</label>
          <div class="tabCon"></div>
        </li>
        <li id="tab3" class="btnCon">
          <input
            type="radio"
            name="tabmenu"
            id="tabmenu3"
            @click="reviewClick"
          />
          <label for="tabmenu3">리뷰</label>
          <div class="tabCon"></div>
        </li>
      </ul>
    </div>
  </div>

  <Menu v-if="menuStore.data == 1"></Menu>
  <Info v-if="menuStore.data == 2"></Info>
  <Review v-if="menuStore.data == 3"></Review>
</template>

<script>
import Header from "@/components/customer/BackButtonHeader.vue";
import Menu from "@/components/customer/OrderMenu.vue";
import Info from "@/components/customer/OrderInfo.vue";
import Review from "@/components/customer/OrderReview.vue";
import { useMenuStore } from "@/stores/customer/menu/menu";
import { useStoreDetail } from "@/stores/customer/menu/storeDetail";
import { onMounted } from "vue-demi";
import { useReviewRoadStore } from "@/stores/customer/review/reviewRoad";

export default {
  components: {
    Header,
    Menu,
    Info,
    Review,
  },
  setup() {
    onMounted(() => {
      detailStore.getStoreInfo();
    });
    // console.log(JSON.stringify(detailStore.aboutStore.data))
    const menuStore = useMenuStore();
    const detailStore = useStoreDetail();


    // tab control
    function menuClick() {
      menuStore.data = 1;
    }
    function infoClick() {
      menuStore.data = 2;
    }
    function reviewClick() {
      menuStore.data = 3;
    }

    detailStore.getStoreInfo();

    const reviewStore = useReviewRoadStore();

    reviewStore.getReview();


    // // star rate control
    // let star = 0 
    // const rate1 = document.getElementById("lrate1")
    // const rate2 = document.getElementById("rate2")
    // const rate3 = document.getElementById("rate3")
    // const rate4 = document.getElementById("rate4")
    // const rate5 = document.getElementById("rate5")
    // star = detailStore.aboutStore.data.grade;
    // console.log('star ' + JSON.stringify  (detailStore.aboutStore.data.grade))
    // console.log(star)
    // if (star >= 0 && star < 2) {
    //   $('input:radio[name=playrtList]:input[value="son"]').attr(
    //     "checked",
    //     true
    //   );
    //   document.getElementById("rate1") == true
    //   // rate1 == true
    //   // console.log('rate1 ' + rate1)
    // }else if(detailStore.aboutStore.data.grade>=2 && detailStore.aboutStore.data.grade<3){
    //   document.getElementById("rate2") == true

    // }else if(star>=3 && star<4){
    //   document.getElementById("rate3") == true
    // }else if(star>=4 && star<5){
    //   document.getElementById("rate4") == true
    // }else if(star>=5){
    //   document.getElementById("rate5") == true
    // }

    return {
      menuStore,
      detailStore,
      // star,
      // rate1,
      // rate2,
      // rate3,
      // rate4,
      // rate5,
      menuClick,
      infoClick,
      reviewClick,
    };
  },
};
</script>

<style scoped>
/* .container{
    height: auto;
    width: 100%;
} */

/***************** truck info ******************/
.truck {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  max-width: 100%;
}
.grade {
  height: 1rem;
  margin-top: 0.3rem;
  margin-bottom: 00.3rem;
}
/***************** card ******************/
.cardContainer {
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  margin-bottom: 1.5rem;
}
.card {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  width: 21.5rem;
  height: 8rem;
  border: 0.1rem solid #ffdd2d;
  border-radius: 1rem;
  box-shadow: 2px 3px 15px -6px grey;
}

/***************** tabs start ******************/
ul {
  list-style: none;
  padding-left: 0px;
}
.tabmenu {
  width: 100%;
  margin: 0 auto;
  position: relative;
}
.tabmenu ul {
  position: relative;
}
.tabmenu ul li {
  display: inline-block;
  width: 33.33%;
  /* float:left;   */
  text-align: center;
  background: #f9f9f9;
  line-height: 40px;
}
.tabmenu label {
  display: block;
  width: 100%;
  height: 40px;
  line-height: 40px;
}
.tabmenu input {
  display: none;
}
.tabCon {
  display: none;
  width: 100%;
  text-align: left;
  padding: 20px;
  position: absolute;
  left: 0;
  top: 40px;
  box-sizing: border-box;
  /* border : 5px solid #f9f9f9; */
}
.tabmenu input:checked ~ label {
  background: #ccc;
  text-decoration-line: underline;
  text-underline-position: under;
  /* text-underline-offset: inherit; */
  /* border-bottom: 1px solid #ffdd2d; */
}
.tabmenu input:checked ~ .tabCon {
  display: flex;
}
/***************** tabs end ******************/

/***************** 별점 start ******************/
#starForm fieldset {
  display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
  border: 0; /* 필드셋 테두리 제거 */
}
#starForm input[type="radio"] {
  display: none; /* 라디오박스 감춤 */
}
#starForm label {
  font-size: 3em; /* 이모지 크기 */
  color: transparent; /* 기존 이모지 컬러 제거 */
  text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */
}
#starForm label:hover {
  color: black; /* text 컬러 원상태로 */
  opacity: 0.5; /* text 투명도 50% */
}
#starForm label:hover ~ label {
  opacity: 0.5;
  color: black;
}
#starForm fieldset {
  display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
  direction: rtl; /* 이모지 순서 반전 */
  border: 1px; /* 필드셋 테두리 제거 */
}
#starForm fieldset legend {
  text-align: left;
}
#starForm input[type="radio"]:checked ~ label {
  opacity: 1; /* text 투명도 100% */
  color: black;
}
/***************** 별점 end ******************/
</style>