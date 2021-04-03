import http from "@/utils/http";
import router from "@/router/index.js";
const state = {
  listData: null,
  listDataDeatail: null,
  loginTokken: null,
  detailImageurl: [],
  totalPage: null,
  page: 1,
  newlistData: null,

  isLogin: false,
  isLoginError: false,
  userInfo: null,


};

const getters = {};

const actions = {
  getList({ commit }) {
    return http.process("user", "list").then((data) => {
      console.log(data)
      commit("setListData", data);
    }).catch(err => {
      console.log(err)
    });
  },
  getListDetail({ commit }, payload) {
    return http.process("user", "listdetail", { id: payload }).then((data) => {
      commit("setListDataDetail", data);
    });
  },

  postUserLogin({ commit }, loginObj) {

    console.log(loginObj)
    return http.process("user", "login", loginObj
    ).then((res) => {
      commit("setLoginTokken", res);

    }).catch(err => {
      console.log(err)
      commit("loginError")
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
  loginSuccess(state) {
    state.isLogin = true
    state.isLoginError = false
    router.push({ name: 'Home1' })

  },

  //로그인 실패
  loginError(state) {
    state.isLogin = false
    state.isLoginError = true

  },


  setListData(state, data) {
    state.listData = data;
    // state.totalPage = data.total_pages;

  },
  setListDataDetail(state, data) {

    const arr = []
    arr.push(data.imageurl1)
    arr.push(data.imageurl2)
    arr.push(data.imageurl3)
    state.detailImageurl = arr;
    state.listDataDeatail = data;

  },
  setLoginTokken(state, data) {
    console.log(data)
    state.loginTokken = data
    alert("Complete Login")
    router.push({ name: 'Home1' })
  }
};

export default {
  namespaced: true,
  getters,
  state,
  actions,
  mutations,
};
