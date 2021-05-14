<template>
  <v-container>
    <v-card>
      <h1 align="center" class="grey--text">
        <v-icon size="xxx-large" color="orange">mdi-gift</v-icon>
        My Cart List
      </h1>
      <v-card v-for="(cart,index) in cartList" :key="cart.id">
    
      
        <v-layout>
          <v-flex xs3>
            <v-img v-bind:src="cart.board.imageurl1 | loadImgOrPlaceholder" contain height="125px"></v-img>
          </v-flex>
          <v-layout column>
            <v-card-title>
              <h4>{{ cart.board.title }}</h4>
            </v-card-title>
            <v-card-text>{{ `가격 : ${cart.board.price} 원 `| moneyFilter }}</v-card-text>
          </v-layout>
          <v-card-actions>
            <v-btn right color="blue-grey" class="ma-2 white--text" fab @click="cartDelete(index,cart.id)">
              <v-icon dark>
                mdi-delete
              </v-icon>
            </v-btn>
          </v-card-actions>
        </v-layout>
      </v-card>
      <v-card-subtitle>
        <h3 align="center">
          Count
          <p style="color: orange">{{ cartList.length }}</p>
          Total Price($ {{ total| moneyFilter }} 원)
        </h3>
      </v-card-subtitle>
    </v-card>
    <v-spacer />
  </v-container>
</template>

<script>
import http from "@/utils/http";
import myMixin from "@/filter";

export default {
  mixins: [myMixin],
  data() {
    return {
      cartList: [],
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      console.log("cart init...");
      this.getCartList(this.id);
    },
    getCartList() {
      const token = localStorage.getItem("user")
      return http.process("cart", "list", null, { token: token })
        .then((res) => {
          console.log()
          this.cartList = res
        }).catch((err) => {
          console.log(err)
        })
    },
    cartDelete(idx, cartId) {
      const token = localStorage.getItem("user")
      this.cartList.splice(idx, 1);
      return http.process("cart", "delete", {id:cartId}, { token: token })
        .then((res) => {
          console.log()
          this.cartList = res
        }).catch((err) => {
          console.log(err)
        })
    },

  },

  computed: {
    total() {
      let total = 0;
      this.cartList.forEach(cartItem => {
        total += parseInt(cartItem.board.price);
      });
      return total;
    }
  }
};
</script>