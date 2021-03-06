<template>
  <v-app>
    <div>
      <v-card class="mx-auto" max-width="auto">
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
          <v-btn icon @click="$refs.search.focus()">
            <v-icon size="xx-large" color="white">mdi-magnify</v-icon>
          </v-btn>
        </v-toolbar>
        <br>
        
        <v-container class="py-0">
          <v-row align="center" justify="start">
            <v-col v-for="(selection, i) in selections" :key="selection.text" class="shrink">
              <v-chip :disabled="loading" close @click:close="selected.splice(i, 1)">
                <v-icon left v-text="selection.icon"></v-icon>
                {{ selection.text }}
              </v-chip>
            </v-col>

            <v-col v-if="!allSelected" cols="12">
              <v-text-field ref="search" v-model="search" full-width hide-details label="Search" single-line></v-text-field>
            </v-col>
          </v-row>
        </v-container>

        <v-divider v-if="!allSelected"></v-divider>

        <v-list>
          <template v-for="item in categories">
            <v-list-item v-if="!selected.includes(item)" :key="item.text" :disabled="loading" @click="selected.push(item)">
              <v-list-item-avatar>
                <v-icon :disabled="loading" v-text="item.icon"></v-icon>
              </v-list-item-avatar>
              <v-list-item-title v-text="item.text"></v-list-item-title>
            </v-list-item>
          </template>
        </v-list>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn :disabled="!selected.length" :loading="loading" color="purple" text @click="next">
            Next
          </v-btn>
        </v-card-actions>
      </v-card>

      <navigationdrawer v-model="openMenu" />
      <div v-if="this.$router.currentRoute.path=='/'">
        <Carousel />
      </div>
    </div>
    <v-spacer></v-spacer>
    <v-main>
      <router-view></router-view>
      <!--      <HelloWorld/>-->
    </v-main>
  </v-app>
</template>

<script>
import navigationdrawer from "./views/usertraders/ut-drawer.vue";
import Carousel from "./views/usertraders/carousel.vue";
export default {

  data: () => ({
    openMenu: false,

    items: [
      {
        text: '중고책',
        icon: 'mdi-book',
      },
      {
        text: '물품',
        icon: 'mdi-folder-star-multiple',
      },
      {
        text: '수업',
        icon: 'mdi-calendar-range',
      },

    ],
    loading: false,
    search: '',
    selected: [],
  }),
  components: {
    navigationdrawer,
    Carousel,
  },
  computed: {
    allSelected() {
      return this.selected.length === this.items.length
    },
    categories() {
      const search = this.search.toLowerCase()

      if (!search) return this.items

      return this.items.filter(item => {
        const text = item.text.toLowerCase()

        return text.indexOf(search) > -1
      })
    },
    selections() {
      const selections = []

      for (const selection of this.selected) {
        selections.push(selection)
      }

      return selections
    },
  },

  watch: {
    selected() {
      this.search = ''
    },
  },

  methods: {
    next() {
      this.loading = true

      setTimeout(() => {
        this.search = ''
        this.selected = []
        this.loading = false
      }, 2000)
    },
  },
};
</script>