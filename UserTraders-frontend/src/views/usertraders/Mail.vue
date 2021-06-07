<template>
  <v-sheet height="600" class="overflow-hidden" style="position: relative;">
    <v-container class="fill-height">
      <v-row align="center" justify="center">
        <v-btn color="#F57C00" dark @click.stop="drawer = !drawer">
          쪽지함
        </v-btn>
      </v-row>
    </v-container>

    <v-navigation-drawer v-model="drawer" absolute temporary>
      <v-list-item>
        <v-list-item-avatar>
          <v-img src="https://image.bugsm.co.kr/artist/images/1000/800491/80049126.jpg"></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title>사용자</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-divider></v-divider>
      <v-list dense>
        <v-list-item :to="{ name: 'MailWrite'}" active-class="deep-purple--text text--accent-4">
          <v-list-item-icon>
            <v-icon>mdi-email-plus-outline</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>쪽지쓰기</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item :to="{ name: 'MailGet'}" active-class="deep-purple--text text--accent-4">
          <v-list-item-icon>
            <v-icon>mdi-email-send-outline</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>받은쪽지함</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item :to="{ name: 'MailSend'}" active-class="deep-purple--text text--accent-4">
          <v-list-item-icon>
            <v-icon>mdi-email-receive-outline</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>보낸쪽지함</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-sheet>
</template>
<script>
export default {
  data() {
    return {
      drawer: null,
      // items: [
      //   { title: '쪽지쓰기', icon: 'mdi-email-plus-outline', link: 'MailWrite'  },
      //   { title: '받은쪽지함', icon: 'mdi-email-send-outline' },
      //   { title: '보낸쪽지함', icon: 'mdi-email-receive-outline' },
      //   { title: '휴지통', icon: 'mdi-delete-forever' },
      // ],
    }
  },
}
</script>