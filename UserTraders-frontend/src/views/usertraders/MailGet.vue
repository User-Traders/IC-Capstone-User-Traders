<template>
  <v-simple-table>

    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            내용
          </th>
          <th class="text-left">
            보낸사람
          </th>
          <th>읽음</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in mail" :key="item.id">
          <td>{{ item.title }}</td>
          <td>{{ item.recvId.username }}</td>
          <td>{{ item.recvRead }}</td>
          <td>
            <v-btn color="#F57C00" style="color:white" @click="messageSend(item.sentId.id,item.sentId.username)">
              답장하기
            </v-btn>
          </td>
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
      this.messageGet()
    },
    messageGet() {
      return http
        .process("mail", "recv", {}, { token: this.token })
        .then((res) => {
          console.log(res)
          this.mail = res
        })
        .catch((err) => {
          console.log(err)
        });
    },
    messageSend(id, username) {
      this.$router.push({ name: "MailWrite", params: { id: id, email: username } });
    },

  },
};
</script>