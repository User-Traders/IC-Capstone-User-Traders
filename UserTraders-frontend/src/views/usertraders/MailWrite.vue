<template>
  <div>

    <v-container>
      <v-col cols="12" sm="6">
        <v-text-field v-model="email" label="받는 유저 이메일을 입력해주세요:)" outlined clearable></v-text-field>
      </v-col>
      <v-col cols="12" sm="6">
        <v-text-field v-model="title" label="제목을 입력해주세요" outlined clearable></v-text-field>
      </v-col>
      <v-col cols="12" md="6">
        <v-textarea v-model="content" outlined name="" label="쪽지 내용을 입력해주세요:)"></v-textarea>
      </v-col>

      <div class="my-2">
        <v-btn color="#F57C00" fab x-large dark @click="messageSend">
          <v-icon>mdi-send</v-icon>
        </v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
import http from "@/utils/http";

export default {

  props: ["id", "email"],

  data() {
    return {
      token: "",
      recvId: null,
      title: "",
      content: "",
    };
  },

  computed: {

  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.token = localStorage.getItem("user");
      this.recvId = this.id
    },
    messageSend() {
      return http
        .process("mail", "send", { recvId: this.recvId, title: this.title, content: this.content }, { token: this.token })
        .then((res) => {
          console.log(res);
          this.$router.push({ name: "MailSend" });
        })
        .catch((err) => {
          console.log(err)
        });
    },
  },
};
</script>
