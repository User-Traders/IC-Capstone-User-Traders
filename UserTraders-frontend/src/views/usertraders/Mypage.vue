<template>
  <div>

    <v-row>
      <v-col v-for="(item, i) in userBList" :key="`item-${i}`" cols="12" sm="4">
        <v-card class="mx-auto" max-width="344" outlined>
          <v-list-item three-line>
            <v-list-item-content>
              <div class="overline mb-4">
                  {{item.modifiedDate|timeForToday}}
              </div>
              <v-list-item-title class="headline mb-1">
                 {{item.title}}
              </v-list-item-title>
              <v-list-item-subtitle>{{item.content}}</v-list-item-subtitle>
             <v-list-item-subtitle>{{item.price|money}}</v-list-item-subtitle>
       
            </v-list-item-content>

            <v-list-item-avatar tile size="120" color="grey">
         <v-img v-bind:src="item.imageurl1 |loadImgOrPlaceholder">
         </v-img>
            </v-list-item-avatar>
         
          </v-list-item>

          <v-card-actions>
            <v-btn outlined rounded text color="orange">
             상세보기
            </v-btn>
             <v-btn outlined rounded text color="orange">
              수정하기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>
<script>
import { mapState } from "vuex";
import http from "@/utils/http";
export default {
  data() {
    return {
      userBList: [],
    }
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

  mounted() {
    if (localStorage.getItem("user")) {
      const token = localStorage.getItem("user")
      this.userBoardList(token)
    } else {
      this.$router.push({ name: 'UserLogin' });
    }

  },
  computed: {
    ...mapState({
      userInfo: (state) => state.users.userInfo,

    }),
  },
  methods: {
    userBoardList(token) {
      console.log(token)
      return http.process("user", "userBoard", null, { token: token }).then((res) => {
        console.log(res)
        this.userBList = res
      }).catch((err) => {
        console.log(err)
      })
    },
  },
}
</script>
