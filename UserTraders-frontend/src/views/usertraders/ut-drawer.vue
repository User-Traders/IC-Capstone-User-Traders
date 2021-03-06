<template>

  <v-navigation-drawer :value="value" absolute>
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
      <v-list-item-group v-model="group" active-class="deep-purple--text text--accent-4">
        <v-list-item v-for="item in items" :key="item.title" link>
          <v-list-item-icon>
            <v-icon >{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
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
      group: null,
      items: [
        { title: 'Home', icon: 'mdi-home', iconColor: 'orange accent-4' },
        { title: 'Create', icon: 'mdi-plus', iconColor: 'orange accent-4' },
        { title: 'About', icon: 'mdi-help-box', iconColor: 'orange accent-4' },
      ],
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