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
            <v-btn class="mx-2" fab dark medium color="pink">
              <v-icon dark>
                mdi-heart
              </v-icon>
              {{listDataDeatail.likecount}}
            </v-btn>
          </v-card-title>
          <v-card-subtitle>
            {{listDataDeatail.content}}
          </v-card-subtitle>
          <v-card-text>가격 : {{ listDataDeatail.price}}원 </v-card-text>

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
import myMixin from "@/filter";
export default {
  mixins: [myMixin],
  props: ["id"],

  components: {
    Loding,
  },
  data() {
    return {
      isLoading: true,
      token: "",
    };
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
      this.token = localStorage.getItem("user")
    },
    getListDetail(id) {
      this._getListDetail(id).then(() => {
        this.isLoading = false;
      });
    },
    cartAdd(id) {
      return http.process("cart", "register", { boardId: id }, { token: this.token })
        .then((res) => {
          console.log(res)
          this.$router.push({ name: "Cart" })
        }).catch((err) => {
          console.log(err)
          alert("로그인 후 이용해 주세요")
          this.$router.push({ name: 'UserLogin' });
        })
    },

    ...mapActions({
      _getListDetail: "users/getListDetail",

    }),
  },

};
</script>
