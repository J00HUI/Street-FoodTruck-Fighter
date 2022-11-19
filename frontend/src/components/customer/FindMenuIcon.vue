<template>
  <div class="MenuScroll">
    <label
      ><input type="radio" name="menu" value="전체" />
      <img
        src="@/assets/allmenu.svg"
        class="menuIcon"
        @click="menuSelect(0)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="햄버거" />
      <img
        src="@/assets/hamburger.svg"
        class="menuIcon"
        @click="menuSelect(1)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="핫도그" />
      <img
        src="@/assets/hotdog.svg"
        class="menuIcon"
        @click="menuSelect(2)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="카페" /><img
        src="@/assets/coffee.svg"
        class="menuIcon"
        @click="menuSelect(3)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="고구마" /><img
        src="@/assets/sweetpotato.svg"
        class="menuIcon"
        @click="menuSelect(4)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="아이스크림" />
      <img
        src="@/assets/icecream.svg"
        class="menuIcon"
        @click="menuSelect(5)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="와플" />
      <img
        src="@/assets/waffle.svg"
        class="menuIcon"
        @click="menuSelect(6)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="스테이크" />
      <img src="@/assets/steak.svg" class="menuIcon" @click="menuSelect(7)" alt
    /></label>

    <label
      ><input type="radio" name="menu" value="샌드위치" />
      <img
        src="@/assets/sandwich.svg"
        class="menuIcon"
        @click="menuSelect(8)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="꼬치" />
      <img
        src="@/assets/skeweredfood.svg"
        class="menuIcon"
        @click="menuSelect(9)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="라면" />
      <img
        src="@/assets/ramen.svg"
        class="menuIcon"
        @click="menuSelect(10)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="피자" />
      <img
        src="@/assets/pizza.svg"
        class="menuIcon"
        @click="menuSelect(11)"
        alt
    /></label>

    <label
      ><input type="radio" name="menu" value="음료수" />
      <img
        src="@/assets/drink.svg"
        class="menuIcon"
        style="margin-right: 3%"
        @click="menuSelect(12)"
        alt
    /></label>
  </div>
</template>

<script>
import { useStoreDetail } from "@/stores/customer/menu/storeDetail";

export default {
  setup() {
    const storeDetail = useStoreDetail();

    function menuSelect(menu) {
      if (menu == 0) {
        sessionStorage.setItem("selectedMenu", "전체");
        storeDetail.getNearStoreInfo();
      } else if (menu == 1) {
        sessionStorage.setItem("selectedMenu", "햄버거");
        storeDetail.getNearStoreInfo();
      } else if (menu == 2) {
        sessionStorage.setItem("selectedMenu", "핫도그");
        storeDetail.getNearStoreInfo();
      } else if (menu == 3) {
        sessionStorage.setItem("selectedMenu", "카페");
        storeDetail.getNearStoreInfo();
      } else if (menu == 4) {
        sessionStorage.setItem("selectedMenu", "고구마");
        storeDetail.getNearStoreInfo();
      } else if (menu == 5) {
        sessionStorage.setItem("selectedMenu", "아이스크림");
        storeDetail.getNearStoreInfo();
      } else if (menu == 6) {
        sessionStorage.setItem("selectedMenu", "와플");
        storeDetail.getNearStoreInfo();
      } else if (menu == 7) {
        sessionStorage.setItem("selectedMenu", "스테이크");
        storeDetail.getNearStoreInfo();
      } else if (menu == 8) {
        sessionStorage.setItem("selectedMenu", "샌드위치");
        storeDetail.getNearStoreInfo();
      } else if (menu == 9) {
        sessionStorage.setItem("selectedMenu", "꼬치");
        storeDetail.getNearStoreInfo();
      } else if (menu == 10) {
        sessionStorage.setItem("selectedMenu", "라면");
        storeDetail.getNearStoreInfo();
      } else if (menu == 11) {
        sessionStorage.setItem("selectedMenu", "피자");
        storeDetail.getNearStoreInfo();
      } else if (menu == 12) {
        sessionStorage.setItem("selectedMenu", "음료수");
        storeDetail.getNearStoreInfo();
      }
    }
    return {
      storeDetail,
      menuSelect,
    };
  },
};
</script>

<style scoped>
.MenuScroll {
  overflow-x: scroll;
  white-space: nowrap;

  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.MenuScroll::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
.menuIcon {
  width: 20%;
  margin-top: 7%;
  margin-left: 3%;
}
[type="radio"] {
  position: absolute;
  opacity: 0;
  width: 0;
  height: 0;
}
/* IMAGE STYLES */
[type="radio"] + img {
  cursor: pointer;
}

/* CHECKED STYLES */
[type="radio"]:checked + img {
  /* outline: 2px solid #f00; */
  border: 0.2rem solid var(--color-orange-1);
  border-radius: 3rem;
  opacity: 70%;
  box-sizing: border-box;
}
</style>