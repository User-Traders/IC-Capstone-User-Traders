<template>
  <div>
    <loding v-if="isLoading" />
    <div v-if="listDataDeatail">
      <v-row class="fill-height " align="center" justify="center">
        <v-card class="pa-2" tile flat>
          <v-carousel progress-color="orange">
            <v-carousel-item
              v-for="(item, i) in detailImageurl"
              :key="i"
              v-bind:src="item | loadImgOrPlaceholder"
              width="344"
              height="auto"
              reverse-transition="fade-transition"
              transition="fade-transition"
            ></v-carousel-item>
          </v-carousel>
        
          <div style="width: 100%;" v-if="listDataDeatail.status">
            <h3 style="color :blue; text-align:right">판매중</h3>
          </div>
          <div v-else>
            <h3 style="color :red; text-align:right">판매완료</h3>
          </div>

          <div style="width: 100%; ">
            
              <v-card-title>
                <h4>{{ listDataDeatail.title }}</h4>
              </v-card-title>
            
          </div>

          <v-card-subtitle style="clear:both">
            {{ listDataDeatail.content }}
          </v-card-subtitle>

          <div style="width: 100%;">
            <div style="width: 50%; float: left">
              <v-card-text>가격 : {{ listDataDeatail.price | moneyFilter }} won </v-card-text>
            </div>
            <div style="width: 50%; float: right; text-align: right">
              <v-card-text>
                <v-icon>
                  mdi-cart
                </v-icon>
                {{ listDataDeatail.cartcount }}
                <v-icon>
                  mdi-heart
                </v-icon>
                {{ listDataDeatail.likecount }}
                <v-icon>
                  mdi-eye-outline
                </v-icon>
                {{ listDataDeatail.viewcount }}
              </v-card-text>
            </div>
          </div>

          <div class="mt-15">
            <v-btn
              color="blue-grey"
              class="ma-2 white--text"
              @click="cartAdd(listDataDeatail.id)"
            >
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
            <v-btn
              class="ma-2"
              fab
              dark
              small
              color="pink"
              @click="likeAdd(listDataDeatail.id)"
            >
              <v-icon dark>
                mdi-heart
              </v-icon>
            </v-btn>
          </div>
        </v-card>
      </v-row>
      <br />
      <br />
      <br />
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
      this.token = localStorage.getItem("user");
    },
    getListDetail(id) {
      this._getListDetail(id).then(() => {
        this.isLoading = false;
      });
    },
    cartAdd(id) {
      return http
        .process("cart", "register", { boardId: id }, { token: this.token })
        .then((res) => {
          console.log(res);
          this.$router.push({ name: "Cart" });
        })
        .catch((err) => {       
          if(
            err.message === "로그인 되지 않았습니다. 로그인 해주세요."
          ){
            alert(err.message);
            this.$router.push(this.$route.query.redirect || '/user/login')
          }
          if (
            err.message === "나의 게시물은 장바구니에 담을 수 없습니다." ||
            err.message === "중복된 게시물은 장바구니에 담을 수 없습니다."
          ) {
            alert(err.message);
            this.$router.push(this.$route.query.redirect || '/detail/'+id)
          }
        });
    },
    likeAdd(id) {
      return http
        .process("like", "register", { boardId: id }, { token: this.token })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
          alert("로그인 후 이용해 주세요");
          this.$router.push({ name: "UserLogin" });
        });
    },
    ...mapActions({
      _getListDetail: "users/getListDetail",
    }),
  },
};
</script>
