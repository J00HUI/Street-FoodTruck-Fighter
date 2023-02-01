<template>
  <div class="modal-wrapper">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="cardContainer">
          <div class="card">
            <h1 class="newMenuTitle">새로운 메뉴 등록</h1>
            <label for="my-new-menu-img" class="truckInput inputImg">
              <input
                @change="setNewMenuImg"
                id="my-new-menu-img"
                type="file"
                accept=".gif, .jpg, .png"
              />
              <img id="my-menu-img" class="truckImg imgVisible" src alt />
              <img
                class="addIcon"
                src="@/assets/ceo/myAddImgIcon.svg"
                alt="추가"
              />
            </label>

            <label for="new-menu-name" class="truckInput inputText">
              <img
                style="width: 1.5rem"
                src="@/assets/ceo/myNewMenuName.svg"
                alt
              />
              <input
                v-model="myStore.newMenuData.name"
                id="new-menu-name"
                type="text"
                placeholder="메뉴 이름을 입력해 주세요"
              />
            </label>
            <label for="new-menu-price" class="truckInput inputText">
              <img style="width: 1.5rem" src="@/assets/ceo/myPrice.svg" alt />
              <input
                id="new-menu-price"
                type="text"
                maxlength="20"
                v-model="myStore.newMenuData.price"
                placeholder="가격을 입력해 주세요"
              />
            </label>

            <label class="descriptionLabel" for="new-menu-describe">
              <div class="descriptionIcon">
                <img
                  class="descriptionIcon2"
                  src="@/assets/ceo/myDescription.svg"
                  alt
                />
              </div>
              <textarea
                v-model="myStore.newMenuData.description"
                id="new-menu-describe"
                class="description"
                placeholder="메뉴설명을 입력해주세요"
              ></textarea>
            </label>
            <div class="newMenuList">
              <img
                v-for="i in myStore.newMenuDataList.length - 1"
                @click="loadMenu(i - 1)"
                :src="myStore.createMenuImgUrlList[i - 1]"
                :key="i"
                :id="i"
                class="newMenuIcon"
              />
              <img
                @click="addMenu"
                class="newMenuIcon"
                src="@/assets/ceo/myAddImgIcon.svg"
                alt
              />
            </div>
            <div class="buttons">
              <button type="button" class="acceptbutton" @click="addMenu">
                추가
              </button>
              <button type="button" class="acceptbutton" @click="setAndUpdateMenu">
                등록
              </button>
              <button type="button" class="acceptbutton" @click="modalToggle">
                취소
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useCeoMyStore } from "@/stores/ceo/my.js";
export default {
  setup() {
    const myStore = useCeoMyStore();
    myStore.getMenu();
    function setNewMenuImg(e) {
      if (myStore.createImgUrl !== null) {
        URL.revokeObjectURL(myStore.createImgUrl);
      }
      myStore.createMenuImgList[myStore.myTypeData.newMenuIndex] =
        e.target.files[0];
      myStore.createImgUrl = URL.createObjectURL(e.target.files[0]);
      e.target.nextElementSibling.src = myStore.createImgUrl;
      e.target.nextElementSibling.classList.remove("imgVisible");
      e.target.nextElementSibling.nextElementSibling.classList.add(
        "imgVisible"
      );
    }
    function modalToggle() {
      myStore.myTypeData.modalView = !myStore.myTypeData.modalView;
    }

    function loadMenu(idx) {
      // 만드 메뉴 정보 가져오기
      myStore.myTypeData.newMenuIndex = idx;
      myStore.newMenuData = myStore.newMenuDataList[idx];
      
      const myMenuImg = document.getElementById("my-menu-img");
      URL.revokeObjectURL(myStore.createImgUrl);
      if (myStore.createMenuImgList[myStore.myTypeData.newMenuIndex] !== null) {
        myStore.createImgUrl = URL.createObjectURL(
        myStore.createMenuImgList[myStore.myTypeData.newMenuIndex]
      );

      myMenuImg.src = myStore.createImgUrl;
      myMenuImg.classList.remove("imgVisible");
      myMenuImg.nextElementSibling.classList.add("imgVisible");
      } else {
        myMenuImg.classList.add("imgVisible");
        myMenuImg.nextElementSibling.classList.remove("imgVisible");
      }
      
    }
    function addMenu() {
      if (myStore.newMenuData.name === null || "") {
        alert("이름을 입력해주세요");
      } else if (myStore.newMenuData.price === null || "") {
        alert("가격을 입력해주세요");
      } else if (myStore.newMenuData.description === null || "") {
        alert("설명을 입력해주세요");
      } else if (
        myStore.createMenuImgList[myStore.myTypeData.newMenuIndex] === undefined
      ) {
        alert("이미지를 입력해주세요");
      } else {
        // 이미지 박스 초기화 및 가리기
        URL.revokeObjectURL(myStore.createImgUrl);
        myStore.createImgUrl = null;

        const myMenuImg = document.getElementById("my-menu-img");
        myMenuImg.src = null;
        myMenuImg.classList.add("imgVisible");
        myMenuImg.nextElementSibling.classList.remove("imgVisible");
        // 정보 넣기
        myStore.newMenuDataList[myStore.myTypeData.newMenuIndex] =
          Object.assign({}, myStore.newMenuData);
        if (
          myStore.createMenuImgUrlList[myStore.myTypeData.newMenuIndex] !== null
        ) {
          URL.revokeObjectURL(
            myStore.createMenuImgUrlList[myStore.myTypeData.newMenuIndex]
          );
          myStore.createMenuImgUrlList[myStore.myTypeData.newMenuIndex] =
            URL.createObjectURL(
              myStore.createMenuImgList[myStore.myTypeData.newMenuIndex]
            );
        }
        myStore.newMenuData = {
          name: null,
          price: null,
          description: null,
        };
        myStore.myTypeData.newMenuIndex += 1;
        if (
          myStore.myTypeData.newMenuIndex === myStore.newMenuDataList.length
        ) {
          myStore.newMenuDataList.push(myStore.newMenuData);
        }
        document.getElementById("my-new-menu-img").value = null;
      }
    }
    function setAndUpdateMenu() {
      myStore.setNewMenu();
      myStore.updateNewMenu();
    }

    return {
      myStore,
      setNewMenuImg,
      modalToggle,
      loadMenu,
      addMenu,
      setAndUpdateMenu,
    };
  },
};
</script>

<style scoped>
/***************** modal style start ******************/
.modal-wrapper {
  position: absolute;
  z-index: 100;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}
.modal-content {
  height: 100%;
}
.modal-dialog {
  margin-top: 0%;
  height: 100%;
  width: 100%;
}
/***************** modal style end ******************/
/***************** card style start ******************/
.cardContainer {
  height: 100%;
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  margin-bottom: 1.5rem;
}
.card {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 90%;
  height: 95%;
  margin: auto;

  background-color: white;
  border: 0.1rem solid lightgray;
  border-radius: 1rem;
  box-shadow: 2px 3px 15px -6px gray;
}
/***************** card style end ******************/
input {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border: none;
  outline-style: none;
  padding: 0 1rem;
  font-size: 1rem;
  background-color: var(--color-purple-1);
  font: 1.1rem "SCoreDream";
}
input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
}
button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: none;
}
textarea {
  resize: none;
  overflow-y: scroll;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  border: none;
  outline: none;
  background-color: var(--color-gray-1);
  font: 1rem "SCoreDream";
}
.newMenuTitle {
  font: 1.5rem "SCoreDream";
  margin: 1rem;
}

/* add Img start */
.truckInput {
  display: flex;
  position: relative;
  justify-content: space-evenly;
  align-items: center;
  border-radius: 1rem;
  background-color: var(--color-purple-1);
  width: 90%;
  height: 40%;
  margin: 4%;
  margin-top: 0;
}
.inputImg {
  height: 32%;
}

.addIcon {
  position: absolute;
  opacity: 30%;
  height: 50%;
  width: 50%;
}

/***************** review style end ******************/
.truckImg {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0%;
  border-radius: 1rem;
  background-size: contain;
  background-repeat: no-repeat;
  z-index: 100;
}
.inputImg {
  height: 32%;
  background-color: var(--color-gray-1);
  border: 2px solid var(--color-purple-1);
}
.inputText {
  height: 10%;
  background-color: var(--color-purple-1);
}
.imgVisible {
  visibility: hidden;
}
.descriptionIcon {
  position: relative;
  height: 1.5rem;
  top: 1.1rem;
  left: 1.1rem;
  display: block;
}
.descriptionLabel {
  width: 90%;
  height: 16%;
  border-radius: 1rem;
  background-color: var(--color-purple-1);
}
.description {
  position: relative;
  top: 1.5rem;
  left: 1.5rem;
  width: calc(96% - 2rem);
  margin: auto;
  height: calc(80% - 2rem);
}

textarea::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
.acceptbutton {
  font: 1rem "SCoreDream";
  border-radius: 2rem;
  margin: 1rem auto 1rem auto;
  width: 24%;
  height: 72%;
  color: white;
  background-color: var(--color-purple-2);
  filter: drop-shadow(0px 10px 22px rgba(149, 173, 254, 0.3));
}
.buttons {
  display: flex;
  justify-content: space-evenly;
  width: 100%;
}
.newMenuList {
  white-space: nowrap;
  overflow-x: scroll;
  width: 90%;
  margin: 0.5rem;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.newMenuList::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
.newMenuIcon {
  display: inline-block;
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  border: 0.2rem solid var(--color-purple-1);
  margin-right: 1rem;
}
.descriptionIcon2 {
  vertical-align: bottom;
  width: 1.5rem;
}

</style>