<template>
  <Header></Header>
  <div class="name">
    <div class="familyName">
      <h1>윤</h1>
    </div>
    <h3>{{curUserData.name}}</h3>
  </div>

  <div class="accountData">
    <label for="email"></label>
    <input
      type="email"
      id="email"
      name="email"
      class="email"
      placeholder="이메일"
      style="text-align: center"
    />
    <br />
    <div style="margin-top: 3%">
      <label for="phonenum"></label>
      <input
        type="text"
        id="phonenum"
        name="phonenum"
        class="phonenum"
        placeholder="전화 번호"
        style="text-align: center"
      />
    </div>
    <div style="margin-top: 3%">
      <label for="pwd"></label>
      <input
        type="password"
        id="pwd"
        name="pwd"
        class="pwd"
        placeholder="비밀번호"
        style="text-align: center"
      />
    </div>
    <div style="margin-top: 3%">
      <label for="pwdcheck"></label>
      <input
        type="password"
        id="pwdcheck"
        name="pwdcheck"
        class="pwdcheck"
        placeholder="비밀번호 확인"
        style="text-align: center"
      />
    </div>
  </div>
</template>

<script>
import Header from "@/components/customer/BackButtonHeader.vue";
import {useMypageStore} from '@/stores/customer/mypage/mypage'
export default {
  components: {
    Header,
  },
  setup() {
    const store = useMypageStore()
    const curUserData = sessionStorage.getItem("user")
    let strFamilyName = curUserData.name.substr(0,1)
    console.log(curUserData)
    return{
      store,
      curUserData,
      strFamilyName,

    }
  },
};
</script>

<style>
.name {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.familyName {
  height: 8rem;
  width: 8rem;
  margin-top: 1rem;
  border-radius: 50%;
  background-color: #fff6dd;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
/********** account inform style start  ***********/

.email,
.pwd,
.phonenum,
.pwdcheck{
  width: 20rem;
  height: 4rem;
  margin-left: 2rem;

  background: #f7f8f8;
  border-radius: 1rem;

  border: none;
  outline-style: none;
}

.numcheck,
.ceonumcheck {
  width: 14.5rem;
  height: 4rem;

  background: #f7f8f8;
  border-radius: 1rem;

  border: none;
  outline-style: none;
}
input.email::-webkit-input-placeholder {
  background-image: url(@/assets/email.svg);
  background-size: contain;
  /* background-position: 7.2rem center; */
  background-position: 1.5rem;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
input.pwd::-webkit-input-placeholder {
  background-image: url(@/assets/password.svg);
  background-size: contain;
  /* background-position: 7rem center; */
  background-position: 1.5rem;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
input.pwdcheck::-webkit-input-placeholder {
  background-image: url(@/assets/password.svg);
  background-size: contain;
  /* background-position: 6rem center; */
  background-position: 1.5rem;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
input.phonenum::-webkit-input-placeholder {
  background-image: url(@/assets/ceo/myCallIcon.svg);
  background-size: contain;
  /* background-position: 2rem center; */
  background-position: 1.62rem;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}

/********** account inform style end  ***********/
</style>