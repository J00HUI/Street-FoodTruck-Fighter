<template>
    <label for="my-truck-img"  class="truckInput inputImg"> <input id="my-truck-img" type="file" accept=".gif, .jpg, .png"></label>

  <label for="my-truck-img" class="truckInput inputText"></label>
	<div class="truckInput inputText"></div>
	<div class="truckInput inputText"></div>
	<div class="truckInput inputText"></div>
	<button class="updateButton">수정</button>
</template>

<script>
export default {

}
</script>

<style>
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
	border-radius: 1rem;
  background-color: var(--color-gray-1);
	width: 90%;
	margin: 5%;
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