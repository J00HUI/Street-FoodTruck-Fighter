// const HOST = "http://localhost:8000/api/"
const HOST = "http://k7b206.p.ssafy.io:8080/"
// const api = " api/"
const USER = "auth/"
const CEO = 'ceo/'
const CUSTOMER = 'customer/'
const FOODTRUCK = "foodtruck/"
const REVIEW = 'review/'
const ORDER = 'order/'
const SCHEDULE = 'schedule/'
export default {
  user: {
    login: () => HOST + "login",
    logout: ()=> HOST + USER + "logout",
    signup: () => HOST + USER + "signup" ,
    getUserInfo: () => HOST + 'user',
  },
  foodtruck:{
    registerFoodTruck: () => HOST + 'foodtruck',
    registerFoodTruckReview: () => HOST + FOODTRUCK + 'review',
    getFoodTruckReview: (foodtruck_id) => HOST + FOODTRUCK + REVIEW + `${foodtruck_id}`,
    getFoodTruck: (foodtruck_id) => HOST + FOODTRUCK + `${foodtruck_id}`,
    getNearFoodTruck: () => HOST + FOODTRUCK + 'near',
    updateFoodTruck: () => HOST + 'foodtruck',
    search: (keyword) => HOST + FOODTRUCK + 'search/' + `${keyword}`,
  },
  survey: {
    survey: () => HOST + 'survey/'
  },
  orders: {
    customer:() => HOST + 'order',
    acceptOrders: (order_id) => HOST + ORDER + CEO + `${order_id}`,
    getCustomerOrdersAll: () => HOST + ORDER + CUSTOMER + 'all',
    getCeoOrders: () => HOST + ORDER + 'ceo',
    getCeoOrdersAll: () => HOST + ORDER + CEO + 'all',
    cancelOrders: () => HOST + 'order',
  },
  schedule: {
    setSchedule:() => HOST + 'schedule',
    cancelSchedule:(schedule_id) => HOST + SCHEDULE + `${schedule_id}`,
  },
}
