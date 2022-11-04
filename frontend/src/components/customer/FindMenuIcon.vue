<template>
  <div class="MenuScroll">
    <img src="@/assets/allmenu.svg" class="menuIcon" alt />
    <img
      src="@/assets/hamburger.svg"
      class="menuIcon hamburger"
      @click="menuSelect()"
      alt
    />
    <img src="@/assets/hotdog.svg" class="menuIcon" alt />
    <img src="@/assets/coffee.svg" class="menuIcon" alt />
    <img src="@/assets/sweetpotato.svg" class="menuIcon" alt />
    <img src="@/assets/icecream.svg" class="menuIcon" alt />
    <img src="@/assets/waffle.svg" class="menuIcon" />
    <img src="@/assets/steak.svg" class="menuIcon" />
    <img src="@/assets/sandwich.svg" class="menuIcon" alt />
    <img src="@/assets/skeweredfood.svg" class="menuIcon" alt />
    <img src="@/assets/ramen.svg" class="menuIcon" alt />
    <img src="@/assets/pizza.svg" class="menuIcon" alt />
    <img
      src="@/assets/drink.svg"
      class="menuIcon"
      style="margin-right: 3%"
      alt
    />
  </div>
</template>

<script>
export default {
  methods: {
    menuSelect() {
      let selected = document.querySelector(".hamburger");
      selected.classList.toggle("selected");
    },
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
.selected {
  /* border: 0.2rem solid #ffe060; */
  border: 0.2rem solid var(--color-orange-1);
  border-radius: 3rem;
  opacity: 70%;
  box-sizing: border-box;
}
</style>