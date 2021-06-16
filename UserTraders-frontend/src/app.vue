<template>
  <v-app>
    <div>
      <v-app-bar
        flat
        color="orange accent-4"
        height="70"
        class="fixed-bar "
        fixed
      >
        <v-toolbar-title>
          <v-btn icon @click="$router.go(-1)">
            <v-icon size="xx-large" color="white">mdi-arrow-left</v-icon>
          </v-btn>
        </v-toolbar-title>

        <v-toolbar-title class="ma-2">
          <a
            href="/"
            style="text-decoration-line:none ; color:white; font-size : large "
          >
            User Traders
          </a>
        </v-toolbar-title>

        <v-spacer></v-spacer>

        <div v-if="loginflag">
          <v-btn icon :to="{ name: 'Cart' }">
            <v-icon large color="white">mdi-cart</v-icon>
          </v-btn>
        </div>

        <div v-if="loginflag">
          <v-btn icon :to="{ name: 'Mypage' }">
            <v-icon large color="white">mdi-information</v-icon>
          </v-btn>
        </div>

        <div v-if="!loginflag">
          <v-menu bottom left>
            <template v-slot:activator="{ on, attrs }">
              <v-btn dark icon v-bind="attrs" v-on="on">
                <v-icon size="xx-large">mdi-account-alert-outline</v-icon>
              </v-btn>
            </template>

            <v-list dense nav>
              <v-list-item
                :to="{ name: 'UserLogin' }"
                active-class="deep-purple--text text--accent-4"
              >
                <v-list-item-content>
                  <v-list-item-title style="font-size : medium"
                    >로그인
                    <v-icon>mdi-arrow-right-thick</v-icon>
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>

              <v-list-item
                :to="{ name: 'SignUp' }"
                active-class="deep-purple--text text--accent-3"
              >
                <v-list-item-content>
                  <v-list-item-title style="font-size : medium"
                    >회원가입
                    <v-icon>mdi-account-plus</v-icon>
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
        <div v-else>
          <v-menu bottom left>
            <template v-slot:activator="{ on, attrs }">
              <v-btn dark icon v-bind="attrs" v-on="on">
                <v-icon size="xx-large">mdi-account-check-outline</v-icon>
              </v-btn>
            </template>
            <v-list dense nav>
              <v-list-item
                active-class="deep-purple--text text--accent-3"
                style="cursor : pointer"
              >
                <v-list-item-content>
                  <v-list-item-title
                    @click="userLogout"
                    style="font-size : medium"
                    >로그아웃
                    <v-icon>mdi-logout</v-icon>
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
      </v-app-bar>
    </div>
    <v-spacer></v-spacer>
    <v-main>
      <router-view></router-view>
    </v-main>
    <v-bottom-navigation style="padding-top:10px" color="#ff6d00" fixed>
      <v-btn :to="{ name: 'Home1' }">
        <span style="color : #00000099; font-size : medium">홈</span>
        <v-icon large>mdi-home</v-icon>
      </v-btn>
      <div v-if="loginflag">
      <v-btn :to="{ name: 'Mail' }">
        <span style="color : #00000099;  font-size : medium">쪽지함</span>
        <v-icon large>mdi-chat</v-icon>
      </v-btn>
      </div>

      <v-btn :to="{ name: 'Search' }">
        <span style="color : #00000099; font-size : medium">검색</span>
        <v-icon large>mdi-shopping-search</v-icon>
      </v-btn>

      <div v-if="loginflag">
        <v-btn :to="{ name: 'Create' }">
          <span style="color : #00000099; font-size : medium">중고거래</span>
          <v-icon large color="blue">mdi-plus-circle-outline</v-icon>
        </v-btn>
      </div>

      <div v-if="loginflag">
        <div
          id="kakao-talk-channel-add-button"
          data-channel-public-id="_paeus"
          data-size="small"
          data-support-multiple-densities="true"
          
          style=" width : 36px ; height : 36px ; margin-top : 5px ; "
        ></div>
       </div>
       
    </v-bottom-navigation>
  </v-app>
</template>

<script>
window.kakaoAsyncInit = function() {
  Kakao.Channel.createAddChannelButton({
    container: "#kakao-talk-channel-add-button",
  });
};

(function(d, s, id) {
  var js,
    fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s);
  js.id = id;
  js.src = "https://developers.kakao.com/sdk/js/kakao.channel.min.js";
  fjs.parentNode.insertBefore(js, fjs);
})(document, "script", "kakao-js-sdk");

import { mapState, mapActions } from "vuex";
export default {
  data: () => ({
    openMenu: false,
    loginflag: false,
  }),
  mounted() {},

  computed: {
    ...mapState({
      isLogin: (state) => state.users.isLogin,
      isLoginError: (state) => state.users.isLoginError,
    }),
  },
  mounted() {
    this.loginCheck();
  },
  methods: {
    loginCheck() {
      if (!localStorage.getItem("user")) {
        this.loginflag = false;
      } else {
        this.loginflag = true;
      }
    },

    userLogout() {
      this.getUserLogout().then(() => {
        this.isLoading = false;
        localStorage.removeItem("user");
        this.$router.push({ name: "Home1" });
      });
    },
    ...mapActions({
      getUserLogout: "auth/getUserLogout",
    }),
  },
};
</script>
<style scoped>
.fixed-bar {
  position: sticky;
  position: -webkit-sticky; /* for Safari */
  top: 6em;
  z-index: 2;
}
</style>
