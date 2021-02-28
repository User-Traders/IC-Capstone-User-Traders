<template>
  <div>
    <h1>Create</h1>
    vuelidate
    <form>
      <v-text-field v-model="name" :error-messages="nameErrors" :counter="10" label="Name" required @input="$v.name.$touch()" @blur="$v.name.$touch()"></v-text-field>
      <v-text-field v-model="email" :error-messages="emailErrors" label="E-mail" required @input="$v.email.$touch()" @blur="$v.email.$touch()"></v-text-field>
      <!-- <v-select v-model="select" :items="items" :error-messages="selectErrors" label="Item" required @change="$v.select.$touch()" @blur="$v.select.$touch()"></v-select> -->
      <v-checkbox v-model="checkbox" :error-messages="checkboxErrors" label="Do you agree?" required @change="$v.checkbox.$touch()" @blur="$v.checkbox.$touch()"></v-checkbox>

      <v-btn class="mr-4" @click="submit">
        submit
      </v-btn>
      <v-btn @click="clear">
        clear
      </v-btn>
    </form>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import { validationMixin } from 'vuelidate'//mixin 구성 요소로 직접 가져옴
import { required, maxLength, email } from 'vuelidate/lib/validators'

export default {
  components: {},
  mixins: [validationMixin],

  validations: {//required 공백 만 포함하는 빈 배열 및 문자열을 확인 ,maxlength 입력에 지정된 최대 길이
    name: { required, maxLength: maxLength(10) },
    email: { required, email },
    select: { required },
    checkbox: {
      checked(val) {
        return val
      },
    },
  },

  data: () => ({
    name: '',
    email: '',
    select: null,
    checkbox: false,
  }),
  computed: {
    ...mapState({

    }),
    checkboxErrors() {
      const errors = []
      if (!this.$v.checkbox.$dirty) return errors//dirty 사용자가 유효성 검사중인 필드를 한 번 이상 터치 했는지 여부를 나타냄
      !this.$v.checkbox.checked && errors.push('You must agree to continue!')
      return errors
    },

    nameErrors() {
      const errors = []
      if (!this.$v.name.$dirty) return errors
      !this.$v.name.maxLength && errors.push('Name must be at most 10 characters long')
      !this.$v.name.required && errors.push('Name is required.')
      return errors
    },
    emailErrors() {
      const errors = []
      if (!this.$v.email.$dirty) return errors
      !this.$v.email.email && errors.push('Must be valid e-mail')
      !this.$v.email.required && errors.push('E-mail is required')
      return errors
    },
  },
  mounted() {
    this.init();
  },
  methods: {


    submit() {

      this.postCreate(this.name, this.email)
      // this.$v.$touch()
    },

    clear() {
      this.$v.$reset()
      this.name = ''
      this.email = ''
      this.select = null
      this.checkbox = false
    },
    init() {
      console.log("create init...");
      //   this.postCreate(this.id);
    },
    postCreate(st1, str2) {
      this._postCreate(st1, str2).then(() => { });
      this.$router.push({ name: 'JunHome'});
      this.$alert.success('create success.')
    },

    ...mapActions({
      _postCreate: "jusers/postCreate",

    }),
  },
};
</script>

