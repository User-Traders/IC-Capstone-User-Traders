<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            내용
          </th>
          <th class="text-left">
            받는사람
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in mail" :key="item.id">
          <td>{{ item.title }}</td>
          <td>{{ item.recvId.username }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>
<script>
import http from "@/utils/http";
export default {
  data() {
    return {
      mail: [],
      token: "",
    };
  },

  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.token = localStorage.getItem("user");
      this.messageSend()
    },

    messageSend() {
      return http
        .process("mail", "sent", {}, { token: this.token })
        .then((res) => {
          console.log(res);
          this.mail = res
        })
        .catch((err) => {
          console.log(err)
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
  },
};
</script>