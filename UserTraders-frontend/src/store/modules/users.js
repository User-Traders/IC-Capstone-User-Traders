import http from "@/utils/http";
const state = {
  listData: null,
  listDataDeatail: null,
  detailImageurl: [],
  totalPage: null,
  page: 1,
  newlistData: null,
  categories: [],
};

const getters = {};
const actions = {
  getList({ commit }) {
    return http.process("user", "list").then((data) => {
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
  getCategories({ commit }) {

    return http.process("user", "categoryAll")
      .then((data => {
        commit("setCategories", data);
      }
      )).catch((err) => { console.log(err) })
  },


};

const mutations = {
  setListData(state, data) {
    state.listData = data;
  },
  setListDataDetail(state, data) {
    const arr = []
    arr.push(data.imageurl1)
    arr.push(data.imageurl2)
    arr.push(data.imageurl3)
    state.detailImageurl = arr;
    state.listDataDeatail = data;
  },
  setCategories(state, data) {
    state.categories = data
  },

};

export default {
  namespaced: true,
  getters,
  state,
  actions,
  mutations,
};
