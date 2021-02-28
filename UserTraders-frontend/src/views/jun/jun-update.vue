<template>
  <div>
    
    
      <form>
        <v-text-field v-model="name" :error-messages="nameErrors" :counter="10" label="Name" required @input="$v.name.$touch()" @blur="$v.name.$touch()"></v-text-field>
        <v-text-field v-model="email" :error-messages="emailErrors" label="E-mail" required @input="$v.email.$touch()" @blur="$v.email.$touch()"></v-text-field>

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
import { mapActions, mapState } from "vuex";
import { validationMixin } from 'vuelidate'
import { required, maxLength, email } from 'vuelidate/lib/validators'

export default {
  mixins: [validationMixin],
  props: ["editId"],

  validations: {
    name: { required, maxLength: maxLength(10) },
    email: { required, email },

    checkbox: {
      checked(val) {
        return val
      },
    },
  },
  components: {},
  data() {
    return {
      isLoading: true,
      name: '',
      email: '',

      checkbox: false,
    };
  },
  computed: {
    ...mapState({
      listDataDeatail: (state) => state.jusers.listDataDeatail,
    }),


    checkboxErrors() {
      const errors = []
      if (!this.$v.checkbox.$dirty) return errors
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
      this.putUpdate(this.name, this.email);
    },
    clear() {
      this.$v.$reset()
      this.name = ''
      this.email = ''
      this.select = null
      this.checkbox = false
    },

    init() {
      console.log("Update init...");
      this.getListDetail(this.editId);
    },
    getListDetail(editId) {
      this._getListDetail(editId).then(() => {
        this.name = this.listDataDeatail.data.first_name
        this.email = this.listDataDeatail.data.email
        this.isLoading = false;

      });
    },
    putUpdate(st1, str2) {
      this._putUpdate(st1, str2).then(() => { });
      this.$alert.success("Update success.");
      this.$router.replace({ name: 'JunDetail' , params:{id:this.editId} });//q ㅂ
    },
    ...mapActions({
      _putUpdate: "jusers/putUpdate",
      _getListDetail: "jusers/getListDetail",
    }),
  },
};
</script>
