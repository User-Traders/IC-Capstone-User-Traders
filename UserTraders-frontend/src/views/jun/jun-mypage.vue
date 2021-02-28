<template>
  <div>mypage
     <h1> {{userInfo}}</h1>
  </div>
  
</template>
<script>
import { mapState } from "vuex"
export default {
  computed: {
    ...mapState({
          userInfo: (state) => state.jusers.userInfo,
    })
  }

}
</script>