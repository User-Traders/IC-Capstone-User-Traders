<template>

  <div>
    <br>
    <br>
    <br>
    <v-card width="400" class="mx-auto ">
      <v-card-title style="background-color: lightgrey; margin-bottom:5px;">
        <h3 style="color: white">
          Sign Up
        </h3>
      </v-card-title>
      <v-card-text>
        <div id="signUpForm">
          <v-form>
            <v-container>
              <v-row>
                <v-col>
                  <v-text-field v-model="email" :rules="[rules.required]" label="Email address" prepend-icon="mdi-account-circle" required outlined></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col>
                  <v-text-field v-model="password" :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'" :rules="[rules.required, rules.minPw]" :type="show1 ? 'text' : 'password'" name="input-10-1" label="Password" hint="At least 8 characters" prepend-icon="mdi-lock" counter @click:append="show1 = !show1" outlined></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col>
                  <v-text-field v-model="passwordCheck" :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'" :rules="[rules.required, rules.minPw, rules.pwCheck]" :type="show2 ? 'text' : 'password'" name="input-10-1" label="Password Check" hint="Enter your password once more." prepend-icon="mdi-lock" counter @click:append="show2 = !show2" outlined></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col>
                  <v-text-field v-model="name" :rules="[rules.minName]" label="Name" prepend-icon="mdi-account" required outlined></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-text-field v-model="number" label="휴대폰 번호 -없이 입력" prepend-icon="mdi-cellphone" oninput="javascript: this.value = this.value.replace(/[^0-9]/g, '');" outlined></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-select v-model="select" :items="items" @click="departmentName" label="학과 선택" prepend-icon="mdi-school" data-vv-name="select" required outlined></v-select>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </div>
      </v-card-text>
      <v-divider> </v-divider>
      <v-card-actions>
        <v-btn :disabled="!valid" rounded color="blue-grey " class="mr-3 white--text" @click="signUpSubmit">
          Sign up
          <v-icon right>mdi-arrow-right-thick</v-icon>
        </v-btn>

      </v-card-actions>
    </v-card>
    <br>

    <br>
  </div>
</template>
<script>
import http from "@/utils/http";
export default {
  data() {
    return {
      show1: false,
      show2: false,
      email: "",
      password: "",
      passwordCheck: "",
      name: "",
      valid: true,
      items: [],
      itemsid: [],
      select: "",
      number: "",
      rules: {
        required: input => !!input || "Required.",
        minPw: password => password.length >= 8 || "Min 8 characters",
        minName: Name => Name.length >= 1 || "Min 1 characters",
        minPhone: number => number.length >= 11 || "Min 11 characters",
        pwCheck: passwordCheck =>
          this.password === passwordCheck || "Password mismatch"
      }
    };
  },
  watch: {
    number: function () {
      return this.number = this.number.replace(/[^0-9]/g, '');
    }
  },
  methods: {
    departmentName() {
      return http.process("user", "listdepartment").then((data) => {
        for (var i = 0; i < data.length; i++) {
          this.items = this.items.concat(data[i].id + " " + data[i].name)
        }
      }).catch(err => {
        console.log(err)
      });

    },
    signUpSubmit() {
      const temp = this.select.split(" ")
      const department = { id: temp[0] }
      const userObj = { userid: this.email, password: this.password, tel: this.number, department: department, name: this.name }
      return http.process("user", "signup", userObj)
        .then((res) => {
          console.log(res)
          this.$router.push({ name: 'UserLogin' });

        }).catch((err) => { console.log(err) })

    },

  },
}

</script>
<style scoped>
</style>