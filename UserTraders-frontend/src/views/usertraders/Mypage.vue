<template>
  <div>{{userInfo}}
    {{userBList}}
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

  mounted() {
    if (localStorage.getItem("user")) {
      const token = localStorage.getItem("user")
      this.userBoardList(token)
    }else{
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
