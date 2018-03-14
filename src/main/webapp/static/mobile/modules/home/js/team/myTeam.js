function scroll(obj) {
    var tmp = (obj.scrollLeft)++;
    //当滚动条到达右边顶端时
    if (obj.scrollLeft==tmp) obj.innerText += obj.innerText;
    //当滚动条滚动了初始内容的宽度时滚动条回到最左端
    if (obj.scrollLeft>=obj.firstChild.offsetWidth) obj.scrollLeft=0;
  }
function imgError(){
	console.log(this)
}
var vm = new Vue({
  el: "#app",
  mixins:[VueMixin.select],
  data: {
    // 控制是否展开下拉框
    navBar: false,
    loading:false,
    isError:false,
    isMask:false,
    message:'',
    // 选中的下拉text
    selectText: '',
    selectId:null,
    //已经点过赞或者差的数组
    clickedArr:[],
    // 差评时显示弹窗
    isBad:false,
    //差评时的员工信息
    badItem:null,
    // 差评内容
    badContent:'',
    // 显示确认弹窗
    isConfirm:false,
    selectItems: [],
    items: [],
  },
  methods: {
    // 点赞or差评
    handleClick: function(flag,item) {
      if(item.isClick) return false;
      if(flag === 0){
        this.isBad = true;
        this.isMask = true;
        this.badItem = item;
      }else{
        this.submitEvent(1,item)
      }
    },
    imgUrl:function(item){
    	var self = this,img=null;
    	if(item.headPic){
    		return item.headPic;
    	}else{
    		img = this.imgError(item)
    		return img
    	}
    	
    },
    imgError:function(item){
    	var workType = null;
		if(item.workType === '项目经理'){
			workType = 'manager.png'
		}else if(item.workType === '销售人员'){
			workType = 'server.png'
		}else if(item.workType === '设计人员'){
			workType = 'design.png'
		}else if(item.workType === '质检人员'){
			workType = 'pqc.png'
		}else{
			workType = 'worker.png'
		}
		return baseStatic + '/mobile/modules/home/images/' + workType;
    },
    //onerror 动态设置
    imgErrorType:function(item){
    	var workType = null;
    	if(item.workType === '项目经理'){
			workType = 'manager.png'
		}else if(item.workType === '销售人员'){
			workType = 'server.png'
		}else if(item.workType === '设计人员'){
			workType = 'design.png'
		}else if(item.workType === '质检人员'){
			workType = 'pqc.png'
		}else{
			workType = 'worker.png'
		}
    	return workType
    },
    // 空差评提交
    handleSubmit:function(){
      if(this.badContent === ''){
        this.isConfirm = true;
      }else{
        this.submitEvent(0,this.badItem);
      }
    },
    // 差评提交
    submitEvent:function(flag,item){
      var self = this,data = {
    	orderId:self.selectId,
        evaluateType:flag,   // 好评1差评0
        evaluateContent:self.badContent,
        employeeId:item.id ? item.id : null,
        employeeName:item.memberName,
        employeePhone:item.memberPhone,
        storeId:item.storeId,
        workType:item.workType
      };
      this.loading = true;
      self.$http.post(baseUrl + '/app/home/score/commitEvaluate',data).then(function(res) {
        if(res.data.code == 200){
          if(flag === 0){
            self.badContent = '';
            self.badItem = null;
          }
          item.isClick = true;
          item.clickType = flag;
          self.isConfirm = self.isBad = self.navBar = self.isMask = false;
        }else{
        	self.isError = true;
        	self.message = res.data.message;
        }
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
    	this.isConfirm = this.isBad = this.navBar = this.isError = this.isMask = false;
    	 
    },
    getWorkData:function(){
      var self = this;
      this.loading = true;
      this.$http.get(baseUrl + '/app/home/score/selectAllTeamMember?orderId=' + self.selectId).then(function(res){
        if(res.data.code == 200){
          self.items = res.data.data.map(function(val){
            //设置状态为灰色
            if(val.evaluateTime){
              //判断如果当天点过则不能再点
              val.isClick = moment().format('YYYY-MM-DD') === moment(val.evaluateTime).format('YYYY-MM-DD') ? true : false;
              val.clickType = val.isClick ? Number(val.evaluateType) : 3
            }else{
              val.isClick = false;
              val.clickType = 3;
            }
            val.currentScore = val.currentScore == 0 ? 5 : val.currentScore;
            return val;
          });
          this.loading = false;
        }
      })
    }
  },
  created:function(){
    this.getAddress();
  },
  ready:function(){
    //跑马灯
    if(document.getElementById('marquee').offsetWidth < document.getElementById('marquee').scrollWidth){
      this.timer = setInterval("scroll(document.getElementById('marquee'))",15);
    }
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
      }
    },
    selectId:function(val){
      this.getWorkData();
    }
  }
})
