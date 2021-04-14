<template>
  <v-app>
    <div>
      <v-app-bar flat color="orange accent-4" height="70" class="fixed-bar " fixed>
        <!-- <v-app-bar-nav-icon @click.stop="openMenu = !openMenu" color="white"></v-app-bar-nav-icon> -->
        <v-toolbar-title>

          <v-btn icon @click="$router.go(-1)">
            <v-icon size="xx-large" color="white">mdi-arrow-left</v-icon>
          </v-btn>

        </v-toolbar-title>
        <v-toolbar-title style="color:white" class="ma-2">User Traders</v-toolbar-title>
        <v-spacer></v-spacer>
        <Togglesearch />
        <v-btn icon :to="{ name: 'Cart'}">
          <v-icon size="xx-large" color="white">mdi-cart</v-icon>
        </v-btn>
        <div v-if="!isLogin">
          <v-menu bottom left>
            <template v-slot:activator="{ on, attrs }">
              <v-btn dark icon v-bind="attrs" v-on="on">
                <v-icon size="xx-large">mdi-account-alert-outline</v-icon>
              </v-btn>
            </template>

            <v-list dense nav>
              <v-list-item :to="{ name: 'UserLogin'}" active-class="deep-purple--text text--accent-4">
                <v-list-item-content>
                  <v-list-item-title>Login</v-list-item-title>
                </v-list-item-content>
              </v-list-item>

              <v-list-item :to="{ name: 'SignUp'}" active-class="deep-purple--text text--accent-3">

                <v-list-item-content>
                  <v-list-item-title>Sign Up</v-list-item-title>
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
              <v-list-item active-class="deep-purple--text text--accent-4">
                <v-list-item-content>
                  <v-list-item-title @click="userLogout">Logout</v-list-item-title>
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
    <v-bottom-navigation color="orange" fixed>

      <v-btn :to="{ name: 'Home1'}">
        <span>홈</span>

        <v-icon large>mdi-home</v-icon>
      </v-btn>

      <v-btn>
        <span>쪽지</span>
        <v-icon large>mdi-chat</v-icon>
      </v-btn>
      <v-btn>
        <span>카테고리{{isLogin}}</span>

        <v-icon large>mdi-shopping-search</v-icon>
      </v-btn>
      <v-btn :to="{ name: 'Mypage'}">
        <span>나의-유트</span>

        <v-icon large>mdi-account</v-icon>
      </v-btn>
      <v-btn :to="{ name: 'Create'}">
        <span>유트-등록</span>
        <v-icon large color="orange">mdi-plus</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </v-app>
</template>

<script>
import { mapState, mapActions } from "vuex";
import Togglesearch from "./components/toggle-search.vue";
export default {

  data: () => ({
    openMenu: false,
  }),
  mounted() {

  },
  components: {
    Togglesearch,
  },
  computed: {

    ...mapState({
      isLogin: (state) => state.users.isLogin,
      isLoginError: (state) => state.users.isLoginError,
    }),

  },
  watch: {
    loginCheck() {
      if (!localStorage.getItem("user")) {
        return false
      }
      else {
        return true
      }
    },
  },
  methods: {
    userLogout() {
      this.getUserLogout().then(() => {
        this.isLoading = false;
      });
    },
    // tokenCheck() {
    //   const token = localStorage.getItem("user")
    //   console.log("zzzzz"+token)
    //   if (localStorage.getItem("user")) {
    //     this.isLogin = true


    //   }
    //   else {
    //     this.isLogin = false
    //   }
    // },
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