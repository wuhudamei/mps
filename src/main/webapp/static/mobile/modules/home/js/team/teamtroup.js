function scroll(obj) {
    var tmp = (obj.scrollLeft)++;
    //当滚动条到达右边顶端时
    if (obj.scrollLeft==tmp) obj.innerText += obj.innerText;
    //当滚动条滚动了初始内容的宽度时滚动条回到最左端
    if (obj.scrollLeft>=obj.firstChild.offsetWidth) obj.scrollLeft=0;
  }
var vm = new Vue({
  el:"#app",
  mixins:[VueMixin.select],
  data:{
    stars:5,
    isError:false,
    message:'',
    loading:false,
    navBar:false,
    //跑马灯定时器
    timer:null,
    selectText: '',
    selectId:null,
    items:[],
    //历史评价
    historyItems:[],
    selectItems: [],
  },

  methods:{
    getHistory:function(){
      var self = this;
      this.loading = true;
      self.$http.get(baseUrl+'/app/home/score/getOrderHistoryScore?orderId=' + self.selectId).then(function(res) {
        if (res.data.code == 200) {
          self.historyItems = res.data.data;
          self.loading = false;
        }
      })
    },
    getNoScore:function(){
      var self = this;
      this.loading = true;
      self.$http.get(baseUrl+'/app/home/score/getOrderNoScoreType?orderId=' + self.selectId).then(function(res) {
        if (res.data.code == 200) {
          self.items = res.data.data.map(function(val){val.score = 5;val.content = '';return val;});
          self.loading = false;
        }
      })
    },
    starClick:function(index,item){
      item.score = index + 1;
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
    // 确认提交
    handleSubmit:function(item,index){
      var data = {
        orderId:this.selectId,
        scoreType:item.scoreCode,
        scoreValue:item.score,
        scoreContent:item.content
      }
      var self = this;
      self.loading = true;
      this.$http.post(baseUrl+'/app/home/score/commitOrderScore',data).then(function(res){
        if (res.data.code == 200) {
          self.items.splice(index,1);
          self.historyItems.unshift({
        	  scoreTypeCn:item.scoreName,
        	  scoreValue:item.score,
        	  scoreContent:item.content
          })
        }else{
        	self.isError = true;
        	self.message =res.data.message;
        }
        self.loading = false;
      })
    }
  },
  ready:function(){
	  this.getAddress();
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
    },
    selectId:function(val){
      this.getNoScore();
      this.getHistory();
    }
  }
})