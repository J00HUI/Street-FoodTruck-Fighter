<template>
  <label for="my-truck-img" class="truckInput inputImg">
    <input @change="set_img" id="my-truck-img" type="file" accept=".gif, .jpg, .png" />
    <img class="TruckImg" src="@/assets/ceo/myAddImgIcon.svg" alt />
  </label>

  <label for="my-truck-img" class="truckInput inputText"></label>
  <div class="truckInput inputText"></div>
  <div class="truckInput inputText"></div>
  <div class="truckInput inputText"></div>
  <button class="updateButton">수정</button>
</template>

<script>
export default {
	setup() {
		const test_img = ''
		function set_img (e) {
			console.log(e.target.nextElementSibling())
			console.log(e.target.files)
			URL.createObjectURL(e.target.files)

			document.getElementById("img태그id").src
		

		}
		return {
			test_img,
			set_img,
		}
	}
};
</script>

<style scoped>
input[type="file"] {
	position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}
button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: none;
}
.truckInput {
  display: flex;
	justify-content: space-around;
	align-items: center;
  border-radius: 1rem;
  background-color: var(--color-gray-1);
  width: 88%;
  margin: 6%;
}
.TruckImg {
	opacity: 30%;
}
.inputImg {
  height: 32%;
}
.inputText {
  height: 8%;
}
.updateButton {
  font: 1rem "SCoreDream";
  border-radius: 2rem;
  width: 80%;
  margin-left: 10%;
  height: 6%;
  color: white;
  background-color: var(--color-purple-2);
  filter: drop-shadow(0px 10px 22px rgba(149, 173, 254, 0.3));
}
</style>