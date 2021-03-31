<template>
  <div>
    <v-container v-if="listData" three-line>
      <v-row>
        <v-col v-for="(item, i) in listData" :key="`item-${i}`" cols="12" sm="4">
          <div>
            <v-hover v-slot="{ hover }">

              <v-card class="mx-auto" max-width="344">
                <div @click="detailPush(item.id)">
                  <v-img v-bind:src="item.imageurl1 |loadImgOrPlaceholder" :aspect-ratio="16/16" height="mx-auto">
                    <v-expand-transition>
                      <div v-if="hover" class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text" style="height: 100%;">
                        {{ item.content }}
                      </div>
                    </v-expand-transition>
                  </v-img>
                </div>
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

    </v-container>
    <infinite-loading @infinite="infiniteHandler" spinner="waveDots" forceUseInfiniteWrapper></infinite-loading>

  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import InfiniteLoading from 'vue-infinite-loading';
import http from "@/utils/http";
export default {

  data() {
    return {
      limit: 1,
      listData: [],
    };
  },
  filters: {
    loadImgOrPlaceholder: function (path) {
      return require("@/assets/images/" + path)
    }
  },
  components: {
    InfiniteLoading,


  },
  computed: {
    ...mapState({
      // listData: (state) => state.jusers.listData,
    }),

  },
  comments: {
    InfiniteLoading
  },
  mounted() {
    this.init();
  },
  methods: {
    infiniteHandler($state) {
      http.process("user", "listInfinte", {
        limit: this.limit,
      },
      )
        .then((res) => {
          setTimeout(() => {
            if (res.length) {
              console.log(res)
              this.listData = this.listData.concat(res);
              $state.loaded();
              this.limit += 2;
            } else {
              $state.complete();
              alert("더 이상 검색 결과가 없습니다")
            }
          }, 1000);
        })
        .catch((err) => {
          console.log(err)
        });
    },

    init() {
      console.log("home init...");

    },
    // getList() {
    //   this._getList().then(() => {
    //   });

    // },
    detailPush(id) {
      this.$router.push({ name: 'BoardDetail', params: { id: id } });
    },
    imageConversion(imageurl) {

      return require(imageurl)
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