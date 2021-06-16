import http from "@/utils/http";
import router from "@/router/index.js";
const state = {
    isLogin: false,
    isLoginError: false,
};

const getters = {};
const actions = {
    postUserLogin({ commit }, loginObj) {
        return http.process("user", "login", loginObj
        ).then((res) => {
            commit("setLoginTokken", res);
            localStorage.setItem("user", res)
            http.process("user", "userinfo", null, { token: res }
            ).then(res => {
                console.log(res)
                commit("setUserInfo", res);
                alert("로그인 되었습니다.")
                router.go({name:'Home1'})
            }).catch(err => { console.log(err) })
        }).catch(err => {
            console.log(err)
            console.log(err.message)
            if (
                err.message === "가입되지 않은 E-MAIL 입니다." ||
                err.message === "잘못된 비밀번호입니다."
              ) {
                alert(err.message);
                this.$router.push(this.$route.query.redirect || '/user/login')
              }
            commit("logoutState")
        });
    },
    getUserLogout({ commit }) {
            return http.process("user", "logout")
                .then((res) => {
                    console.log(res)
                    commit("logoutState")
                    router.go({name:'Home1'})
                }).catch((err) => { 
                    console.log(err) 
                    this.$router.push(this.$route.query.redirect || '/')
                })

    },
    
    validTokenError({ commit }) {
        localStorage.removeItem('user')
        commit("logoutState")
        this.$router.push({ name: 'UserLogin' });
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
    //로그인 성공
    setLoginTokken(state) {
        state.isLogin = true
        state.isLoginError = false

    },
    setUserInfo(state, data) {
        state.userInfo = data
    },

    //로그인 실패
    loginError(state) {
        state.isLogin = false
        state.isLoginError = true

    },
    //로그아웃 처리 상태값 변환
    logoutState(state) {
        state.isLogin = false
        state.isLoginError = false
        state.userInfo = null
        localStorage.removeItem('user')
    },


};

export default {
    namespaced: true,
    getters,
    state,
    actions,
    mutations,
};
