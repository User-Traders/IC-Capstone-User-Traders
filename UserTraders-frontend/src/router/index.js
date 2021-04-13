import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store/modules/users.js"

Vue.use(VueRouter)


const Home1 = () => import(/* webpackChunkName: "jun" */ "../views/home.vue");
const JunHome = () => import(/* webpackChunkName: "jun" */ "../views/jun/jun-home.vue");
const JunCreate = () => import(/* webpackChunkName: "jun" */ "../views/jun/jun-create.vue");
const JunUpdate = () => import(/* webpackChunkName: "jun" */ "../views/jun/jun-update.vue");
const JunMypage = () => import(/* webpackChunkName: "jun" */ "../views/jun/jun-mypage.vue");
const JunLogin = () => import("../views/jun/jun-login.vue");
const Create = () => import("../views/usertraders/BoardCreate.vue");
const BoardDetail = () => import("../views/usertraders/BoardDetail.vue")
const UserLogin = () => import("../views/usertraders/UserLogin.vue")
const SignUp = () => import("../views/usertraders/SignUp.vue")
const Empty = () => import("../components/empty.vue")

const routes = [
  {
    path: "/",
    name: "Home1",
    component: Home1,
    meta: { isAuth: true },
    /*     beforeEnter: requireAuth() */

  },

  {
    path: "/create",
    name: "Create",
    component: Create,

  },
  {
    path: "/detail/:id",
    name: "BoardDetail",
    component: BoardDetail,
    props: true
  },
  {
    path: "/user",
    component: Empty,
    children: [
      {
        path: 'login',
        name: "UserLogin",
        component: UserLogin,
      },
      {
        path: 'signup',
        name: "SignUp",
        component: SignUp,
      },


    ]
  },




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
