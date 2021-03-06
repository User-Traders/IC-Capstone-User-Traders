import Vue from "vue";
import VueRouter from "vue-router";
import Empty from "../views/jun/empty.vue";
import store from "@/store/modules/jusers.js"

Vue.use(VueRouter)


const Home1 = () => import(/* webpackChunkName: "jun" */ "../views/home.vue");
const JunHome = () => import(/* webpackChunkName: "jun" */ "../views/jun/jun-home.vue");
const JunCreate = () => import(/* webpackChunkName: "jun" */ "../views/jun/jun-create.vue");
const JunDetail = () => import(/* webpackChunkName: "jun" */ "../views/jun/jun-detail.vue");
const JunUpdate = () => import(/* webpackChunkName: "jun" */ "../views/jun/jun-update.vue");
const JunMypage = () => import(/* webpackChunkName: "jun" */ "../views/jun/jun-mypage.vue");
const JunLogin = () => import("../views/jun/jun-login.vue");
const restTest = () => import(/* webpackChunkName: "jun" */ "../views/jun/rest-test.vue");
const routes = [
  {
    path: "/",
    name: "Home1",
    component: Home1,
    meta: { isAuth: true },
    /*     beforeEnter: requireAuth() */
  },
  
  {
    path: "/jun",
    component: Empty,
    children: [
      {
        path: "home",
        name: "JunHome",
        component: JunHome
      },
      {
        path: "create",
        name: "JunCreate",
        meta: { pageAuth: true },
        component: JunCreate
      },
      {
        path: "detail/:id",
        name: "JunDetail",
        component: JunDetail,
        props: true

      },
      {
        path: ":id/edit",
        name: "JunUpdate",

        component: JunUpdate,
        props: true
      },
      {
        path: "login",
        name: "JunLogin",
        meta: { loginAuth: true },
        component: JunLogin,

      },
      {
        path: "mypage",
        name: "JunMypage",
        meta: { pageAuth: true },
        component: JunMypage,

      },
      {
        path : "rest",
        name : "restTest",
        component : restTest,
      }
    ],
  }
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  scrollBehavior() {
    return { x: 0, y: 0 };
  },
  routes,
});
//형준
router.beforeEach((to, from, next) => {//login page 처리
  if (to.matched.some(record => record.meta.loginAuth)) {
    if (store.state.isLogin == false) {
      next()
    } else {
      alert("Login complete")
      next("/jun/home")
    }
  } else {
    next() // 반드시 next()를 호출하십시오!
  }
})
router.beforeEach((to, from, next) => {// pageAuth
  if (to.matched.some(record => record.meta.pageAuth)) {

    if (store.state.isLogin == true) {
      next()
    } else {
      alert("Login please")
      next("/jun/login")
    }
  } else {
    next() // 반드시 next()를 호출하십시오!
  }
})


export default router;
