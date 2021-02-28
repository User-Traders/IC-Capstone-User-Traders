<template>
  <div>

    <v-alert :value="isLoginError" type="error" mb-3>
      Check! email and password!!
    </v-alert>
    <v-alert :value="isLogin" type="success" mb-3>
      Login Complete
    </v-alert>

    <v-form ref="form" v-model="valid" lazy-validation>

      <v-text-field v-model="email" :rules="emailRules" label="E-mail" required></v-text-field>

      <v-text-field type="password" v-model="password" :counter="10" :rules="passwordRules" label="password" required></v-text-field>
      <v-btn :disabled="!valid" color="success" class="mr-4" @click="submit">
        submit
      </v-btn>

      <v-btn color="error" class="mr-4" @click="reset">
        Reset Form
      </v-btn>

    </v-form>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex"

export default {
  data: () => ({
    valid: true,

    email: '',
    emailRules: [
      v => !!v || 'E-mail is required',
      v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
    ],
    password: '',
    passwordRules: [
      v => !!v || 'password is required',
      v => (v && v.length <= 10) || 'password must be less than 10 characters',
    ],
    pp: false

  }),
  computed: {
    ...mapState({
      isLogin: (state) => state.jusers.isLogin,
      isLoginError: (state) => state.jusers.isLoginError,
    }),
  },

  methods: {
    submit() {
      let userobj = { "email": this.email, "password": this.password };
      this.$refs.form.validate()
      this._login(userobj);
    },



    reset() {
      this.$refs.form.reset()
    },
    ...mapActions({
      _postLogin: "jusers/postLogin",
      _login: "jusers/login",




    }),

  },
}
</script>