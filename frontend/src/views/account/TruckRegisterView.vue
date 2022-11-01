<template>
  <div style="margin-top: 9%">
    <img src="@/assets/arrow.svg" style="width: 5%; margin-left: 5%" alt />
    <span
      style="
        font-family: SCoreDream;
        font-style: normal;
        font-weight: 500;
        font-size: 200%;
        margin-left: 24%;
      "
      >회원가입</span
    >
  </div>

  <div style="position: relative; margin-top: 7%">
    <nav style="display: flex; height: 8%">
      <div class="salesNav">
        <span class="underLine">사장님</span>
      </div>
      <div class="salesNav salesNavR">
        <span>고객님</span>
      </div>
    </nav>
  </div>

  <div
    style="
      font-family: SCoreDream;
      font-style: normal;
      font-weight: 500;
      font-size: 120%;
      margin-top: 7%;
      text-align: center;
    "
  >
    내 푸드트럭 등록
  </div>

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
      v-model="myStore.myData.truckName"
      type="text"
    />
  </label>
  <label for="truck-call-number" class="truckInput inputText">
    <img src="@/assets/ceo/myCallIcon.svg" alt />
    <input
      id="truck-call-number"
      placeholder="전화번호"
      v-model="myStore.myData.callNumber"
      type="tel"
    />
  </label>
  <div class="truckInput inputText">
    <img src="@/assets/ceo/myEmptyMarkerIcon.svg" alt />
    <input type="text" placeholder="위치" disabled />
    <img class="markerIcon" src="@/assets/ceo/myMarkerIcon.svg" alt />
  </div>
  <label for="truck-operating" class="truckInput inputText">
    <div class="timeInputBox">
      <span class="timePlaceHoleder">open</span>
      <input
        id="truck-operating"
        title="open"
        v-model="myStore.myData.openTime"
        type="time"
      />
    </div>
    ~
    <div class="timeInputBox">
      <span class="timePlaceHoleder">close</span>
      <input
        style="padding-right: 1rem"
        v-model="myStore.myData.closeTime"
        type="time"
      />
    </div>
  </label>
  <div style="margin-top: 3%; text-align: center;">
    <label for="nickname"></label>
    <input
      type="text"
      id="nickname"
      name="nickname"
      class="nickname"
      placeholder="카테고리"
      style="text-align: center"
    />
  </div>
  <div style="text-align: center; margin-top: 10%">
    <button type="button" @click="myUpdate" class="nextButton">수정</button>
  </div>
</template>

<script>
import { useCeoMyStore } from "@/stores/ceo/my.js";
export default {
  setup() {
    const myStore = useCeoMyStore();
    function set_img(e) {
      myStore.myData.truckImg = e.target.files[0];
      let ImgUrl = URL.createObjectURL(e.target.files[0]);
      e.target.nextElementSibling.src = ImgUrl;
      e.target.nextElementSibling.classList.remove("imgVisible");
      e.target.nextElementSibling.nextElementSibling.classList.add(
        "imgVisible"
      );
    }
    function myUpdate() {
      console.log(myStore.myData);
    }
    return {
      myStore,
      set_img,
      myUpdate,
    };
  },
};
</script>

<style scoped>
.email,
.pwd,
.pwdcheck,
.nickname {
  width: 20rem;
  height: 4rem;

  background: #f7f8f8;
  border-radius: 1rem;

  border: none;
  outline-style: none;
}
.phonenum,
.numcheck,
.ceonumcheck {
  width: 14.5rem;
  height: 4rem;

  background: #f7f8f8;
  border-radius: 1rem;

  border: none;
  outline-style: none;
}
input.email::-webkit-input-placeholder {
  background-image: url(@/assets/email.svg);
  background-size: contain;
  /* background-position: 7.2rem center; */
  background-position: 1.5rem;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
input.pwd::-webkit-input-placeholder {
  background-image: url(@/assets/password.svg);
  background-size: contain;
  /* background-position: 7rem center; */
  background-position: 1.5rem;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
input.pwdcheck::-webkit-input-placeholder {
  background-image: url(@/assets/password.svg);
  background-size: contain;
  /* background-position: 6rem center; */
  background-position: 1.5rem;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
input.nickname::-webkit-input-placeholder {
  background-image: url(@/assets/human.svg);
  background-size: contain;
  /* background-position: 6rem center; */
  background-position: 1.5rem;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
input.phonenum::-webkit-input-placeholder {
  background-image: url(@/assets/password.svg);
  background-size: contain;
  /* background-position: 2rem center; */
  background-position: 1.62rem;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
input.numcheck::-webkit-input-placeholder {
  background-image: url(@/assets/password.svg);
  background-size: contain;
  /* background-position: 2rem center; */
  background-position: 1.62rem;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
input.ceonumcheck::-webkit-input-placeholder {
  background-image: url(@/assets/password.svg);
  background-size: contain;
  /* background-position: 2rem center; */
  background-position: 1.62rem;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
.checkButton {
  width: 5rem;
  height: 3.7rem;
  margin-left: 2%;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  background: #fdeca6;
  border-radius: 1.3rem;
  border: none;
  outline-style: none;
}
.nextButton {
  width: 15rem;
  height: 4rem;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 600;
  font-size: 1rem;

  filter: drop-shadow(0rem 0.625rem 1.3rem rgba(150, 170, 250, 0.3));

  background: #ffe060;
  border-radius: 2rem;
  border: none;
  outline-style: none;
}
.salesNav {
  display: flex;
  justify-content: center;
  align-items: center;
  font: 1rem "SCoreDream";
  font-weight: 600;
  width: 50%;
  color: #c8c8c8;
  padding: auto;
  border: 0.001rem solid #c8c8c8;
  border-top: none;
  border-left: none;
  border-bottom: none;
}
.salesNavR {
  border-top: none;
  border-right: none;
  border-bottom: none;
}
.underLine {
  border-bottom: 0.1rem solid black;
  color: black;
}

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
.markerIcon {
  position: absolute;
  right: 1rem;
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
</style>