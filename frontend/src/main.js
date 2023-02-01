import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import axios from "axios";
import VueSplide from '@splidejs/vue-splide';

const app = createApp(App);
const pinia = createPinia();

app.config.globalProperties.$axios = axios;

createApp(App).use(router).use(pinia).use(VueSplide).mount('#app')