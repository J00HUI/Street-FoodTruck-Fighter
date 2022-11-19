<template>
  <div style="margin-top: 10%">
    <span
      style="
        font-family: SCoreDream;
        font-style: normal;
        font-size: 105%;
        margin-left: 5%;
      "
      >우리동네 푸드트럭</span
    >
  </div>

  <router-link to="/find" style="text-decoration: none">
    <div style="margin-top: 5%; text-align: center">
      <input
        type="button"
        value="전체 메뉴 보기"
        class="loginButton"
        style="cursor: pointer"
        @click="menuSelect(0)"
      />
    </div>
  </router-link>

  <router-link to="/find" style="text-decoration: none">
    <img
      src="@/assets/hamburger.svg"
      class="firstContent"
      style="margin-top: 5%"
      @click="menuSelect(1)"
      alt
  /></router-link>

  <router-link to="/find" style="text-decoration: none"
    ><img
      src="@/assets/hotdog.svg"
      class="middleContent"
      @click="menuSelect(2)"
      alt
  /></router-link>

  <router-link to="/find" style="text-decoration: none"
    ><img
      src="@/assets/coffee.svg"
      class="middleContent"
      @click="menuSelect(3)"
      alt
  /></router-link>

  <router-link to="/find" style="text-decoration: none"
    ><img
      src="@/assets/sweetpotato.svg"
      class="middleContent"
      @click="menuSelect(4)"
      alt
  /></router-link>

  <router-link to="/find" style="text-decoration: none"
    ><img
      src="@/assets/icecream.svg"
      class="firstContent"
      @click="menuSelect(5)"
      alt
  /></router-link>

  <router-link to="/find" style="text-decoration: none"
    ><img
      src="@/assets/waffle.svg"
      class="middleContent"
      @click="menuSelect(6)"
      alt
  /></router-link>

  <router-link to="/find" style="text-decoration: none"
    ><img
      src="@/assets/steak.svg"
      class="middleContent"
      @click="menuSelect(7)"
      alt
  /></router-link>

  <router-link to="/find" style="text-decoration: none"
    ><img
      src="@/assets/sandwich.svg"
      class="middleContent"
      @click="menuSelect(8)"
      alt
  /></router-link>

  <router-link to="/find" style="text-decoration: none"
    ><img
      src="@/assets/skeweredfood.svg"
      class="firstContent"
      @click="menuSelect(9)"
      alt
  /></router-link>

  <router-link to="/find" style="text-decoration: none"
    ><img
      src="@/assets/ramen.svg"
      class="middleContent"
      @click="menuSelect(10)"
      alt
  /></router-link>

  <router-link to="/find" style="text-decoration: none"
    ><img
      src="@/assets/pizza.svg"
      class="middleContent"
      @click="menuSelect(11)"
      alt
  /></router-link>

  <router-link to="/find" style="text-decoration: none">
    <img
      src="@/assets/drink.svg"
      class="middleContent"
      @click="menuSelect(12)"
      alt
    />
  </router-link>
</template>
    
    <script>
export default {
  setup() {
    function menuSelect(menu) {
      if (menu == 0) {
        sessionStorage.setItem("selectedMenu", "전체");
      } else if (menu == 1) {
        sessionStorage.setItem("selectedMenu", "햄버거");
      } else if (menu == 2) {
        sessionStorage.setItem("selectedMenu", "핫도그");
      } else if (menu == 3) {
        sessionStorage.setItem("selectedMenu", "카페");
      } else if (menu == 4) {
        sessionStorage.setItem("selectedMenu", "고구마");
      } else if (menu == 5) {
        sessionStorage.setItem("selectedMenu", "아이스크림");
      } else if (menu == 6) {
        sessionStorage.setItem("selectedMenu", "와플");
      } else if (menu == 7) {
        sessionStorage.setItem("selectedMenu", "스테이크");
      } else if (menu == 8) {
        sessionStorage.setItem("selectedMenu", "샌드위치");
      } else if (menu == 9) {
        sessionStorage.setItem("selectedMenu", "꼬치");
      } else if (menu == 10) {
        sessionStorage.setItem("selectedMenu", "라면");
      } else if (menu == 11) {
        sessionStorage.setItem("selectedMenu", "피자");
      } else if (menu == 12) {
        sessionStorage.setItem("selectedMenu", "음료수");
      }
    }

    return {
      menuSelect,
    };
  },
};
</script>
  
<style scoped>
.loginButton {
  width: 21rem;
  height: 4.5rem;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 1rem;
  color: white;

  background: var(--color-orange-1);
  border-radius: 1rem;
  border: 1px solid #d8e2ff;
  outline-style: none;
}
.firstContent {
  width: 20%;
  margin-left: 7.5%;
}
.middleContent {
  width: 20%;
  margin-left: 1.75%;
}
</style>
    