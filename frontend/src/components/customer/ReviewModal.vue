<template>
  <div class="modal-wrapper">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="cardContainer">
          <div class="card">
            <form name="starForm" id="starForm">
              <fieldset>
                <input type="radio" name="rating" value="5" id="rate1" /><label
                  for="rate1"
                  >⭐</label
                >
                <input type="radio" name="rating" value="4" id="rate2" /><label
                  for="rate2"
                  >⭐</label
                >
                <input type="radio" name="rating" value="3" id="rate3" /><label
                  for="rate3"
                  >⭐</label
                >
                <input type="radio" name="rating" value="2" id="rate4" /><label
                  for="rate4"
                  >⭐</label
                >
                <input type="radio" name="rating" value="1" id="rate5" /><label
                  for="rate5"
                  >⭐</label
                >
              </fieldset>
            </form>
            <textarea
              class="review"
              placeholder="리뷰를 입력해 주세요."
            ></textarea>
            <!-- <div class="picture" placeholder="사진을 등록해 주세요.">

                    </div> -->

            <label for="my-truck-img" class="truckInput inputImg">
              <input
                @change="set_img"
                id="my-truck-img"
                type="file"
                accept=".gif, .jpg, .png"
              />
              <img class="truckImg imgVisible" src alt />
              <img
                class="addIcon"
                src="@/assets/ceo/myAddImgIcon.svg"
                alt="추가"
              />
            </label>

            <div class="buttons">
              <button class="button" id="regist" @click="registerReview">등록</button>
              <button class="button" id="close" @click="onClose">취소</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useCeoMyStore } from "@/stores/ceo/my.js";
export default {
  setup(props, { emit }) {
    const myStore = useCeoMyStore();
    const onClose = () => {
      emit("close");
    };
    function set_img(e) {
      myStore.myData.truckImg = e.target.files[0];
      let ImgUrl = URL.createObjectURL(e.target.files[0]);
      e.target.nextElementSibling.src = ImgUrl;
      e.target.nextElementSibling.classList.remove("imgVisible");
      e.target.nextElementSibling.nextElementSibling.classList.add(
        "imgVisible"
      );
    }
    function registerReview(){

    }

    return {
      myStore,
      onClose,
      set_img,
      registerReview,
    };
  },
};
</script>

<style scoped>
/***************** modal style start ******************/
.modal-wrapper {
  position: fixed;
  z-index: 100;
  width: 100%;
  height: 100%;
  overflow: hidden;
  
  background-color: rgba(0, 0, 0, 0.5);
}
.modal-dialog {
  display: fixed;
  margin-top: 40%;
  height: 20rem;
  width: 100%;
}
/***************** modal style end ******************/
/***************** card style start ******************/
.cardContainer {
  height: 30rem;
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  margin-bottom: 1.5rem;
}
.card {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 21.5rem;
  height: 100%;

  background-color: white;
  border: 0.1rem solid lightgray;
  border-radius: 1rem;
  box-shadow: 2px 3px 15px -6px gray;
}
/***************** card style end ******************/

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

/***************** review style start ******************/
.review {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 18.2rem;
  height: 20%;
  padding-top: 0.8rem;
  padding-left: 0.5rem;
  margin-top: 0.2rem;

  background-color: #fff5dc;
  border: 0;
  border-radius: 1rem;

  overflow: hidden;
  resize: none;
}

/* add Img start */
.truckInput {
  display: flex;
  position: relative;
  justify-content: space-evenly;
  align-items: center;
  border-radius: 1rem;
  background-color: #fff5dc;
  width: 19rem;
  height: 40%;
  margin: 4%;
}
.inputImg {
  height: 32%;
}
input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}
.addIcon {
  position: absolute;
  opacity: 30%;
  height: 50%;
  width: 50%;
}
/* add Img end */

.button {
  height: 3rem;
  width: 6rem;
  border-radius: 1.5rem;
  border: 0;
  margin-top: 1rem;
  font-size: 20px;
  color: white;
  font-weight: bold;
}
#regist {
  margin-right: 0.75rem;
  background-color: #ffdd00;
}
#close {
  background-color: #f78888;
}
/***************** review style end ******************/
</style>