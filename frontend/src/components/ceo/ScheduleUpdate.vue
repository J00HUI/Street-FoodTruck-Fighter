<template>
  <label for="truck-name" class="truckInput inputText">
    <img src="@/assets/ceo/ScheduleCalendarIcon.svg" alt />
    <span style="width: 70%">
      <button @click="yesterday">
        <img src="@/assets/ceo/nav2Back.svg" alt />
      </button>
      <input
        class="scheduleDateInput"
        disabled
        type="text"
        v-model="
          scheduleStore.scheduleDateDtoList[
            scheduleStore.scheduleTypeData.dateIdx
          ].workingDay
        "
      />
      <button @click="tomorrow">
        <img
          style="transform: rotate(180deg)"
          src="@/assets/ceo/nav2Back.svg"
          alt
        />
      </button>
    </span>
  </label>
  <label for="schedule-title" class="truckInput inputText">
    <img style="width: 1.3rem" src="@/assets/ceo/scheduleTitleIcon.svg" alt />
    <input
      id="schedule-title"
      type="text"
      v-model="scheduleStore.scheduleAddForm.title"
      @focus="inputType"
      style="width: 60%"
      placeholder=""
    />
  </label>
  <label for="schedule-operating" class="truckInput inputText">
    <div class="timeInputBox">
      <span class="timePlaceHoleder">open</span>
      <input
        id="schedule-operating"
        title="open"
        type="time"
        class="time"
        @change="setStartTime"
        v-model="
          scheduleStore.scheduleDateDtoList[
            scheduleStore.scheduleTypeData.dateIdx
          ].startTime
        "
      />
    </div>
    ~
    <div class="timeInputBox">
      <span class="timePlaceHoleder">close</span>
      <input
        style="padding-right: 1rem"
        type="time"
        class="time"
        @change="setEndTime"
        v-model="
          scheduleStore.scheduleDateDtoList[
            scheduleStore.scheduleTypeData.dateIdx
          ].endTime
        "
      />
    </div>
  </label>

  <div id="truck-name" class="truckInput inputText">
    <img :src="kakaoStore.searchTypeData.iconType" alt />
    <input
      type="text"
      v-model="scheduleStore.scheduleAddForm.address"
      @focus="inputType"
      style="width: 60%"
      placeholder="위치"
    />
  </div>

  <kakaoMap class="truckInput inputMap"></kakaoMap>
  <div class="scheduleButtons">
    <button type="button" @click="addSchedule" class="updateButton">
      등록
    </button>
    <button @click="deleteSchedule" class="updateButton">삭제</button>
  </div>
</template>

<script>
import kakaoMap from "@/components/ceo/ScheduleKakaoMap.vue";
import { useCeoScheduleStore } from "@/stores/ceo/schedule";
import { useKakaoStore } from "@/stores/kakao";
import router from "@/router";
export default {
  components: {
    kakaoMap,
  },
  setup() {
    const scheduleStore = useCeoScheduleStore();
    const kakaoStore = useKakaoStore();
    if (scheduleStore.scheduleDateDtoList.length === 1) {
      router.push("/schedule");
    }

    kakaoStore.searchTypeData.viewType = "schedule";
    function yesterday() {
      if (scheduleStore.scheduleTypeData.dateIdx > 0) {
        scheduleStore.scheduleTypeData.dateIdx =
          scheduleStore.scheduleTypeData.dateIdx - 1;
      }
    }
    function tomorrow() {
      if (
        scheduleStore.scheduleTypeData.dateIdx <
        scheduleStore.scheduleDateDtoList.length - 1
      ) {
        scheduleStore.scheduleTypeData.dateIdx =
          scheduleStore.scheduleTypeData.dateIdx + 1;
      }
    }
    function inputType() {
      kakaoStore.searchTypeData.searchType = "input";
    }
    function setStartTime(e) {
      scheduleStore.scheduleTypeData.dateIdx;
      if (scheduleStore.scheduleTypeData.dateIdx === 0) {
        scheduleStore.scheduleDateDtoList.forEach(
          (item) => (item.startTime = e.target.value)
        );
      }
    }
    function setEndTime(e) {
      scheduleStore.scheduleTypeData.dateIdx;
      if (scheduleStore.scheduleTypeData.dateIdx === 0) {
        scheduleStore.scheduleDateDtoList.forEach(
          (item) => (item.endTime = e.target.value)
        );
      }
    }
    let scheduleId = null;
    function addSchedule() {
      scheduleId = scheduleStore.scheduleAddForm.scheduleId;
      if (scheduleId !== null && scheduleId !== undefined) {
        scheduleStore.deleteSchedule(scheduleId);
      }

      scheduleStore.setSchedule();
    }
    function deleteSchedule() {
      scheduleId = scheduleStore.scheduleAddForm.scheduleId;
      if (scheduleId !== null && scheduleId !== undefined) {
        scheduleStore.deleteSchedule(scheduleId);
      } else {
        router.push("/schedule");
      }
    }

    return {
      scheduleStore,
      kakaoStore,
      yesterday,
      tomorrow,
      setStartTime,
      setEndTime,
      inputType,
      addSchedule,
      deleteSchedule,
    };
  },
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
  width: 44%;

  margin: 6%;
  margin-top: 2%;
  height: 2rem;
  color: white;
  background-color: var(--color-purple-2);
  filter: drop-shadow(0px 10px 22px rgba(149, 173, 254, 0.3));
}
.timeInputBox {
  width: 90%;
  display: flex;
  flex-direction: column;
}
.scheduleAddress {
  word-break: keep-all;
  width: 70%;
}
.scheduleDateInput {
  text-align: center;
  width: 50%;
  padding: 0;
  vertical-align: sub;
}
.scheduleButtons {
  width: 100%;
  height: 2rem;
  display: flex;
}
.time {
background-color: none;
}
</style>