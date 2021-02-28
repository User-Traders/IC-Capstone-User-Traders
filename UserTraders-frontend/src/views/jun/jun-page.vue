<template>
  <div>
  
    <v-pagination
        v-model="page"
        :length="totalPage">
        
      </v-pagination>
  </div>
</template>

<script>
export default {
  props: ["totalPage"],
  data() {
    return {
       page: 1,
    };
  },
  methods: {
    movePage(page) {
      console.log(page)
      this.$emit("movePage", page);
    },
    ppp(){
    console.log("emf")
    }
  }
};
</script>