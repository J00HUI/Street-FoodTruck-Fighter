<template>
  <label for="truck-name" class="truckInput inputText">
    <img src="@/assets/ceo/ScheduleCalendarIcon.svg" alt />
    <div>
      <button @click="yesterday">
        <img src="@/assets/ceo/nav2Back.svg" alt />
      </button>
      <input
        class="scheduleDateInput"
        max="3000-01-01"
        type="date"
        v-model="kakaoStore.scheduleData.date"
      />
      <!-- <time style="padding: 0 4%" v-model="kakaoStore.scheduleData.date">{{}}</time> -->
      <button>
        <img style="transform:rotate(180deg);" src="@/assets/ceo/nav2Back.svg" alt />
      </button>
    </div>

    <!-- <input id="truck-name" placeholder="상호명" type="text" /> -->
  </label>
  <label for="schedule-operating" class="truckInput inputText">
    <div class="timeInputBox">
      <span class="timePlaceHoleder">open</span>
      <input
        id="schedule-operating"
        v-model="kakaoStore.scheduleData.openTime"
        title="open"
        type="time"
      />
    </div>~
    <div class="timeInputBox">
      <span class="timePlaceHoleder">close</span>
      <input style="padding-right:1rem" v-model="kakaoStore.scheduleData.closeTime" type="time" />
    </div>
  </label>

  <div id="truck-name" class="truckInput inputText">
    <img src="@/assets/ceo/myMarkerIcon.svg" alt />
    <img v-if="kakaoStore.searchTypeData.iconType === true" src="@/assets/ceo/addressIcon.svg" alt />
    <img
      v-if="kakaoStore.searchTypeData.iconType === false"
      src="@/assets/ceo/addressXIcon.svg"
      alt
    />
    <input
      type="text"
      v-model="kakaoStore.scheduleData.address"
      @focus="inputType"
      style="padding:0px"
      placeholder="위치"
    />
  </div>

  <kakaoMap class="truckInput inputMap"></kakaoMap>
  <button type="button" @click="scheduleUpdate" class="updateButton">수정</button>
</template>

<script>
import kakaoMap from "@/components/ceo/ScheduleKakaoMap.vue";
import { useKakaoStore } from "@/stores/kakao.js";
export default {
  components: {
    kakaoMap
  },
  setup() {
    const kakaoStore = useKakaoStore();
    kakaoStore.searchTypeData.viewType = "schedule";
    function yesterday() {
      console.log();
      // kakaoStore.scheduleData.date = kakaoStore.scheduleData.date - 1
    }
    function scheduleUpdate() {
      console.log(kakaoStore.scheduleData);
    }
    function inputType() {
      kakaoStore.searchTypeData.searchType = "input";
    }
    return {
      kakaoStore,
      yesterday,
      scheduleUpdate,
      inputType,
    };
  }
};
</script>

<style scoped>
img {
  vertical-align: middle;
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
button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: none;
}
button:hover {
  cursor: pointer;
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
.inputText {
  height: 8%;
}
.inputMap {
  height: 40%;
}

input[type="time"] {
  padding: 0;
  flex-direction: row-reverse;
}
.timePlaceHoleder {
  padding: 0 0.7rem;
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
.timeInputBox {
  display: flex;
  flex-direction: column;
}
.scheduleAddress {
  word-break: keep-all;
  width: 70%;
}
.scheduleDateInput {
  padding: 0;
  vertical-align: sub;
}
</style>