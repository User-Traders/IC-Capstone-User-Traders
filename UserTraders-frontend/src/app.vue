<template>
  <v-app>
    <div>

      <v-app-bar flat color="orange accent-4" height="70" class="fixed-bar " fixed>
        <!-- <v-app-bar-nav-icon @click.stop="openMenu = !openMenu" color="white"></v-app-bar-nav-icon> -->
        <v-toolbar-title>
          <v-btn color="orange" fab small @click="$router.go(-1)">
            <v-icon color="white">mdi-arrow-left</v-icon>
          </v-btn>
        </v-toolbar-title>
        <v-toolbar-title style="color:white" class="ma-2">User Traders</v-toolbar-title>
        <v-spacer></v-spacer>
        <Togglesearch />
        <v-btn icon>
          <v-icon size="xx-large" color="white">mdi-cart</v-icon>
        </v-btn>

        <v-menu bottom left>
          <template v-slot:activator="{ on, attrs }">
            <v-btn dark icon v-bind="attrs" v-on="on">
              <v-icon size="xx-large">mdi-login</v-icon>
            </v-btn>
          </template>

          <v-list dense nav>
            <v-list-item :to="{ name: 'JunLogin'}" active-class="deep-purple--text text--accent-4">
              <v-list-item-content>
                <v-list-item-title>Login</v-list-item-title>
              </v-list-item-content>
            </v-list-item>

            <v-list-item :to="{ name: 'Create'}" active-class="deep-purple--text text--accent-3">

              <v-list-item-content>
                <v-list-item-title>Sign Up</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-menu>

      </v-app-bar>

      <navigationdrawer v-model="openMenu" />

    </div>
    <v-spacer></v-spacer>
    <v-main>

      <br><br>
      <br><br>
      <router-view></router-view>

    </v-main>

    <v-bottom-navigation color="primary" fixed>

      <v-btn>
        <span>홈</span>

        <v-icon large>mdi-home</v-icon>
      </v-btn>

      <v-btn>
        <span>쪽지</span>

        <v-icon large>mdi-chat</v-icon>
      </v-btn>

      <v-btn>
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
import navigationdrawer from "./components/ut-drawer.vue";
import Togglesearch from "./components/toggle-search.vue";
export default {

  data: () => ({
    openMenu: false,

  }),
  components: {
    navigationdrawer,
    Togglesearch,
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