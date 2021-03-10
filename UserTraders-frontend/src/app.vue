<template>
  <v-app>
    <div>
      <v-card class="mx-auto" max-width="auto" fluid>

        <v-toolbar flat color="orange accent-4" height="90">
          <v-app-bar-nav-icon @click.stop="openMenu = !openMenu" color="white"></v-app-bar-nav-icon>

          <v-toolbar-title style="color:white">User Traders</v-toolbar-title>
          <v-spacer></v-spacer>
          <Togglesearch />
          <v-btn icon>
            <v-icon size="xx-large" color="white">mdi-cart</v-icon>
          </v-btn>

          <v-menu bottom left>
            <template v-slot:activator="{ on, attrs }">
              <v-btn dark icon v-bind="attrs" v-on="on">
                <v-icon size="xx-large">mdi-account</v-icon>
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
                  <v-list-item-title>Mypage</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-menu>

        </v-toolbar>

      </v-card>

      <navigationdrawer v-model="openMenu" />

    </div>
    <v-spacer></v-spacer>
    <v-main>
      <router-view></router-view>
    </v-main>
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
<style>
.fixed {
  position: fixed;
}
</style>