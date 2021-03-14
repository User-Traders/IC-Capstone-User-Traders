<template>
  <div>
    <form> <input type="file" name="photo" id="photo" multiple /> </form>
    <v-btn @click="submit">submit</v-btn>
  </div>
</template>
 <script>
import axios from "axios";
export default {
  data() {
    return {
      photoFile: [],
    }
  },
  methods: {
    submit() {
      var frm = new FormData();
      this.photoFile = document.getElementById("photo");
      console.log(this.photoFile.files)
      this.photoFile.getElementById.remove(1)
      console.log(this.photoFile.files)
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
    }
  }
}
</script>

