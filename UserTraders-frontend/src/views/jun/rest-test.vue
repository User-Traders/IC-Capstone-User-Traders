<template>
  <div>
    <v-btn @click="restGet">GET</v-btn>
    <v-btn @click="restDetailGet">DetailGET</v-btn>
    <v-btn @click="restPost">Post</v-btn>
    <v-btn @click="restDelete">Delete</v-btn>
    {{pp}}
  </div>
</template>
<script>
import axios from "axios"
export default {
  data() {
    return {
      pp: null,
      data: { id: 7, name: "형준공학과", shortName: "형준", phone: "02-2610-9563" }
    };
  },
  methods: {
    restGet() {
      axios.get('http://localhost:8088/departments')
        .then((res) => {
          console.log(res)
          this.pp = res
        })
        .catch(err => {
          console.log('error:', err)
        })
    },
    restDetailGet() {
      axios.get('http://localhost:8088/department/1', {

      })
        .then((res) => {
          console.log(res)
          this.pp = res
        })
        .catch(err => {
          console.log('error:', err)
        })
    },
    restPost() {
      axios.post('http://localhost:8088/department', this.data, {

      })
        .then((result) => {
          console.log(result)
        })
        .catch(e => {
          console.log('error:', e)
        })
    },

    restDelete() {
      axios.delete('http://localhost:8088/department/7'

      )
        .then((result) => {
          console.log(result)
        })
        .catch(e => {
          console.log('error:', e)
        })
    },

  }

}
</script>