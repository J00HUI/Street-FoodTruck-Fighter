<template>
  <label for="my-truck-img" class="truckInput inputImg">
    <input @change="set_img" id="my-truck-img" type="file" accept=".gif, .jpg, .png" />
    <img class="truckImg imgVisible" src alt />
    <img class="addIcon" src="@/assets/ceo/myAddImgIcon.svg" alt="추가" />
  </label>
  <label for="truck-name" class="truckInput inputText">
    <img src="@/assets/ceo/myTruckNameIcon.svg" alt />
    <input id="truck-name" placeholder="상호명" v-model="myStore.myData.truckName" type="text" />
  </label>
  <label for="truck-call-number" class="truckInput inputText">
    <img src="@/assets/ceo/myCallIcon.svg" alt />
    <input id="truck-call-number" placeholder="전화번호" v-model="myStore.myData.callNumber" type="tel" />
  </label>
  <div id="ceo-default-address" class="truckInput inputText">
    <img src="@/assets/ceo/myEmptyMarkerIcon.svg" alt />
    <img v-if="kakaoStore.searchTypeData.iconType === true" src="@/assets/ceo/addressIcon.svg" alt />
    <img
      v-if="kakaoStore.searchTypeData.iconType === false"
      src="@/assets/ceo/addressXIcon.svg"
      alt
    />
    <input
      type="text"
      v-model="myStore.positionData.address"
      @focus="inputType"
      style="padding:0px"
      placeholder="위치"
    />
    <a href="#ceo-schedule-map">
      <img @click="toggleMap" src="@/assets/ceo/myMarkerIcon.svg" alt />
    </a>
  </div>
  <div class="ceoDefaultMap">
    <defaultKakaoMap v-if="toggle.isMap"></defaultKakaoMap>
  </div>
  <label for="truck-operating" class="truckInput inputText">
    <div class="timeInputBox">
      <span class="timePlaceHoleder">open</span>
      <input id="truck-operating" title="open" v-model="myStore.myData.openTime" type="time" />
    </div>~
    <div class="timeInputBox">
      <span class="timePlaceHoleder">close</span>
      <input style="padding-right:1rem" v-model="myStore.myData.closeTime" type="time" />
    </div>
  </label>
  <button type="button" @click="modalToggle">메뉴추가</button>
  <myMenu v-if="myStore.myTypeData.modalView"></myMenu>
  <button type="button" @click="myUpdate" class="updateButton">수정</button>
</template>

<script>
import { ref } from "vue";
import { useKakaoStore } from "@/stores/kakao";
import { useCeoMyStore } from "@/stores/ceo/my.js";
import defaultKakaoMap from "@/components/ceo/ScheduleKakaoMap.vue";
import myMenu from "@/components/ceo/MyMenuModal.vue";
export default {
  components: { defaultKakaoMap, myMenu },
  setup() {
    const myStore = useCeoMyStore();
    const kakaoStore = useKakaoStore();
    myStore.getImg()
    kakaoStore.searchTypeData.viewType = "my";
    const toggle = ref({
      isMap: false
    });
    function toggleMap() {
      toggle.value.isMap = !toggle.value.isMap;
    }
    function set_img(e) {
      if (myStore.createImgUrl !== null) {
        URL.revokeObjectURL(myStore.createImgUrl);
      }
      myStore.myData.truckImg = e.target.files[0];
      myStore.createImgUrl = URL.createObjectURL(e.target.files[0]);
      e.target.nextElementSibling.src = myStore.createImgUrl;
      e.target.nextElementSibling.classList.remove("imgVisible");
      e.target.nextElementSibling.nextElementSibling.classList.add(
        "imgVisible"
      );
    }
    function myUpdate() {
      myStore.setImg()
      console.log(myStore.myData);
    }
    function inputType() {
      kakaoStore.searchTypeData.searchType = "input";
    }
    function modalToggle() {
      myStore.myTypeData.modalView = !myStore.myTypeData.modalView;
    }
    return {
      myStore,
      kakaoStore,
      toggle,
      toggleMap,
      set_img,
      myUpdate,
      inputType,
      modalToggle
    };
  }
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
  width: 80%;
  margin-left: 10%;
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
</style>