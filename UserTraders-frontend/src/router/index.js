import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store/modules/users.js"

Vue.use(VueRouter)


const Home1 = () => import(/* webpackChunkName: "jun" */ "../views/home.vue");
const Create = () => import("../views/usertraders/BoardCreate.vue");
const BoardDetail = () => import("../views/usertraders/BoardDetail.vue")
const UserLogin = () => import("../views/usertraders/UserLogin.vue")
const SignUp = () => import("../views/usertraders/SignUp.vue")
const Empty = () => import("../components/empty.vue")
const Mypage= ()=>import("../views/usertraders/Mypage.vue")
const Cart =()=>import("../views/usertraders/Cart.vue")
const Search =()=>import("../views/usertraders/Search.vue")
const Mail =()=>import("../views/usertraders/Mail.vue")
const MailWrite =()=>import("../views/usertraders/MailWrite.vue")
const MailGet =()=>import("../views/usertraders/MailGet.vue")
const MailSend =()=>import("../views/usertraders/MailSend.vue")
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
    path: "/search",
    name: "Search",
    component: Search,
    // props: true
  },
  {
    path: "/mail",
    name: "Mail",
    component: Mail,
     props: true
  },
  {
    path: "/mailwrite",
    name: "MailWrite",
    component: MailWrite,
    props: true
  },
  {
    path: "/mailget",
    name: "MailGet",
    component: MailGet,
    // props: true
  },
  {
    path: "/mailsend",
    name: "MailSend",
    component: MailSend,
    // props: true
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
      {
        path:'mypage',
        name:"Mypage",
        component:Mypage,
      },
      {
        path:'cart',
        name:'Cart',
        component:Cart,
      }
    ]
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
