// const HOST = "http://localhost:8000/api/"
const HOST = "https://k7b206.p.ssafy.io/api/";
// const api = " api/"
const AUTH = "auth/";
const USER = "user/";
const CEO = "ceo/";
const CUSTOMER = "customer/";
const FOODTRUCK = "foodtruck/";
const REVIEW = "review/";
const ORDER = "order/";
const SCHEDULE = "schedule/";
const PHONE = "phone/";
const MENU = "menu/"
export default {
  user: {
    login: () => HOST + AUTH + "login",
    logout: () => HOST + AUTH + "logout",
    signup: () => HOST + USER + "signup",
    getUserInfo: () => HOST + "user",
  },
  foodtruck: {
    registerFoodTruck: () => HOST + "foodtruck",
    registerFoodTruckReview: () => HOST + FOODTRUCK + "review",
    getFoodTruckReview: (foodtruck_id) =>
      HOST + FOODTRUCK + REVIEW + `${foodtruck_id}`,
    getFoodTruck: (foodtruck_id) => HOST + FOODTRUCK + `${foodtruck_id}`,
    getNearFoodTruck: () => HOST + FOODTRUCK + "near",
    updateFoodTruck: () => HOST + "foodtruck",
    search: (keyword) => HOST + FOODTRUCK + "search/" + `${keyword}`,
    setImg: () => HOST + FOODTRUCK + CEO + "upload",
    getImg: (foodtruck_id) => HOST + FOODTRUCK + "image/" + `${foodtruck_id}`,
  },
  survey: {
    survey: () => HOST + "survey",
    surveyFind: () => HOST + "survey/" + "find",
  },
  orders: {
    cancelOrders: (order_id) => HOST + ORDER + "cancel" + `${order_id}`,
    acceptOrders: () => HOST + ORDER + CEO + "accept/",
    getCeoOrders: (ceo_id) => HOST + ORDER + CEO + "accepted/" + `${ceo_id}`,
    getNotAcceptedOrder: (ceo_id) => HOST + ORDER + CEO + "not/" + "accepted/" + `${ceo_id}`,
    getCustomerOrders: () => HOST + ORDER + "customer",
    setCustomerOrders: () => HOST + ORDER + "customer",
    getCustomerOrdersAll: () => HOST + ORDER + CUSTOMER + "all",
    finishOrder: (order_id) => HOST + ORDER + "done/" + `${order_id}`,
  },
  schedule: {
    setSchedule: () => HOST + "schedule",
    cancelSchedule: (schedule_id) => HOST + SCHEDULE + `${schedule_id}`,
    getSchedule: () => HOST + SCHEDULE + "all",
  },
  review: {
    getReviewImg: (review_id) => HOST + REVIEW + "img/" + { review_id },
    setReviewImg: (order_id) => HOST + REVIEW + "upload" + `${order_id}`,
    setReview: () => HOST + REVIEW,
    getReview: (foodtruck_id) => HOST + REVIEW + `${foodtruck_id}`,
  },
  sms: {
    sendSMS: () => HOST + "phone",
    checkSMS: () => HOST + PHONE + "sms",
  },
  business: {
    businessPath: () => HOST + 'business'
  },
  menu: {
    setMenu: () => HOST + 'menu',
    getMenu: (foodtruck_id) => HOST + MENU + CUSTOMER + `${foodtruck_id}`,
    getMenuImg: (menu_id) => HOST + MENU + "image/" + `${menu_id}`,
    setMenuImg: () => HOST + MENU + "upload",
    updateMenu: () => HOST + MENU + "update",
    deleteMenu: (menu_id) => HOST + MENU + `${menu_id}`,
  },
  pay:{
    pay: () => HOST + 'v1/' + 'pay',
    paySuccess: (pg_token) => HOST + 'v1/' + 'pay/' + 'success/' + `${pg_token}`,
  }
};
