<template>
  <div>
    <!-- <Carousel /> -->
    {{listData}}

    <v-container v-if="listData" three-line>
      <v-row>
        <v-col v-for="(item, i) in listData" :key="`item-${i}`" cols="12" sm="4">
          <div>
            <v-card class="mx-auto" max-width="344">
            


              <v-img :src=" '/images/' +listData.imageurl1" width="344" height="344"></v-img>
              <v-icon color="pink">mdi-delete</v-icon>
            
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
