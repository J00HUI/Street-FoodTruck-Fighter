<template>
  <label for="my-truck-img" class="truckInput inputImg">
    <input
      @change="set_img"
      id="my-truck-img"
      type="file"
      accept=".gif, .jpg, .png"
    />
    <img class="truckImg imgVisible" src alt />
    <img class="addIcon" src="@/assets/ceo/myAddImgIcon.svg" alt="추가" />
  </label>
  <label for="truck-name" class="truckInput inputText">
    <img src="@/assets/ceo/myTruckNameIcon.svg" alt />
    <input
      id="truck-name"
      placeholder="상호명"
      v-model="myStore.myData.name"
      type="text"
    />
  </label>
  <label for="truck-call-number" class="truckInput inputText">
    <img src="@/assets/ceo/myCallIcon.svg" alt />
    <input
      id="truck-call-number"
      placeholder="전화번호"
      v-model="myStore.myData.phone"
      type="tel"
    />
  </label>

  <div class="ceoDefaultMap">
    <defaultKakaoMap v-if="toggle.isMap"></defaultKakaoMap>
  </div>
  <div class="truckInput inputText">
    <img
      style="width: 1.5rem"
      :src="categoryList[myStore.myTypeData.myCategoryIndex][0]"
      alt
    />
    <div
      @click="changeCategory"
      class="categoryInput"
      id="truck-category"
      type="text"
    >
      {{ categoryList[myStore.myTypeData.myCategoryIndex][1] }}
    </div>
    <button class="addMenuButton" type="button" @click="modalToggle">
      +메뉴
    </button>
  </div>
  <label class="truckInput inputTextarea" for="my-truck-describe">
    <div class="descriptionIcon">
      <img class="descriptionIcon2" src="@/assets/ceo/myDescription.svg" alt />
    </div>

    <textarea
      v-model="myStore.myData.description"
      id="my-truck-describe"
      class="description"
      placeholder="설명을 입력해주세요"
    ></textarea>
  </label>

  <myMenu v-if="myStore.myTypeData.modalView"></myMenu>
  <button type="button" @click="myUpdate" class="updateButton">수정</button>
</template>

<script>
import { ref } from "vue";
import { useKakaoStore } from "@/stores/kakao";
import { useCeoMyStore } from "@/stores/ceo/my.js";
import defaultKakaoMap from "@/components/ceo/ScheduleKakaoMap.vue";
import myMenu from "@/components/ceo/MyMenuModal.vue";
import categoryIcon from "@/assets/ceo/categoryIcon.svg";
import coffee from "@/assets/ceo/myIcon/coffee.svg";
import drink from "@/assets/ceo/myIcon/drink.svg";
import hamburger from "@/assets/ceo/myIcon/hamburger.svg";
import icecream from "@/assets/ceo/myIcon/icecream.svg";
import hotdog from "@/assets/ceo/myIcon/hotdog.svg";
import pizza from "@/assets/ceo/myIcon/pizza.svg";
import ramen from "@/assets/ceo/myIcon/ramen.svg";
import sandwich from "@/assets/ceo/myIcon/sandwich.svg";
import skeweredfood from "@/assets/ceo/myIcon/skeweredfood.svg";
import steak from "@/assets/ceo/myIcon/steak.svg";
import sweetpotato from "@/assets/ceo/myIcon/sweetpotato.svg";
import waffle from "@/assets/ceo/myIcon/waffle.svg";

export default {
  components: { defaultKakaoMap, myMenu },
  setup() {
    const myStore = useCeoMyStore();
    const kakaoStore = useKakaoStore();
    myStore.getMyFoodTruck();
    myStore.getImg();
    const categoryList = [
      [categoryIcon, "카테고리"],
      [coffee, "카페"],
      [drink, "음료"],
      [hamburger, "햄버거"],
      [hotdog, "핫도그"],
      [icecream, "아이스크림"],
      [pizza, "피자"],
      [ramen, "라면"],
      [sandwich, "샌드위치"],
      [skeweredfood, "꼬지"],
      [steak, "스테이크"],
      [sweetpotato, "군고구마"],
      [waffle, "와플"],
    ];
    console.log(myStore.myTypeData.myCategoryIndex);
    // myStore.getImg();
    kakaoStore.searchTypeData.viewType = "my";
    const toggle = ref({
      isMap: false,
    });
    function toggleMap() {
      toggle.value.isMap = !toggle.value.isMap;
    }
    function set_img(e) {
      if (myStore.createImgUrl !== null) {
        URL.revokeObjectURL(myStore.createImgUrl);
      }
      myStore.myTypeData.truckImg = e.target.files[0];
      myStore.createImgUrl = URL.createObjectURL(e.target.files[0]);
      e.target.nextElementSibling.src = myStore.createImgUrl;
      e.target.nextElementSibling.classList.remove("imgVisible");
      e.target.nextElementSibling.nextElementSibling.classList.add(
        "imgVisible"
      );
    }
    function myUpdate() {
      if (myStore.myTypeData.is_update) {
        myStore.updateFoodTruck();
      } else {
        myStore.setFoodTruck();
      }
    }

    function modalToggle() {
      myStore.myTypeData.modalView = !myStore.myTypeData.modalView;
    }
    function changeCategory() {
      myStore.myTypeData.myCategoryIndex =
        (myStore.myTypeData.myCategoryIndex + 1) % categoryList.length;
      myStore.myData.category =
        categoryList[myStore.myTypeData.myCategoryIndex][1];
    }
    return {
      myStore,
      kakaoStore,
      categoryList,
      toggle,
      toggleMap,
      set_img,
      myUpdate,
      modalToggle,
      changeCategory,
    };
  },
};
</script>

<style scoped>
input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}
input {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border: none;
  outline-style: none;
  padding: 0 1rem;
  font-size: 1rem;
  background-color: var(--color-gray-1);
  font: 1.1rem "SCoreDream";
}
input[type="time"] {
  padding: 0;
  flex-direction: row-reverse;
}
button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: none;
}
textarea {
  resize: none;
  overflow-y: scroll;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  border: none;
  outline: none;
  font: 1rem "SCoreDream";
}
textarea::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
.truckInput {
  display: flex;
  position: relative;
  justify-content: space-evenly;
  align-items: center;
  border-radius: 1rem;
  background-color: var(--color-gray-1);
  width: 88%;
  margin: 6%;
}
.imgVisible {
  visibility: hidden;
}

.truckImg {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0%;
  border-radius: 1rem;
  background-size: contain;
  background-repeat: no-repeat;
  z-index: 100;
}
.addIcon {
  opacity: 30%;
}
label:hover {
  cursor: pointer;
}
.inputImg {
  height: 32%;
}
.inputText {
  height: 8%;
}
.inputTextarea {
  position: relative;
  height: 20%;
}
.timePlaceHoleder {
  padding: 0 0.7rem;
}
.timeInputBox {
  display: flex;
  flex-direction: column;
}
.updateButton {
  font: 1rem "SCoreDream";
  border-radius: 2rem;
  width: 88%;
  margin-left: 6%;
  height: 6%;
  color: white;
  background-color: var(--color-purple-2);
  filter: drop-shadow(0px 10px 22px rgba(149, 173, 254, 0.3));
}
.updateButton:hover {
  cursor: pointer;
}
.ceoDefaultMap {
  position: relative;
  width: 88%;
  margin: 6%;
  border-radius: 1rem;
}
*::placeholder {
  color: black;
}
.categoryInput {
  font: 1rem "SCoreDream";
  padding: 0 1.5rem 0 1.5rem;
  width: 35%;
}
.descriptionIcon {
  position: absolute;
  height: 1.5rem;
  top: 1.1rem;
  left: 1.5rem;
  display: block;
}

.description {
  position: absolute;
  top: 3rem;
  left: 1.5rem;
  width: calc(94% - 2rem);
  margin: auto;
  height: calc(76% - 2rem);
}
.descriptionIcon2 {
  vertical-align: bottom;
  width: 1.5rem;
}
.addMenuButton {
  font: 0.75rem "SCoreDream";
}
</style>