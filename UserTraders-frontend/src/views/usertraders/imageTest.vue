<template>
  <div>

    <v-card max-width="500" max-height="auto" class="mx-auto my-12">

      <div v-if="!image">
        <h2>Select an image1</h2>
        <!-- <v-file-input :rules="rules" accept="image/png, image/jpeg, image/bmp" prepend-icon="mdi-camera" label="이미지 1" id="photo" type="file" @change="onFileChange"></v-file-input> -->
        <!-- <input type="file" @change="onFileChange" hidden> -->
        <input ref="imageInput" id="photo" type="file" hidden @change="onFileChange" >
        <v-btn color="blue-grey" class="ma-2 white--text" @click="onClickImageUpload">
          Imagae Upload
          <v-icon right dark>
            mdi-cloud-upload
          </v-icon>
        </v-btn>

      </div>
      <div v-else>
        <img :src="image" width="150" height="150" />
        <button @click="removeImage">Remove image</button>
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
      image: '',
      rules: [
        value => !value || value.size < 2000000 || 'Avatar size should be less than 2 MB!',
      ],
    }
  },
  methods: {
    onClickImageUpload() {
      this.$refs.imageInput.click();

    },

    onFileChange(e) {
      console.log
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length)
        return;
      this.createImage(files[0]);
    },
    createImage(file) {
      this.image = new Image();
      var reader = new FileReader();


      reader.onload = (e) => {
        this.image = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    removeImage: function () {
      this.image = '';
    },


    allSubmit() {
      var frm = new FormData();
      this.photoFile = document.getElementById("photo");

      for (var i = 0; i < this.photoFile.files.length; i++) {
        frm.append("files", this.photoFile.files[i]);
      }
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