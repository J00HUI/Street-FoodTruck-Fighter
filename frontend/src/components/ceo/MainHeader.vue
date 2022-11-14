<template>
  <header>
    <div>
      <div style="display:flex">
        <img src="@/assets/markerIcon.svg" style="width:2.5rem;" alt />
        <address class="header__text-style">주소</address>
      </div>

      <div class="header__name-style">
        <span>
          <span class="header__text-style" style="margin: auto 0px auto 1.5rem; height:100%">이름님</span>
          <img src="@/assets/chefIcon.svg" style="width:1.25rem;" alt />
        </span>
        <span>
          <router-link to>
            <img src="@/assets/noticeIcon.svg" alt />
          </router-link>
          <router-link to="/myprofile">
            <img src="@/assets/humanIcon.svg" style="margin-left:1rem;" alt />
          </router-link>
        </span>
      </div>
    </div>
    <label for="ceo-search" class="header__search-label">
      <img src="@/assets/SearchIcon.svg" style="width: 1.5rem" alt />
      <input type="search" id="ceo-search" />
    </label>
  </header>
</template>

<script>
export default {};
</script>

<style scoped>
header {
  position: relative;
  width: 92%;
  background: var(--color-purple-2);
  font-size: 1rem;
  border-radius: 0px 0px 1rem 1rem;
  padding: 4%;
  margin-bottom: 4rem;
}
address {
  display: inline-block;
  margin: auto 0 auto 0;
}

input {
  border: none;
  outline-style: none;
  padding: 0 0 0 1rem;
  width: 80%;
}
p {
  display: inline;
  margin: auto;
  text-align: center;
}
img {
  vertical-align: top;
}
.header__name-style {
  display: flex;
  justify-content: space-between;
  padding: 1rem;
}
.header__search-label {
  position: absolute;
  display: flex;
  box-sizing: border-box;
  left: 5%;
  width: 90%;
  height: 30%;
  top: 85%;
  border-radius: 0.75rem;
  box-shadow: 0 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  background-color: white;
  padding: 0 1rem 0 1rem;
}
.header__text-style {
  color: white;
  font-family: SCoreDream;
  font-style: normal;
}
</style>
