<template>
     <label for="survey-search" class="surveySearchLabel">
      <img src="@/assets/SearchIcon.svg" style="width: 1.5rem" alt />
      <input type="search" id="survey-search" />
    </label>
</template>

<script>
export default {
}
</script>

<style scoped>
input {
  border: none;
  outline-style: none;
  padding: 0 0 0 1rem;
  width: 80%;
  background-color: unset;
}
.surveySearchLabel {
  display: flex;
  box-sizing: border-box;
  margin-left: 5%;
  width: 90%;
  height: 4.5%;
  padding: 0 1rem 0 1rem;
  border-radius: 0.75rem;
  box-shadow: 0 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  background-color: var(--color-pink-0);
}
</style>