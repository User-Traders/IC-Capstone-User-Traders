<template>
  <div>
    <v-container>
      {{userInfo}}
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
                          <v-list-item-subtitle>{{item.price|moneyFilter}}</v-list-item-subtitle>

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
import http from "@/utils/http";
import myMixin from "@/filter";
import { userTokenValid } from "@/api/userValid"
export default {
  mixins: [myMixin],
  data() {
    return {
      userBList: [],
      userInfo: [],
      messages: [
        {
          avatar: 'https://avatars0.githubusercontent.com/u/9064066?v=4&s=460',
          title: 'Welcome to UserTraders!',
        },
      ],

    }
  },
  mounted() {
    const token = localStorage.getItem("user")
    if (!token) {
      alert("로그인 후 이용해 주세요")
      this.$router.push({ name: 'UserLogin' });
    } else if (!userTokenValid(token)) {
      alert("토큰이 만료되었습니다. 다시 로그인 해주세요!!")
      this.$router.push({ name: 'UserLogin' });
    }
    this.userBoardList(token)
    this.userInfoList(token)
  },

  methods: {
    userBoardList(token) {
      return http.process("user", "userBoard", null, { token: token })
        .then((res) => {
          this.userBList = res
        }).catch((err) => {
          console.log(err)
        })
    },
    userInfoList(token){
      return http.process("user", "userinfo", null, { token: token })
        .then((res) => {
          this.userInfo = res
        }).catch((err) => {
          console.log(err)
        })
    }
    
  },


}
</script>
