<template>
  <main>
    <div class="main__date">
      <img src="@/assets/3d_calendarIcon.svg" alt />
      <span class="main__date-time">
        <time>2022/10/25 토요일</time>
        <address class="main__date-address">
          <img src="@/assets/clockIcon.svg" style="width:1rem;" alt /> 월, 수, 금 09:00 ~ 11:00
        </address>
      </span>
    </div>
    <nav>
      <div>
        <a href>
          <button type="button" class="main__nav-border">
            <img src="@/assets/ceo/navOpen.svg" alt />
          </button>
        </a>
        <a href>
          <button type="button" class="main__nav-border">
            <img src="@/assets/ceo/navClose.svg" alt />
          </button>
        </a>
      </div>
      <a class="nav__a-style" href>
        <img src="@/assets/ceo/navProfit.svg" alt />
      </a>
      <a class="nav__a-style" href>
        <img src="@/assets/ceo/navProfit.svg" alt />
      </a>

      <a class="nav__a-style" href>
        <img src="@/assets/ceo/navDemand.svg" alt />
      </a>
      <a class="nav__a-style" href>
        <img src="@/assets/ceo/navTruck.svg" alt />
      </a>
    </nav>
  </main>
</template>

<script>
export default {};
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
.main__nav-border {
  margin: 5%;
  width: 40%;
  box-shadow: 0 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  border: none;
  background: white;
  border-radius: 2rem;
}
.nav__a-style {
  margin: 10%;
}
</style>