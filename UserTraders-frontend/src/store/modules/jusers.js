import http from "@/utils/http";
import router from "@/router/index.js";
const state = {
  listData: null,
  listDataDeatail: null,
  totalPage: null,
  page: 1,
  newlistData: null,
  memberUser: [

    { id: 1, name: "jin", email: "j@n.net", password: "123" },
    { id: 2, name: "jinn", email: "j@n.nest", password: "123" }

  ],
  isLogin: false,
  isLoginError: false,
  userInfo: null,


};

const getters = {};

const actions = {





  getList({ commit }, payload) {
    return http.process("user", "list", { page: payload }).then((data) => {
      commit("setListData", data);
    });
    /* return http.process("user", "item", { id: 1 }).then(data => {
      commit("setListData", data);
    }) */
  },
  getListDetail({ commit }, payload) {
    return http.process("user", "item", { id: payload }).then((data) => {

      commit("setListDataDetail", data);
    });
  },


  postCreate(str1, str2) {

    return http.process("user", "add", { name: str1, email: str2 })
      .then((res) => {
        console.log(res)

      }).catch(err => {
        console.log(err)
      });
  },



  deleteList(idx) {
    return new Promise((resolve, reject) => {
      return http.process("user", "delete", { id: idx }).then((res) => {
        resolve(res);
        console.log("성공")

      }).catch(err => {
        reject(err);
        console.log("실패")
      });
    });


  },


  putUpdate(str1, str2) {

    return http.process("user", "update", { name: str1, email: str2 })
      .then((res) => {
        console.log(res)

      }).catch(err => {
        console.log(err)
      });
  },
  postLogin(userobj) {
    console.log(userobj)
    return http.process("user", "login", {
      "email": "eve.holt@reqres.in",
      "password": "cityslicka"
    }
    ).then((res) => {
      console.log(res)
     

    }).catch(err => {
      console.log(err)
    });
  },





  login({ state, commit }, loginObj) {
    let selectUser = null
    state.memberUser.forEach(user => {
      if (user.email === loginObj.email) { selectUser = user }
    })
    if (selectUser === null || selectUser.password !== loginObj.password)
      commit("loginError")
    else {
      commit("loginSuccess", selectUser)

    }
  },
};

const mutations = {
  //로그인 성공 했을 때
  loginSuccess(state, payload) {
    console.log("Asdasd")
    state.isLogin = true
    state.isLoginError = false
    state.userInfo = payload
    router.push({name:'JunHome'})
  

  },

  //로그인 실패
  loginError(state) {

    state.isLogin = false
    state.isLoginError = true
  
  },


  setListData(state, data) {
    state.listData = data;
    state.totalPage = data.total_pages;

  },
  setListDataDetail(state, data) {
    state.listDataDeatail = data;

  },
};

export default {
  namespaced: true,
  getters,
  state,
  actions,
  mutations,
};
