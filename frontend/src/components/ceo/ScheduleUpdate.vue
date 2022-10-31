<template>
  <label for="truck-name" class="truckInput inputText">
    <img src="@/assets/ceo/ScheduleCalendarIcon.svg" alt />

    <button>
      <img src="@/assets/ceo/nav2Back.svg" alt />
    </button>
    <time style="padding: 0 4%">asdfasd/</time>
    <button>
      <img style="transform:rotate(180deg);" src="@/assets/ceo/nav2Back.svg" alt />
    </button>

    <!-- <input id="truck-name" placeholder="상호명" type="text" /> -->
  </label>
  <label for="truck-operating" class="truckInput inputText">
    <div class="timeInputBox">
      <span class="timePlaceHoleder">open</span>
      <input id="truck-operating" title="open" type="time" />
    </div>~
    <div class="timeInputBox">
      <span class="timePlaceHoleder">close</span>
      <input style="padding-right:1rem" type="time" />
    </div>
  </label>
  <label for="truck-name" class="truckInput inputText">
    <img src="@/assets/ceo/myMarkerIcon.svg" alt />
    <input id="truck-name" placeholder="주소" type="text" />
  </label>
  <KakaoMap class="truckInput inputMap"></KakaoMap>
  <button type="button" @click="myUpdate" class="updateButton">수정</button>
</template>

<script>
import KakaoMap from "@/components/ceo/ScheduleKakaoMap.vue";
export default {
  components: {
    KakaoMap
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
</style>