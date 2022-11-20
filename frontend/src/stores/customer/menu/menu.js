import { defineStore } from "pinia";

export const useMenuStore = defineStore("Menu", {
    state: () => {
      return {
        data: 1, 
        amount : 0,
      }
    }
  }
)