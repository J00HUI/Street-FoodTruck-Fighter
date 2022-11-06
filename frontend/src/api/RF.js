const HOST = "http://localhost:8000/api/"
// const api = " api/"
const USER = "user/"
const CEO = 'ceo/'
const CUSTOMER = 'customer/'
const FOODTRUCK = "foodtruck/"
const REVIEW = 'review/'
const ORDER = 'order/'
const SCHEDULE = 'schedule/'
export default {
  user: {
    login: () => HOST + "login/",
    logout: ()=> HOST + USER + "logout/",
    signup: () => HOST + USER + "signup/" ,
    getUserInfo: () => HOST + USER
  },
  foodtruck:{
    registerFoodTruck: () => HOST + FOODTRUCK,
    registerFoodTruckReview: () => HOST + FOODTRUCK + REVIEW,
    getFoodTruckReview: (foodtruck_id) => HOST + FOODTRUCK + REVIEW + `${foodtruck_id}/`,
    getFoodTruck: (foodtruck_id) => HOST + FOODTRUCK + `${foodtruck_id}/`,
    getNearFoodTruck: () => HOST + FOODTRUCK + 'near/',
    updateFoodTruck: () => HOST + FOODTRUCK,
    search: (keyword) => HOST + FOODTRUCK + 'search/' + `${keyword}`,
  },
  survey: {
    survey: () => HOST + 'survey/'
  },
  orders: {
    customer:() => HOST + ORDER,
    acceptOrders: (order_id) => HOST + ORDER + CEO + `${order_id}/`,
    getCustomerOrdersAll: () => HOST + ORDER + CUSTOMER + 'all/',
    getCeoOrders: () => HOST + ORDER + CEO,
    getCeoOrdersAll: () => HOST + ORDER + CEO + 'all/',
    cancelOrders: () => HOST + ORDER
  },
  schedule: {
    setSchedule:() => HOST + SCHEDULE,
    cancelSchedule:(schedule_id) => HOST + SCHEDULE + `${schedule_id}/`,
  },
}
