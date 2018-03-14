function scroll(obj) {
    var tmp = (obj.scrollLeft)++;
    //当滚动条到达右边顶端时
    if (obj.scrollLeft==tmp) obj.innerText += obj.innerText;
    //当滚动条滚动了初始内容的宽度时滚动条回到最左端
    if (obj.scrollLeft>=obj.firstChild.offsetWidth) obj.scrollLeft=0;
  }
var vm = new Vue({
  el: "#app",
  data: {
    // 内容
    content: '',
    loading:false,
    isError:false,
    message:'',
      isOk:false,
    // 地址下拉框
    selectItems:[],
    // 问题下拉框
    questionSelect:[],
     // 控制是否展开下拉框
    navBar: false,
    // 选中的下拉text
    selectText: '',
    selectId:null,
    selectCode:null,
    //跑马灯定时器
    timer:null
  },
  mixins:[VueMixin.select],
  methods: {
    // 提交事件
    submitEvent:function(){
      var self = this;
      if(this.content === '') return false;
      var data = {
        orderId:self.selectId,
        complainType:self.selectCode,
        complainContent:self.content
      };
      this.loading = true;
      self.$http.post(baseUrl + '/app/home/score/commitComplain',data).then(function(res) {
        if(res.data.code == 200){
            self.content = '';
            self.isOk=true;
        }else{
        	self.isError = true;
        	self.message =res.data.message;
        }
        self.loading = false;
      },function(){
    	 self.loading = false;
      })
    },
    //下拉选项单击事件
    handleSelect: function(item) {
      if(item.id === this.selectId){
        this.navBar = false;
        return false;
      }  
      this.selectText = item.detail_address;
      this.selectId = item.id;
      this.navBar = false;
    },
    hiddenEvent:function(){
    	this.isError =false;
    	this.navBar = false;
    },
    //获取问题列表
    getQuestion: function() {
      var self = this;
      self.loading = true;
      self.$http.get(baseUrl + '/app/home/score/getComplainType').then(function(res) {
        if (res.data.code == 200) {
          self.questionSelect = res.data.data;
          if(self.questionSelect && self.questionSelect.length !== 0){
            self.selectCode = self.questionSelect[0].dictCode;
          }
        }else{
        	self.isError = true;
        	self.message =res.data.message;
        }
        self.loading = false;
      })
    },
      isKnow:function () {
        var self=this;
        self.isOk=false;
      }
  },
  created:function(){
    this.getAddress();
    this.getQuestion();
  },
  ready:function(){
    //跑马灯
    if(document.getElementById('marquee').offsetWidth < document.getElementById('marquee').scrollWidth){
      this.timer = setInterval("scroll(document.getElementById('marquee'))",15);
    }
    
    // setInterval("scroll(document.getElementById('marquee'))",15);
  },
  watch:{
    navBar:function(val){
      var self = this;
      if(val){
        self.timer = window.clearInterval(self.timer)
      }else{
        if(document.getElementById('marquee').offsetWidth < document.getElementById('marquee').scrollWidth){
          this.timer = setInterval("scroll(document.getElementById('marquee'))",15);
        } 
       // self.timer = setInterval("scroll(document.getElementById('marquee'))",15);
      }
    }
  }
})
