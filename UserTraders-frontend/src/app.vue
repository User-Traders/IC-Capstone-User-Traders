<template>
  <v-app>
    <div>
      <v-card class="mx-auto" max-width="auto" fluid>
        <v-toolbar flat color="orange accent-4" height="90">
          <v-app-bar-nav-icon @click.stop="openMenu = !openMenu" color="white"></v-app-bar-nav-icon>
          <v-toolbar-title style="color:white">User Traders</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn icon>
            <v-icon size="xx-large" color="white">mdi-cart</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon size="xx-large" color="white">mdi-account</v-icon>
          </v-btn>
       
        </v-toolbar>
        
      
         <Togglesearch/>
      </v-card>
       

      <navigationdrawer v-model="openMenu" />
      <div v-if="this.$router.currentRoute.path=='/'">
        <Carousel />
      </div>
    </div>
    <v-spacer></v-spacer>
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
import navigationdrawer from "./components/ut-drawer.vue";
import Carousel from "./components/carousel.vue";
import Togglesearch from "./components/toggle-search.vue";
export default {

  data: () => ({
    openMenu: false,

   
  }),
  components: {
    navigationdrawer,
    Carousel,
    Togglesearch,
  },

};
</script>