(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-b32ef614"],{"108f":function(t,e,i){"use strict";i.d(e,"a",(function(){return r}));var n=i("d232");function r(t){return new Promise((function(e,i){n["a"].process("user","userValid",{token:t}).then((function(t){e(t)})).catch((function(t){i(t)}))}))}},1681:function(t,e,i){},"1f4f":function(t,e,i){"use strict";i("8b37");var n=i("80d2"),r=i("7560"),a=i("58df");function o(t,e){var i=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),i.push.apply(i,n)}return i}function s(t){for(var e=1;e<arguments.length;e++){var i=null!=arguments[e]?arguments[e]:{};e%2?o(Object(i),!0).forEach((function(e){c(t,e,i[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(i)):o(Object(i)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(i,e))}))}return t}function c(t,e,i){return e in t?Object.defineProperty(t,e,{value:i,enumerable:!0,configurable:!0,writable:!0}):t[e]=i,t}e["a"]=Object(a["a"])(r["a"]).extend({name:"v-simple-table",props:{dense:Boolean,fixedHeader:Boolean,height:[Number,String]},computed:{classes:function(){return s({"v-data-table--dense":this.dense,"v-data-table--fixed-height":!!this.height&&!this.fixedHeader,"v-data-table--fixed-header":this.fixedHeader,"v-data-table--has-top":!!this.$slots.top,"v-data-table--has-bottom":!!this.$slots.bottom},this.themeClasses)}},methods:{genWrapper:function(){return this.$slots.wrapper||this.$createElement("div",{staticClass:"v-data-table__wrapper",style:{height:Object(n["f"])(this.height)}},[this.$createElement("table",this.$slots.default)])}},render:function(t){return t("div",{staticClass:"v-data-table",class:this.classes},[this.$slots.top,this.genWrapper(),this.$slots.bottom])}})},"6ca7":function(t,e,i){},"8b37":function(t,e,i){},"9f2e":function(t,e,i){"use strict";i.r(e);var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("br"),i("v-card",{staticClass:"mx-auto my-12",attrs:{"max-width":"500","max-height":"auto"}},[i("v-form",{ref:"form",attrs:{"lazy-validation":""},model:{value:t.valid,callback:function(e){t.valid=e},expression:"valid"}},[i("v-card-title",[i("v-text-field",{attrs:{color:"orange",rules:t.titleRules,required:"",counter:"15",label:"Title",clearable:"","clear-icon":"mdi-close-circle",outlined:""},model:{value:t.title,callback:function(e){t.title=e},expression:"title"}})],1),i("v-card-title",[i("v-textarea",{attrs:{color:"orange",rules:t.contentRules,counter:"50",label:"Content",clearable:"","clear-icon":"mdi-close-circle",outlined:""},model:{value:t.content,callback:function(e){t.content=e},expression:"content"}})],1),i("v-card-title",[i("v-text-field",{attrs:{color:"orange",label:"Price",clearable:"","clear-icon":"mdi-close-circle",oninput:"javascript: this.value = this.value.replace(/[^0-9]/g, '');",outlined:""},model:{value:t.price,callback:function(e){t.price=e},expression:"price"}}),t._v("원 ")],1),i("v-card-title",[i("v-select",{attrs:{color:"orange",items:t.categoryItem,rules:[function(t){return!!t||"category를 선택해주세요"}],required:"","menu-props":{top:!0,offsetY:!0},label:"Category",outlined:""},on:{click:t.categoryName},model:{value:t.category,callback:function(e){t.category=e},expression:"category"}})],1),i("v-card-subtitle",[i("h4",{attrs:{color:"red"}},[t._v(" 필수! : Image는 3장 전부 올려주세요 !!")])]),i("input",{ref:"imageInput",attrs:{type:"file",name:"images[]",id:"photo",hidden:"",multiple:""},on:{change:t.imagesAdd}}),i("v-btn",{staticClass:"ma-2 white--text",attrs:{color:"blue-grey"},on:{click:t.onClickImageUpload}},[t._v(" Imagae Upload "),i("v-icon",{attrs:{right:"",dark:""}},[t._v(" mdi-cloud-upload ")])],1),i("v-simple-table",{scopedSlots:t._u([{key:"default",fn:function(){return[i("thead",[i("tr",[i("th",{staticClass:"text-left"},[t._v(" Image ")]),i("th",{staticClass:"text-left"},[t._v(" Delete ")])])]),i("tbody",t._l(t.image,(function(e,n){return i("tr",{key:n},[i("th",[i("v-img",{attrs:{src:e,width:"130",height:"130"}})],1),i("th",[i("v-btn",{directives:[{name:"show",rawName:"v-show",value:t.image,expression:"image"}],attrs:{dark:"",color:"pink"},on:{click:function(e){return t.removeImage(n)}}},[i("v-icon",{attrs:{dark:""}},[t._v(" mdi-camera ")]),t._v(" Image "+t._s(n+1)+" ")],1)],1)])})),0)]},proxy:!0}])}),i("v-card-subtitle",[i("v-checkbox",{attrs:{rules:[function(t){return!!t||"상품 거래에 동의 해주세요"}],label:"Do you agree?",required:""},model:{value:t.checkbox,callback:function(e){t.checkbox=e},expression:"checkbox"}})],1)],1),i("v-btn",{staticClass:" white--text",attrs:{color:"blue-grey",block:"",disabled:!t.valid},on:{click:t.validate}},[t._v(" Submit "),i("v-icon",{attrs:{right:"",color:"white"}},[t._v(" mdi-checkbox-marked-circle ")])],1)],1),i("br")],1)},r=[],a=i("d232"),o=i("2f62"),s=i("108f");function c(t,e){var i=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),i.push.apply(i,n)}return i}function l(t){for(var e=1;e<arguments.length;e++){var i=null!=arguments[e]?arguments[e]:{};e%2?c(Object(i),!0).forEach((function(e){u(t,e,i[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(i)):c(Object(i)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(i,e))}))}return t}function u(t,e,i){return e in t?Object.defineProperty(t,e,{value:i,enumerable:!0,configurable:!0,writable:!0}):t[e]=i,t}var h={data:function(){return{valid:!0,titleRules:[function(t){return!!t||"title is required"},function(t){return t&&t.length<=15||"Name must be less than 15 characters"}],contentRules:[function(t){return!!t||"content is required"},function(t){return t&&t.length<=50||"50자 이하로 작성 부탁해용"}],checkbox:!1,categoryItem:[],images:{},image:[],photoFile:[],title:"",price:"",category:"",content:"",tokenRefesh:""}},computed:l({},Object(o["c"])({categories:function(t){return t.users.categories}})),mounted:function(){this.token=localStorage.getItem("user"),0==Object(s["a"])(this.token)&&(alert("로그인 시간이 만료되었습니다. 다시 로그인 해주세요!!"),this.$router.push({name:"UserLogin"})),this.token||(alert("로그인을 해야 상품 등록이 가능합니다!!"),this.$router.push({name:"UserLogin"})),this.categoryName()},methods:l({validate:function(){this.$refs.form.validate(),this.allSubmit()},categoryName:function(){this.getCategories();for(var t=0;t<this.categories.length;t++)this.categoryItem=this.categoryItem.concat(this.categories[t].id+" "+this.categories[t].name)},onClickImageUpload:function(){this.$refs.imageInput.click()},imagesAdd:function(t){var e=t.target.files||t.dataTransfer.files;if(this.images=[],this.image=[],Array.prototype.push.apply(this.images,e),3!=this.images.length)return alert("이미지는 3장 올려주세요");this.createImage(this.images)},createImage:function(t){for(var e=0;e<t.length;e++){var i=new FileReader,n=this;i.onload=function(t){n.image.push(t.target.result)},i.readAsDataURL(t[e])}},removeImage:function(t){this.image.splice(t,1),this.images.splice(t,1)},allSubmit:function(){var t=this,e=new FormData;this.photoFile=this.images,e.append("title",this.title),e.append("content",this.content),e.append("price",this.price);var i=this.category.split(" ");e.append("category",i[0]);for(var n=0;n<this.images.length;n++)e.append("files",this.images[n]);return a["a"].process("user","boardCreate",e,{token:this.token}).then((function(e){console.log(e),t.$router.push({name:"Home1"})})).catch((function(t){console.log(t)}))}},Object(o["b"])({getCategories:"users/getCategories"}))},p=h,d=i("2877"),f=i("6544"),m=i.n(f),g=i("8336"),b=i("b0af"),v=i("99d9"),y=(i("6ca7"),i("ec29"),i("9d26")),O=i("c37a"),w=i("5607"),k=i("2b0e"),j=k["a"].extend({name:"rippleable",directives:{ripple:w["a"]},props:{ripple:{type:[Boolean,Object],default:!0}},methods:{genRipple:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return this.ripple?(t.staticClass="v-input--selection-controls__ripple",t.directives=t.directives||[],t.directives.push({name:"ripple",value:{center:!0}}),this.$createElement("div",t)):null}}}),x=i("8547"),I=i("58df");function C(t){t.preventDefault()}var P=Object(I["a"])(O["a"],j,x["a"]).extend({name:"selectable",model:{prop:"inputValue",event:"change"},props:{id:String,inputValue:null,falseValue:null,trueValue:null,multiple:{type:Boolean,default:null},label:String},data:function(){return{hasColor:this.inputValue,lazyValue:this.inputValue}},computed:{computedColor:function(){if(this.isActive)return this.color?this.color:this.isDark&&!this.appIsDark?"white":"primary"},isMultiple:function(){return!0===this.multiple||null===this.multiple&&Array.isArray(this.internalValue)},isActive:function(){var t=this,e=this.value,i=this.internalValue;return this.isMultiple?!!Array.isArray(i)&&i.some((function(i){return t.valueComparator(i,e)})):void 0===this.trueValue||void 0===this.falseValue?e?this.valueComparator(e,i):Boolean(i):this.valueComparator(i,this.trueValue)},isDirty:function(){return this.isActive},rippleState:function(){return this.isDisabled||this.validationState?this.validationState:void 0}},watch:{inputValue:function(t){this.lazyValue=t,this.hasColor=t}},methods:{genLabel:function(){var t=O["a"].options.methods.genLabel.call(this);return t?(t.data.on={click:C},t):t},genInput:function(t,e){return this.$createElement("input",{attrs:Object.assign({"aria-checked":this.isActive.toString(),disabled:this.isDisabled,id:this.computedId,role:t,type:t},e),domProps:{value:this.value,checked:this.isActive},on:{blur:this.onBlur,change:this.onChange,focus:this.onFocus,keydown:this.onKeydown,click:C},ref:"input"})},onBlur:function(){this.isFocused=!1},onClick:function(t){this.onChange(),this.$emit("click",t)},onChange:function(){var t=this;if(this.isInteractive){var e=this.value,i=this.internalValue;if(this.isMultiple){Array.isArray(i)||(i=[]);var n=i.length;i=i.filter((function(i){return!t.valueComparator(i,e)})),i.length===n&&i.push(e)}else i=void 0!==this.trueValue&&void 0!==this.falseValue?this.valueComparator(i,this.trueValue)?this.falseValue:this.trueValue:e?this.valueComparator(i,e)?null:e:!i;this.validate(!0,i),this.internalValue=i,this.hasColor=i}},onFocus:function(){this.isFocused=!0},onKeydown:function(t){}}});function V(t,e){var i=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),i.push.apply(i,n)}return i}function S(t){for(var e=1;e<arguments.length;e++){var i=null!=arguments[e]?arguments[e]:{};e%2?V(Object(i),!0).forEach((function(e){D(t,e,i[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(i)):V(Object(i)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(i,e))}))}return t}function D(t,e,i){return e in t?Object.defineProperty(t,e,{value:i,enumerable:!0,configurable:!0,writable:!0}):t[e]=i,t}var $=P.extend({name:"v-checkbox",props:{indeterminate:Boolean,indeterminateIcon:{type:String,default:"$checkboxIndeterminate"},offIcon:{type:String,default:"$checkboxOff"},onIcon:{type:String,default:"$checkboxOn"}},data:function(){return{inputIndeterminate:this.indeterminate}},computed:{classes:function(){return S(S({},O["a"].options.computed.classes.call(this)),{},{"v-input--selection-controls":!0,"v-input--checkbox":!0,"v-input--indeterminate":this.inputIndeterminate})},computedIcon:function(){return this.inputIndeterminate?this.indeterminateIcon:this.isActive?this.onIcon:this.offIcon},validationState:function(){if(!this.isDisabled||this.inputIndeterminate)return this.hasError&&this.shouldValidate?"error":this.hasSuccess?"success":null!==this.hasColor?this.computedColor:void 0}},watch:{indeterminate:function(t){var e=this;this.$nextTick((function(){return e.inputIndeterminate=t}))},inputIndeterminate:function(t){this.$emit("update:indeterminate",t)},isActive:function(){this.indeterminate&&(this.inputIndeterminate=!1)}},methods:{genCheckbox:function(){return this.$createElement("div",{staticClass:"v-input--selection-controls__input"},[this.$createElement(y["a"],this.setTextColor(this.validationState,{props:{dense:this.dense,dark:this.dark,light:this.light}}),this.computedIcon),this.genInput("checkbox",S(S({},this.attrs$),{},{"aria-checked":this.inputIndeterminate?"mixed":this.isActive.toString()})),this.genRipple(this.setTextColor(this.rippleState))])},genDefaultSlot:function(){return[this.genCheckbox(),this.genLabel()]}}}),_=i("4bd4"),A=i("132d"),E=i("adda"),H=i("b974"),F=i("1f4f"),R=i("8654"),N=i("a844"),T=Object(d["a"])(p,n,r,!1,null,null,null);e["default"]=T.exports;m()(T,{VBtn:g["a"],VCard:b["a"],VCardSubtitle:v["b"],VCardTitle:v["d"],VCheckbox:$,VForm:_["a"],VIcon:A["a"],VImg:E["a"],VSelect:H["a"],VSimpleTable:F["a"],VTextField:R["a"],VTextarea:N["a"]})},a844:function(t,e,i){"use strict";i("1681");var n=i("8654"),r=i("58df");function a(t,e){var i=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),i.push.apply(i,n)}return i}function o(t){for(var e=1;e<arguments.length;e++){var i=null!=arguments[e]?arguments[e]:{};e%2?a(Object(i),!0).forEach((function(e){s(t,e,i[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(i)):a(Object(i)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(i,e))}))}return t}function s(t,e,i){return e in t?Object.defineProperty(t,e,{value:i,enumerable:!0,configurable:!0,writable:!0}):t[e]=i,t}var c=Object(r["a"])(n["a"]);e["a"]=c.extend({name:"v-textarea",props:{autoGrow:Boolean,noResize:Boolean,rowHeight:{type:[Number,String],default:24,validator:function(t){return!isNaN(parseFloat(t))}},rows:{type:[Number,String],default:5,validator:function(t){return!isNaN(parseInt(t,10))}}},computed:{classes:function(){return o({"v-textarea":!0,"v-textarea--auto-grow":this.autoGrow,"v-textarea--no-resize":this.noResizeHandle},n["a"].options.computed.classes.call(this))},noResizeHandle:function(){return this.noResize||this.autoGrow}},watch:{lazyValue:function(){this.autoGrow&&this.$nextTick(this.calculateInputHeight)},rowHeight:function(){this.autoGrow&&this.$nextTick(this.calculateInputHeight)}},mounted:function(){var t=this;setTimeout((function(){t.autoGrow&&t.calculateInputHeight()}),0)},methods:{calculateInputHeight:function(){var t=this.$refs.input;if(t){t.style.height="0";var e=t.scrollHeight,i=parseInt(this.rows,10)*parseFloat(this.rowHeight);t.style.height=Math.max(i,e)+"px"}},genInput:function(){var t=n["a"].options.methods.genInput.call(this);return t.tag="textarea",delete t.data.attrs.type,t.data.attrs.rows=this.rows,t},onInput:function(t){n["a"].options.methods.onInput.call(this,t),this.autoGrow&&this.calculateInputHeight()},onKeyDown:function(t){this.isFocused&&13===t.keyCode&&t.stopPropagation(),this.$emit("keydown",t)}}})},ec29:function(t,e,i){}}]);
//# sourceMappingURL=chunk-b32ef614.b9381301.js.map