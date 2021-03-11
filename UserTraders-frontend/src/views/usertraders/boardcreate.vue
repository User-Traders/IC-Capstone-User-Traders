<template>
  <div>

    <v-card max-width="500" max-height="auto" class="mx-auto my-12">
      <input ref="imageInput" type="file" hidden @change="onChangeImages" multiple>
      <v-btn color="blue-grey" class="ma-2 white--text" @click="onClickImageUpload">
        Imagae Upload
        <v-icon right dark>
          mdi-cloud-upload
        </v-icon>
      </v-btn>

      <div v-if="imageUrl">
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">
                  Image
                </th>
                <th class="text-left">
                  Delete
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, i) in imageUrlObject" v-bind:key="i">
                <th>

                  <v-img :src="item" width="180" height="150"></v-img>

                </th>
                <th>
                  <div v-if="item[i] == null">
                    <v-btn class="mx-2" dark color="success">
                      Delete Success
                    </v-btn>
                  </div>

                  <div v-else>
                    <v-btn class="mx-2" dark color="pink" @click="selectImageDelete(i)">
                      <v-icon dark>
                        mdi-delete
                      </v-icon>
                      Image {{i+1}}
                    </v-btn>
                  </div>
                </th>
              </tr>
            </tbody>
          </template>
        </v-simple-table>

      </div>

    </v-card>
  </div>
</template>
<script>
export default {
  data() {
    return {
      imageUrl: [],
      imageUrlObject: []
    }
  },
  methods: {
    onClickImageUpload() {
      this.$refs.imageInput.click();
    },
    onChangeImages(e) {

      //각각의 URL을 더는 쓰지 않을 땐 URL.revokeObjectURL()을 사용해 하나씩 해제해줘야 합니다.
      //createObjectURL()을 매번 호출할 때마다 새로운 객체 URL을 생성합니다. 
      // URL.createObjectURL()을 통해 생성한 객체 URL을 해제합니다.



      const file = []
      const len = e.target.files.length//선택한 파일 개수 
      for (var i = 0; i < len; i++) {
        file[i] = window.URL.createObjectURL(e.target.files[i])
        URL.revokeObjectURL(e.target.files[i])
        this.imageUrl.push(file[i]);
      }
      this.imageUrlObject = this.imageUrl
      console.log(this.imageUrlObject)
    },
    selectImageDelete(i) {
      console.log(i)
      this.imageUrlObject[i] = null
      console.log(this.imageUrl)
    },
  }
}
</script>