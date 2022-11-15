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
    setImg: () => HOST + FOODTRUCK + "upload",
    getImg: (foodtruck_id) => HOST + FOODTRUCK + "image/" + `${foodtruck_id}`,
  },
  survey: {
    survey: () => HOST + "survey",
    surveyFind: () => HOST + "survey/" + "find",
  },
  orders: {
    customer: () => HOST + "order",
    acceptOrders: (order_id) => HOST + ORDER + CEO + `${order_id}`,
    getNotAcceptedOrder: () => HOST + ORDER + CEO + "not/" + "accepted",
    getCustomerOrders: () => HOST + ORDER + "customer",
    getCustomerOrdersAll: () => HOST + ORDER + CUSTOMER + "all",
    getCeoOrders: () => HOST + ORDER + CEO + "accepted",
    getCeoOrdersAll: () => HOST + ORDER + CEO + "all",
    cancelOrders: (order_id) => HOST + ORDER + "cancel" + `${order_id}`,
    finishOrder: (order_id) => HOST + ORDER + "done/" + `${order_id}`,
  },
  schedule: {
    setSchedule: () => HOST + "schedule",
    cancelSchedule: (schedule_id) => HOST + SCHEDULE + `${schedule_id}`,
    getSchedule: () => HOST + SCHEDULE + "all",
  },
  review: {
    getReviewImg: (review_id) => HOST + REVIEW + "img/" + { review_id },
    setReview: () => HOST + REVIEW + "review",
    getReview: (foodtruck_id) => HOST + REVIEW + REVIEW + `${foodtruck_id}`,
  },
  sms: {
    sendSMS: () => HOST + "phone",
    checkSMS: () => HOST + PHONE + "sms",
  },
};
