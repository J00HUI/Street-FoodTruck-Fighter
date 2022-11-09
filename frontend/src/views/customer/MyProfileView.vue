<template>
  <Header></Header>
  <div class="name">
    <div class="familyName">
      <h1>{{ nickname.substr(0, 1) }}</h1>
    </div>
    <h3>{{ nickname }}</h3>
    <!-- <h3>{{curUserData.name}}</h3> -->
  </div>

  <div class="accountData">
    <label for="email"></label>
    <input
      v-model="email"
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
        v-model="phone"
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

    <div v-if="isUserType" style="margin-top: 3%" >
      <label for="businessNumber"></label>
      <input
      v-model="businessNumber"
        type="text"
        id="businessNumber"
        name="businessNumber"
        class="businessNumber"
        style="text-align: center"
      />
    </div>
  </div>
</template>

<script>
import Header from "@/components/customer/BackButtonHeader.vue";
export default {
  components: {
    Header,
  },
  setup() {
    let curUserData = sessionStorage.getItem("user");
    let nickname = JSON.parse(curUserData).nickname;
    let email = JSON.parse(curUserData).email;
    let phone = JSON.parse(curUserData).phone;
    let businessNumber = JSON.parse(curUserData).businessNumber;
    let userType = JSON.parse(curUserData).userType;

    let isUserType = false
    if(userType === 'CEO')
    {
      isUserType = true
    }else{
      isUserType = false
    }
    
    console.log(JSON.parse(curUserData));

    return {
      nickname,
      email,
      phone,
      curUserData,
      businessNumber,
      userType,
      isUserType,
    };
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
.businessNumber,
.pwdcheck {
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