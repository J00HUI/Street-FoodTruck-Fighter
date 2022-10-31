<template>
  <nav class="ceoFooter">
    <button>
      <img src="@/assets/ceo/footerSchedule.svg" alt />
    </button>
    <button>
      <img src="@/assets/ceo/footerStatistics.svg" alt />
    </button>
    <div class="footerHomeBox">
    <button class="footerHome">
      <img src="@/assets/ceo/footerHome.svg" style="width:4rem; height:4rem;" alt />
    </button>
  </div>
    <button>
      <img src="@/assets/ceo/footerSurvey.svg" alt />
    </button>
    <button>
      <img src="@/assets/ceo/footerMy.svg" alt />
    </button>
  </nav>
</template>

<script>
export default {};
</script>

<style scoped>
button {
  padding: 0;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: none;
}
.ceoFooter {
  position: absolute;
  z-index: 100;
  background-color: white;
  height: 5rem;
  display: flex;
  justify-content: space-around;
  align-items: center;
  box-shadow: 0 -0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  top: calc(100% - 5rem);
  width:100%;
}
.footerHomeBox {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  background-color: white;
  box-shadow: 0 -0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  width:5rem;
  height:5rem;
  top: -2rem;
}
.footerHome {
  display: flex;
  justify-content: center;
  align-items: center;

  border-radius: 50%;
  width:4rem;
  height:4rem;
}
.footerNav {
  height: 100%;
  width: 100%;
}
</style>