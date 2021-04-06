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
        <v-btn icon>
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
    <!-- <v-btn :to="{ name: 'Create'}" fixed bottom fab right>
      <span>유트-등록</span>
      <v-icon large color="orange">mdi-plus</v-icon>
    </v-btn> -->
    <!-- <button type="button" class="transition-swing v-btn v-btn--bottom v-btn--is-elevated v-btn--fab v-btn--fixed v-btn--has-bg v-btn--right v-btn--round theme--light v-size--large primary" aria-label="Scroll to top" title="Scroll to top" style="z-index: 6; margin-bottom: 0px; transform-origin: center center;"><span class="v-btn__content"><span aria-hidden="true" class="v-icon notranslate theme--light"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" role="img" aria-hidden="true" class="v-icon__svg"><path d="M7.41,15.41L12,10.83L16.59,15.41L18,14L12,8L6,14L7.41,15.41Z"></path></svg></span></span></button> -->
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
        <span>카테고리</span>

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
  components: {

    Togglesearch,
  },
  computed: {
    ...mapState({
      isLogin: (state) => state.users.isLogin,
      isLoginError: (state) => state.users.isLoginError,
    }),
  },
  methods: {
    userLogout() {

      this.getUserLogout().then(() => {
        this.isLoading = false;
      });
    },
    ...mapActions({

      getUserLogout: "users/getUserLogout",

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