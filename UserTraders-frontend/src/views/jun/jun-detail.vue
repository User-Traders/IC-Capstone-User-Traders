<template>
  <div>

    <h1>Detail</h1>
    <loding v-if="isLoading" />
    <div v-else>
      <v-list v-if="listDataDeatail && listDataDeatail.data">

        <v-card class="mx-auto" max-width="344">
          <v-img :src="listDataDeatail.data.avatar" width="344"></v-img>

          <v-card-title>
            {{ listDataDeatail.data.first_name }} {{ listDataDeatail.data.last_name }}
          </v-card-title>

          <v-card-subtitle>
            email : {{ listDataDeatail.data.email }}
          </v-card-subtitle>

          <v-card-actions>
            <v-btn color="primary" fab small dark :to="{ name: 'JunUpdate', params:{editId:listDataDeatail.data.id}}">
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn color="primary" fab small dark @click="deleteListbtn(listDataDeatail.data.id)">
              <v-icon>mdi-delete</v-icon>
            </v-btn>

            <v-spacer></v-spacer>

          </v-card-actions>

        </v-card>

      </v-list>
    </div>

  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import Loding from "./jun-loding.vue"
export default {
  props: ["id"],

  components: {
    Loding,
  },
  data() {
    return {
      isLoading: true,
    };
  },
  computed: {
    ...mapState({
      listDataDeatail: (state) => state.jusers.listDataDeatail,
    }),
  },
  mounted() {

    this.init();
  },
  methods: {
    init() {
      console.log("detail init...");
      this.getListDetail(this.id);
    },
    getListDetail(id) {
      this._getListDetail(id).then(() => {
        this.isLoading = false;
      });
    },
    putUpdate() {
      this.putUpdate();
    },
    deleteListbtn(idx) {
      this._deleteList(idx).then(() => {
        this.$alert.success("delete success.");
        this.$router.push({ name: 'JunHome' });
      });
    },
    ...mapActions({
      _getListDetail: "jusers/getListDetail",
      _putUpdate: "jusers/putUpdate",
      _deleteList: "jusers/deleteList",
    }),
  },
};
</script>
