<template>
  <div>

    <v-card max-width="500" max-height="auto" class="mx-auto my-12">
      <input ref="imageInput" id="photo" type="file" hidden @change="onChangeImages" multiple>
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
              <tr v-for="(item, i) in imageUrl" v-bind:key="i">
                <th>
                  <v-img :src="item" width="130" height="130"></v-img>
                </th>
                <th>
                  <v-btn class="" dark color="pink" @click="selectImageDelete(i)">
                    <v-icon dark>
                      mdi-camera
                    </v-icon>
                    Image {{i+1}}
                  </v-btn>

                </th>
              </tr>
            </tbody>
          </template>
        </v-simple-table>

      </div>
      <v-btn color="blue-grey" block class=" white--text" @click="allSubmit">
        Submit
        <v-icon right color="white">
          mdi-checkbox-marked-circle
        </v-icon>
      </v-btn>

    </v-card>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      imageUrl: [],//미리보기
      photofile: [],//전송할 파일
      a: [1, 2, 3, 4]

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

    },
    selectImageDelete(i) {
      this.imageUrl.splice(i, 1)

    },


    allSubmit() {

      var frm = new FormData();
      this.photoFile = document.getElementById("photo");
      console.log(this.photoFile.item)
      for (var i = 0; i < this.photoFile.files.length; i++) {
        frm.append("files", this.photoFile.files[i]);
      }

      frm.delete(2)



      axios.post('http://localhost:8090/api/image', frm,
        {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        })
        .then((res) => {
          console.log(res)
        }).catch((err) => { console.log(err) })
    },
  }
}
</script>