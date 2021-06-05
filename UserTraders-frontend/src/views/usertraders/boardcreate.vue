 <template>
  <div>
    <br>
    <v-card max-width="500" max-height="auto" class="mx-auto my-12">
      <v-form ref="form" v-model="valid" lazy-validation>
        <v-card-title>
          <v-text-field color="orange" :rules="titleRules" required v-model="title" counter="15" label="Title" clearable clear-icon="mdi-close-circle" outlined></v-text-field>
        </v-card-title>
        <v-card-title>
          <v-textarea color="orange" :rules="contentRules" v-model="content" counter="50" label="Content" clearable clear-icon="mdi-close-circle" outlined></v-textarea>
        </v-card-title>
        <v-card-title>
          <v-text-field color="orange" v-model="price" label="Price" clearable clear-icon="mdi-close-circle" oninput="javascript: this.value = this.value.replace(/[^0-9]/g, '');" outlined></v-text-field>원
        </v-card-title>
        <v-card-title>
          <v-select color="orange" @click="categoryName" v-model="category" :items="categoryItem" :rules="[v => !!v || 'category를 선택해주세요']" required :menu-props="{ top: true, offsetY: true }" label="Category" outlined></v-select>
        </v-card-title>
        <v-card-subtitle>
          <h4 color="red"> 필수! : Image는 3장 전부 올려주세요 !!</h4>
        </v-card-subtitle>
        <input type="file" ref="imageInput" name="images[]" id="photo" @change="imagesAdd" hidden multiple>

        <v-btn color="blue-grey" class="ma-2 white--text" @click="onClickImageUpload">
          Imagae Upload

          <v-icon right dark>
            mdi-cloud-upload
          </v-icon>
        </v-btn>
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
              <tr v-for="(img, i) in image" v-bind:key="i">
                <th>
                  <v-img :src="img" width="130" height="130"></v-img>
                </th>
                <th>
                  <v-btn dark color="pink" v-show="image" @click="removeImage(i)">
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
        <v-card-subtitle>
          <v-checkbox v-model="checkbox" :rules="[v => !!v || '상품 거래에 동의 해주세요']" label="Do you agree?" required></v-checkbox>
        </v-card-subtitle>
      </v-form>
      <v-btn color="blue-grey" block class=" white--text" :disabled="!valid" @click="validate">
        Submit
        <v-icon right color="white">
          mdi-checkbox-marked-circle
        </v-icon>
      </v-btn>

    </v-card>
    <br>
  </div>
</template>
<script>
import http from "@/utils/http";
import { mapState, mapActions } from "vuex";
import { userTokenValid } from "@/api/userValid"
export default {
  data() {
    return {
      
      valid: true,
      titleRules: [
        v => !!v || 'title is required',
        v => (v && v.length <= 15) || 'Name must be less than 15 characters',
      ],
      contentRules:
        [
          v => !!v || 'content is required',
          v => (v && v.length <= 50) || '50자 이하로 작성 부탁해용',
        ],
      checkbox: false,

      categoryItem: [],
      images: {},
      image: [],
      photoFile: [],
      title: '',
      price: '',
      category: '',
      content: '',
      tokenRefesh:"",
    }

  },
  computed: {
    ...mapState({
      categories: (state) => state.users.categories,
    }),

  },
    mounted() {
     this.token = localStorage.getItem("user")
     if (userTokenValid(this.token) == false) {
      alert("로그인 시간이 만료되었습니다. 다시 로그인 해주세요!!")
      this.$router.push({ name: 'UserLogin' });
    }
    if (!this.token) {
      alert("로그인을 해야 상품 등록이 가능합니다!!")
      this.$router.push({ name: 'UserLogin' });
    }  
     this.categoryName()
  },
  methods: {
    validate() {
      this.$refs.form.validate()
      this.allSubmit()
    },
    categoryName() {
      this.getCategories()
      for (var i = 0; i < this.categories.length; i++) {
        this.categoryItem = this.categoryItem.concat(this.categories[i].id + " " + this.categories[i].name)
      }
    },
    onClickImageUpload() {
      this.$refs.imageInput.click();
    },
    imagesAdd(e) {
      var files = e.target.files || e.dataTransfer.files;
      this.images = [];
      this.image = [];
      Array.prototype.push.apply(this.images, files);
      if (this.images.length != 3) {
        return alert("이미지는 3장 올려주세요")
      }
      this.createImage(this.images);
    },

    createImage(file) {

      for (var i = 0; i < file.length; i++) {
        var reader = new FileReader();
        var vm = this;
        reader.onload = (e) => {
          vm.image.push(e.target.result);
        };
        reader.readAsDataURL(file[i]);
      }
    },

    removeImage(key) {
      this.image.splice(key, 1);
      this.images.splice(key, 1);
    },

    allSubmit() {//server 전송 
      var frm = new FormData();
      this.photoFile = this.images;
      frm.append("title", this.title);
      frm.append("content", this.content);
      frm.append("price", this.price);
      const tempCategory = this.category.split(" ")
      frm.append("category", tempCategory[0]);
      for (var i = 0; i < this.images.length; i++) {
        frm.append("files", this.images[i]);
      }
      return http.process("user", "boardCreate", frm,{token:this.token})
        .then((res) => {
          console.log(res)
          this.$router.push({ name: 'Home1' });
        }).catch((err) => {
          console.log(err)
        })
    },
    ...mapActions({
      getCategories: "users/getCategories",

    }),
  }
}
</script>