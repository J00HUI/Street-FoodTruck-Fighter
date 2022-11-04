<template>
  <img
    src="@/assets/xicon.svg"
    style="width: 5%; margin-top: 15%; margin-left: 5%"
    alt
  />

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
    <img
      src="@/assets/hamburger.svg"
      style="width: 20%; margin-left: 7.5%; margin-top: 13%"
      class="hamburger"
      @click="menuSelect()"
      alt
    />
    <img src="@/assets/hotdog.svg" style="width: 20%; margin-left: 1.75%" alt />
    <img src="@/assets/coffee.svg" style="width: 20%; margin-left: 1.75%" alt />
    <img
      src="@/assets/sweetpotato.svg"
      style="width: 20%; margin-left: 1.75%"
      alt
    />
    <img
      src="@/assets/icecream.svg"
      style="width: 20%; margin-left: 7.5%"
      alt
    />
    <img src="@/assets/waffle.svg" style="width: 20%; margin-left: 1.75%" alt />
    <img src="@/assets/steak.svg" style="width: 20%; margin-left: 1.75%" alt />
    <img
      src="@/assets/sandwich.svg"
      style="width: 20%; margin-left: 1.75%"
      alt
    />
    <img
      src="@/assets/skeweredfood.svg"
      style="width: 20%; margin-left: 7.5%"
      alt
    />
    <img src="@/assets/ramen.svg" style="width: 20%; margin-left: 1.75%" alt />
    <img src="@/assets/pizza.svg" style="width: 20%; margin-left: 1.75%" alt />
    <img src="@/assets/drink.svg" style="width: 20%; margin-left: 1.75%" alt />

    <div style="margin-top: 10%; text-align: center">
      <label for="directinput"></label>
      <input
        type="text"
        id="directinput"
        name="directinput"
        class="directinput"
        placeholder="직접 입력"
        style="text-align: center"
      />
    </div>
  </div>

  <div style="text-align: center">
    <input type="button" value="제출" class="SubmitButton" />
  </div>
</template>
        
<script>
export default {
  data() {
    return {
      // ceoSignUp: true,
      // customerSignUp: false,
    };
  },
  methods: {
    menuSelect() {
      let selected = document.querySelector(".hamburger");
      selected.classList.toggle("selected");
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
.directinput {
  width: 18rem;
  height: 3.7rem;

  background: white;
  border-radius: 1rem;

  border: none;
  outline-style: none;
}
input.directinput::-webkit-input-placeholder {
  background-image: url(@/assets/pen.svg);
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
.selected {
  /* border: 0.2rem solid #ffe060; */
  border: 0.2rem solid var(--color-orange-1);
  border-radius: 3rem;
  opacity: 70%;
  box-sizing: border-box;
}
</style>