<template>
  <main>
    <div class="main__date">
      <img src="@/assets/3d_calendarIcon.svg" alt />
      <span class="main__date-time">
        <time>2022/10/25 토요일</time>
        <address class="main__date-address">
          <img src="@/assets/clockIcon.svg" style="width: 1rem" alt /> 월, 수,
          금 09:00 ~ 11:00
        </address>
      </span>
    </div>
    <nav>
      <div class="main__nav-box">
        <router-link to="/start">
          <button type="button" class="main__nav-style">
            <img class="main__nav-img" src="@/assets/ceo/navOpen.svg" alt />
          </button>
        </router-link>

        <span @click="salesStore.setStatistics">
          <button type="button" class="main__nav-style">
            <img class="main__nav-img" src="@/assets/ceo/navClose.svg" alt />
          </button>
        </span>
      </div>

      <div class="nav__a-link">
        <router-link class="nav__a-style" to="/salesstatistics">
          <img src="@/assets/ceo/navStatistics.svg" alt />
        </router-link>
        <router-link class="nav__a-style" to="/schedule">
          <img src="@/assets/ceo/navSchedule.svg" alt />
        </router-link>
      </div>
      <div class="nav__a-link">
        <router-link class="nav__a-style" to="/surveycheck">
          <img src="@/assets/ceo/navSurvey.svg" alt />
        </router-link>
        <router-link class="nav__a-style" to="/mytruck">
          <img src="@/assets/ceo/navTruck.svg" alt />
        </router-link>
      </div>
    </nav>
  </main>
</template>

<script>
import { useCeoSalesStore } from "@/stores/ceo/sales.js";
export default {
  setup() {
    const salesStore = useCeoSalesStore();
    return { salesStore };
  },
};
</script>

<style scoped>
main {
  font-family: "SCoreDream";
}
time {
  margin: auto 0 auto 0;
}
img {
  vertical-align: top;
}
.main__date {
  display: flex;
  margin: 5%;
  box-sizing: border-box;
  padding: 1rem;
  border-radius: 1rem;
  width: 90%;
  box-shadow: 0px 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
}
.main__date-time {
  display: flex;
  flex-direction: column;
  padding: 0 0 0 1rem;
}
.main__date-address {
  font-size: 0.75rem;
  color: var(--color-gray-2);
  font-style: normal;
  margin: auto 0 auto 0;
}
.main__nav-box {
  height: 100%;
  margin: 4%;
}
.main__nav-style {
  margin: 1%;
  width: 48%;
  box-shadow: 0 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  border: none;
  background: white;
  border-radius: 2rem;
}
.main__nav-img {
  height: 15%;
  margin: 10%;
}

.nav__a-link {
  display: flex;
  justify-content: space-around;
}
.nav__a-link::before,
.nav__a-link::after {
  display: inline-block;
  content: "";
}
.nav__a-style {
  height: 15%;
  margin-top: 5%;
}
</style>