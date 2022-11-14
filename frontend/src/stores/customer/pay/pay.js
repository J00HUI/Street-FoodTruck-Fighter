import { defineStore } from "pinia";

export const usePayStore = defineStore("Pay", {
    state: () => {
      return {
        payState : 0,   // 0 : ok , 1 : cancel , 2 : fail
      }
    }
  }
)