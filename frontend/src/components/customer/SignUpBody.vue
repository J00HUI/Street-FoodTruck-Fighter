<template>
  <div
    style="position: relative; margin-top: 7%"
    v-if="accountStore.signUpCheck.ceoSignUp"
  >
    <nav style="display: flex; height: 8%">
      <div class="salesNav" @click="signUpCeo">
        <span class="underLine">사장님</span>
      </div>
      <div class="salesNav salesNavR" @click="signUpCustomer">
        <span>고객님</span>
      </div>
    </nav>
  </div>

  <div
    style="position: relative; margin-top: 7%"
    v-if="accountStore.signUpCheck.customerSignUp"
  >
    <nav style="display: flex; height: 8%">
      <div class="salesNav salesNavL" @click="signUpCeo">
        <span>사장님</span>
      </div>
      <div class="salesNav" style="border-right: none" @click="signUpCustomer">
        <span class="underLine">고객님</span>
      </div>
    </nav>
  </div>

  <div style="text-align: center" v-if="accountStore.signUpCheck.ceoSignUp">
    <div style="margin-top: 7%">
      <form action="/action_page.php">
        <label for="email"></label>
        <input
          type="email"
          id="email"
          name="email"
          class="email"
          placeholder="이메일"
          v-model="accountStore.userData.email"
          style="text-align: center"
        />
        <br />
        <div style="margin-top: 3%">
          <label for="pwd"></label>
          <input
            type="password"
            id="pwd"
            name="pwd"
            class="pwd"
            placeholder="비밀번호"
            v-model="accountStore.userData.password"
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
            v-model="accountStore.userData.passwordCheck"
            style="text-align: center"
          />
        </div>
        <div style="margin-top: 3%">
          <label for="nickname"></label>
          <input
            type="text"
            id="nickname"
            name="nickname"
            class="nickname"
            placeholder="닉네임"
            v-model="accountStore.userData.nickname"
            style="text-align: center"
          />
        </div>
        <div style="margin-top: 3%">
          <label for="phonenum"></label>
          <input
            type="text"
            id="phonenum"
            name="phonenum"
            class="phonenum"
            placeholder="휴대폰 번호"
            v-model="accountStore.userData.phone"
            style="text-align: center"
          />
          <input type="button" value="인증" class="checkButtonCeo" />
        </div>

        <div style="margin-top: 3%">
          <label for="numcheck"></label>
          <input
            type="text"
            id="numcheck"
            name="numcheck"
            class="numcheck"
            placeholder="인증 번호"
            v-model="accountStore.userData.verificationCode"
            style="text-align: center"
          />
          <input type="button" value="확인" class="checkButtonCeo" />
        </div>

        <div style="margin-top: 3%">
          <label for="ceonumcheck"></label>
          <input
            type="text"
            id="ceonumcheck"
            name="ceonumcheck"
            class="ceonumcheck"
            placeholder="사업자 등록번호"
            v-model="accountStore.userData.businessNumber"
            style="text-align: center"
          />
          <input type="button" value="인증" class="checkButtonCeo" @click="accountStore.checkBusinessNum" />
        </div>

        <div style="margin-top: 10%">
          <input
            type="button"
            value="다음"
            class="nextButtonCeo"
            @click="accountStore.signUp"
          />
        </div>
      </form>
    </div>
  </div>

  <div style="text-align: center" v-if="accountStore.signUpCheck.customerSignUp">
    <div style="margin-top: 7%">
      <form action="/action_page.php">
        <label for="email"></label>
        <input
          type="email"
          id="email"
          name="email"
          class="email"
          placeholder="이메일"
          v-model="accountStore.userData.email"
          style="text-align: center"
        />
        <br />
        <div style="margin-top: 3%">
          <label for="pwd"></label>
          <input
            type="password"
            id="pwd"
            name="pwd"
            class="pwd"
            placeholder="비밀번호"
            v-model="accountStore.userData.password"
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
            v-model="accountStore.userData.passwordCheck"
            style="text-align: center"
          />
        </div>
        <div style="margin-top: 3%">
          <label for="nickname"></label>
          <input
            type="text"
            id="nickname"
            name="nickname"
            class="nickname"
            placeholder="닉네임"
            v-model="accountStore.userData.nickname"
            style="text-align: center"
          />
        </div>
        <div style="margin-top: 3%">
          <label for="phonenum"></label>
          <input
            type="text"
            id="phonenum"
            name="phonenum"
            class="phonenum"
            placeholder="휴대폰 번호"
            v-model="accountStore.userData.phone"
            style="text-align: center"
          />
          <input type="button" value="인증" class="checkButton" @click="accountStore.sendNum"/>
        </div>

        <div style="margin-top: 3%">
          <label for="numcheck"></label>
          <input
            type="text"
            id="numcheck"
            name="numcheck"
            class="numcheck"
            placeholder="인증 번호"
            v-model="accountStore.userData.verificationCode"
            style="text-align: center"
          />
          <input type="button" value="확인" class="checkButton" @click="accountStore.checkNum" />
        </div>

        <div style="margin-top: 10%">
          <input
            type="button"
            value="완료"
            class="nextButton"
            @click="accountStore.signUp"
          />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { useAccountStore } from "@/stores/account";

export default {
  components: {},

  setup() {
    const accountStore = useAccountStore();

    function signUpCeo() {
      accountStore.signUpCheck.ceoSignUp = true;
      accountStore.signUpCheck.customerSignUp = false;
    }

    function signUpCustomer() {
      accountStore.signUpCheck.ceoSignUp = false;
      accountStore.signUpCheck.customerSignUp = true;
    }

    return {
      accountStore,
      signUpCeo,
      signUpCustomer,
    };
  },
};
</script>

<style scoped>
.email,
.pwd,
.pwdcheck,
.nickname {
  width: 20rem;
  height: 4rem;

  background: #f7f8f8;
  border-radius: 1rem;

  border: none;
  outline-style: none;
}
.phonenum,
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
input.nickname::-webkit-input-placeholder {
  background-image: url(@/assets/human.svg);
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
  background-image: url(@/assets/password.svg);
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
input.numcheck::-webkit-input-placeholder {
  background-image: url(@/assets/password.svg);
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
input.ceonumcheck::-webkit-input-placeholder {
  background-image: url(@/assets/password.svg);
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
.checkButton {
  width: 5rem;
  height: 3.7rem;
  margin-left: 2%;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  background: #fdeca6;
  border-radius: 1.3rem;
  border: none;
  outline-style: none;
}
.checkButtonCeo {
  width: 5rem;
  height: 3.7rem;
  margin-left: 2%;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;
  color: #d076cd;

  background: #f7e8f7;
  border-radius: 1.3rem;
  border: none;
  outline-style: none;
}
.nextButton {
  width: 15rem;
  height: 4rem;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 600;
  font-size: 1rem;

  filter: drop-shadow(0rem 0.625rem 1.3rem rgba(150, 170, 250, 0.3));

  background: #ffe060;
  border-radius: 2rem;
  border: none;
  outline-style: none;
}
.nextButtonCeo {
  width: 15rem;
  height: 4rem;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 600;
  font-size: 1rem;
  color: #ffffff;

  filter: drop-shadow(0rem 0.625rem 1.3rem rgba(150, 170, 250, 0.3));

  background: #d076cd;
  border-radius: 2rem;
  border: none;
  outline-style: none;
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