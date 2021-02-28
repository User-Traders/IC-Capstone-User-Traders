<template>
  <div>

    <loding v-if="isLoading" />
    <div v-else>
      <v-btn color="warning" x-large fab dark :to="{ name: 'JunLogin'}">
        Login
      </v-btn>

      <v-btn color="primary" x-large fab dark :to="{ name: 'JunMypage'}">
        Mypage
      </v-btn>

      <v-container v-if="listData.page && listData.data" three-line>
        <v-row>
          <v-col v-for="(item, i) in listData.data" :key="`item-${i}`">
            <div>
              <v-card class="mx-auto" max-width="344">
                <v-img :src="item.avatar" width="344"></v-img>
                <v-icon color="pink">mdi-delete</v-icon>
                <input type="checkbox" :value="item.id" v-model="checkedNames">
                <v-card-title>
                  {{ item.first_name }} {{ item.last_name }}
                </v-card-title>

                <v-card-subtitle>
                  <v-list-item-icon>
                    <v-icon color="indigo">
                      mdi-email
                    </v-icon>
                    {{ item.email }}
                  </v-list-item-icon>

                </v-card-subtitle>

                <v-card-actions>
                  <v-btn color="orange lighten-2" text :to="{ name: 'JunDetail', params:{id:item.id}}">
                    Detail
                  </v-btn>

                  <v-spacer></v-spacer>

                </v-card-actions>

              </v-card>
            </div>
          </v-col>
        </v-row>
        <!-- <Paging :totalPage="listData.total_pages" @movePage="movePage" /> -->

        <v-pagination v-model="page" :length="listData.total_pages" @input="movePage">

        </v-pagination>
      </v-container>
    </div>

    <div v-if="checkedNames.length!==0">
      <span>삭제 목록 {{ checkedNames }} </span>
      <v-btn color="error" @click="checkdelete">
        <v-icon>mdi-delete</v-icon>
      </v-btn>
    </div>

  </div>

</template>

<script>
import { mapState, mapActions } from "vuex";
import Loding from "./jun-loding.vue";

export default {
  components: {
    Loding,

  },
  data() {
    return {
      isLoading: true,
      checkedNames: [],
      page: 1,
      promiseAllDelete: [],

    };
  },
  computed: {
    ...mapState({
      listData: (state) => state.jusers.listData,
      isLogin: (state) => state.jusers.isLogin,
    }),
  },
  mounted() {
    this.init();
  },
  methods: {
    checkdelete() {
      console.log("~~ 1")
      //  let temp
      this.isLoading = true
      /*       for (var i = 0; i < this.checkedNames.length; i++) {
              temp = this._deleteList(this.checkedNames[i])
              this.promiseAllDelete.push(temp);
              console.log("~~ 2")
            }
      
            console.log("~~ 3") */
      Promise.all(this.checkedNames.map(tempName => this._deleteList(tempName))).then(() => {


        this.isLoading = false;
        this.checkedNames = []
        this.$alert.success("deleted")

      }, e => {
        console.log(e)
        this.isLoading = false;
        this.$alert.success("deleted fail")
      })


    },



    init() {
      console.log("home init...");
      this.getList();
    },
    getList(pagenum) {
      this.isLoading = true;
      this._getList(pagenum).then(() => {
        this.isLoading = false;

      });

    },
    movePage() {
      console.log("TLq")
      this.getList(this.page);
    },
    ...mapActions({
      _getList: "jusers/getList",
      _deleteList: "jusers/deleteList",



    }),
  },
};
</script>
