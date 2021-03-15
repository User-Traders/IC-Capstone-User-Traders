<template>
  <div v-if="!image">
    <h2>Select an image</h2>
    <input type="file" @change="onFileChange">
  </div>
  <div v-else>
    <img :src="image" width="150" height="150"/>
    <button @click="removeImage">Remove image</button>
  </div>
</template>
<script>

export default {
  data() {
    return {
      image: ''
    }
  },
  methods: {

    onFileChange(e) {
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



    // allSubmit() {
    //   var frm = new FormData();
    //   this.photoFile = document.getElementById("photo");

    //   for (var i = 0; i < this.photoFile.files.length; i++) {
    //     frm.append("files", this.photoFile.files[i]);
    //   }
    //   axios.post('http://localhost:8090/api/image', frm,
    //     {
    //       headers: {
    //         'Content-Type': 'multipart/form-data'
    //       }
    //     })
    //     .then((res) => {
    //       console.log(res)
    //     }).catch((err) => { console.log(err) })
    // },
  }
}
</script>