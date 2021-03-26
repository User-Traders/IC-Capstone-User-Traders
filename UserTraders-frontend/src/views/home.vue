<template>
  <div>
    <!-- <Carousel /> -->

    <v-container v-if="listData" three-line>
      <v-row>
        <v-col v-for="(item, i) in listData" :key="`item-${i}`" cols="12" sm="4">
          <div>
            <v-hover v-slot="{ hover }">
              <v-card class="mx-auto" max-width="344">
                <!-- <img :src="require(listDataImageUrl1[i])"> -->
                <!-- <img v-bind:src="listDataImageUrl1[i] |loadImgOrPlaceholder"> -->
                <!-- <img  v-bind:src="listDataImageUrl1[i] |loadImgOrPlaceholder" width="mx-auto" height="200"> -->

                <v-img v-bind:src="listDataImageUrl1[i] |loadImgOrPlaceholder" :aspect-ratio="16/16" height="mx-auto">
                  <v-expand-transition>
                    <div v-if="hover" class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text" style="height: 100%;">
                      {{ item.content }}
                    </div>
                  </v-expand-transition>
                </v-img>

                <v-card-text class="pt-6" style="position: relative;">
                  <v-btn absolute color="orange" class="white--text" fab large right top>
                    <v-icon>mdi-cart</v-icon>
                  </v-btn>

                  <h3 class="display-1 font-weight-light orange--text mb-2">
                    {{item.title}}
                  </h3>
                  <div class="font-weight-light title mb-2">
                    {{item.price}} won
                  </div>
                </v-card-text>
              </v-card>
            </v-hover>
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
  filters: {
    loadImgOrPlaceholder: function (path) {


      return require("@/assets/images/" + path)

    }
  },
  components: {
    // Carousel,


  },
  computed: {
    ...mapState({
      listData: (state) => state.jusers.listData,
      listDataImageUrl1: (state) => state.jusers.listDataImageUrl1,

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
    imageConversion(imageurl) {
      console.log(String(imageurl))
      return require(String(imageurl))
    },

    ...mapActions({
      _getList: "jusers/getList",




    }),
  },
};
</script>
<style>
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.5;
  position: absolute;
  width: 100%;
}
</style>