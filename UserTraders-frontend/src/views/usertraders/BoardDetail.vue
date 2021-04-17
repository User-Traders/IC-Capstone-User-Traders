<template>
  <div>

    <loding v-if="isLoading" />
    <div v-if="listDataDeatail">

      <v-row class="fill-height " align="center" justify="center">
        <v-card class="pa-2" tile flat>
          <v-carousel progress-color="orange">
            <v-carousel-item v-for="(item, i) in detailImageurl" :key="i" v-bind:src="item |loadImgOrPlaceholder" width="344" height="auto" reverse-transition="fade-transition" transition="fade-transition"></v-carousel-item>
          </v-carousel>

          <v-card-title>
            <h4>{{ listDataDeatail.title }}</h4>
            <v-btn class="mx-2" fab dark small color="pink">
              <v-icon dark>
                mdi-heart
              </v-icon>
            </v-btn>
          </v-card-title>
          <v-card-subtitle>
            {{listDataDeatail.content}}
          </v-card-subtitle>
          <v-card-text>{{ `가격 : ${listDataDeatail.price} 원 ` }}</v-card-text>

          <v-btn color="blue-grey" class="ma-2 white--text" @click="cartAdd(listDataDeatail.id)">
            Add To Cart
            <v-icon right dark>
              mdi-cart
            </v-icon>
          </v-btn>

          <v-btn color="blue-grey" class="ma-2 white--text">
            쪽지 보내기
            <v-icon right dark>
              mdi-arrow-right-bold
            </v-icon>
          </v-btn>

        </v-card>

      </v-row>
      <br>
      <br>
      <br>
    </div>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import Loding from "./jun-loding.vue";
import http from "@/utils/http";
export default {
  props: ["id"],

  components: {
    Loding,
  },
  data() {
    return {
      isLoading: true,
    };
  },
  filters: {
    loadImgOrPlaceholder: function (path) {
      return require("@/assets/images/" + path)
    }
  },
  computed: {
    ...mapState({
      listDataDeatail: (state) => state.users.listDataDeatail,
      detailImageurl: (state) => state.users.detailImageurl,
    }),
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      console.log("detail init...");
      this.getListDetail(this.id);
    },
    getListDetail(id) {
      this._getListDetail(id).then(() => {
        this.isLoading = false;
      });
    },
    cartAdd(id) {
      console.log(id)
      return http.process("cart", "register",id)
        .then((res) => {
          console.log(res)
          this.userBList = res
        }).catch((err) => {
          console.log(err)
        })
    },

    ...mapActions({
      _getListDetail: "users/getListDetail",

    }),
  },

};
</script>
