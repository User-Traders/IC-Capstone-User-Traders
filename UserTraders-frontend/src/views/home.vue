<template>
  <div>
    <!-- <v-img :src="@/assets/images3f3d1070ddf9408da9e2773945d8a261.jpg" width="344" height="344"></v-img> -->
    <!-- <Carousel /> -->
    {{listData.imageurl1}}

    <v-container v-if="listData" three-line>
      <v-row>
        <v-col v-for="(item, i) in listData" :key="`item-${i}`" cols="12" sm="4">
          <div>
            <v-card class="mx-auto" max-width="344">
              <img :src="listImageurl1">
<!-- <img src="@/assets/images/3f3d1070ddf9408da9e2773945d8a261.jpg"> -->
              <v-card-title>
                {{ item.title }}
              </v-card-title>

              <v-card-subtitle>
                <v-list-item-icon>
                  <v-icon color="indigo">
                    mdi-content
                  </v-icon>
                  {{ item.content }}
                </v-list-item-icon>

              </v-card-subtitle>

              <v-card-actions>
                <!-- <v-btn color="orange lighten-2" text :to="{ name: 'JunDetail', params:{id:item.id}}">
                    Detail
                  </v-btn> -->

                <v-spacer></v-spacer>

              </v-card-actions>

            </v-card>
          </div>
        </v-col>
      </v-row>
      <!-- <Paging :totalPage="listData.total_pages" @movePage="movePage" /> -->

    </v-container>

  </div>
</template>

<script>
// import Carousel from "../components/carousel.vue";
import { mapState, mapActions } from "vuex";


export default {

  data() {
    return {

    };
  },
  components: {
    // Carousel,


  },
  computed: {
    ...mapState({
      listData: (state) => state.jusers.listData,
      listImageurl1 :(state)=> state.jusers.listImageurl1,

    }),
  },
  mounted() {
    this.init();
  },
  methods: {


    init() {
      console.log("home init...");
      this.getList();
    },
    getList() {

      this._getList().then(() => {


      });

    },

    ...mapActions({
      _getList: "jusers/getList",




    }),
  },
};
</script>
