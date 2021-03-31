<template>

  <v-navigation-drawer :value="value" absolute fixed>
   
  
    <v-list-item>
      <v-list-item-content>
        <v-list-item-title class="title">
          사용자명
        </v-list-item-title>
        <v-list-item-subtitle>
          email
        </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>

    <v-divider></v-divider>

    <v-list dense nav>

      <v-list-item :to="{ name: 'Home1'}" active-class="deep-purple--text text--accent-4">
        <v-list-item-icon>
          <v-icon>mdi-home</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Home</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item :to="{ name: 'Create'}" active-class="deep-purple--text text--accent-4">
        <v-list-item-icon>
          <v-icon>mdi-plus</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Create</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

     


    </v-list>
    <template v-slot:append>
      <div class="pa-2">
        <v-btn block color="deep-purple lighten-4">
          Logout
        </v-btn>
      </div>
    </template>
  </v-navigation-drawer>

</template>
<script>
export default {
  model: {
    prop: "value",
    event: "input",
  },
  props: {
    value: Boolean,
  },
  data() {
    return {

      right: null,
    };
  },
  watch: {
    group() {
      this.close();
    },
  },
  mounted() {
    this.init();
  },
  methods: {
    init() { },
    close() {
      this.$emit("input", false);
    },
  },
};
</script>