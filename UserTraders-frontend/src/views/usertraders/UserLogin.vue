<template>

  <div>
    <v-card width="400" class="mx-auto ">
      <div v-if="!isLoginError">
        <v-card-title style="background-color: #CD1F48 ">
          <h3 style="color: white">Login</h3>
        </v-card-title>
      </div>

      <div v-else>
        <v-card-title style="background-color: #FF3232">
          <h3 style="color: white"> Check! email and password!!</h3>
        </v-card-title>
      </div>
      <v-card-text>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field v-model="email" :rules="emailRules" label="E-mail" prepend-icon="mdi-account-circle" required></v-text-field>
          <v-text-field v-model="password" label="password" prepend-icon="mdi-lock"  type="password"></v-text-field>
        </v-form>
      </v-card-text>
      <v-divider> </v-divider>
      <v-card-actions>
        <v-btn :disabled="!valid" color="grey" class="mr-3 white--text" @click="loginSubmit">
          Go to Login
          <v-icon right>mdi-arrow-right-thick</v-icon>
        </v-btn>

        <v-btn color="grey" class="mr-4 white--text" @click="reset">
          Reset
          <v-icon right>mdi-backup-restore</v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>

</template>
<script>
import { mapState, mapActions } from "vuex";
export default {
  data: () => ({
    valid: true,
    email: "",
    emailRules: [
      v => !!v || "E-mail is required",
      v => /.+@.+\..+/.test(v) || "E-mail must be valid"
    ],
    password: "",
  }),
  computed: {
    ...mapState({
      isLogin: (state) => state.users.isLogin,
      isLoginError: (state) => state.users.isLoginError,
    }),
  },
  methods: {
    validate() {
      this.$refs.form.validate();
    },
    reset() {
      this.$refs.form.reset();
    },


    loginSubmit() {
      const loginObj = { userid: this.email, password: this.password }
      this.postUserLogin(loginObj).then(() => {
        this.isLoading = false;
      });
    },

    ...mapActions({
      postUserLogin: "users/postUserLogin",

    }),

  }
};
</script>
