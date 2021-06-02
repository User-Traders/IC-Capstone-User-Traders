<template>
  <div>
    <v-container>
      <v-row justify="center" class="mt-13">
        <v-expansion-panels popout>
          <v-expansion-panel v-for="(message, i) in messages" :key="i" hide-actions>
            <div v-if="i==0">
              <v-expansion-panel-header>
                <v-row align="center" class="spacer" no-gutters>
                  <v-col cols="4" sm="2" md="1">
                    <v-avatar size="36px">
                      <img v-if="message.avatar" alt="Avatar" src="https://avatars.githubusercontent.com/u/43032589?v=4">
                      <v-icon v-else :color="message.color" v-text="message.icon"></v-icon>
                    </v-avatar>
                  </v-col>
                  <v-col class="text-no-wrap" cols="5" sm="3">
                    <v-chip v-if="message.new" :color="`${message.color} lighten-4`" class="ml-0 mr-2 black--text" label small>
                      {{ message.new }} new
                    </v-chip>
                    <strong>내 정보</strong>
                  </v-col>

                </v-row>
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-divider></v-divider>
                <v-row>
                  <v-list-item three-line>
                    <v-list-item-content>
                      <div class="overline mb-4">
                        email : {{userInfo.userid}}
                      </div>
                      <div class="overline mb-4">
                        이름 : {{userInfo.name}}
                      </div>
                      <div class="overline mb-4">
                        가입일 : {{userInfo.createdDate}}
                      </div>
                      <div class="overline mb-4">
                        학과 : {{department}}
                      </div>
                      <div class="overline mb-4">
                        TEL : {{userInfo.tel}}
                      </div>
                    </v-list-item-content>
                  </v-list-item>

                  <v-card-actions>
                    <v-btn outlined rounded text>
                      회원정보 수정
                    </v-btn>
                  </v-card-actions>
                </v-row>
              </v-expansion-panel-content>
            </div>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-row>

      <v-row justify="center" class="mb-13">
        <v-expansion-panels popout>
          <v-expansion-panel v-for="(message, i) in messages" :key="i" hide-actions>
            <div v-if="i==0">
              <v-expansion-panel-header>
                <v-row align="center" class="spacer" no-gutters>
                  <v-col cols="4" sm="2" md="1">
                    <v-avatar size="36px">
                      <img v-if="message.avatar" alt="Avatar" src="https://avatars.githubusercontent.com/u/43032589?v=4">
                      <v-icon v-else :color="message.color" v-text="message.icon"></v-icon>
                    </v-avatar>
                  </v-col>
                  <v-col class="text-no-wrap" cols="5" sm="3">
                    <v-chip v-if="message.new" :color="`${message.color} lighten-4`" class="ml-0 mr-2 black--text" label small>
                      {{ message.new }} new
                    </v-chip>
                    <strong>거래 목록</strong>
                  </v-col>
                  <v-col class="grey--text text-truncate hidden-sm-and-down">
                    총 {{userBList.length}} 개의 게시물
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
                          <div class="overline mb-4">

                            거래 상태 : {{item.status|tradeStatus}}
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
import { mapActions } from "vuex";
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
      department: "",
    }
  },
  mounted() {
    const token = localStorage.getItem("user")
    if (!token) {
      this.$router.push({ name: 'UserLogin' })
    } 
    if (!userTokenValid(token)) {
      alert("토큰이 만료되었습니다. 로그아웃됩니다.")    
      this.$router.push(this.$route.query.redirect || '/user/login')
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
    userInfoList(token) {
      return http.process("user", "userinfo", null, { token: token })
        .then((res) => {
          this.userInfo = res
          this.department = res.department.name
        }).catch((err) => {
          console.log(err)
          if(
            err.message === "로그인 되지 않았습니다. 로그인 해주세요." ||
            err.message === "유저정보가 없습니다."
          ){
            alert(err.message);
            this.getUserLogout().then(() => {
            this.isLoading = false;
            localStorage.removeItem("user")
            this.$router.push({ name: 'Home1' });
            });
            this.$router.push(this.$route.query.redirect || '/user/login')
          }
        })
    },
    ...mapActions({
      getUserLogout: "auth/getUserLogout",
    })

          }
}
</script>
