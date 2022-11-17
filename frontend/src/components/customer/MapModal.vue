<template>
  <div class="modal-wrapper">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="cardContainer">
          <div class="card">
            <h2>현재 내 위치</h2>
            <MyLocationMap></MyLocationMap>
            <button class="button" id="close" @click="onClose">닫기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MyLocationMap from "@/components/customer/MyLocationMap.vue";

export default {
  components: {
    MyLocationMap,
  },

  setup(props, { emit }) {
    const onClose = () => {
      emit("close");
    };

    return {
      onClose,
    };
  },
};
</script>

<style scoped>
.modal-wrapper {
  position: fixed;
  z-index: 100;
  /* top: 0;
  left: 0; */
  width: 100%;
  height: 90%;
  overflow: hidden;
  background-color: rgba(0, 0, 0, 0.5);
}
.modal-dialog {
  display: fixed;
  margin-top: 40%;
  height: 20rem;
  width: 100%;
}
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
  height: 105%;

  background-color: white;
  border: 0.1rem solid lightgray;
  border-radius: 1rem;
  box-shadow: 2px 3px 15px -6px gray;
}
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
#close {
  background-color: #f78888;
}
[type="radio"] {
  vertical-align: middle;
  appearance: none;
  outline: none;
}
input[type="radio"]:checked {
  border: 4px solid black;
}
</style>