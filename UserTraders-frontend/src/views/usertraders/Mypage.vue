<template>
  <div>
    <v-container>
      <v-row justify="center">
        <v-subheader>My Traders</v-subheader>
        <v-expansion-panels popout>
          <v-expansion-panel v-for="(message, i) in messages" :key="i" hide-actions>
            <div v-if="i==0">
              <v-expansion-panel-header>
                <v-row align="center" class="spacer" no-gutters>
                  <v-col cols="4" sm="2" md="1">
                    <v-avatar size="36px">
                      <img v-if="message.avatar" alt="Avatar" src="https://avatars0.githubusercontent.com/u/9064066?v=4&s=460">
                      <v-icon v-else :color="message.color" v-text="message.icon"></v-icon>
                    </v-avatar>
                  </v-col>
                  <v-col class="text-no-wrap" cols="5" sm="3">
                    <v-chip v-if="message.new" :color="`${message.color} lighten-4`" class="ml-0 mr-2 black--text" label small>
                      {{ message.new }} new
                    </v-chip>
                    <strong v-html="message.title"></strong>
                  </v-col>
                  <v-col class="grey--text text-truncate hidden-sm-and-down">
                  Total {{userBList.length}} List
                  </v-col>
                </v-row>
              </v-expansion-panel-header>

              <v-expansion-panel-content>
                <v-divider></v-divider>
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
              </v-expansion-panel-content>
            </div>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import http from "@/utils/http";
export default {
  data() {
    return {
      userBList: [],
      messages: [
        {
          avatar: 'https://avatars0.githubusercontent.com/u/9064066?v=4&s=460',
          title: 'Welcome to UserTraders!',
        },
      ],
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
    const token = localStorage.getItem("user")
    console.log(token)
    return http.process("user", "userValid", { token: token })
      .then((res) => {
        console.log(res)
        if (res) {
          this.userBoardList(token)
        } else {
          this.validTokenError()
        }
      }).catch((err) => {
        console.log(err)
        this.validTokenError()
      })
  },
  computed: {
    ...mapState({
      userInfo: (state) => state.users.userInfo,

    }),
  },
  methods: {
    userBoardList(token) {
      console.log(token)
      return http.process("user", "userBoard", null, { token: token })
        .then((res) => {
          console.log(res)
          this.userBList = res
        }).catch((err) => {
          console.log(err)
        })
    },
    ...mapActions({
      validTokenError: "auth/validTokenError",

    }),
  },


}
</script>
