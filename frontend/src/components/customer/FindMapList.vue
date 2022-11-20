<template>
  <div style="text-align: center; margin-top: 3%">
    <label for="address"></label>
    <input
      type="text"
      id="address"
      name="address"
      class="address"
      placeholder="주소를 입력하세요."
      style="
        text-align: center;
        font-family: SCoreDream;
        font-style: normal;
        font-weight: 500;
        font-size: 0.8rem;
      "
    />
  </div>

  <div style="position: relative; margin-top: 7%" v-if="isMap">
    <nav style="display: flex; height: 8%">
      <div class="salesNav" @click="findMap()">
        <span class="underLine" style="cursor: pointer">지도</span>
      </div>
      <div class="salesNav salesNavR" @click="findList()">
        <span style="cursor: pointer">목록</span>
      </div>
    </nav>
  </div>

  <div style="position: relative; margin-top: 7%" v-if="isList">
    <nav style="display: flex; height: 8%">
      <div class="salesNav salesNavL" @click="findMap()">
        <span style="cursor: pointer">지도</span>
      </div>
      <div class="salesNav" style="border-right: none" @click="findList()">
        <span class="underLine" style="cursor: pointer">목록</span>
      </div>
    </nav>
  </div>

  <FindMenuIcon></FindMenuIcon>
  <TruckMap style="margin-top: 7%" v-if="isMap"></TruckMap>
  <TruckList v-if="isList"></TruckList>
</template>

<script>
import FindMenuIcon from "@/components/customer/FindMenuIcon.vue";
import TruckMap from "@/components/customer/TruckMap.vue";
import TruckList from "@/components/customer/TruckList.vue";

export default {
  components: {
    FindMenuIcon,
    TruckMap,
    TruckList,
  },

  data() {
    return {
      isMap: true,
      isList: false,
    };
  },

  methods: {
    findMap() {
      this.isMap = true;
      this.isList = false;
    },

    findList() {
      this.isMap = false;
      this.isList = true;
    },

    menuSelect() {
      let selected = document.querySelector(".hamburger");
      selected.classList.toggle("selected");
    },
  },
};
</script>

<style scoped>
.address {
  width: 22rem;
  height: 3.3rem;

  background: #fdeca6;
  border-radius: 0.75rem;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);

  border: none;
  outline-style: none;
}
input.address::-webkit-input-placeholder {
  background-image: url(@/assets/SearchIcon.svg);
  background-size: contain;
  background-position: 1rem center;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
.salesNav {
  display: flex;
  justify-content: center;
  align-items: center;
  font: 1rem "SCoreDream";
  font-weight: 600;
  width: 50%;
  color: #c8c8c8;
  padding: auto;
  border: 0.001rem solid #c8c8c8;
  border-top: none;
  border-left: none;
  border-bottom: none;
  margin-top: 3%;
}
.salesNavL {
  border-top: none;
  border-left: none;
  border-bottom: none;
}
.salesNavR {
  border-top: none;
  border-right: none;
  border-bottom: none;
}
.underLine {
  border-bottom: 0.1rem solid black;
  color: black;
}
</style>