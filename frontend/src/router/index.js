import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "main",
    component: () => import("@/views/main/MainView.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/account/LoginView.vue"),
  },
  {
    path: "/signup",
    name: "signup",
    component: () => import("@/views/account/SignUpView.vue"),
  },
  {
    path: "/truckregister",
    name: "truckregister",
    component: () => import("@/views/account/TruckRegisterView.vue"),
  },
  {
    path: "/cart",
    name: "cart",
    component: () => import("@/views/customer/CartView.vue"),
  },
  {
    path: "/find",
    name: "find",
    component: () => import("@/views/customer/FindView.vue"),
  },
  {
    path: "/menuselect",
    name: "menuselect",
    component: () => import("@/views/customer/MenuSelectView.vue"),
  },
  {
    path: "/menudetail",
    name: "menudetail",
    component: () => import("@/views/customer/MenuDetail.vue"),
  },
  {
    path: "/myprofile",
    name: "myprofile",
    component: () => import("@/views/customer/MyProfileView.vue"),
  },
  {
    path: "/notice",
    name: "notice",
    component: () => import("@/views/customer/NoticeView.vue"),
  },
  {
    path: "/orderlist",
    name: "orderlist",
    component: () => import("@/views/customer/OrderListView.vue"),
  },
  {
    path: "/order",
    name: "order",
    component: () => import("@/views/customer/OrderView.vue"),
  },
  {
    path: "/pay",
    name: "pay",
    component: () => import("@/views/customer/PayView.vue"),
  },
  {
    path: "/survey",
    name: "survey",
    component: () => import("@/views/customer/SurveyView.vue"),
  },
  {
    path: "/ceomain",
    name: "ceomain",
    component: () => import("@/views/ceo/CeoMainView.vue"),
  },
  {
    path: "/mytruck",
    name: "mytruck",
    component: () => import("@/views/ceo/MyTruckView.vue"),
  },
  {
    path: "/salesstatistics",
    name: "salesstatistics",
    component: () => import("@/views/ceo/SalesStatisticsView.vue"),
  },
  {
    path: "/schedule",
    name: "schedule",
    component: () => import("@/views/ceo/ScheduleView.vue"),
  },
  {
    path: "/scheduleupdate",
    name: "scheduleupdate",
    component: () => import("@/views/ceo/ScheduleUpdateView.vue"),
  },
  {
    path: "/start",
    name: "start",
    component: () => import("@/views/ceo/StartView.vue"),
  },
  {
    path: "/surveycheck",
    name: "surveycheck",
    component: () => import("@/views/ceo/SurveyCheckView.vue"),
  },
  {
    path: "/success",
    name: "paysuccess",
    component: () => import("@/components/customer/PayOk.vue"),
  },
  {
    path: "/fail",
    name: "payfail",
    component: () => import("@/components/customer/PayFail.vue"),
  },
  {
    path: "/cancel",
    name: "paycancel",
    component: () => import("@/components/customer/PayCancel.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
