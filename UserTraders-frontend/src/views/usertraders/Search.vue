<template>

  <div>
    <br>
    <br>
    <br>
    <v-card class="mx-auto" height="auto" align="center"></v-card>
    <v-card class="mx-auto overflow-hidden" height="auto">
      <v-toolbar color="white">
        <v-text-field label="search" hide-details single-line v-model="keyword" @keyup.enter="titleSearch" color="orange" text></v-text-field>
        <v-btn icon @click="titleSearch">
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
      </v-toolbar>
      <v-spacer></v-spacer>
      <div style="text-align:center;">
        <v-card class="d-flex justify-space-around mb-6" flat>
          <v-card class="pa-2" tile flat>
            <v-btn color="white" fab x-large dark @click="searchCategory(1)">
              <v-icon color="green"> mdi-book-open-page-variant-outline</v-icon>
            </v-btn>
            <v-card-text>도서</v-card-text>
          </v-card>
          <v-card class="pa-2" tile flat>
            <v-btn color="white" fab x-large dark @click="searchCategory(2)">
              <v-icon color="#FDD835">mdi-hanger</v-icon>
            </v-btn>
            <v-card-text>의류</v-card-text>
          </v-card>
          <v-card class="pa-2" tile flat>
            <v-btn color="white" fab x-large dark @click="searchCategory(3)">
              <v-icon color="#F48FB1">mdi-paw</v-icon>
            </v-btn>
            <v-card-text>반려동물</v-card-text>
          </v-card>
        </v-card>
        <v-card class="d-flex justify-space-around mb-6" flat>
          <v-card class="pa-2" tile flat>
            <v-btn color="white" fab x-large dark @click="searchCategory(4)">
              <v-icon color="#D500F9">mdi-television-classic</v-icon>
            </v-btn>
            <v-card-text>가전용품</v-card-text>
          </v-card>
          <v-card class="pa-2" tile flat>
            <v-btn color="white" fab x-large dark @click="searchCategory(5)">
              <v-icon color="#6D4C41">mdi-sofa-single</v-icon>
            </v-btn>
            <v-card-text>가구</v-card-text>
          </v-card>
          <v-card class="pa-2" tile flat>
            <v-btn color="white" fab x-large dark @click="searchCategory(6)">
              <v-icon color="#78909C">mdi-storefront-outline</v-icon>
            </v-btn>
            <v-card-text>잡화</v-card-text>
          </v-card>
        </v-card>
        <v-card class="d-flex justify-space-around mb-6" flat>
          <v-card class="pa-2" tile flat>
            <v-btn color="white" fab x-large dark @click="searchCategory(7)">
              <v-icon color="#1E88E5">mdi-gamepad-variant</v-icon>
            </v-btn>
            <v-card-text>취미</v-card-text>
          </v-card>
          <v-card class="pa-2" tile flat>
            <v-btn color="white" fab x-large dark @click="searchCategory(8)">
              <v-icon color="black">mdi-dumbbell</v-icon>
            </v-btn>
            <v-card-text>스포츠</v-card-text>
          </v-card>
          <v-card class="pa-2" tile flat>
            <v-btn color="white" fab x-large dark @click="searchCategory(9)">
              <v-icon color="#D32F2F">mdi-lipstick</v-icon>
            </v-btn>
            <v-card-text>뷰티</v-card-text>
          </v-card>
        </v-card>
      </div>
      <h2 style="text-align: center">
        "
        <span style="color: cornflowerblue ;font-weight: bold">{{
          searchKeyword
        }}</span>
        " 에 대한 검색 결과
        <span style="color: cornflowerblue ;font-weight: bold">{{ listData.length }}
        </span>
        건
      </h2>
      <br />
      <v-container v-if="listData" three-line>
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
                    <h4>
                      {{item.user.userid}}
                    </h4>

                    <div class="font-weight-medium title mb-2">
                      {{ item.price | moneyFilter }} won
                    </div>
                    {{item.modifiedDate|timeForToday}}

                  </v-card-text>
                </v-card>

              </v-hover>

            </div>
          </v-col>
        </v-row>
      </v-container>
      <br>
      <br>
    </v-card>
  </div>
</template>

<script>
import myMixin from "@/filter";
import http from "@/utils/http";
export default {
  mixins: [myMixin],
  data() {
    return {
      isLoading: false,
      listData: [],
      searchKeyword: "",
      keyword: ""
    };
  },
  computed: {},
  methods: {
    detailPush(id) {
      this.$router.push({ name: "BoardDetail", params: { id: id } });
    },
    searchCategory(id) {
      return http.process("user", "categorySearch", { id: id })
        .then((res) => {
          console.log(res)
          this.listData = res
          this.searchKeyword = "카테고리 검색"
        }).catch((err) => {
          console.log(err)
        })
    },
    titleSearch() {
      return http.process("user", "keywordSearch",  { keyword: this.keyword })
        .then(res => {
          this.listData = res
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>