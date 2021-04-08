<template>
  <div>
    <v-container v-if="listData" three-line>
      <br>
      <br>
      <br>
      <br>
      <v-row>
        <v-col v-for="(item, i) in listData" :key="`item-${i}`" cols="12" sm="4">
          <div>
            <v-hover v-slot="{ hover }">

              <v-card class="mx-auto" max-width="344">
                <div @click="detailPush(item.id)">
                  <v-img v-bind:src="item.imageurl1 |loadImgOrPlaceholder" :aspect-ratio="11/8" height="mx-auto">
                    <v-expand-transition>
                      <div v-if="hover" class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text" style="height: 100%;">
                      </div>
                    </v-expand-transition>
                  </v-img>
                </div>
                <v-card-text class="pt-6" style="position: relative;">
                  <v-btn absolute color="pink darken-1" class="white--text" fab small right top>
                    <v-icon>mdi-heart</v-icon>
                  </v-btn>

                  <h3 class=" font-weight-bold orange--text mb-2">
                    {{item.category.name}} : {{item.title}}
                  </h3>
                  <div class="font-weight-medium title mb-2">
                    {{ item.price | money }} won
                  </div>
                  {{item.modifiedDate|timeForToday}}
                </v-card-text>
              </v-card>

            </v-hover>

          </div>
        </v-col>
      </v-row>

    </v-container>
    <infinite-loading @infinite="infiniteHandler" spinner="waveDots" forceUseInfiniteWrapper></infinite-loading>
    <br>
    <br>
    <br>
  </div>
</template>

<script>
import { mapActions } from "vuex";
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
    },
    money: function (value) {
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    timeForToday: function (value) {
      const today = new Date();
      const timeValue = new Date(value);

      const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
      if (betweenTime < 1) return '방금전';
      if (betweenTime < 60) {
        return `${betweenTime}분전`;
      }

      const betweenTimeHour = Math.floor(betweenTime / 60);
      if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간전`;
      }

      const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
      if (betweenTimeDay < 365) {
        return `${betweenTimeDay}일전`;
      }

      return `${Math.floor(betweenTimeDay / 365)}년전`;
    }
  },
  components: {
    InfiniteLoading,
  },
  computed: {
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
      })
        .then((res) => {
          setTimeout(() => {
            if (res.length) {
              this.listData = this.listData.concat(res);
              $state.loaded();
              this.limit += 1;
            } else {
              $state.complete();
              alert("더 이상 목록이 존재하지 않습니다.")
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

    detailPush(id) {
      this.$router.push({ name: 'BoardDetail', params: { id: id } });
    },
    imageConversion(imageurl) {

      return require(imageurl)
    },

    ...mapActions({
      _getList: "users/getList",
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